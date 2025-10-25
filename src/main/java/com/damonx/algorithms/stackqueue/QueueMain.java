package com.damonx.algorithms.stackqueue;

public class QueueMain
{
    public static void main(String[] args)
    {
        Queue myqueue = new Queue(7);

        myqueue.getFirst();
        myqueue.getLast();
        myqueue.getLength();
        myqueue.printQueue();

        myqueue.enQueue(8);
        myqueue.printQueue();

        System.out.println("Dequed item: " + myqueue.deQueue().value);
        myqueue.printQueue();
    }
}
