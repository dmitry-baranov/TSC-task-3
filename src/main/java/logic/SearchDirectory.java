package logic;

import java.io.File;
import java.util.List;

public class SearchDirectory implements Runnable {

    private File directory;

    public SearchDirectory(File directory) {
        this.directory = directory;
    }

    public void run() {
        File[] directoryEntries = directory.listFiles();
        for (File entry : directoryEntries) {
            if (entry.isDirectory()) {
                ExecutorLogic.getExecutor().execute(new SearchDirectory(entry));
            }
            List<String> list = ExecutorLogic.getExtension();
            boolean i = false;
            for (String e : list) {
                if (entry.getName().endsWith(e)) {
                    i = true;
                }
            }
            if (i) {
                ExecutorLogic.getExecutor().execute(new SearchLine(entry));
            }
        }

    }

}
