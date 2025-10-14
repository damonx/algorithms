package com.damonx.algorithms.leetcode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum
{
    public static int[] twoSum(int[] nums, int target)
    {
        final Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }
    public static void main(String[] args)
    {
        final int[] twoSums = twoSum(new int[]{2, 7, 11, 15}, 26);
        for (int i = 0; i < twoSums.length; i++) {
            System.out.print(twoSums[i] + " ");
        }

    }
}
