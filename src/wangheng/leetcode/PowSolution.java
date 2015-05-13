package wangheng.leetcode;

public class PowSolution {

    public static void main(String[] args) {
        PowSolution solution = new PowSolution();
        for (int i = 0; i < 20; i++) {
            System.out.println(solution.pow(2, i));
        }
        System.out.println(solution.pow(0.00001, 2147483647));
        System.out.println(solution.pow(1, 2147483647));
        System.out.println(solution.pow(1, -2147483648));
    }

    public double pow2(double x, int n) {
        if (n == 0)
            return 1.0;
        double half = pow2(x, n / 2);
        if (n % 2 == 0)
            return half * half;
        else if (n > 0)
            return half * half * x;
        else
            return half * half / x;
    }

    public double pow(double x, int n) {
        if (n < 0) {
            if (n == Integer.MIN_VALUE) {
                return pow(x, Integer.MIN_VALUE + 1) * pow(x, -1);
            }
            return 1 / pow(x, -n);
        }
        if (n == 0)
            return 1;
        else if (n == 1)
            return x;

        double res = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                res = res * x;
            }
            x = x * x;
            n = n / 2;
        }
        return res;
    }
}
