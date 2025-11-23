package com.damonx.algorithms.set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetMain
{
    public static boolean hasUniqueChars(final String string) {
        final Set<Character> charSet = new HashSet<>();

        for (char ch: string.toCharArray()) {
            if (charSet.contains(ch)) {
                return false;
            }
            charSet.add(ch);
        }
        return true;
    }

    /**
     * Input:
     * arr1: An integer array
     * arr2: An integer array
     * target: An integer value
     *
     * Output:
     *
     * Return a list of integer arrays, where each array contains a pair of integers,
     * one from arr1 and the other from arr2, that sum up to the target value.
     * @param arr1
     * @param arr2
     * @param target
     * @return
     */
    public static List<int[]> findPairs(int[] arr1, int[] arr2, int target) {
        if (arr1.length == 0 || arr2.length == 0) {
            return List.of();
        }
        final List<int[]> pairs = new ArrayList<>();
        final Set<Integer> mySet = new HashSet<>();
        for (int num : arr1) {
            mySet.add(num);
        }
        for (int num: arr2) {
            final int complement = target - num;
            if (mySet.contains(complement)) {
                pairs.add(new int[]{complement, num});
            }
        }
        return pairs;
    }

    public static void main(String[] args) {
        System.out.println("These tests confirm hasUniqueChars() returns");
        System.out.println("true if all characters are unique and false");
        System.out.println("if any character repeats.");
        System.out.println();

        // Test 1: All unique characters
        System.out.println("Test 1: All Unique Characters");
        String input1 = "abcdef";
        System.out.println("Expected: true");
        System.out.println("Actual: " + hasUniqueChars(input1));
        System.out.println();

        // Test 2: Repeating characters
        System.out.println("Test 2: Repeating Characters");
        String input2 = "hello";
        System.out.println("Expected: false");
        System.out.println("Actual: " + hasUniqueChars(input2));
        System.out.println();

        // Test 3: Empty string
        System.out.println("Test 3: Empty String");
        String input3 = "";
        System.out.println("Expected: true");
        System.out.println("Actual: " + hasUniqueChars(input3));
        System.out.println();

        // Test 4: Single character
        System.out.println("Test 4: Single Character");
        String input4 = "x";
        System.out.println("Expected: true");
        System.out.println("Actual: " + hasUniqueChars(input4));
        System.out.println();

        // Test 5: Case sensitivity and symbols
        System.out.println("Test 5: Case Sensitivity and Symbols");
        String input5 = "AbC!@";
        String input6 = "AaA";
        System.out.println("Expected (AbC!@): true");
        System.out.println("Actual: " + hasUniqueChars(input5));
        System.out.println("Expected (AaA): false");
        System.out.println("Actual: " + hasUniqueChars(input6));
        System.out.println();

        /*
            EXPECTED OUTPUT:
            ----------------
            These tests confirm hasUniqueChars() returns
            true if all characters are unique and false
            if any character repeats.

            Test 1: All Unique Characters
            Expected: true
            Actual: true

            Test 2: Repeating Characters
            Expected: false
            Actual: false

            Test 3: Empty String
            Expected: true
            Actual: true

            Test 4: Single Character
            Expected: true
            Actual: true

            Test 5: Case Sensitivity and Symbols
            Expected (AbC!@): true
            Actual: true
            Expected (AaA): false
            Actual: false
        */

    }


}
