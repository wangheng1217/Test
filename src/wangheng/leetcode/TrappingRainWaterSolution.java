package wangheng.leetcode;

public class TrappingRainWaterSolution {
    
    // improve on trap2, constant space
    public int trap3(int[] A) {
        int water = 0;
        int l = A.length;
        
        int maxIndex = 0;
        for (int i = 1; i < l; i++) {
            if (A[i] > A[maxIndex]) maxIndex = i;
        }

        int pre = 0;
        for (int i = 0; i < maxIndex; i++) {
            if (pre > A[i]) water = water + (pre-A[i]);
            pre = Math.max(pre, A[i]);
        }
        
        pre = 0;
        for (int i = l-1; i > maxIndex; i--) {
            if (pre > A[i]) water = water + (pre-A[i]);
            pre = Math.max(pre, A[i]);
        }
        
        return water;
    }

    // calculate the left max and right max, then calculate water for each bar
    public int trap2(int[] A) {
        int result = 0;
        if (A.length < 3)
            return result;

        int[] leftMax = new int[A.length];
        leftMax[0] = 0;
        for (int i = 1; i < A.length; i++) {
            leftMax[i] = max(leftMax[i - 1], A[i - 1]);
        }

        int[] rightMax = new int[A.length];
        rightMax[A.length - 1] = 0;
        for (int i = A.length - 2; i >= 0; i--) {
            rightMax[i] = max(rightMax[i + 1], A[i + 1]);
        }

        for (int i = 0; i < A.length; i++) {
            int height = min(leftMax[i], rightMax[i]);
            if (height > A[i]) {
                result = result + (height - A[i]);
            }
        }

        return result;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }

    // calculate begin and end index for each section
    public int trap(int[] A) {
        int result = 0;
        int i = 1;
        while (i < A.length) {
            if (A[i - 1] > A[i]) {
                int begin = i - 1;
                int end = 0;

                int maxIndex = 0;
                int maxValue = 0;

                for (int j = i + 1; j < A.length; j++) {
                    if (A[j] >= A[i - 1]) {
                        end = j;
                        break;
                    } else {
                        if (A[j] > maxValue) {
                            maxIndex = j;
                            maxValue = A[j];
                        }
                    }
                }

                if (end == 0) {
                    if (maxValue == 0) {
                        break;
                    } else {
                        end = maxIndex;
                    }
                }

                result = result + calculateWater(A, begin, end);
                i = end + 1;
            } else {
                i++;
            }
        }

        return result;
    }

    private int calculateWater(int[] A, int begin, int end) {
        int result = 0;
        int height = A[begin] < A[end] ? A[begin] : A[end];
        for (int i = begin + 1; i < end; i++) {
            if (A[i] < height) {
                result = result + (height - A[i]);
            }
        }
        return result;
    }

}
