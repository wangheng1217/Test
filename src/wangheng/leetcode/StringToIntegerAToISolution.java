package wangheng.leetcode;

public class StringToIntegerAToISolution {

    public int atoi(String str) {
        if (str == null)
            return 0;

        Boolean signFlag = null;

        Long result = null;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                if (signFlag == null)
                    continue;
                else
                    break;
            }
            if (signFlag == null) {
                if (str.charAt(i) == '+')
                    signFlag = true;
                else if (str.charAt(i) == '-')
                    signFlag = false;
                else {
                    int n = convert(str.charAt(i));
                    if (n < 0)
                        break;
                    else {
                        signFlag = true;
                        result = (long) n;
                    }
                }
            } else {
                int n = convert(str.charAt(i));
                if (n < 0)
                    break;
                else {
                    if (result == null)
                        result = 0l;
                    result = result * 10 + n;
                }
            }
        }

        if (result == null)
            return 0;
        if (signFlag == true) {
            if (result.longValue() > Integer.MAX_VALUE)
                return Integer.MAX_VALUE;
            else
                return result.intValue();
        } else {
            if ((-result.longValue()) < Integer.MIN_VALUE)
                return Integer.MIN_VALUE;
            else
                return -result.intValue();
        }
    }

    private int convert(char c) {
        int i = (int) c - (int) '0';
        if (i >= 0 && i <= 9)
            return i;
        else
            return -1;
    }

}
