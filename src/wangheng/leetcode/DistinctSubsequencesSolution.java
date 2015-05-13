package wangheng.leetcode;

public class DistinctSubsequencesSolution {

    public int numDistinctDP(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];

        for (int i = 0; i <= s.length(); i++) {
            dp[i][t.length()] = 1;
        }
        /*
        for (int j = 0; j < t.length(); j++) {
            dp[s.length()][j] = 0;
        }
        */

        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = t.length() - 1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }

        return dp[0][0];
    }

    public int numDistinct(String S, String T) {
        return numDistinct(S, 0, T, 0);
    }

    private int numDistinct(String s, int sStart, String t, int tStart) {
        if (tStart == t.length())
            return 1;
        if (sStart == s.length())
            return 0;

        if (s.charAt(sStart) == t.charAt(tStart)) {
            return numDistinct(s, sStart + 1, t, tStart + 1)
                    + numDistinct(s, sStart + 1, t, tStart);
        } else {
            return numDistinct(s, sStart + 1, t, tStart);
        }
    }

}
