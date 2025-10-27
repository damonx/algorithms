package com.damonx.algorithms.stackqueue;

public class MyQueueWithStacks
{
    private ArrayListStack<Integer> stack1;
    private ArrayListStack<Integer> stack2;

    public MyQueueWithStacks()
    {
        stack1 = new ArrayListStack<>();
        stack2 = new ArrayListStack<>();
    }

    public int peek() {
        return stack1.peek();
    }

    public boolean isEmpty()
    {
        return stack1.isEmpty();
    }

    /**
     *  queue is a data structure that follows the First-In-First-Out (FIFO) principle,
     *  meaning that the first element added to the queue will be the first one to be removed.
     *  Your implementation must maintain the FIFO order of elements in the queue using two stacks.
     *
     * This method takes an integer value as input and adds it to the queue.
     * @param value
     */
    public void enqueue(int value) {
        // Transfer elements from stack1 to stack2
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        // Push the new value onto stack1
        stack1.push(value);
        // Transfer elements back from stack2 to stack1
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }

    public Integer dequeue() {
        if (stack1.isEmpty()) {
            return null;
        }
        return stack1.pop();
    }
}
