package wangheng.leetcode;

public class UniquePathsIISolution {
    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int[] map = new int[obstacleGrid[0].length];
        if (obstacleGrid[0][0] == 0) map[0] = 1;
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[i][j] == 1) {
                    map[j] = 0;
                } else {
                    if (j > 0) map[j] = map[j-1] + map[j];
                }
            }
        }
        return map[obstacleGrid[0].length-1];
    }


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] map = new int[m][n];

        boolean blocked = false;
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 1)
                blocked = true;
            if (blocked) {
                map[i][0] = 0;
            } else {
                map[i][0] = 1;
            }
        }

        blocked = false;
        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == 1)
                blocked = true;
            if (blocked) {
                map[0][j] = 0;
            } else {
                map[0][j] = 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    map[i][j] = 0;
                } else {
                    map[i][j] = map[i - 1][j] + map[i][j - 1];
                }
            }
        }

        return map[m - 1][n - 1];
    }

}
