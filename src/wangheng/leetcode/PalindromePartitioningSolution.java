package wangheng.leetcode;

import java.util.*;

public class PalindromePartitioningSolution {

    /*
        boolean[][] isPalindrome = new boolean[length][length];
        for (int l = 1; l <= length; l++) {
            for (int i = 0; i+l-1 < length; i++) {
                isPalindrome[i][i+l-1] = (s.charAt(i) == s.charAt(i+l-1)) && (i+1 > i+l-2 || isPalindrome[i+1][i+l-2]);
            }
        }

     */
    public ArrayList<ArrayList<String>> partition(String s) {
        ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();
        if (s == null || s.length() == 0)
            return res;
        dfs(s, 0, new ArrayList<String>(), res);
        return res;
    }

    private void dfs(String s, int start, ArrayList<String> list,
            ArrayList<ArrayList<String>> res) {
        if (start == s.length()) {
            res.add(new ArrayList<String>(list));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                list.add(s.substring(start, i + 1));
                dfs(s, i + 1, list, res);
                list.remove(list.size() - 1);
            }
        }

    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end))
                return false;
            start++;
            end--;
        }
        return true;
    }

}
