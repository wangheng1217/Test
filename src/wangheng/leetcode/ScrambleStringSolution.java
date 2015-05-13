package wangheng.leetcode;

import java.util.Arrays;

public class ScrambleStringSolution {

    public static void main(String[] args) {
        String s1 = "abcdefghijklmnopq";
        String s2 = "efghijklmnopqcadb";
        ScrambleStringSolution solution = new ScrambleStringSolution();
        long a = System.currentTimeMillis();
        System.out.println(solution.isScrambleDP(s1, s2));
        long b = System.currentTimeMillis();
        System.out.println(b - a);
    }

    public boolean isScrambleDP(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        if (s1.length() == 0)
            return true;
        int length = s1.length();
        boolean[][][] dp = new boolean[length + 1][length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                dp[1][i][j] = (s1.charAt(i) == s2.charAt(j)) ? true : false;
            }
        }

        for (int l = 2; l <= length; l++) {
            for (int i = 0; i + l <= length; i++) {
                for (int j = 0; j + l <= length; j++) {

                    for (int k = 1; k < l; k++) {
                        if ((dp[k][i][j] && dp[l - k][i + k][j + k])
                                || (dp[k][i][j + l - k] && dp[l - k][i + k][j])) {
                            dp[l][i][j] = true;
                        }
                    }

                }
            }
        }

        return dp[length][0][0];
    }

    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length())
            return false;
        if (s1.length() == 0)
            return true;
        int[][][] map = new int[s1.length()][s1.length()][s1.length() + 1];
        boolean boo = isScramble(s1, 0, s2, 0, s1.length(), map);
        return boo;
    }

    private boolean isScramble(String s1, int start1, String s2, int start2,
            int length, int[][][] map) {
        if (map[start1][start2][length] != 0) {
            if (map[start1][start2][length] == 1)
                return true;
            else
                return false;
        }

        // sort and compare, if different, return false.
        char[] c1 = s1.substring(start1, start1 + length).toCharArray();
        char[] c2 = s2.substring(start2, start2 + length).toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        for (int i = 0; i < c1.length; i++) {
            if (c1[i] != c2[i])
                return false;
        }

        if (length == 1) {
            if (s1.charAt(start1) == s2.charAt(start2)) {
                map[start1][start2][length] = 1;
                return true;
            } else {
                map[start1][start2][length] = 2;
                return false;
            }
        }

        for (int i = 1; i < length; i++) {
            if (isScramble(s1, start1, s2, start2, i, map)
                    && isScramble(s1, start1 + i, s2, start2 + i, length - i,
                            map)) {
                map[start1][start2][length] = 1;
                return true;
            }
            if (isScramble(s1, start1, s2, start2 + length - i, i, map)
                    && isScramble(s1, start1 + i, s2, start2, length - i, map)) {
                map[start1][start2][length] = 1;
                return true;
            }
        }
        map[start1][start2][length] = 2;
        return false;
    }

    private boolean isScramble(String s1, int start1, int end1, String s2,
            int start2, int end2) {
        if (start1 == end1) {
            if (s1.charAt(start1) == s2.charAt(start2))
                return true;
            else
                return false;
        }

        for (int i = 1; i <= end1 - start1; i++) {
            if (isScramble(s1, start1, start1 + i - 1, s2, start2, start2 + i
                    - 1)
                    && isScramble(s1, start1 + i, end1, s2, start2 + i, end2))
                return true;
            if (isScramble(s1, start1, start1 + i - 1, s2, end2 - i + 1, end2)
                    && isScramble(s1, start1 + i, end1, s2, start2, end2 - i))
                return true;
        }
        return false;
    }

}
