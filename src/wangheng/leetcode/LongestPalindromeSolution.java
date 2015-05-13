package wangheng.leetcode;

// difference between substring and subsequence
public class LongestPalindromeSolution {
    public String longestPalindrome3(String s) {
        if (s.length() < 2) return s;
        int l = s.length();
        boolean[][] map = new boolean[l][l];
        for (int i = 0; i < l; i++) {
            map[i][i] = true;
        }
        int x = 0, y = 0;
        for (int i = 1; i < l; i++) {
            for (int j = 0; i+j < l; j++) {
                if (s.charAt(j) != s.charAt(i+j)) {
                    map[j][i+j] = false;
                } else {
                    if (j+1 >= i+j-1 || map[j+1][i+j-1] == true) {
                        map[j][i+j] = true;
                        x = j;
                        y = i+j;
                    }
                }
            }
        }
        return s.substring(x, y+1);
    }
    
    public String longestPalindrome2(String s) {
        if (s.length() <= 1) return s;
        String longestSubstr = "";
        for (int i = 0; i < 2*s.length()-1; i++) {
            if (i%2 == 0) {
                int pos = i/2;
                int j = 0;
                for (; pos-j >= 0 && pos+j < s.length(); j++) {
                    if (s.charAt(pos-j) == s.charAt(pos+j)) {
                        continue;
                    } else {
                        break;
                    }
                }
                if ( 2*j-1 > longestSubstr.length() ) {
                    longestSubstr = s.substring(pos-j+1, pos+j);
                }
            } else {
                int pos = i/2;
                int j = 0;
                for (; pos-j >= 0 && pos+1+j < s.length(); j++) {
                    if (s.charAt(pos-j) == s.charAt(pos+1+j)) {
                        continue;
                    } else {
                        break;
                    }
                }
                if (2*j > longestSubstr.length()) {
                    longestSubstr = s.substring(pos-j+1, pos+j+1);
                }
            }
        }
        return longestSubstr;
    }

    //TODO O(n) method suffix tree

    public String longestPalindrome(String s) {
        if (s.length() < 2)
            return s;

        int[] P = new int[2 * s.length() - 1];
        P[0] = 1;
        int center = 0, left = 0, right = 0;
        for (int i = 1; i < P.length; i++) {
            if (i > right) {
                if (i % 2 == 0) {
                    P[i] = 1;
                    center = i;
                    left = i;
                    right = i;
                    for (int k = right + 2; 2 * i - k >= 0 && k < P.length; k = k + 2) {
                        if (s.charAt(k / 2) == s.charAt((2 * i - k) / 2)) {
                            P[i] = P[i] + 2;
                            right = k;
                            left = 2 * i - k;
                        } else
                            break;
                    }
                } else {
                    P[i] = 0;
                    for (int k = i + 1; 2 * i - k >= 0 && k < P.length; k = k + 2) {
                        if (s.charAt(k / 2) == s.charAt((2 * i - k) / 2)) {
                            P[i] = P[i] + 2;
                            center = i;
                            right = k;
                            left = 2 * i - k;
                        } else
                            break;
                    }
                }
            } else {
                int j = 2 * center - i;
                if (i % 2 == 0) {
                    int halfLength = (P[j] - 1) / 2;
                    if ((i + 2 * halfLength) < right)
                        P[i] = P[j];
                    else {
                        P[i] = right - i + 1;
                        center = i;
                        left = 2 * i - right;
                        for (int k = right + 2; 2 * i - k >= 0 && k < P.length; k = k + 2) {
                            if (s.charAt(k / 2) == s.charAt((2 * i - k) / 2)) {
                                P[i] = P[i] + 2;
                                right = k;
                                left = 2 * i - k;
                            } else
                                break;
                        }
                    }
                } else {
                    int halfLength = P[j] / 2;
                    if ((i + 2 * halfLength - 1) < right)
                        P[i] = P[j];
                    else {
                        P[i] = right - i + 1;
                        center = i;
                        left = 2 * i - right;
                        for (int k = right + 2; 2 * i - k >= 0 && k < P.length; k = k + 2) {
                            if (s.charAt(k / 2) == s.charAt((2 * i - k) / 2)) {
                                P[i] = P[i] + 2;
                                right = k;
                                left = 2 * i - k;
                            } else
                                break;
                        }
                    }
                }
            }
        }

        int maxIndex = 0;
        for (int i = 0; i < P.length; i++) {
            if (P[i] > P[maxIndex])
                maxIndex = i;
        }
        if (maxIndex % 2 == 0) {
            return s.substring(maxIndex / 2 - ((P[maxIndex] - 1) / 2), maxIndex
                    / 2 + ((P[maxIndex] - 1) / 2) + 1);
        } else {
            return s.substring((maxIndex + 1) / 2 - (P[maxIndex] / 2),
                    (maxIndex - 1) / 2 + (P[maxIndex] / 2) + 1);
        }
    }

    /*
    public String longestPalindrome(String s) {
        if (s.length() < 2)
            return s;
        int longestX = 0, longestY = 0;
        int i = 0, j = 0;
        while (j < s.length()) { // i <= j
            for (int k = 0; i - k >= 0 && j + k < s.length(); k++) {
                boolean equal = (s.charAt(i - k) == s.charAt(j + k));
                if (!equal)
                    break;
                int length = j + k + 1 - (i - k);
                if (length > longestY + 1 - longestX) {
                    longestY = j + k;
                    longestX = i - k;
                }
            }

            if (j == i)
                j++;
            else
                i++;
        }
        return s.substring(longestX, longestY + 1);
    }

    public String longestPalindrome(String s) {
        if (s.length() < 2)
            return s;
        boolean[][] p = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            p[i][i] = true;
        }
        int longestX = 0, longestY = 0;
        for (int l = 2; l <= s.length(); l++) {
            for (int i = 0; i + l <= s.length(); i++) {
                if (l == 2 || p[i + 1][i + l - 2] == true) {
                    boolean isPalindromic = (s.charAt(i) == s.charAt(i + l - 1));
                    if (isPalindromic) {
                        p[i][i + l - 1] = true;
                        longestX = i;
                        longestY = i + l - 1;
                    }
                } else
                    p[i][i + l - 1] = false;
            }
        }
        return s.substring(longestX, longestY + 1);
    }

    public String longestPalindrome(String s) {
        if (s.length() < 2)
            return s;
        for (int l = s.length(); l > 0; l--) {
            for (int i = 0; i + l <= s.length(); i++) {
                String substring = s.substring(i, i + l);
                if (isPalindromic(substring))
                    return substring;
            }
        }
        return null;
    }

    public String longestPalindrome(String s) {
        String longestSubstring = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String substring = s.substring(i, j + 1);
                boolean isPalindromic = isPalindromic(substring);
                if (isPalindromic
                        && substring.length() > longestSubstring.length()) {
                    longestSubstring = substring;
                }
            }
        }
        return longestSubstring;
    }

    private boolean isPalindromic(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1))
                return false;
        }
        return true;
    }
    */
}