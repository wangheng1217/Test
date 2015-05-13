package wangheng.leetcode;

public class DivideTwoIntegersSolution {

    //TODO: unsolved
    public static void main(String[] args) {
        System.out.println(new DivideTwoIntegersSolution().divide(2147483647, 1));
    }

    public int divide(int dividend, int divisor) {
        boolean sign = true;
        if (dividend < 0) {
            dividend = -dividend;
            sign = !sign;
        }
        if (divisor < 0) {
            divisor = -divisor;
            sign = !sign;
        }

        int result = dividePositive(dividend, divisor);
        return sign ? result : -result;
    }

    private int dividePositive(int dividend, int divisor) {
        if (dividend < divisor)
            return 0;
        int count = 1;
        while (dividend >= divisor + divisor) {
            divisor = divisor + divisor;
            count++;
        }
        int result = 0;
        for (int i = count; i > 0; i--) {
            if (dividend >= divisor) {
                result = result + (1 << (i-1));
                dividend = dividend - divisor;
            }
            divisor>>=1;
        }
        return result;
    }

}
