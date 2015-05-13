package wangheng.leetcode;

import java.util.*;

public class LongestConsecutiveSequenceSolution {

    public static void main(String[] args) {
        LongestConsecutiveSequenceSolution solution = new LongestConsecutiveSequenceSolution();
        int[] num1 = { 100, 4, 200, 1, 3, 2 };
        int[] num2 = { 4, 1, 5, 2, 3 };
        System.out.println(solution.longestConsecutive2(num1));
        System.out.println(solution.longestConsecutive2(num2));
    }

    public int longestConsecutive2(int[] num) {
        Map<Integer, Integer> lengthMap = new HashMap<Integer, Integer>();

        int maxLength = 0;

        for (int i = 0; i < num.length; i++) {
            int n = num[i];
            if (lengthMap.get(n) == null) {
                if (lengthMap.get(n - 1) == null) {
                    if (lengthMap.get(n + 1) == null) {
                        lengthMap.put(n, 1);
                    } else {
                        lengthMap.put(n, lengthMap.get(n + 1) + 1);
                        lengthMap.put(n + lengthMap.get(n + 1),
                                lengthMap.get(n + 1) + 1);
                    }
                } else {
                    if (lengthMap.get(n + 1) == null) {
                        lengthMap.put(n, lengthMap.get(n - 1) + 1);
                        lengthMap.put(n - lengthMap.get(n - 1),
                                lengthMap.get(n - 1) + 1);
                    } else {
                        int length = lengthMap.get(n - 1)
                                + lengthMap.get(n + 1) + 1;
                        lengthMap.put(n, length);
                        lengthMap.put(n - lengthMap.get(n - 1), length);
                        lengthMap.put(n + lengthMap.get(n + 1), length);
                    }
                }

                if (lengthMap.get(n) > maxLength)
                    maxLength = lengthMap.get(n);
            }
        }

        return maxLength;
    }

    // O(n)
    public int longestConsecutive(int[] num) {
        Map<Integer, Integer> leftMap = new HashMap<Integer, Integer>();
        Map<Integer, Integer> rightMap = new HashMap<Integer, Integer>();

        int maxLength = 0;

        for (int i = 0; i < num.length; i++) {
            int n = num[i];
            if (leftMap.get(n) == null) {
                if (leftMap.get(n - 1) == null) {
                    if (leftMap.get(n + 1) == null) {
                        leftMap.put(n, n);
                        rightMap.put(n, n);
                    } else {
                        leftMap.put(n, n);
                        rightMap.put(n, rightMap.get(n + 1));
                        leftMap.put(rightMap.get(n + 1), n);
                    }
                } else {
                    if (leftMap.get(n + 1) == null) {
                        rightMap.put(n, n);
                        leftMap.put(n, leftMap.get(n - 1));
                        rightMap.put(leftMap.get(n - 1), n);
                    } else {
                        leftMap.put(n, leftMap.get(n - 1));
                        rightMap.put(n, rightMap.get(n + 1));
                        leftMap.put(rightMap.get(n + 1), leftMap.get(n - 1));
                        rightMap.put(leftMap.get(n - 1), rightMap.get(n + 1));
                    }
                }

                int length = rightMap.get(n) - leftMap.get(n) + 1;
                if (length > maxLength) {
                    maxLength = length;
                }
            }
        }

        return maxLength;
    }

}
