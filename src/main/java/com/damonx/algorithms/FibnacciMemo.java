package com.damonx.algorithms;

import java.util.HashMap;
import java.util.Map;

public class FibnacciMemo
{
    private static Map<Integer, Integer> memo = new HashMap<>();

    public static int fib(int n)
    {
        if (n < 2) {
            return n;
        }

        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        int result = fib(n - 1) + fib(n - 2);
        memo.put(n, result);
        return result;
    }

    public static void main(String[] args)
    {
        System.out.println(fib(20));
    }
}
