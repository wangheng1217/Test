package wangheng.leetcode;

import java.util.ArrayList;

public class SpiralMatrixSolution {

    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (matrix.length == 0)
            return res;
        if (matrix[0].length == 0)
            return res;
        int rows = matrix.length;
        int cols = matrix[0].length;
        for (int depth = 0; depth <= cols - 1 - depth
                && depth <= rows - 1 - depth; depth++) {
            if (depth == cols - 1 - depth) {
                for (int i = depth, j = depth; i <= rows - 1 - depth; i++) {
                    res.add(matrix[i][j]);
                }
            } else if (depth == rows - 1 - depth) {
                for (int i = depth, j = depth; j <= cols - 1 - depth; j++) {
                    res.add(matrix[i][j]);
                }
            } else {
                for (int i = depth, j = depth; j < cols - 1 - depth; j++) {
                    res.add(matrix[i][j]);
                }
                for (int i = depth, j = cols - 1 - depth; i < rows - 1 - depth; i++) {
                    res.add(matrix[i][j]);
                }
                for (int i = rows - 1 - depth, j = cols - 1 - depth; j > depth; j--) {
                    res.add(matrix[i][j]);
                }
                for (int i = rows - 1 - depth, j = depth; i > depth; i--) {
                    res.add(matrix[i][j]);
                }
            }
        }
        return res;
    }

}
