package mahajan.prateek.java.concurrency;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static mahajan.prateek.java.concurrency.Utils.sleepForMillis;

/**
 * Created by: pramahajan on 10/3/20 6:13 AM GMT+05:30
 */
public class Basic {

    @Test
    public void process_by_3_threads_concurrently() {
        String str = "123456789";
        Runnable r1 = new StringPrinter(0, 3, str);
        Runnable r2 = new StringPrinter(1, 3, str);
        Runnable r3 = new StringPrinter(2, 3, str);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(r1);
        executorService.submit(r2);
        executorService.submit(r3);

//        new Thread(r1).start();
//        new Thread(r2).start();
//        new Thread(r3).start();


        sleepForMillis(3000);
    }

    private static class StringPrinter implements Runnable {
        private int counter, incrementer;
        private String s;

        private StringPrinter(int counter, int incrementer, String s) {
            this.counter = counter;
            this.incrementer = incrementer;
            this.s = s;
        }

        @Override
        public void run() {
            while (counter<s.length()) {
                System.out.println(s.charAt(counter));
                counter+=incrementer;
                sleepForMillis(100);
            }
        }
    }

}
