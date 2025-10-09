package com.damonx.algorithms;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Utility class for checking whether two strings are Anagrams.
 *
 * <p>An Anagram is a word or phrase formed by rearranging the letters of another,
 * for example: "listen" → "silent".</p>
 *
 * <p>This class demonstrates multiple algorithmic approaches:</p>
 * <ul>
 *   <li>Map-based frequency comparison</li>
 *   <li>Sorting-based comparison</li>
 *   <li>Fixed-size array frequency check</li>
 *   <li>Cosine similarity of frequency vectors</li>
 * </ul>
 *
 * <p>All methods are case-sensitive and assume ASCII input for simplicity.</p>
 */
public final class AnagramChecker {

    private AnagramChecker() {}

    /** 
     * Map-based frequency comparison.
     * Uses character frequency maps and compares them directly.
     */
    public static boolean isAnagramByMap(final String strA, final String strB) {
        if (isInvalidInput(strA, strB)) return false;

        Map<Integer, Long> freqA = toFrequencyMap(strA);
        Map<Integer, Long> freqB = toFrequencyMap(strB);

        return freqA.equals(freqB);
    }

    /** 
     * Sorting-based comparison.
     * Sorts both strings and checks for equality.
     */
    public static boolean isAnagramBySort(final String strA, final String strB) {
        if (isInvalidInput(strA, strB)) return false;

        String sortedA = strA.chars()
            .sorted()
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();

        String sortedB = strB.chars()
            .sorted()
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();

        return sortedA.equals(sortedB);
    }

    /** 
     * Array-based counting.
     * Uses a fixed 128-size frequency array (for ASCII).
     */
    public static boolean isAnagramByArray(final String strA, final String strB) {
        if (isInvalidInput(strA, strB)) return false;

        int[] counts = new int[128]; // ASCII range

        for (int i = 0; i < strA.length(); i++) {
            counts[strA.charAt(i)]++;
            counts[strB.charAt(i)]--;
        }

        for (int count : counts) {
            if (count != 0) return false;
        }

        return true;
    }

    /** 
     * Cosine Similarity of Character Frequency Vectors.
     * 
     * Treats each string as a high-dimensional vector of character frequencies.
     * Two strings are anagrams if their vectors are identical → cosine similarity = 1.
     */
    public static boolean isAnagramByCosine(final String strA, final String strB) {
        if (isInvalidInput(strA, strB)) return false;

        Map<Integer, Long> freqA = toFrequencyMap(strA);
        Map<Integer, Long> freqB = toFrequencyMap(strB);

        Set<Integer> allChars = new HashSet<>();
        allChars.addAll(freqA.keySet());
        allChars.addAll(freqB.keySet());

        double dot = 0.0;
        double normA = 0.0;
        double normB = 0.0;

        for (int ch : allChars) {
            double a = freqA.getOrDefault(ch, 0L);
            double b = freqB.getOrDefault(ch, 0L);
            dot += a * b;
            normA += a * a;
            normB += b * b;
        }

        if (normA == 0 || normB == 0) return false;

        double cosine = dot / (Math.sqrt(normA) * Math.sqrt(normB));
        return Math.abs(cosine - 1.0) < 1e-9;
    }

    // Helper methods
    private static Map<Integer, Long> toFrequencyMap(String str) {
        return str.chars()
            .boxed()
            .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
    }

    private static boolean isInvalidInput(String strA, String strB) {
        return strA == null || strB == null || strA.length() != strB.length();
    }

    /** Simple demo and verification */
    public static void main(String[] args) {
        String[][] tests = {
            {"listen", "silent"},
            {"triangle", "integral"},
            {"apple", "papel"},
            {"rat", "car"},
            {"abcd", "abcde"}
        };

        for (String[] pair : tests) {
            String a = pair[0], b = pair[1];
            System.out.printf("'%s' vs '%s' -> Map:%s, Sort:%s, Array:%s, Cosine:%s%n",
                a, b,
                isAnagramByMap(a, b),
                isAnagramBySort(a, b),
                isAnagramByArray(a, b),
                isAnagramByCosine(a, b)
            );
        }
    }
}