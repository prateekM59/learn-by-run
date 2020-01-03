package mahajan.prateek.java.concurrency;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static mahajan.prateek.java.concurrency.Utils.sleepForMillis;
import static mahajan.prateek.java.concurrency.Utils.threadName;
import static mahajan.prateek.java.concurrency.Utils.time;

/**
 * Created by: pramahajan on 1/3/20 12:56 PM GMT+05:30
 */
public class FutureTest {
    @Test
    public void show_that_Future_is_nonBlocking() {
        System.out.println(time() + "Starting test" + threadName());

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Runnable ioMainTask = new Runnable() {
            @Override
            public void run() {
                System.out.println(time() + "Mocking I/O" + threadName());
                sleepForMillis(5000);
                System.out.println(time() + "Completed I/O" + threadName());
            }
        };

        Future<?> future = executorService.submit(ioMainTask);

        System.out.println(time() + threadName() + ", isDone: "  + future.isDone());

        System.out.println(time() + threadName() + "is not blocked on future");
        System.out.println(time() + threadName() + "waiting for all other threads to complete");
        sleepForMillis(10000);
        System.out.println(time() + "Finishing test" + threadName());
    }
}
