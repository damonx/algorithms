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
    }
}
