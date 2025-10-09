package com.damonx.algorithms;

import java.util.Map;
import java.util.stream.Collectors;

public class AnagrameChecker
{
    /**
     * Return true if strB is an anagram of strA.
     */
    public static boolean isAnagram(final String strA, final String strB) {
        if (isInvalidInput(strA, strB)) return false;

        final Map<Integer, Long> freqA = strA.chars()
            .boxed()
            .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        final Map<Integer, Long> freqB = strB.chars()
            .boxed()
            .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

        return freqA.equals(freqB);
    }

    private static boolean isInvalidInput(final String strA, final String strB)
    {
        if (strA == null || strB == null || strA.length() != strB.length()) {
            return true;
        }
        return false;
    }

    /**
     * Return true if strB is an anagram of strA.
     */
    public static boolean isAnagramSort(final String strA, final String strB) {
        if (isInvalidInput(strA, strB)) {
            return false;
        }
        // Convert strings to sorted character arrays
        String sortedA = strA.chars()
            .sorted()
            .collect(StringBuilder::new,
                StringBuilder::appendCodePoint,
                StringBuilder::append)
            .toString();

        String sortedB = strB.chars()
            .sorted()
            .collect(StringBuilder::new,
                StringBuilder::appendCodePoint,
                StringBuilder::append)
            .toString();

        return sortedA.equals(sortedB);
    }

    /**
     * Return true if strB is an anagram of strA.
     */
    public static boolean isAnagramArray(final String strA, final String strB) {
        if (isInvalidInput(strA, strB)) return false;

        int[] counts = new int[128]; // Assuming ASCII characters

        for (int i = 0; i < strA.length(); i++) {
            counts[strA.charAt(i)]++;
            counts[strB.charAt(i)]--;
        }

        for (int count : counts) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args)
    {
        System.out.println(isAnagram("abc", "ccbbaa"));
        System.out.println(isAnagram(" ", ""));
        System.out.println(isAnagramSort("abc", "cba"));
        System.out.println(isAnagramArray("abc", "cba"));
    }
}
