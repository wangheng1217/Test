package wangheng.nclaptop;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 * http://www.geeksforgeeks.org/dynamic-programming-set-14-maximum-sum-increasing-subsequence/
 */
public class MaximumSumIncreasingSubsequence {

    @Test
    public void test() {
        MaximumSumIncreasingSubsequence solution = new MaximumSumIncreasingSubsequence();
        assertEquals(106, solution.maxSumIncreasingSubsequence(new int[] {1, 101, 2, 3, 100, 4, 5}));
        assertEquals(22, solution.maxSumIncreasingSubsequence(new int[] {3, 4, 5, 10}));
        assertEquals(10, solution.maxSumIncreasingSubsequence(new int[] {10, 5, 4, 3}));
        assertEquals(203, solution.maxSumIncreasingSubsequence(new int[] {2, 100, 1, 3, 101, 4, 5}));
        assertEquals(0, solution.maxSumIncreasingSubsequence(new int[] {}));
        assertEquals(1, solution.maxSumIncreasingSubsequence(new int[] {1}));
    }

    public int maxSumIncreasingSubsequence(int[] array) {
        if (array.length == 0) return 0;
        int[] maxSum = new int[array.length];
        int maxValue = Integer.MIN_VALUE;
        for (int i = array.length-1; i >= 0; i--) {
            maxSum[i] = array[i];
            for (int j = i+1; j < array.length; j++) {
                if (array[i] < array[j] && maxSum[i] < array[i] + maxSum[j]) {
                    maxSum[i] = array[i] + maxSum[j];
                }
            }
            if (maxSum[i] > maxValue) maxValue = maxSum[i];
        }
        return maxValue;
    }
}
