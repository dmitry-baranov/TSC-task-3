package logic;

import java.io.File;

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
                    String nameFile = entry.getName();
                    int index = nameFile.indexOf(".");
                    if (data.getExtension().contains(nameFile.substring(index))) {
                        data.getExecutor().execute(new SearchLine(data, entry));
                    }
                }

            }
        }

    }

}
