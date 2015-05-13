package wangheng.leetcode;

public class PlusOneSolution {

    public int[] plusOne2(int[] digits) {
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int sum = digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;

            if (carry == 0)
                return digits;
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

    public int[] plusOne(int[] digits) {
        boolean fullOfNine = true;
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != 9) {
                fullOfNine = false;
                break;
            }
        }

        if (fullOfNine) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            for (int i = 1; i < res.length; i++) {
                res[i] = 0;
            }
            return res;
        } else {
            int sum = digits[digits.length - 1] + 1;
            digits[digits.length - 1] = sum % 10;
            int carry = sum / 10;
            for (int i = 1; i < digits.length; i++) {
                sum = digits[digits.length - 1 - i] + carry;
                digits[digits.length - 1 - i] = sum % 10;
                carry = sum / 10;
            }
            return digits;
        }
    }
}
