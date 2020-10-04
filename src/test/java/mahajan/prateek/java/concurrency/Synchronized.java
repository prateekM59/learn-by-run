package mahajan.prateek.java.concurrency;

import org.junit.Test;

import static mahajan.prateek.java.concurrency.Utils.sleepForMillis;
import static mahajan.prateek.java.concurrency.Utils.startThread;
import static mahajan.prateek.java.concurrency.Utils.threadName;
import static mahajan.prateek.java.concurrency.Utils.time;
import static mahajan.prateek.java.concurrency.Utils.waitForThread;

/**
 * Created by: pramahajan on 10/3/20 11:58 PM GMT+05:30
 */

/**
 * Shown only for instance methods Synchronization (using this object)
 * Static methods can also be synchronized using Class object
 * Also synchronized blocks can be used to limit the Critical section since Synchronization comes at a cost
 * Refer http://tutorials.jenkov.com/java-concurrency/synchronized.html
 */
public class Synchronized {

    @Test
    // Notice that if adder thread is running add() method (used sleep to keep the thread there for some time), subtractor
    // thread can NOT run EVEN a different subtract() method concurrently
    // Why? Because both add() and subtract() are synced on same Counter object

    // So MAX ONE thread in ONE of ALL methods synced on SAME monitor object

    // This can be verified using time:
    // if add() runs, it will take 5s for next thread (either adder thread or subtractor thread)
    // to run its methods (i.e. add() for adder or subtract() for subtractor)
    // Similarly, if subtract() runs, it will take 3s for next thread (either adder thread or subtractor thread)
    // to run its methods (i.e. add() for adder or subtract() for subtractor)
    public void diff_methods_synced_on_same_monitor_can_NOT_run_concurrently_by_two_threads() {
        Counter counter = new Counter(5, 3); // same object used by both threads

        Thread adderThread = new Thread(new CountingRunner(counter, true), "Adder Thread");
        Thread subtractorThread = new Thread(new CountingRunner(counter, false), "Subtractor Thread");

        startThread(adderThread);
        startThread(subtractorThread);

        waitForThread(adderThread);
        waitForThread(subtractorThread);

    }

    @Test
    // Initialized two adder threads that will call the synced add() methods
    // But since the Counter objects are different, its instance methods are also synced on different objects (this)
    // So two threads would interleave i.e. run concurrently (can be verified though times)
    // Keeping sleepTimes 2 and 3 would validate this easily since at time t=6 (LCM of 2,3), both threads would run at almost same time
    public void single_method_synced_on_different_monitors_CAN_be_run_concurrently_by_two_threads() throws InterruptedException {
        // Using different Counter objects for different threads
        Counter adderCounter1 = new Counter(2, Integer.MAX_VALUE);
        Counter adderCounter2 = new Counter(3, Integer.MAX_VALUE);

        Thread adderThread1 = new Thread(new CountingRunner(adderCounter1, true), "Adder Thread1");
        Thread adderThread2 = new Thread(new CountingRunner(adderCounter2, true), "Adder Thread2");

        startThread(adderThread1);
        startThread(adderThread2);

        waitForThread(adderThread1);
        waitForThread(adderThread2);

    }

    @Test
    // This should be easy => if 2 threads can parallelize add() using 2 monitors,
    // they should be able to parallelize add() and subtract() as well
    // Keeping sleepTimes 2 and 3 would validate this easily since at time t=6 (LCM of 2,3), both threads would run at almost same time
    public void diff_methods_synced_on_different_monitors_CAN_be_run_concurrently_by_two_threads() throws InterruptedException {
        // Using different Counter objects for different threads
        Counter adderCounter = new Counter(2, Integer.MAX_VALUE);
        Counter subtractorCounter = new Counter(Integer.MAX_VALUE,3);

        Thread adderThread = new Thread(new CountingRunner(adderCounter, true), "Adder Thread");
        Thread subtractorThread = new Thread(new CountingRunner(subtractorCounter, false), "Subtractor Thread");

        startThread(adderThread);
        startThread(subtractorThread);

        waitForThread(adderThread);
        waitForThread(subtractorThread);

    }

    private static class CountingRunner implements Runnable {
        private final Counter counter;
        private final boolean adder;

        public CountingRunner(Counter counter, boolean adder) {
            this.counter = counter;
            this.adder = adder;
        }

        @Override
        public void run() {
            for (int i=0;i<5;i++) {
                if (this.adder) {
                    counter.add();
                } else {
                    counter.subtract();
                }
            }
        }
    }

    private static class Counter {
        private int value = 0;
        private final int adderSleepSec, subtractorSleepSec;

        private Counter(int adderSleepSec, int subtractorSleepSec) {
            this.adderSleepSec = adderSleepSec;
            this.subtractorSleepSec = subtractorSleepSec;
        }

        public synchronized void add() { // synced on this
            System.out.println(time() + threadName() + " ADD.");
            this.value++;
            sleepForMillis(adderSleepSec*1000);

        }

        public synchronized void subtract() { // synced on this
            System.out.println(time() + threadName() + " SUBTRACT.");
            this.value--;
            sleepForMillis(subtractorSleepSec*1000);
        }
    }

}
