package mahajan.prateek.java.concurrency;

/**
 * Created by: pramahajan on 10/4/20 3:26 AM GMT+05:30
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static mahajan.prateek.java.concurrency.Utils.startThread;
import static mahajan.prateek.java.concurrency.Utils.threadName;
import static mahajan.prateek.java.concurrency.Utils.waitForThread;

/**
 * wait(), notify() and notifyAll()
 * For easy review, https://www.netjstech.com/2015/07/inter-thread-communiction-wait-notify-java-multi-thread.html
 *
 *
 */

public class ThreadSignaling {


    /**
     * Classic producer consumer problem: There is a buffer of fixed size, say 1. Producer and Consumer threads
     * are running concurrently forever => Producer adds while Consumer removes from buffer.
     * Problem to solve:
     * 1. Producer should not add if buffer is full
     * 2. Consumer should not consume if buffer is empty
     *
     * Even w/o wait() and notify(), the P/C problem can be solved using busy waiting - nested while loops in both P/C
     * http://tutorials.jenkov.com/java-concurrency/thread-signaling.html
     *
     * e.g. in Producer,
     *
     * while(true) {
     *      while (buffer.size()>0) {
     *          // do nothing => waste CPU
     *      }
     *   buffer.add(someValue);
     * }
     *
     * But that would keep CPU busy needlessly => so wait() can help to sleep producer thread and save CPU cycles
     */
    @Test
    public void producer_consumer() {
        List<Integer> sharedBuffer = new ArrayList<>();

        // shared buffer (and hence shared monitor) among two threads, see synchronized block in Producer/Consumer
        Producer producer = new Producer(sharedBuffer);
        Consumer consumer = new Consumer(sharedBuffer);

        Thread producerThread = new Thread(producer, "Producer-Thread");
        Thread consumerThread = new Thread(consumer, "Consumer-Thread");

        startThread(producerThread);
        startThread(consumerThread);

        waitForThread(producerThread);
        waitForThread(consumerThread);
    }

    private static class Producer implements Runnable {

        private final List<Integer> buffer;
        int i = 0;

        private Producer(List<Integer> buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            while(true) {
                synchronized (buffer) {
                    while (!buffer.isEmpty()) { // while instead of if to avoid "spurious wake ups"
                        try {
                            System.out.println("Producer Waiting");

                            // This thread is going into wait (sleep) now - monitor privileges will be relinquished
                            buffer.wait();
                            // If this thread is awakened, control will come here with full monitor privileges restored
                            // I earlier wrongly thought that awakened thread will again have to go through sync(buffer) block
                            System.out.println("Producer Awakened");
                        } catch (InterruptedException e) { // InterruptedException is checked and can't be thrown as Runnable doesn't throw it
                            e.printStackTrace();
                        }
                    }

                    System.out.println("Produced: " + i);
                    buffer.add(i++);

                    System.out.println("Producer Notifies");
                    buffer.notify();

                    if (i==3) { // to end the test case
                        System.out.println("Producer Stopping");
                        break;
                    }

                }
            }

        }
    }

    private static class Consumer implements Runnable {

        private final List<Integer> buffer;

        private Consumer(List<Integer> buffer) {
            this.buffer = buffer;
        }

        @Override
        public void run() {
            while(true) {
                synchronized (buffer) {
                    while (buffer.isEmpty()) { // while instead of if to avoid "spurious wake ups"
                        try {
                            System.out.println("Consumer Waiting");

                            // This thread is going into wait (sleep) now - monitor privileges will be relinquished
                            buffer.wait();
                            // If this thread is awakened, control will come here with full monitor privileges restored
                            // I earlier wrongly thought that awakened thread will again have to go through sync(buffer) block
                            System.out.println("Consumer Awakened");
                        } catch (InterruptedException e) { // InterruptedException is checked and can't be thrown as Runnable doesn't throw it
                            e.printStackTrace();
                        }
                    }

                    int output = buffer.remove(0);
                    System.out.println("Consumed: " + output);

                    System.out.println("Consumer Notifies");
                    buffer.notify();

                    if (output == 2) { // to end test case
                        System.out.println("Consumer Stopping");
                        break;
                    }
                }
            }
        }
    }



