package wangheng.leetcode;

public class InterleavingStringSolution {

    public static void main(String[] args) {
        InterleavingStringSolution solution = new InterleavingStringSolution();
        String s1 = "aabcc";
        String s2 = "dbbca";
        String s3 = "aadbbcbcac";
        String s4 = "aadbbbaccc";
        System.out.println(solution.isInterleave(s1, s2, s3));
        System.out.println(solution.isInterleave(s1, s2, s4));
    }
    
    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        
        int l1 = s1.length();
        int l2 = s2.length();
        boolean[][] map = new boolean[l1+1][l2+1];

        for (int i = l1; i >= 0; i--) {
            for (int j = l2; j >= 0; j--) {
                if (i == l1 && j == l2) {
                    map[i][j] = true;
                } else if (i == l1) {
                    map[i][j] = map[i][j+1] && s2.charAt(j) == s3.charAt(i+j);
                } else if (j == l2) {
                    map[i][j] = map[i+1][j] && s1.charAt(i) == s3.charAt(i+j);
                } else {
                    map[i][j] = (map[i][j+1] && s2.charAt(j) == s3.charAt(i+j)) || (map[i+1][j] && s1.charAt(i) == s3.charAt(i+j));
                }
            }
        }
        
        return map[0][0];
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];

        for (int i = s1.length(); i >= 0; i--) {
            for (int j = s2.length(); j >= 0; j--) {
                if (i == s1.length()) {
                    if (j == s2.length()) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = s2.substring(j, s2.length()).equals(
                                s3.substring(i + j, s3.length()));
                    }
                } else {
                    if (j == s2.length()) {
                        dp[i][j] = s1.substring(i, s1.length()).equals(
                                s3.substring(i + j, s3.length()));
                    } else {
                        if (s1.charAt(i) == s3.charAt(i + j)
                                && dp[i + 1][j] == true) {
                            dp[i][j] = true;
                        } else {
                            if (s2.charAt(j) == s3.charAt(i + j)
                                    && dp[i][j + 1] == true) {
                                dp[i][j] = true;
                            } else {
                                dp[i][j] = false;
                            }
                        }
                    }
                }
            }
        }

        return dp[0][0];
    }

    public boolean isInterleaveRecursion(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length())
            return false;
        return isInterleave(s1, 0, s2, 0, s3, 0);
    }

    private boolean isInterleave(String s1, int start1, String s2, int start2,
            String s3, int start3) {
        if (start1 >= s1.length()) {
            if (start2 >= s2.length()) {
                return true;
            } else {
                return s2.substring(start2, s2.length()).equals(
                        s3.substring(start3, s3.length()));
            }
        } else {
            if (start2 >= s2.length()) {
                return s1.substring(start1, s1.length()).equals(
                        s3.substring(start3, s3.length()));
            }
        }

        boolean boo = false;
        if (s1.charAt(start1) == s3.charAt(start3)) {
            boo = isInterleave(s1, start1 + 1, s2, start2, s3, start3 + 1);
        }
        if (boo)
            return true;
        if (s2.charAt(start2) == s3.charAt(start3)) {
            boo = isInterleave(s1, start1, s2, start2 + 1, s3, start3 + 1);
        }
        return boo;
    }

}
