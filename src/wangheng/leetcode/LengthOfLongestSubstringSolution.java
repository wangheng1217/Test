package wangheng.leetcode;

import java.util.Hashtable;

public class LengthOfLongestSubstringSolution {

    //Longest Substring Without Repeating Characters
    public int lengthOfLongestSubstring(String s) {
        if (s.length() < 2)
            return s.length();
        int longestLength = 1;
        Hashtable<Character, Integer> table = new Hashtable<Character, Integer>();
        table.put(s.charAt(0), 0);
        for (int i = 0, j = 1; j < s.length(); j++) {
            Integer indexFound = table.get(s.charAt(j));
            if (indexFound == null || indexFound.intValue() < i) {
                int length = j + 1 - i;
                if (length > longestLength) {
                    longestLength = length;
                }
            } else {
                i = indexFound.intValue() + 1;
            }
            table.put(s.charAt(j), j);
        }
        return longestLength;
    }
    
    /*
    public int lengthOfLongestSubstring(String s) {
        if (s.length() < 2)
            return s.length();
        int longestLength = 1;
        String longestString = s.substring(0, 1);
        for (int i = 0, j = 1; j < s.length(); j++) {
            int indexFound = findCharInString(s, i, j);
            if (indexFound == -1) {
                int length = j + 1 - i;
                if (length > longestLength) {
                    longestLength = length;
                    longestString = s.substring(i, j + 1);
                }

            } else {
                i = indexFound + 1;
            }
        }
        return longestLength;
    }

    private int findCharInString(String s, int i, int j) {
        for (int k = i; k < j; k++) {
            if (s.charAt(k) == s.charAt(j))
                return k;
        }
        return -1;
    }
    */
}
