package mahajan.prateek.java.concurrency;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static mahajan.prateek.java.concurrency.Utils.sleepForMillis;
import static mahajan.prateek.java.concurrency.Utils.startThread;
import static mahajan.prateek.java.concurrency.Utils.threadName;
import static mahajan.prateek.java.concurrency.Utils.waitForThread;

/**
 * Created by: pramahajan on 10/4/20 11:06 PM GMT+05:30
 */


/**
 * Locks are advanced version of Synchronized block
 * Whereas Conditions are advanced version of wait/notify/notifyAll
 * Advt:
 * 1. Locks allow to apply Timeouts so that threads can give up on lock (not present in Synchronized block so blocking is infinite until lock is obtained)
 * 2. Sync block must be contained fully in one method but lock.lock() and lock.unlock() can span across different methods
 * 3. Finer control in Locks using tryLock(), etc
 *
 * Disadvt:
 * 1. User needs to explicitly take lock (using .lock()) and relinquish (using .unlock()) =>
 * failing any of these cause issues but in sync block, locking and releasing was done automatically
 * 2. Difficult semantics
 *
 * http://tutorials.jenkov.com/java-util-concurrent/lock.html
 * https://javarevisited.blogspot.com/2015/06/java-lock-and-condition-example-producer-consumer.html
 *
 */
public class LocksAndBlockingQueues {

    /**
     * Blocking Queues (or BoundedBuffers) can be used to solve P/C problem easily
     * doesn't allow producer to add (by returning false or blocking it, depending upon method used) if buffer is full
     * doesn't allow consumer to remove (by returning null or blocking it, depending upon method used) if buffer is empty
     *
     * https://java2blog.com/custom-blockingqueue-implementation-java/
     */
    @Test
    public void blocking_queue_using_locks() {
        BQ bq = new BQ();
        Producer p1 = new Producer(bq);
        Consumer c1 = new Consumer(bq);

        Thread t1 = new Thread(p1, "Producer-Thread");
        Thread t2 = new Thread(c1, "Consumer-Thread");

        startThread(t1);
        startThread(t2);

        waitForThread(t1);
        waitForThread(t2);
    }

    private static class Producer implements Runnable {
        private final BQ bq;

        private Producer(BQ bq) {
            this.bq = bq;
        }

        @Override
        public void run() {
            for (int i=0;i<10;i++) {
                try {
                    bq.add(i);
                    sleepForMillis(50); // introduce different delays in P/C to test
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class Consumer implements Runnable {
        private final BQ bq;

        public Consumer(BQ bq) {
            this.bq = bq;
        }

        @Override
        public void run() {
            for (int i=0;i<10;i++) {
                try {
                    bq.remove();
                    sleepForMillis(70); // introduce different delays in P/C to test
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class BQ {
        private static final int MAX = 3;
        private final Deque<Integer> buffer = new ArrayDeque<>();
        private final Lock lock = new ReentrantLock();

        // Used 2 different Conditions, each for Producer(P) and Consumer(C)
        // In wait/notify test (file ThreadSignaling), we had only one Object (buffer itself) acting as lock for both P/C
        // Although in that test also, we could have 2 different Objects acting as different monitors for P and C
        // And here in this test also, we can have only one Condition for both P and C
        // But having 2 diff Conditions/monitors help in notifying only producers or only consumers and save CPU
        private final Condition bufferFull = lock.newCondition(), bufferEmpty = lock.newCondition();

        private void add(Integer e) throws InterruptedException {
            lock.lock(); // equivalent to { of sync block

            try {
                while (buffer.size()==MAX) {
                    System.out.println(threadName() + " Waiting - Buffer is full");
                    bufferFull.await();  // just like monitor.wait()
                }

                System.out.println(threadName() + " Added " + e);
                buffer.offerLast(e);

                // just like monitor.notifyAll()
                // note that having 2 different conditions for P/C help us to only notify either Ps or Cs
                bufferEmpty.signalAll(); // notify only consumers

            } finally {
                lock.unlock(); // equivalent to } of sync block
            }
        }

        private void remove() throws InterruptedException {
            lock.lock();

            try {
                while (buffer.isEmpty()) {
                    System.out.println(threadName() + " Waiting - Buffer is empty");
                    bufferEmpty.await();
                }

                int e = buffer.pollFirst();
                System.out.println(threadName() + " Removed " + e);
                bufferFull.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * Test BlockingQueue of Java: using blocking methods put()/take() instead of special-value methods offer()/poll()
     * Use offer()/poll() always
     */
    @Test
    public void java_blocking_queue_blocking_methods() {
        final int MAX = 3;
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(MAX);
        BlockingProducerJavaBQ p1 = new BlockingProducerJavaBQ(blockingQueue);
        BlockingConsumerJavaBQ c1 = new BlockingConsumerJavaBQ(blockingQueue);

        Thread t1 = new Thread(p1, "Producer-Thread");
        Thread t2 = new Thread(c1, "Consumer-Thread");

        startThread(t1);
        startThread(t2);

        waitForThread(t1);
        waitForThread(t2);
    }

    private static class BlockingProducerJavaBQ implements Runnable {
        private final BlockingQueue<Integer> bq;

        public BlockingProducerJavaBQ(BlockingQueue<Integer> bq) {
            this.bq = bq;
        }

        @Override
        public void run() {
            for (int i=0;i<10;i++) {
                try {
                    bq.put(i);
                    System.out.println(threadName() + " Added " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private static class BlockingConsumerJavaBQ implements Runnable {
        private final BlockingQueue<Integer> bq;

        public BlockingConsumerJavaBQ(BlockingQueue<Integer> bq) {
            this.bq = bq;
        }

        @Override
        public void run() {
            for (int i=0;i<10;i++) {
                try {
                    int e = bq.take();
                    System.out.println(threadName() + " Polled " + e);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * Test BlockingQueue of Java: using special-value methods offer()/poll() instead of blocking methods put()/take()
     * Use offer()/poll() always
     */
    @Test
    public void java_blocking_queue_special_value_methods() {
        final int MAX = 3;
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(MAX);
        SpecialValueProducerJavaBQ p1 = new SpecialValueProducerJavaBQ(blockingQueue);
        SpecialValueConsumerJavaBQ c1 = new SpecialValueConsumerJavaBQ(blockingQueue);

        Thread t1 = new Thread(p1, "Producer-Thread");
        Thread t2 = new Thread(c1, "Consumer-Thread");

        startThread(t1);
        startThread(t2);

        waitForThread(t1);
        waitForThread(t2);
    }

    private static class SpecialValueProducerJavaBQ implements Runnable {
        private final BlockingQueue<Integer> bq;

        public SpecialValueProducerJavaBQ(BlockingQueue<Integer> bq) {
            this.bq = bq;
        }

        @Override
        public void run() {
            for (int i=0;i<10;i++) {
                boolean success = bq.offer(i);
                if (success) {
                    System.out.println(threadName() + " Offered " + i);
                } else {
                    System.out.println(threadName() + " Queue full. Could not offer " + i);
                }
            }
        }
    }

    private static class SpecialValueConsumerJavaBQ implements Runnable {
        private final BlockingQueue<Integer> bq;

        public SpecialValueConsumerJavaBQ(BlockingQueue<Integer> bq) {
            this.bq = bq;
        }

        @Override
        public void run() {
            for (int i=0;i<10;i++) {
                Integer e = bq.poll();
                if (e != null) {
                    System.out.println(threadName() + " Polled " + e);
                } else {
                    System.out.println(threadName() + " Queue Empty. Could not poll");
                }
            }
        }
    }

}
