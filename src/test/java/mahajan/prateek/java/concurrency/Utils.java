package mahajan.prateek.java.concurrency;

import java.time.LocalTime;

/**
 * Created by: pramahajan on 1/3/20 12:56 PM GMT+05:30
 */
public interface Utils {

    String abc = "ad";

    static void sleepForMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static String threadName() {
        return " - In thread: " + Thread.currentThread().getName() + " - ";
    }

    static String time() {
        return " - Time: " + LocalTime.now().toString() + " - ";
    }

    static void startThread(Thread thread) {
        System.out.println(time() + threadName() + "STARTING: " + thread.getName());
        thread.start();
    }

    static void waitForThread(Thread thread) { // TIP for writing concurrency tests: Use Thread.join() instead of Thread.sleep(100s), etc
        System.out.println(time() + threadName() + "WAITING for: " + thread.getName());
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

