package wangheng.leetcode;

public class IntToRomanSolution {
    public String intToRoman2(int num) {
        int[] nums = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String[] symbols = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        StringBuilder res = new StringBuilder();
        int i = 0;
        while (num > 0) {
            int times = num / nums[i];
            num -= nums[i] * times;
            for (; times > 0; times--) {
                res.append(symbols[i]);
            }
            ++i;
        }
        return res.toString();
    }

    // Input is guaranteed to be within the range from 1 to 3999
    public String intToRoman(int num) {
        String roman = "";
        for (int i = 1; i <= 4 && num > 0; i++) {
            String s = convert(num % 10, i);
            roman = s + roman;
            num = num / 10;
        }
        return roman;
    }

    // num from 0 to 9
    private String convert(int num, int position) {
        String one = null, five = null, ten = null;
        if (position == 1) {
            one = "I";
            five = "V";
            ten = "X";
        } else if (position == 2) {
            one = "X";
            five = "L";
            ten = "C";
        } else if (position == 3) {
            one = "C";
            five = "D";
            ten = "M";
        } else if (position == 4) {
            one = "M";
            five = null;
            ten = null;
        }

        switch (num) {
        case 0:
            return "";
        case 1:
            return one;
        case 2:
            return one + one;
        case 3:
            return one + one + one;
        case 4:
            return one + five;
        case 5:
            return five;
        case 6:
            return five + one;
        case 7:
            return five + one + one;
        case 8:
            return five + one + one + one;
        case 9:
            return one + ten;
        default:
            return "";
        }
    }

}
