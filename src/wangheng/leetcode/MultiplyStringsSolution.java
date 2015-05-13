package wangheng.leetcode;

import java.util.ArrayList;
import java.util.List;

public class MultiplyStringsSolution {

    public static void main(String[] args) {
//         String num1 =
//         "67671277416626746657687631784163120468201083678333022807";
//         String num2 =
//         "7513826174917015375128752933843614929738429142691134209414990271995868820936126459879940531569978476559542476";
        String num1 = "256117489511377083148593685533950561400363410418754703282767252221661609163404299";
        String num2 = "61200496111643709081218550902198211480012378840070191147459688611759881218205422431757614";
//        String num1 = "29476";
//        String num2 = "919";
        MultiplyStringsSolution solution = new MultiplyStringsSolution();
        long a = System.currentTimeMillis();
        System.out.println(solution.multiply(num1, num2));
        long b = System.currentTimeMillis();
        System.out.println(b - a);
        System.out.println(solution.multiply2(num1, num2));
        long c = System.currentTimeMillis();
        System.out.println(c - b);
        System.out.println(solution.multiply3(num1, num2));
        long d = System.currentTimeMillis();
        System.out.println(d - c);
        System.out.println(solution.multiply4(num1, num2));
        long e = System.currentTimeMillis();
        System.out.println(e - d);
    }
    
    public String multiply4(String num1, String num2) {
        int[] num = new int[num1.length()+num2.length()];
        for(int i = 0; i < num1.length(); i++) {
            int carry = 0;
            for(int j = 0; j < num2.length(); j++) {
                int n = convert(num1.charAt(num1.length()-1-i)) * convert(num2.charAt(num2.length()-1-j)) + num[num.length-i-j-1] + carry;
                num[num.length-i-j-1] = n%10;
                carry = n/10;
            }
            
            if(carry != 0) {
                num[num.length-i-num2.length()-1] = carry;
            }
        }
        
        int firstNotZero = -1;
        for(int i = 0; i < num.length; i++) {
            if(num[i] != 0) {
                firstNotZero = i;
                break;
            }
        }
        
        if(firstNotZero == -1) return "0";
        
        StringBuffer sb = new StringBuffer();
        for(int i = firstNotZero; i < num.length; i++) {
            sb.append(num[i]);
        }
        return sb.toString();
    }

    public String multiply3(String num1, String num2) {
        List<String> list = new ArrayList<String>();
        for (int i = num2.length() - 1; i >= 0; i = i - 4) {
            int c;
            if (i - 3 >= 0) {
                c = Integer.valueOf(num2.substring(i - 3, i + 1));
            } else {
                c = Integer.valueOf(num2.substring(0, i + 1));
            }

            if (c == 0)
                continue;

            String n = multiply3(num1, c);

            for (int j = i; j < num2.length() - 1; j++) {
                n = n + "0";
            }
            list.add(n);
        }

        return sum(list);
    }

    private String multiply3(String num1, int num2) {
        List<String> list = new ArrayList<String>();
        for (int i = num1.length() - 1; i >= 0; i = i - 4) {
            int c;
            if (i - 3 >= 0) {
                c = Integer.valueOf(num1.substring(i - 3, i + 1));
            } else {
                c = Integer.valueOf(num1.substring(0, i + 1));
            }

            if (c == 0)
                continue;
            String n = null;
            n = c * num2 + "";

            for (int j = i; j < num1.length() - 1; j++) {
                n = n + "0";
            }
            list.add(n);
        }
        return sum(list);
    }

    public String multiply2(String num1, String num2) {
        if (num1.length() < num2.length())
            return multiply(num2, num1);

        List<String> list = new ArrayList<String>();

        for (int i = num2.length() - 1; i >= 0; i = i - 8) {
            int c;
            if (i - 7 >= 0) {
                c = Integer.valueOf(num2.substring(i - 7, i + 1));
            } else {
                c = Integer.valueOf(num2.substring(0, i + 1));
            }

            if (c == 0)
                continue;

            String n = multiply(num1, c);

            for (int j = i; j < num2.length() - 1; j++) {
                n = n + "0";
            }
            list.add(n);
        }

        return sum(list);
    }

    public String multiply(String num1, String num2) {
        if (num1.length() < num2.length())
            return multiply(num2, num1);

        List<String> list = new ArrayList<String>();

        for (int i = num2.length() - 1; i >= 0; i--) {
            int c = convert(num2.charAt(i));
            String n = null;
            if (c == 1) {
                n = num1;
            } else if (c > 1) {
                n = multiply(num1, c);
            } else {
                continue;
            }

            for (int j = i; j < num2.length() - 1; j++) {
                n = n + "0";
            }
            list.add(n);
        }

        return sum(list);
    }

    private String multiply(String num1, int num2) {
        List<String> list = new ArrayList<String>();
        for (int i = num1.length() - 1; i >= 0; i--) {
            int c = convert(num1.charAt(i));
            String n = null;
            if (c > 0) {
                n = c * num2 + "";
            } else {
                continue;
            }

            for (int j = i; j < num1.length() - 1; j++) {
                n = n + "0";
            }
            list.add(n);
        }
        return sum(list);
    }

    private String sum(List<String> list) {
        int i = 0;
        int carry = 0;
        String result = "";
        while (true) {
            int r = carry;
            boolean flag = false;
            for (int j = 0; j < list.size(); j++) {
                String s = list.get(j);
                if (s.length() - 1 - i >= 0) {
                    flag = true;
                    int num = convert(s.charAt(s.length() - 1 - i));
                    r = r + num;
                }
            }
            if (flag == false) {
                result = (carry == 0 ? "" : carry) + result;
                break;
            } else {
                carry = r / 10;
                result = r % 10 + result;
                i++;
            }
        }
        
        // format result, remove the zeros at the beginning
        int firstNotZero = -1;
        for(int j = 0; j < result.length(); j++) {
            if(result.charAt(j) != '0') {
                firstNotZero = j;
                break;
            }
        }
        
        if(firstNotZero == -1) {
            result = "0";
        }
        else{
            result = result.substring(firstNotZero, result.length());
        }
        
        return result;
    }

    private int convert(char c) {
        return c - '0';
    }

}