    /**
     * Two threads => thread1 should print odd numbers and thread2 print even number (that too in order)
     * T1 1
     * T2 2
     * T1 3
     * T2 4
     * T1 5...
     */
    @Test
    public void print_odd_even() {
        int max = 10;
        Counter counter = new Counter(max);  // Can't use Integer since that's immutable
        Thread oddThread = new Thread(new OddEvenRunner(counter, true), "OddThread");
        Thread evenThread = new Thread(new OddEvenRunner(counter, false), "EvenThread");

        startThread(oddThread);
        startThread(evenThread);

        waitForThread(oddThread);
        waitForThread(evenThread);

    }

    private static class OddEvenRunner implements Runnable {
        private final Counter counter;
        private final boolean odd;

        private OddEvenRunner(Counter counter, boolean odd) {
            this.counter = counter;
            this.odd = odd;
        }

        private boolean canPrint() {
            return ((odd && counter.value%2==1) || (!odd && counter.value%2==0));
        }

        @Override
        public void run() {
            while (true) {
                synchronized (counter) {
                    while (counter.valid() && !canPrint()) {
                        try {
                            counter.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // See next test for theory
                    counter.notify();

                    // while loop is broken but any of 2 conditions might be false => which one?
                    if (counter.valid()) { // still valid => so loop must have broken due to canPrint() == true
                        System.out.println(threadName() + counter.value++);
                    } else {
                        break;
                    }
                }
            }
        }
    }

    private static class Counter {
        int value, max;

        public Counter(int max) {
            this.max = max;
        }

        public boolean valid() {
            return value<max;
        }
    }



    @Test
    public void print_string_by_3_threads_sequentially() {
        String s = "0123456789";
        Counter counter = new Counter(s.length());
        SequentialPrinter s0 = new SequentialPrinter(counter, 0, s);
        SequentialPrinter s1 = new SequentialPrinter(counter, 1, s);
        SequentialPrinter s2 = new SequentialPrinter(counter, 2, s);

        Thread t0 = new Thread(s0, "T0");
        Thread t1 = new Thread(s1, "T1");
        Thread t2 = new Thread(s2, "T2");

        startThread(t0);
        startThread(t1);
        startThread(t2);

        waitForThread(t0);
        waitForThread(t1);
        waitForThread(t2);
    }

    private static class SequentialPrinter implements Runnable {
        private final Counter counter;
        private final int remainder;
        private final String s;

        private SequentialPrinter(Counter counter, int remainder, String s) {
            this.counter = counter;
            this.remainder = remainder;
            this.s = s;
        }

        private boolean canPrint() {
            return counter.value%3 == remainder;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (counter) {
                    while (counter.valid() && !canPrint()) {
                        try {
                            counter.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // Some theory about notify/notifyAll (should also refer to JavaDoc):
                    // Suppose current thread is T0 and it calls lock.notify()
                    // Calling notify() just switches some WAITING thread T1's state from WAITING to BLOCKED (https://stackoverflow.com/a/15680579/8350901 for waiting vs blocked)
                    // and that BLOCKED T1 now competes with other BLOCKED threads (T2,T3) which are BLOCKED on lock already
                    // (T2 is at entry of sync block and T3 was awakened previously like T1 but haven't got the lock yet)
                    // JVM selects one of them arbitrarily so there's no guarantee which thread would get the lock.
                    // 1. Case 1: T1 does NOT get the lock and T2 enters the sync block at line 257
                    // 2. Case 2: T1 gets the lock => then T1 does not have to go through sync block entry
                    // and can resume control after wait() call at L271 with full lock privileges => this is called exiting the wait() call
                    // 3. Case 3: T1 does NOT get the lock and T3 gets the lock (same as case 2, T3 exits wait() with full lock privileges)
                    // But in any of above cases, the selected thread (T1/T2/T3) can NOT get lock
                    // unless this thread (T0) exits sync block at L299 => so it is okay putting notify statement here (and not at end of sync)
                    counter.notifyAll();

                    // Control reached here - Either of above 2 conditions in while are false now (but which one?)
                    // So, these 2 conditions are necessary to be checked again
                    // otherwise awakened thread can print wrongly for value>max

                    if (counter.valid()) { // not valid => so loop must have broken due to canPrint() == true
                        System.out.println(threadName() + s.charAt(counter.value++));
                    } else { // counter.maxed() is true => break now
                        break;
                    }
                }
            }
        }
    }

}
