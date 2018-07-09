package logic;

import errors.MyException;
import errors.Response;

import java.io.*;

public class SearchLine implements Runnable {

    private ExecutorData data;
    private File file;

    public SearchLine(ExecutorData executor, File directory) {
        this.data = executor;
        this.file = directory;
    }

    public void run() {
        try {
            InputStream inputStream = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                if (sCurrentLine.toLowerCase().contains(data.getLine().toLowerCase())) {
                    FileWriter writer;
                    try {
                        writer = new FileWriter(data.getResult(), true);
                    } catch (FileNotFoundException e) {
                        throw new MyException(Response.RESULT_FILE_NOT_FOUND);
                    } catch (UnsupportedEncodingException e) {
                        throw new MyException(Response.UNSUPPORTED_ENCODING_EXCEPTION);
                    }
                    writer.write(Thread.currentThread().getId() + "\t" + file.getName() + "\t" + sCurrentLine  + "\n");
                    writer.flush();
                }
            }
        } catch (NumberFormatException e) {
            throw new MyException(Response.NUMBER_FORMAT_EXCEPTION);
        } catch (UnsupportedEncodingException e) {
            throw new MyException(Response.INVALID_INPUT_DATA);
        } catch (IOException e) {
            throw new MyException(Response.FILE_NOT_FOUND);
        }finally {
            data.getInverseSemaphore().taskCompleted();
        }
    }
}
