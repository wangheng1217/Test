package wangheng.leetcode;

public class PalindromeNumberSolution {
    
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        int div = 1;
        while (x / div >= 10) {
            div = div * 10;
        }

        while (div > 1) {
            int first = (x / div) % 10;
            int last = x % 10;
            if (first != last)
                return false;
            x = x / 10;
            div = div / 100;
        }

        return true;
    }

    /*
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        if (reverse(x) == x)
            return true;
        else
            return false;
    }

    // some language might not be able to check overflow
    private int reverse(int x) {
        long result = 0;
        while (x > 0) {
            result = result * 10 + x % 10;
            x = x / 10;
        }
        if (result > Integer.MAX_VALUE)
            return -1;
        else
            return (int) result;
    }
    */
}
