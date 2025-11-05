package com.damonx.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindDuplicates
{
    public static List<Integer> findDuplicates(int[] nums) {
        final Map<Integer, Integer> numCounts = new HashMap<>();
        for (int num : nums) {
            //numCounts.put(num, numCounts.getOrDefault(num, 0) + 1);
            numCounts.merge(num, 1, Integer::sum);
        }
        return numCounts.entrySet().stream()
            .filter(entry -> entry.getValue() > 1)
            .map(entry -> entry.getKey())
            .toList();
    }

    public static Character firstNonRepeatingChar(final String str) {
        char[] chars  = str.toCharArray();
        final Map<Character, Integer> charCounts = new HashMap<>(chars.length);
        for (char c : chars) {
            //charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
            charCounts.merge(c, 1, Integer::sum);
        }
        for (char c : chars) {
            if (charCounts.get(c) == 1) {
                return c;
            }
        }
        return null;
    }

    public static List<List<String>> groupAnagrams(String[] strings) {
        final Map<String, List<String>> anagramGroups = new HashMap<>();

        for (String string : strings) {
            char[] chars = string.toCharArray();
            Arrays.sort(chars);
            String canonical = new String(chars);
            if (anagramGroups.containsKey(canonical)) {
                anagramGroups.get(canonical).add(string);
            } else {
                List<String> group = new ArrayList<>();
                group.add(string);
                anagramGroups.put(canonical, group);
            }
            // Alternatively:
            // anagramGroups.computeIfAbsent(new String(chars), k -> new ArrayList<>()).add(string);
        }

        return new ArrayList<>(anagramGroups.values());
    }

    public static int[] twoSum(int[] nums, int target) {
        final Map<Integer, Integer> numMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int complement = target - num;

            if (numMap.containsKey(complement)) {
                return new int[]{numMap.get(complement), i};
            }
            numMap.put(num, i);
        }

        return new int[]{};
    }

    public static void main(String[] args)
    {
        System.out.println("These tests confirm findDuplicates() correctly");
        System.out.println("identifies all elements that occur more than once.");
        System.out.println();

        // Test 1: Single duplicate
        System.out.println("Test 1: Single Duplicate");
        int[] nums1 = {1, 2, 3, 2};
        System.out.println("Expected: [2]");
        System.out.println("Actual: " + findDuplicates(nums1));
        System.out.println();

        // Test 2: No duplicates
        System.out.println("Test 2: No Duplicates");
        int[] nums2 = {1, 2, 3, 4};
        System.out.println("Expected: []");
        System.out.println("Actual: " + findDuplicates(nums2));
        System.out.println();

        // Test 3: Multiple duplicates
        System.out.println("Test 3: Multiple Duplicates");
        int[] nums3 = {1, 2, 2, 3, 3, 4};
        System.out.println("Expected: [2, 3] (order may vary)");
        System.out.println("Actual: " + findDuplicates(nums3));
        System.out.println();

        // Test 4: All elements are duplicates
        System.out.println("Test 4: All Elements Duplicates");
        int[] nums4 = {5, 5, 5, 5};
        System.out.println("Expected: [5]");
        System.out.println("Actual: " + findDuplicates(nums4));
        System.out.println();

        // Test 5: Empty array
        System.out.println("Test 5: Empty Array");
        int[] nums5 = {};
        System.out.println("Expected: []");
        System.out.println("Actual: " + findDuplicates(nums5));
        System.out.println();

        System.out.println("These tests confirm firstNonRepeatingChar()");
        System.out.println("returns the first unique character or null");
        System.out.println("if no unique character exists.");
        System.out.println();

        // Test 1: First unique character exists
        System.out.println("Test 1: First Unique Exists");
        String input1 = "swiss";
        System.out.println("Expected: w");
        System.out.println("Actual: " + firstNonRepeatingChar(input1));
        System.out.println();

        // Test 2: No unique character
        System.out.println("Test 2: No Unique Character");
        String input2 = "aabbcc";
        System.out.println("Expected: null");
        System.out.println("Actual: " + firstNonRepeatingChar(input2));
        System.out.println();

        // Test 3: First character is unique
        System.out.println("Test 3: First Character Unique");
        String input3 = "alphabet";
        System.out.println("Expected: l");
        System.out.println("Actual: " + firstNonRepeatingChar(input3));
        System.out.println();

        // Test 4: Mixed duplicates
        System.out.println("Test 4: Mixed Duplicates");
        String input4 = "success";
        System.out.println("Expected: u");
        System.out.println("Actual: " + firstNonRepeatingChar(input4));
        System.out.println();

        // Test 5: Empty string
        System.out.println("Test 5: Empty String");
        String input5 = "";
        System.out.println("Expected: null");
        System.out.println("Actual: " + firstNonRepeatingChar(input5));
        System.out.println();

    }
}
