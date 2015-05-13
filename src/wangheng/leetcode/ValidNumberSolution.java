package wangheng.leetcode;

public class ValidNumberSolution {
    
    // "1." ".1" expected to be true
    public boolean isNumber(String s) {
        if (s == null) return false;
        
        int i = 0;
        int e = s.length() - 1;
        
        while (i <= e && s.charAt(i) == ' ') i++;
        if (i >= s.length()) return false;
        while (i <= e && s.charAt(e) == ' ') e--;
        
        if (s.charAt(i) == '+' || s.charAt(i) == '-') i++;
        
        boolean num = false;
        boolean dot = false;
        boolean exp = false;
        
        while (i <= e) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = true;
            } else if (c == '.') {
                if (dot || exp) return false;
                dot = true;
            } else if (c == 'e') {
                if (exp || !num) return false;
                exp = true;
                num = false;
            } else if (c == '+' || c == '-') {
                if (s.charAt(i-1) != 'e') return false;
                num = false;
            } else {
                return false;
            }
            i++;
        }
        
        return num;
    }

    public boolean isNumber2(String s) {
        boolean signedNum = false;
        boolean isPoint = false;
        boolean isE = false;
        boolean signedE = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ')
                continue;
            if ((c < '0' || c > '9') && c != '.' && c != '-' && c != '+'
                    && c != 'e')
                return false;
            if (isE == false) {
                if (c == 'e')
                    isE = true;
                else {
                    if (signedNum == false) {
                        if ((c < '0' || c > '9') && c != '-' && c != '+'
                                && c != '.')
                            return false;
                        else {
                            signedNum = true;
                        }
                    } else {
                        if (isPoint) {
                            if (c < '0' || c > '9')
                                return false;
                        } else {
                            if (c == '.')
                                isPoint = true;
                            else {
                                if (c < '0' || c > '9')
                                    return false;
                            }
                        }
                    }
                }
            } else {
                if (signedE == false) {
                    if ((c < '0' || c > '9') && c != '-' && c != '+')
                        return false;
                    else
                        signedE = true;
                } else {
                    if ((c < '0' || c > '9'))
                        return false;
                }
            }
        }
        return true;
    }

}
