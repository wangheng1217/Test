package wangheng.leetcode;

public class MinimumPathSumSolution {
    public int minPathSum2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] map = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) map[j] = map[j] + grid[i][j];
                else if (i == 0) map[j] = map[j-1] + grid[i][j];
                else map[j] = Math.min(map[j-1], map[j]) + grid[i][j];
            }
        }
        return map[n-1];
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] map = new int[m][n];

        map[0][0] = grid[0][0];

        for (int i = 1; i < m; i++) {
            map[i][0] = map[i - 1][0] + grid[i][0];
        }

        for (int j = 1; j < n; j++) {
            map[0][j] = map[0][j - 1] + grid[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                map[i][j] = min(map[i - 1][j], map[i][j - 1]) + grid[i][j];
            }
        }

        return map[m - 1][n - 1];
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }

}
