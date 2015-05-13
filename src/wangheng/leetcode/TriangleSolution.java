package wangheng.leetcode;

import java.util.ArrayList;

public class TriangleSolution {

    public int minimumTotal(ArrayList<ArrayList<Integer>> triangle) {
        if (triangle.isEmpty())
            return 0;
        int[] dp = new int[triangle.size()];
        ArrayList<Integer> lastRow = triangle.get(triangle.size() - 1);
        for (int i = 0; i < dp.length; i++) {
            dp[i] = lastRow.get(i);
        }
        for (int i = triangle.size() - 2; i >= 0; i--) {
            ArrayList<Integer> row = triangle.get(i);
            for (int j = 0; j < row.size(); j++) {
                dp[j] = row.get(j) + min(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }

    public int minimumTotalRecursion(ArrayList<ArrayList<Integer>> triangle) {
        return minimumTotal(triangle, 0, 0);
    }

    private int minimumTotal(ArrayList<ArrayList<Integer>> triangle, int row,
            int col) {
        if (row == triangle.size() - 1)
            return triangle.get(row).get(col);
        return triangle.get(row).get(col)
                + min(minimumTotal(triangle, row + 1, col),
                        minimumTotal(triangle, row + 1, col + 1));
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }

}
