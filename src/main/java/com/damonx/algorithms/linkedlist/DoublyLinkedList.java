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

    public Node get(int index) {
        if (index >= length || index < 0) {
            return null;
        }
        Node temp = head;
        if (index < length / 2) {
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        } else {
            temp = tail;
            for (int i = length - 1; i > index; i-- ) {
                temp = temp.prev;
            }
        }
        return temp;
    }

    public boolean set(int index, int value) {
       final Node temp = get(index);
       if (temp != null) {
           temp.value = value;
           return true;
       }
       return false;
    }

    public boolean insert(int index, int value) {
        if (index > length || index < 0) {
            return false;
        }
        if (index == 0) {
            prepend(value);
            return true;
        }
        if (index == length) {
            append(value);
            return true;
        }
        final Node newNode = new Node(value);
        Node before = get(index - 1);
        Node after = before.next;
        newNode.prev = before;
        newNode.next = after;
        before.next = newNode;
        after.prev = newNode;
        length++;
        return true;
    }

    public Node remove(int index) {
        if (index >= length || index < 0) {
            return null;
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == length - 1) {
            return removeLast();
        }
        Node temp = get(index);
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;
        length--;
        return temp;
    }

    public boolean isPalindrome() {
        if (length <= 1) return true;

        Node forwardNode = head;
        Node backwardNode = tail;
        for (int i = 0; i < length / 2; i++) {
            if (forwardNode.value != backwardNode.value) return false;
            forwardNode = forwardNode.next;
            backwardNode = backwardNode.prev;
        }
        return true;
//        if (head == null || length == 1) {
//            return true;
//        }
//        Node forward = head;
//        Node backward = tail;
//        while (forward != backward && forward.prev != backward) {
//            if (forward.value != backward.value) {
//                return false;
//            }
//            forward = forward.next;
//            backward = backward.prev;
//        }
//        return true;
    }

    public void reverse() {
        if (length <= 1) {
            return;
        }
        Node temp = null;
        Node current = head;
        while (current != null) {
            current.prev = current.next;
            current.next = temp;
            temp = current;
            current = current.prev;
        }
        temp = head;
        head = tail;
        tail = temp;
    }

    public void partitionList(int x) {
        //   +===================================================+
        //   |               WRITE YOUR CODE HERE                |
        //   | Description:                                      |
        //   | - Partitions a doubly linked list around a value  |
        //   |   `x`.                                            |
        //   | - Nodes with values less than `x` come before     |
        //   |   nodes with values greater than or equal to `x`. |
        //   |                                                   |
        //   | Behavior:                                         |
        //   | - Uses two dummy nodes to build two sublists:     |
        //   |   one for < x, one for >= x.                      |
        //   | - Traverses the list, linking nodes to the        |
        //   |   appropriate sublist and updating prev pointers. |
        //   | - Joins the two sublists together.                |
        //   | - Updates the head and resets head.prev to null.  |
        //
        if (head == null) {
            return;
        }
        Node dummy1 = new Node(0);
        Node dummy2 = new Node(0);
        Node prev1 = dummy1;
        Node prev2 = dummy2;
        Node current = head;
        while (current != null) {
            if (current.value < x) {
                prev1.next = current;
                current.prev = prev1;
                prev1 = current;
            } else {
                prev2.next = current;
                current.prev = prev2;
                prev2 = current;
            }
            current = current.next;
        }
        prev2.next = null;
        prev1.next = dummy2.next;
        if (dummy2.next != null) {
            dummy2.next.prev = prev1;
        }

        head = dummy1.next;
        if (head != null) {
            head.prev = null;
        }
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
