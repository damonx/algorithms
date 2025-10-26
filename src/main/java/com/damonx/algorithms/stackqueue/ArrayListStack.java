package com.damonx.algorithms.stackqueue;

import java.util.ArrayList;

public class ArrayListStack<T> {

    private ArrayList<T> stackList = new ArrayList<>();

    public ArrayList<T> getStackList() {
        return stackList;
    }

    public void printStack() {
        for (int i = stackList.size()-1; i >= 0; i--) {
            System.out.println(stackList.get(i));
        }

    }

    public boolean isEmpty() {
        return stackList.size() == 0;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        } else {
            return stackList.get(stackList.size() - 1);
        }
    }

    public int size() {
        return stackList.size();
    }

    public void push(T value) {
        stackList.add(value);
    }

    public T pop() {
        if (stackList.isEmpty()) {
            return null;
        }
        return stackList.remove(stackList.size() - 1);
    }

    public static String reverseString(String string) {
        ArrayListStack<Character> stack = new ArrayListStack<>();
        String reversedString = "";

        for (char c : string.toCharArray()) {
            stack.push(c);
        }

        while (!stack.isEmpty()) {
            reversedString += stack.pop();
        }

        return reversedString;
    }

    public static boolean isBalancedParentheses(final String str) {
        if (str == null) {
            return false;
        }
        if (str.length() == 0) {
            return true;
        }
        final ArrayListStack<Character> stack = new ArrayListStack<>();
        char[] strChar = str.toCharArray();
        for (int i = 0; i < strChar.length; i++) {
            if (strChar[i] == ')' && stack.isEmpty()) {
                return false;
            }
            if (stack.isEmpty() || strChar[i] == stack.peek()) {
                stack.push(strChar[i]);
            } else {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
