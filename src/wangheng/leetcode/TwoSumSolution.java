package wangheng.leetcode;

import java.util.HashMap;

public class TwoSumSolution {
    public int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] result = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            Integer j = map.get(numbers[i]);
            if (j == null) {
                map.put(target - numbers[i], i);
            } else {
                result[0] = j + 1;
                result[1] = i + 1;
            }
        }
        return result;
    }
}