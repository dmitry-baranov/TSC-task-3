package logic;

import errors.MyException;
import errors.Response;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorData {

    private ExecutorService executor;
    private String line;
    private Set<String> extension = new HashSet<>();
    private File result;
    private InverseSemaphore inverseSemaphore;

    public ExecutorData(String threads, String line, String expansion, String result, InverseSemaphore inverseSemaphore) {
        try {
            this.executor = Executors.newFixedThreadPool(Integer.parseInt(threads));
            this.inverseSemaphore = inverseSemaphore;
            this.line = line;
            String[] s = expansion.split(";");
            if (s.length != 0) {
                for (String value : s) {
                    if (!value.equals(null)) {
                        extension.add(value);
                    }
                }
            }

            this.result = new File(result);
        } catch (NumberFormatException e) {
            throw new MyException(Response.NUMBER_FORMAT_EXCEPTION);
        }
    }

    public String getLine() {
        return line;
    }

    public Set<String> getExtension() {
        return extension;
    }

    public ExecutorService getExecutor() {
        return executor;
    }

    public File getResult() {
        return result;
    }

    public InverseSemaphore getInverseSemaphore() {
        return inverseSemaphore;
    }
}
