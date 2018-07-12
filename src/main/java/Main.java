import errors.MyException;
import errors.Response;
import logic.ExecutorData;
import logic.InverseSemaphore;
import logic.SearchDirectory;


import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            InverseSemaphore inverseSemaphore = new InverseSemaphore();
            ExecutorData executorData = new ExecutorData(args[0], args[1], args[3], args[4], inverseSemaphore);
            inverseSemaphore.beforeSubmit();
            executorData.getExecutor().execute(new SearchDirectory(executorData, new File(args[2])));
            Thread.sleep(100);
            inverseSemaphore.awaitCompletion();
            executorData.getExecutor().shutdown();
        }
        catch (InterruptedException e) {
            throw new MyException(Response.INTERRUPTEDEXCEPTION);
        } catch (MyException e) {
            System.out.println(e.getResponse().getErrorCode() + e.getResponse().getErrorMessage());
        }
    }
}