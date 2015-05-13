package wangheng.leetcode;

public class DecodeWaysSolution {

    public static void main(String[] args) {
        String s = "1787897759966261825913315262377298132516969578441236833255596967132573482281598412163216914566534565";
        DecodeWaysSolution solution = new DecodeWaysSolution();
        long a = System.currentTimeMillis();
        System.out.println(solution.numDecodings(s));
        long b = System.currentTimeMillis();
        System.out.println(b - a);
        System.out.println(solution.numDecodings2(s));
        long c = System.currentTimeMillis();
        System.out.println(c - b);
        System.out.println(solution.numDecodings3(s));
        long d = System.currentTimeMillis();
        System.out.println(d - c);
    }
    
    public int numDecodings4(String s) {
        if (s == null || "".equals(s)) return 0;
        
        int[] dp = new int[s.length()];
        
        if (s.charAt(0) == '0') return 0;
        else dp[0] = 1;
        
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != '0') dp[i] = dp[i] + dp[i-1];
            if (s.charAt(i-1) == '1' || (s.charAt(i-1) == '2' && (s.charAt(i) != '7' && s.charAt(i) != '8' && s.charAt(i) != '9'))) {
                if (i == 1) dp[i] = dp[i] + 1;
                else dp[i] = dp[i] + dp[i-2];
            }
        }
        
        return dp[s.length()-1];
    }

    public int numDecodings(String s) {
        if (s.length() == 0)
            return 0;
        int[] dp = new int[s.length()];
        for (int i = dp.length - 1; i >= 0; i--) {
            if (s.charAt(i) == '0')
                dp[i] = 0;
            else {
                if (i == dp.length - 1) {
                    dp[i] = 1;
                } else {
                    if (Integer.valueOf(s.substring(i, i + 2)) <= 26) {
                        if (i + 2 == dp.length) {
                            dp[i] = dp[i + 1] + 1;
                        } else {
                            dp[i] = dp[i + 1] + dp[i + 2];
                        }
                    } else {
                        dp[i] = dp[i + 1];
                    }
                }
            }
        }
        return dp[0];
    }

    public int numDecodings2(String s) {
        if (s.length() == 0)
            return 0;
        return numDecodings(s, 0);
    }

    private int numDecodings(String s, int startIndex) {
        if (startIndex > s.length() - 1)
            return 1;
        if (startIndex == s.length() - 1) {
            if (s.charAt(startIndex) == '0')
                return 0;
            else
                return 1;
        }

        if (s.charAt(startIndex) == '0')
            return 0;

        if (Integer.valueOf(s.substring(startIndex, startIndex + 2)) <= 26) {
            return numDecodings(s, startIndex + 1)
                    + numDecodings(s, startIndex + 2);
        } else {
            return numDecodings(s, startIndex + 1);
        }
    }
    
    public int numDecodings3(String s) {
        if (s == null || "".equals(s)) return 0;
        
        int[] result = new int[1];
        dfs(s, 0, result);
        return result[0];
    }
    
    private void dfs(String s, int p, int[] result) {
        if (p == s.length()) {
            result[0] = result[0] + 1;
            return;
        }
        
        if (s.charAt(p) != '0') dfs(s, p+1, result);
        
        if (p < s.length()-1 && (s.charAt(p) == '1' || (s.charAt(p) == '2' && (s.charAt(p+1) != '7' && s.charAt(p+1) != '8' && s.charAt(p+1) != '9')))) dfs(s, p+2, result);
    }


}
