package com.damonx.algorithms.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class LinkedList {

    private Node head;
    private Node tail;

    public int getLength()
    {
        return length;
    }

    private int length;

    class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public LinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void printAll() {
        if (head == null) {
            System.out.println("Head: null");
            System.out.println("Tail: null");
        } else {
            System.out.println("Head: " + head.value);
            System.out.println("Tail: " + tail.value);
        }
        System.out.println("\nLinked List:");
        if (head == null) {
            System.out.println("empty");
        } else {
            printList();
        }
    }

    public void makeEmpty() {
        head = null;
        tail = null;
    }

    public boolean isEmpty() {
        return head == null && tail == null;
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }



    /**
     * 1 -> 2 -> 3 -> 4 -> 5
     * Middle Node: 3
     * ===========================
     * 1 -> 2 -> 3 -> 4 -> 5 -> 6
     * Middle Node: 4
     * @return
     */
    public Node findMiddleNode() {
        if (isEmpty()) {
            return null;
        }
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * This method should be able to detect if there is a cycle or loop present in the linked list.
     * @return
     */
    public boolean hasLoop() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) return true;
        }
        return false;
    }

    /**
     * returns the k-th node from the end of a singly linked list.
     * @param k
     * @return
     */
    public Node findKthFromEnd(int k) {
        if (k <= 0) return null;

        Node slow = head;
        Node fast = head;

        for (int i = 0; i < k; i++) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    public void removeDuplicatesBySet() {
        Set<Integer> values = new HashSet<>();
        Node previous = null;
        Node current = head;
        while (current != null) {
            if (values.contains(current.value)) {
                previous.next = current.next;
                length -= 1;
            } else {
                values.add(current.value);
                previous = current;
            }
            current = current.next;
        }
    }

    public void removeDuplicates() {
        // Step 1: Start at the beginning of the list.
        // Initialize `current` to point to the head node.
        // `current` will be used to navigate through the list,
        // one node at a time, starting from the head node.
        Node current = head;

        // Step 2: Check if we've reached the end of the list.
        // We loop until `current` becomes null, which means
        // we've checked all nodes for duplicates.
        while (current != null) {

            // Step 3: Set up a runner node.
            // Initialize `runner` to the `current` node.
            // We'll use `runner` to scan ahead and find duplicates
            // of the `current` node.
            Node runner = current;

            // Step 4: Loop through the remaining nodes.
            // `runner.next` will be null at the end of the list.
            while (runner.next != null) {

                // Step 5: Compare nodes.
                // Check if `runner.next` (the next node) has the
                // same value as `current`.
                if (runner.next.value == current.value) {

                    // Step 6: Remove duplicate.
                    // If we find a duplicate, we skip it by
                    // setting `runner.next` to `runner.next.next`.
                    runner.next = runner.next.next;

                    // Step 7: Update list length.
                    // We removed a node, so decrease the list length
                    // by 1 to keep it accurate.
                    length -= 1;

                } else {

                    // Step 8: Move to the next node.
                    // If the next node is not a duplicate,
                    // move `runner` to the next node to continue.
                    runner = runner.next;
                }
            }

            // Step 9: Move to the next unique node.
            // After checking all nodes for duplicates of the
            // current value, move `current` to the next node.
            current = current.next;
        }
    }

    /**
     * The method aims to convert a binary number, represented by the linked list, into its decimal form.
     *  1->1->1  => 7
     *  1->1  => 3
     * @return
     */
    public int binaryToDecimal() {
        int num = 0;
        Node current = head;
        while (current != null) {
            num = num * 2 + current.value;
            current = current.next;
        }
        return num;
    }

    /**
     * The function partitionList takes an integer x as a parameter and modifies the current linked list in place according to the specified criteria.
     * If the linked list is empty (i.e., head is null), the function should return immediately without making any changes.
     *
     * Example 1:
     * Input:
     * Linked List: 3 -> 8 -> 5 -> 10 -> 2 -> 1 x: 5
     *
     * Process:
     * Values less than 5: 3, 2, 1
     *
     * Values greater than or equal to 5: 8, 5, 10
     * Output:
     * Linked List: 3 -> 2 -> 1 -> 8 -> 5 -> 10
     */
    public void partitionList(int x) {
        if (length == 0) {
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
                prev1 = current;
            } else {
                prev2.next = current;
                prev2 = current;
            }
            current = current.next;
        }
        prev2.next = null;
        prev1.next = dummy2.next;
        head = dummy1.next;
        dummy1 = null;
        dummy2 = null;
    }

    /**
     * Given the following linked list:
     * 1 -> 2 -> 3 -> 4 -> 5
     * Calling reverseBetween(1, 3) should result in the following modified linked list:
     * 1 -> 4 -> 3 -> 2 -> 5
     */
    public void reverseBetween(int startIndex, int endIndex) {
        // Check: If linked list is empty, nothing to reverse.
        // Exit the method.
        if (head == null) return;

        // Create a 'dummyNode' that precedes the head.
        // Simplifies handling edge cases.
        Node dummyNode = new Node(0);
        dummyNode.next = head;

        // 'previousNode' is used to navigate to the node
        // right before our sublist begins.
        Node previousNode = dummyNode;

        // Move 'previousNode' to node just before sublist.
        for (int i = 0; i < startIndex; i++) {
            previousNode = previousNode.next;
        }

        // 'currentNode' marks the first node of sublist.
        Node currentNode = previousNode.next;

        // Loop reverses the section from startIndex to endIndex.
        for (int i = 0; i < endIndex - startIndex; i++) {

            // 'nodeToMove' is the node we'll move to sublist start.
            Node nodeToMove = currentNode.next;

            // Detach 'nodeToMove' from its current position.
            currentNode.next = nodeToMove.next;

            // Attach 'nodeToMove' at the beginning of the sublist.
            nodeToMove.next = previousNode.next;

            // Move 'nodeToMove' to the start of our sublist.
            previousNode.next = nodeToMove;
        }

        // Adjust 'head' if the first node was part of sublist.
        head = dummyNode.next;
    }

    /**
     * Input List: 1 -> 2 -> 3 -> 4
     * Final result: 2 → 1 → 4 → 3
     */
    public void swapPairs() {
        Node dummy = new Node(0);
        dummy.next = head;
        Node previous = dummy;
        Node first = head;

        while(first != null && first.next != null) {
            Node second = first.next;

            // Perform the swap
            previous.next = second;
            first.next = second.next;
            second.next = first;

            // Move pointers
            previous = first;
            first = first.next;
        }

        head = dummy.next;
    }

}
