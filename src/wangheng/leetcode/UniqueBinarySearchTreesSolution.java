package wangheng.leetcode;

public class UniqueBinarySearchTreesSolution {

    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int res = 0;
            int j = 1;
            for (j = 1; j <= i && j - 1 < i - j; j++) {
                res = res + dp[j - 1] * dp[i - j];
            }
            res = res * 2;
            if (j - 1 == i - j)
                res = res + dp[j - 1] * dp[i - j];
            dp[i] = res;
        }
        return dp[n];
    }

    public int numTreesRecursion(int n) {
        if (n == 0)
            return 1;
        int res = 0;
        for (int i = 1; i <= n; i++) {
            res = res + numTreesRecursion(i - 1) * numTreesRecursion(n - i);
        }
        return res;
    }

}
