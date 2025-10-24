package com.damonx.algorithms.stackqueue;

public class Main {
    public static void main(String[] args) {
        Stack myStack = new Stack(4);
        myStack.getTop();
        myStack.getHeight();
        myStack.printStack();

        myStack.push(5);
        myStack.getTop();
        myStack.getHeight();
        myStack.printStack();

        System.out.println("Popped Node's value is: " + myStack.pop().value);
        myStack.printStack();
    }
}
