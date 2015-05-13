package wangheng.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class MinimumWindowSubstringSolution {

    public static void main(String[] args) {
        MinimumWindowSubstringSolution solution = new MinimumWindowSubstringSolution();
        String s = "ADOBECODEBANC";
        String t = "ABBC";
        System.out.println(solution.minWindow(s, t));
    }

    public String minWindow(String S, String T) {
        if (T == null || T.equals(""))
            return "";

        HashMap<Character, LinkedList<Integer>> map = new HashMap<Character, LinkedList<Integer>>();
        HashMap<Character, Integer> countMap = new HashMap<Character, Integer>();
        for (int i = 0; i < T.length(); i++) {
            map.put(T.charAt(i), new LinkedList<Integer>());
            if (countMap.containsKey(T.charAt(i))) {
                countMap.put(T.charAt(i), countMap.get(T.charAt(i)) + 1);
            } else {
                countMap.put(T.charAt(i), 1);
            }
        }

        int found = 0;
        int minLength = -1;
        String minWindow = "";
        for (int i = 0; i < S.length(); i++) {
            if (map.containsKey(S.charAt(i))) {
                if (map.get(S.charAt(i)).size() < countMap.get(S.charAt(i))) {
                    map.get(S.charAt(i)).add(i);
                    found++;
                } else {
                    map.get(S.charAt(i)).pollFirst();
                    map.get(S.charAt(i)).add(i);
                }

                if (found == T.length()) {
                    int start = minValue(map);
                    int length = i - start + 1;
                    if (minLength == -1) {
                        minLength = length;
                        minWindow = S.substring(start, i + 1);
                    } else {
                        if (length < minLength) {
                            minLength = length;
                            minWindow = S.substring(start, i + 1);
                        }
                    }
                    map.get(S.charAt(start)).pollFirst();
                    found--;
                }
            }
        }
        return minWindow;
    }

    private int minValue(HashMap<Character, LinkedList<Integer>> map) {
        Iterator<LinkedList<Integer>> ite = map.values().iterator();
        int min = Integer.MAX_VALUE;
        while (ite.hasNext()) {
            min = min(min, ite.next().peekFirst());
        }
        return min;
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }
}
