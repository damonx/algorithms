package com.damonx.algorithms.set;

import java.util.HashSet;
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
