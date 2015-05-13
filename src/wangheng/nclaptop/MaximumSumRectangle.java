package wangheng.nclaptop;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 * http://www.geeksforgeeks.org/dynamic-programming-set-27-max-sum-rectangle-in-a-2d-matrix/
 * 
 * the same idea as Largest Sum Contiguous Subarray for 1D array. Added up the sum for 1D line, for different lines with different width.
 */
public class MaximumSumRectangle {

    @Test
    public void test() {
        MaximumSumRectangle solution = new MaximumSumRectangle();
        assertEquals(29, solution.maxSum(new int[][] { { 1, 2, -1, -4, -20 }
                                                     , { -8, -3, 4, 2, 1 }
                                                     , { 3, 8, 10, 1, 3 }
                                                     , { -4, -1, 1, 7, -6 } }));
    }

    public int maxSum(int[][] array) {
        if (array.length == 0 || array[0].length == 0) return 0;
        int m = array.length;
        int n = array[0].length;
        
        int[][][] height = new int[n][m][m]; // height[col][i][j] means in the given column col, the sum from row i to row j, i <= j
        //int[][] maxSum = new int[m][m]; // maxSum[i][j] means the max rectangle sum between row i and row j, i <= j
        
        for (int col = 0; col < n; col++) {
            for (int i = 0; i < m; i++) {
                for (int j = i; j < m; j++) {
                    if (i == j) height[col][i][j] = array[i][col];
                    else height[col][i][j] = height[col][i][j-1] + array[j][col];
                }
            }
        }
        
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                int sum = 0;
                for (int col = 0; col < n; col++) {
                    sum = sum + height[col][i][j];
                    maxSum = Math.max(maxSum, sum);
                    if (sum < 0) sum = 0;
                }
            }
        }
        
        return maxSum;
    }
}
