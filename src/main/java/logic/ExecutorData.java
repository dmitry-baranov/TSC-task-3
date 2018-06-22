package logic;

import errors.MyException;
import errors.Response;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorData {

    private ExecutorService executor;
    private String line;
    private List<String> extension;
    private File result;

    public ExecutorData(String threads, String line, String expansion, String result) {
        try {
            this.executor = Executors.newFixedThreadPool(Integer.parseInt(threads));
            this.line = line;
            String[] s = expansion.split(";");
            this.extension = Arrays.asList(s);
            this.result = new File(result);
        } catch (NumberFormatException e) {
            throw new MyException(Response.NUMBER_FORMAT_EXCEPTION);
        }
    }

    public String getLine() {
        return line;
    }

    public List<String> getExtension() {
        return extension;
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public File getResult() {
        return result;
    }
}
