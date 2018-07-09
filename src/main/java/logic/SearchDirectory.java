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
        try {
            File[] directoryEntries = directory.listFiles();
            if (directoryEntries != null) {
                for (File entry : directoryEntries) {
                    if (entry.isDirectory()) {
                        data.getInverseSemaphore().beforeSubmit();
                        data.getExecutor().execute(new SearchDirectory(data, entry));
                    } else {
                        String nameFile = entry.getName();
                        int index = nameFile.indexOf(".");
                        System.out.println(nameFile.substring(index));
                        if (data.getExtension().contains(nameFile.substring(index+1))) {
                            data.getInverseSemaphore().beforeSubmit();
                            data.getExecutor().execute(new SearchLine(data, entry));
                        }
                    }

                }
            }
        } finally {
            data.getInverseSemaphore().taskCompleted();
        }



    }

}
