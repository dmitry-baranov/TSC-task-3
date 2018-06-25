package logic;

import java.io.File;
import java.util.List;

public class SearchDirectory implements Runnable {

    private ExecutorData data;
    private File directory;

    public SearchDirectory(ExecutorData data, File directory) {
        this.data = data;
        this.directory = directory;
    }

    public void run() {
        File[] directoryEntries = directory.listFiles();
        if (directoryEntries != null) {
            for (File entry : directoryEntries) {
                if (entry.isDirectory()) {
                    data.getExecutor().execute(new SearchDirectory(data, entry));
                } else {
                    List<String> list = data.getExtension();
                    boolean conformity = false;
                    for (String e : list) {
                        if (entry.getName().endsWith(e)) {
                            conformity = true;
                        }
                    }
                    if (conformity) {
                        data.getExecutor().execute(new SearchLine(data, entry));
                    }
                }

            }
        }

    }

}
