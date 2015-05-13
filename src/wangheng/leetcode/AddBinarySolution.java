package wangheng.leetcode;

public class AddBinarySolution {
    
    public static void main(String[] args) {
        AddBinarySolution solution = new AddBinarySolution();
        String a = "-101101";
        String b = "1";
        System.out.println(solution.addBinary(a, b));
    }
    
    public String addBinary2(String a, String b) {
        String res = "";
        int carry = 0;
        int la = a.length();
        int lb = b.length();
        int length = Math.max(la, lb);
        for (int i = 0; i < length; i++) {
            int m = i < la ? (int)(a.charAt(la-1-i)-'0') : 0;
            int n = i < lb ? (int)(b.charAt(lb-1-i)-'0') : 0;
            int sum = m + n + carry;
            res = sum%2 + res;
            carry = sum/2;
        }
        if (carry == 1) res = 1 + res;
        return res;
    }

    public String addBinary(String a, String b) {
        boolean minusA = false;
        boolean minusB = false;
        if (a.charAt(0) == '-') {
            minusA = true;
            a = a.substring(1, a.length());
        }
        if (b.charAt(0) == '-') {
            minusB = true;
            b = b.substring(1, b.length());
        }

        String res = "";

        int carry = 0;
        for (int i = 0; i <= a.length() || i <= b.length(); i++) {
            int bitA, bitB;
            if (i < a.length()) {
                bitA = ((int) a.charAt(a.length() - 1 - i) - (int) '0')
                        * (minusA ? -1 : 1);
            } else {
                bitA = 0;
            }

            if (i < b.length()) {
                bitB = ((int) b.charAt(b.length() - 1 - i) - (int) '0')
                        * (minusB ? -1 : 1);
            } else {
                bitB = 0;
            }

            int sum = bitA + bitB + carry;
            int bit = sum % 2;
            carry = sum / 2;

            if (i >= a.length() - 1 && i >= b.length() - 1 && carry == 0) {
                res = bit + res;
                break;
            } else {
                res = (bit >= 0 ? bit : -bit) + res;
            }
        }

        boolean minusRes = false;
        if (res.length() > 0 && res.charAt(0) == '-') {
            minusRes = true;
            res = res.substring(1, res.length());
        }

        int firstNotZeroPos = -1;
        for (int i = 0; i < res.length(); i++) {
            if (res.charAt(i) != '0') {
                firstNotZeroPos = i;
                break;
            }
        }

        if (firstNotZeroPos == -1) {
            res = "0";
        } else {
            res = (minusRes ? "-" : "")
                    + res.substring(firstNotZeroPos, res.length());
        }

        return res;
    }

}
