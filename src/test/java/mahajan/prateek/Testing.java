package mahajan.prateek;// Bit simplified version of the same thing...let me know what you guys think

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Testing {

    public static void main(String[] args) {
    Exchange e = new Exchange();
    Broker messageBroker = new Broker(e);

    Queue odd = new ConcurrentLinkedQueue();
    messageBroker.addQueueInExchange(odd, "odd");

    Queue even = new ConcurrentLinkedQueue();
    messageBroker.addQueueInExchange(even, "even");

    Producer p = new Producer(e);
    Consumer C1 = new Consumer("Odd consumer", e, "odd");
    Consumer C2 = new Consumer("Even consumer", e, "odd");
}

private static class Consumer implements Runnable{
    //ds
    Exchange exchange;
    String consumerName;
    String routingQueue;
    Thread consumerThread;

    Consumer(String consumerName, Exchange e, String rq){
        this.consumerName = consumerName;
        exchange = e;
        routingQueue = rq;

        consumerThread = (new Thread(this));
        consumerThread.start();
    }
    @Override
    public void run() {
        while(true){
            try {
                consumerThread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object queueMessage = exchange.consumeMessage(routingQueue);
            if(queueMessage != null)
                System.out.println(consumerName +" reading "+queueMessage);
        }
    }
}

private static class Exchange{

    Map<String, Queue> allQueues = new HashMap<>();

    void addAndRouteQueueMessage(Object payload, String routingKeys){
        String [] rKeys = routingKeys.split(",");
        for(String key :rKeys){
            if(!allQueues.containsKey(key)){
                System.out.println("Routing queue not found : " + key);
                continue;
            }
            Queue currentQueue = allQueues.get(key);
            currentQueue.add(payload);
        }
    }

    public void addNewQueue(Queue q, String queueRoutingKey) {
        allQueues.put(queueRoutingKey, q);
    }

    public Object consumeMessage(String routingQueue) {

        Queue currentQueue = allQueues.get(routingQueue);
        if(currentQueue.isEmpty())
            return null;
        return currentQueue.remove();
    }
}

private static class Broker{
    //ds
    Exchange exchange;

    Broker(Exchange e){
        this.exchange = e;
    }

    void addQueueInExchange(Queue q, String queueRoutingKey){
        exchange.addNewQueue(q,queueRoutingKey);
    }

}

private static class Producer implements Runnable{
    //ds
    Exchange exchange;
    Thread producerThread;

    Producer(Exchange e){
        exchange = e;
        producerThread = (new Thread(this));
        producerThread.start();
    }
    @Override
    public void run() {
        //hasSent = true;
        int i = 0;

        while(i < 100){
            if(i%2 == 0)
                exchange.addAndRouteQueueMessage(i,"even");
            else
                exchange.addAndRouteQueueMessage(i,"odd");
            System.out.println("prod .. "+ i);
            i++;
        }
        System.out.println("prod done ");
    }
}
}