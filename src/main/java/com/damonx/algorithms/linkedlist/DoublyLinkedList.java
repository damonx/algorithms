package com.damonx.algorithms.linkedlist;

public class DoublyLinkedList {

    private Node head;
    private Node tail;
    private int length;


    class Node {
        int value;
        Node next;
        Node prev;

        Node(int value) {
            this.value = value;
        }
    }

    public DoublyLinkedList(final int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public void printList() {
        if (head == null) {
            System.out.println("List is empty");
        }
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
    }

    public void getHead() {
        System.out.println("Head: " + head.value);
    }

    public void getTail() {
        System.out.println("Tail: " + tail.value);
    }

    public void getLength() {
        System.out.println("Length: " + length);
    }

    public void append(int value)
    {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        length++;
    }

    public Node removeLast() {
        if (length == 0) {
            return null;
        }
        Node temp = tail;
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
            temp.prev = null;
        }
        length--;
        return temp;
    }

    public void prepend(int value)
    {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        length++;
    }

    public Node removeFirst() {
        if (length == 0) {
            return null;
        }
        Node temp = head;
        if (length == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
            temp.next = null;
        }
        length--;
        return temp;
    }

    public static void main(String[] args)
    {
        final DoublyLinkedList doublyLinkedList = new DoublyLinkedList(7);

        doublyLinkedList.getHead();
        doublyLinkedList.getTail();
        doublyLinkedList.getLength();
        doublyLinkedList.printList();
        System.out.print("\n");
        doublyLinkedList.append(9);
        doublyLinkedList.printList();
        System.out.print("\n");
        System.out.println("Removed: " + doublyLinkedList.removeLast().value);
        System.out.println("Removed: " + doublyLinkedList.removeLast().value);
        doublyLinkedList.printList();
        System.out.print("\n");
        doublyLinkedList.prepend(10);
        doublyLinkedList.printList();
        System.out.print("\n");
        doublyLinkedList.prepend(20);
        doublyLinkedList.printList();
        System.out.print("\n");
        System.out.println("Removed: " + doublyLinkedList.removeFirst().value);
        System.out.print("\n");
        doublyLinkedList.printList();
        System.out.print("\n");
        System.out.println("Removed: " + doublyLinkedList.removeFirst().value);
        System.out.print("\n");
        doublyLinkedList.printList();
        doublyLinkedList.removeFirst();
        doublyLinkedList.printList();
    }
}
