package wangheng.leetcode;

public class ReverseIntegerSolution {

    public int reverse(int x) {
        if (x < 0)
            return -reverse(-x);
        long result = 0;
        while (x > 0) {
            result = result * 10 + x % 10;
            x = x / 10;
        }
        if (result > Integer.MAX_VALUE)
            throw new RuntimeException("result overflowed");
        return (int) result;
    }

    /*
    public int reverse(int x) {
        if (x < 0)
            return -reverse(-x);
        int result = 0;
        int i = 10, j = 1;
        while (x >= j) {
            int n = (x % i) / j;
            result = result * 10 + n;
            i = i * 10;
            j = j * 10;
        }
        return result;
    }

    public int reverse(int x) {
        if (x >= 0) {
            String s = "" + x;
            String ss = reverse(s);
            return Integer.valueOf(ss);
        } else {
            String s = "" + x;
            String ss = reverse(s.substring(1, s.length()));
            String sss = "-" + ss;
            return Integer.valueOf(sss);
        }
    }

    private String reverse(String s) {
        StringBuffer sb = new StringBuffer();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    */
}