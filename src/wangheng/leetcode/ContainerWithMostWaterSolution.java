package wangheng.leetcode;

public class ContainerWithMostWaterSolution {
    
    // gready, similar to maxSum issue
    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int maxArea = 0;
        while (j > i) {
            int area = (j - i) * min(height[i], height[j]);
            if (area > maxArea)
                maxArea = area;
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxArea;
    }

    /*
    public int maxArea(int[] height) {
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];

        for (int i = 1; i < height.length; i++) {
            leftMax[i] = max(leftMax[i - 1], height[i - 1]);
        }

        for (int i = height.length - 2; i >= 0; i--) {
            rightMax[i] = max(rightMax[i + 1], height[i + 1]);
        }

        int maxArea = 0;
        for (int i = 0; i < height.length; i++) {
            if (leftMax[i] >= height[i])
                continue;
            for (int j = i + 1; j < height.length; j++) {
                if (rightMax[j] >= height[j])
                    continue;
                int area = (j - i) * min(height[i], height[j]);
                if (area > maxArea)
                    maxArea = area;
            }
        }
        return maxArea;
    }
    */

    private int max(int a, int b) {
        return a > b ? a : b;
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }
}
