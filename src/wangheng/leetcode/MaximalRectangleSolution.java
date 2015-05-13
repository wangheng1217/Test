package wangheng.leetcode;

import java.util.Stack;

public class MaximalRectangleSolution {
    
    // use the LargestRectangleInHistogramSolution
    public int maximalRectangle2(char[][] matrix) {
        if (matrix.length == 0) return 0;
        
        int maxArea = 0;
        int[] height = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') {
                    height[j] = 0;
                } else {
                    height[j] = height[j] + 1;
                }
            }
            
            maxArea = Math.max(maxArea, largestRectangleArea(height));
        }
        return maxArea;
    }
    
    private int largestRectangleArea(int[] height) {
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


    // brute force
    public int maximalRectangle(char[][] matrix) {
        int m = 0;

        if (matrix.length == 0 || matrix[0].length == 0)
            return m;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    int width = 1;
                    int height = 1;

                    for (int k = i + 1; k < matrix.length; k++) {
                        if (matrix[k][j] == '1') {
                            height++;
                        } else {
                            break;
                        }
                    }

                    m = max(m, width * height);

                    for (int p = j + width; p < matrix[0].length; p++) {
                        for (int q = i; q < i + height; q++) {
                            if (matrix[q][p] != '1') {
                                height = q - i;
                                break;
                            }
                        }
                        int area = (p - j + 1) * height;
                        m = max(m, area);
                    }
                }
            }
        }

        return m;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

}
