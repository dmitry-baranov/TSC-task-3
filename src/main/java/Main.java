import logic.ExecutorLogic;
import logic.SearchDirectory;

import java.io.File;

public class Main {
    public static void main(String[] args) {
//        Runnable worker = new SearchDirectory(new File(args[0]));
        ExecutorLogic.getInstance(Integer.parseInt(args[1]),args[2]).getExecutor().execute(new SearchDirectory(new File(args[0])));
    }
}