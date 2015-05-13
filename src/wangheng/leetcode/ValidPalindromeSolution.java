package wangheng.leetcode;

public class ValidPalindromeSolution {
    
    // TODO: Character.toLowerCase(c), Character.isLetter(c), Character.isDigit(c)
    public boolean isPalindrome2(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (!isValidChar(s.charAt(i))) i++;
            else if (!isValidChar(s.charAt(j))) j--;
            else {
                if (!isSameChar(s.charAt(i), s.charAt(j))) return false;
                i++;
                j--;
            }
        }
        return true;
    }
    
    private boolean isValidChar(char c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
    
    private boolean isSameChar(char c1, char c2) {
        if (c1 == c2) return true;
        if (c1 >= 'a' && c1 <= 'z' && c2 >= 'A' && c2 <= 'Z' && c1-c2 == 'a'-'A') return true;
        if (c1 >= 'A' && c1 <= 'Z' && c2 >= 'a' && c2 <= 'z' && c1-c2 == 'A'-'a') return true;
        
        return false;
    }

    public boolean isPalindrome(String s) {
        if (s == null || s.length() <= 1)
            return true;

        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            while (i < s.length() && !isAlphanumeric(s.charAt(i))) {
                i++;
            }
            while (j > i && !isAlphanumeric(s.charAt(j))) {
                j--;
            }

            if (i < j) {
                if (!equal(s.charAt(i), s.charAt(j))) {
                    return false;
                }
                i++;
                j--;
            }
        }

        return true;
    }

    private boolean isAlphanumeric(char c) {
        if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')
                || (c >= 'a' && c <= 'z'))
            return true;
        else
            return false;
    }

    private boolean equal(char c1, char c2) {
        return ("" + c1).equalsIgnoreCase("" + c2);
    }

}
