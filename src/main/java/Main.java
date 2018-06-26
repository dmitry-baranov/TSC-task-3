import errors.MyException;
import logic.ExecutorData;
import logic.SearchDirectory;


import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            ExecutorData executorData = new ExecutorData(args[0], args[1], args[3], args[4]);
            executorData.getExecutor().execute(new SearchDirectory(executorData, new File(args[2])));
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(5000);
            executorData.getExecutor().shutdown();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MyException e) {
            System.out.println(e.getResponse().getErrorCode() + e.getResponse().getErrorMessage());
        }

//        try {
//            executorData.getExecutor().awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}