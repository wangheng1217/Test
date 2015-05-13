package wangheng.leetcode;

public class MaximumSubarraySolution {

    public static void main(String[] args) {
        MaximumSubarraySolution solution = new MaximumSubarraySolution();
        int[] A = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        System.out.println(solution.maxSubArray(A));
    }

    public int maxSubArray2(int[] A) {
        int res = A[0];
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum = max(sum + A[i], A[i]);
            res = max(res, sum);
        }
        return res;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    public int maxSubArray(int[] A) {
        int maxSum = -1;
        int currSum = 0;
        int maxMinusValue = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] >= 0) {
                currSum = currSum + A[i];
                if (currSum > maxSum) {
                    maxSum = currSum;
                }
            } else {
                if (maxSum == -1) {
                    if (maxMinusValue == 0 || A[i] > maxMinusValue) {
                        maxMinusValue = A[i];
                    }
                }

                if (currSum + A[i] > 0) {
                    currSum = currSum + A[i];
                } else {
                    currSum = 0;
                }
            }
        }
        return maxSum == -1 ? maxMinusValue : maxSum;
    }

    public int maxSubArrayRecordingMaxSubArray(int[] A) {
        int maxSum = -1;
        int currSum = 0;
        int maxMinusValue = 0;

        // to record the max sub array if needed
        boolean inSubArray = false;
        int currBeginIndex = 0;
        int maxBeginIndex = 0;
        int maxEndIndex = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] >= 0) {
                if (inSubArray == false) {
                    inSubArray = true;
                    currBeginIndex = i;
                }

                currSum = currSum + A[i];
                if (currSum > maxSum) {
                    maxSum = currSum;

                    maxBeginIndex = currBeginIndex;
                    maxEndIndex = i;
                }
            } else {
                if (maxSum == -1) {
                    if (maxMinusValue == 0 || A[i] > maxMinusValue) {
                        maxMinusValue = A[i];

                        maxBeginIndex = i;
                        maxEndIndex = i;
                    }
                }

                if (currSum + A[i] > 0) {
                    currSum = currSum + A[i];
                } else {
                    currSum = 0;

                    inSubArray = false;
                }
            }
        }

        for (int i = maxBeginIndex; i <= maxEndIndex; i++) {
            System.out.print(A[i] + (i == maxEndIndex ? "\n" : " "));
        }
        return maxSum == -1 ? maxMinusValue : maxSum;
    }

}
