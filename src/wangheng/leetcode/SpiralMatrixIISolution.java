package wangheng.leetcode;

public class SpiralMatrixIISolution {

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int count = 1;
        for (int depth = 0; depth <= n - 1 - depth; depth++) {
            if (depth == n - 1 - depth) {
                res[depth][depth] = count++;
            } else {
                for (int i = depth, j = depth; j < n - 1 - depth; j++) {
                    res[i][j] = count++;
                }
                for (int i = depth, j = n - 1 - depth; i < n - 1 - depth; i++) {
                    res[i][j] = count++;
                }
                for (int i = n - 1 - depth, j = n - 1 - depth; j > depth; j--) {
                    res[i][j] = count++;
                }
                for (int i = n - 1 - depth, j = depth; i > depth; i--) {
                    res[i][j] = count++;
                }
            }
        }
        return res;
    }

}
