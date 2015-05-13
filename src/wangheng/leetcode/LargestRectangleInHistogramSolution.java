package wangheng.leetcode;

import java.util.Stack;

public class LargestRectangleInHistogramSolution {

    // Stack
    public int largestRectangleArea(int[] height) {
        java.util.Stack<Integer> stack = new java.util.Stack<Integer>();
        int i = 0;
        int maxArea = 0;
        while (i <= height.length) {
            if (i < height.length
                    && (stack.isEmpty() || height[stack.peek()] <= height[i])) {
                // stack is ascent ordered
                stack.push(i);
                i++;
            } else {
                // simulate a 0 value in height[length], to make sure the value
                // will be finally calculated
                if (i == height.length && stack.isEmpty()) {
                    i++;
                } else {
                    int h = height[stack.pop()];
                    int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                    maxArea = max(maxArea, h * w);
                }
            }
        }
        return maxArea;
    }
    
    public int largestRectangleArea3(int[] height) {
        Stack<Integer> stack = new Stack<Integer>();
        int maxArea = 0;
        int i = 0;
        while (i < height.length) {
            if (stack.isEmpty() || height[stack.peek()] <= height[i]) {
                stack.push(i);
                i++;
            } else {
                int h = height[stack.pop()];
                int w = i - (stack.isEmpty() ? -1 : stack.peek()) - 1;
                int area = h * w;
                maxArea = Math.max(maxArea, area);
            }
        }
        
        while (!stack.isEmpty()) {
            int h = height[stack.pop()];
            int w = i - (stack.isEmpty() ? -1 : stack.peek()) - 1;
            int area = h * w;
            maxArea = Math.max(maxArea, area);
        }
        
        return maxArea;
    }

    public int largestRectangleArea2(int[] height) {
        int maxArea = 0;
        int minHeight = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i; j < height.length; j++) {
                if (j == i) {
                    minHeight = height[i];
                } else {
                    minHeight = min(minHeight, height[j]);
                }

                int area = minHeight * (j - i + 1);
                maxArea = max(maxArea, area);
            }
        }
        return maxArea;
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }
}
