package wangheng.leetcode;

import java.util.Set;

public class WordBreakSolution {
    public boolean wordBreak(String s, Set<String> dict) {
        int length = s.length();
        boolean[] dp = new boolean[length];
        for (int i = length-1; i >= 0; i--) {
            if (dict.contains(s.substring(i, length))) {
                dp[i] = true;
            } else {
                for (int j = i+1; j < length; j++) {
                    if (dp[j] == true && dict.contains(s.substring(i, j))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[0];
    }
}
