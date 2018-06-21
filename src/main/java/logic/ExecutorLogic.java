package logic;

import data.Department;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorLogic {

    private static volatile ExecutorLogic instance;
    private static ExecutorService executor;
    private static List<String> extension;

    private ExecutorLogic(int n, String string) {
        if (executor == null && n != 0 && string != null) {
            executor = Executors.newFixedThreadPool(n);
            String[] s = string.split(" ");
            extension = Arrays.asList(s);
        }
    }

    public static List<String> getExtension() {
        return extension;
    }

    public static ExecutorService getExecutor() {
        return executor;
    }

    public static ExecutorLogic getInstance(int n, String s) {
        ExecutorLogic localInstance = instance;
        if (localInstance == null) {
            synchronized (ExecutorLogic.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ExecutorLogic(n, s);
                }
            }
        }
        return localInstance;
    }


}
