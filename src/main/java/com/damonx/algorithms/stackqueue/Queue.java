package com.damonx.algorithms.stackqueue;

public class Queue
{
    private Node first;
    private Node last;
    private int length;

    public Queue(int value) {
        Node newNode = new Node(value);
        first = newNode;
        last = newNode;
        length = 1;
    }

    class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public void printQueue() {
        Node temp = first;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void getFirst() {
        System.out.println("First Node's value is: " + first.value);
    }

    public void getLast() {
        System.out.println("Last Node's value is: " + last.value);
    }

    public void getLength()
    {
        System.out.println("Length of the Queue is: " + length);
    }

}
