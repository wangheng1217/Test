package wangheng.leetcode;

import java.util.ArrayList;
import java.util.HashMap;

public class PalindromePartitioningIISolution {

    public static void main(String[] args) {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        PalindromePartitioningIISolution solution = new PalindromePartitioningIISolution();
        long a = System.currentTimeMillis();
        System.out.println(solution.minCutDP(s));
        long b = System.currentTimeMillis();
        System.out.println(b - a);
    }

    public int minCutDP(String s) {
        if (s == null || s.length() == 0)
            return 0;

        int length = s.length();
        boolean[][] isPal = new boolean[length][length];
        int[] minCut = new int[length];

        for (int i = 0; i < length; i++) {
            minCut[i] = length - i - 1;
        }

        for (int i = length - 1; i >= 0; i--) {
            for (int j = i; j < length; j++) {
                if (s.charAt(i) == s.charAt(j)
                        && (j - i <= 2 || isPal[i + 1][j - 1])) {
                    isPal[i][j] = true;
                    if (j == length - 1) {
                        minCut[i] = 0;
                    } else {
                        minCut[i] = min(minCut[i], 1 + minCut[j + 1]);
                    }
                }
            }
        }

        return minCut[0];
    }

    public int minCut(String s) {
        if (s == null || s.length() == 0)
            return 0;
        HashMap<Integer, ArrayList<Integer>> map = buildMap(s);
        return minCut(s, 0, map);
    }

    private int minCut(String s, int start,
            HashMap<Integer, ArrayList<Integer>> map) {
        if (start == s.length())
            return -1;
        int minCut = Integer.MAX_VALUE;
        ArrayList<Integer> list = map.get(start);
        for (int i = 0; i < list.size(); i++) {
            minCut = min(minCut, 1 + minCut(s, list.get(i) + 1, map));
        }
        return minCut;
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }

    public int minCut2(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int[] minCut = new int[1];
        minCut[0] = Integer.MAX_VALUE;
        long a = System.currentTimeMillis();
        HashMap<Integer, ArrayList<Integer>> map = buildMap(s);
        long b = System.currentTimeMillis();
        dfs(s, 0, new ArrayList<String>(), minCut, map);
        long c = System.currentTimeMillis();
        System.out.println(b - a);
        System.out.println(c - b);
        return minCut[0];
    }

    private void dfs(String s, int start, ArrayList<String> list, int[] minCut,
            HashMap<Integer, ArrayList<Integer>> map) {
        if (start == s.length()) {
            if (list.size() - 1 < minCut[0]) {
                minCut[0] = list.size() - 1;
            }
            return;
        }

        if (list.size() - 1 >= minCut[0]) {
            return;
        }

        ArrayList<Integer> pList = map.get(start);
        for (int i = pList.size() - 1; i >= 0; i--) {
            int end = pList.get(i);
            list.add(s.substring(start, end + 1));
            dfs(s, end + 1, list, minCut, map);
            list.remove(list.size() - 1);
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

    private HashMap<Integer, ArrayList<Integer>> buildMap(String s) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
        for (int i = 0; i < s.length(); i++) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int j = i; j < s.length(); j++) {
                if (isPalindrome(s, i, j)) {
                    list.add(j);
                }
            }
            map.put(i, list);
        }
        return map;
    }

}
