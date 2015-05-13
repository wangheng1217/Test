package wangheng.leetcode;

public class CountAndSaySolution {
    
    public static void main(String[] args) {
        for(int i = 1; i <= 20; i++) {
            System.out.println(new CountAndSaySolution().countAndSay(i));
        }
    }

    public String countAndSay(int n) {
        String result = "1";
        if (n == 1)
            return result;
        for (int i = 2; i <= n; i++) {
            result = read(result);
        }
        return result;
    }

    private String read(String s) {
        StringBuffer sb = new StringBuffer();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count++;
            if (i + 1 >= s.length() || s.charAt(i) != s.charAt(i + 1)) {
                sb.append(count).append(s.charAt(i));
                count = 0;
            }
        }
        return sb.toString();
    }

}
