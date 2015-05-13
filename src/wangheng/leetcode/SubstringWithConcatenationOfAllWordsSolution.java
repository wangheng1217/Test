package wangheng.leetcode;

import java.util.ArrayList;

// TODO O(N)
public class SubstringWithConcatenationOfAllWordsSolution {
    
    public static void main(String[] args) {
        String S = "";
        for(int i = 0; i < 5000; i++) S = S + "ab";
        String[] L = {"ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba"};
        
        SubstringWithConcatenationOfAllWordsSolution solution = new SubstringWithConcatenationOfAllWordsSolution();
        
        ArrayList<Integer> res = solution.findSubstring(S, L);
        System.out.println(res.size());
        for(int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i) + ", ");
        }
    }

    // brute force O((M-N)*N)
    public ArrayList<Integer> findSubstring2(String S, String[] L) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (L.length == 0)
            return res;
        java.util.HashMap<String, Integer> map = new java.util.HashMap<String, Integer>();
        for (int i = 0; i < L.length; i++) {
            Integer count = map.get(L[i]);
            if (count == null)
                map.put(L[i], 1);
            else
                map.put(L[i], count + 1);
        }
        int n = L[0].length();
        java.util.HashMap<String, Integer> cloneMap;
        for (int i = 0; i + n * L.length <= S.length(); i++) {
            cloneMap = (java.util.HashMap<String, Integer>) map.clone();
            boolean isMatch = true;
            for (int j = 0; j < L.length; j++) {
                String subStr = S.substring(i + j * n, i + (j + 1) * n);
                Integer count = cloneMap.get(subStr);
                if (count == null || count == 0) {
                    isMatch = false;
                    break;
                } else {
                    cloneMap.put(subStr, count - 1);
                }
            }
            if (isMatch)
                res.add(i);
        }
        return res;
    }

    // brute force O((M-N)*N*N)
    public ArrayList<Integer> findSubstring(String S, String[] L) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (L.length == 0)
            return res;
        int n = L[0].length() * L.length;
        for (int i = 0; i + n <= S.length(); i++) {
            if (isMatch(S.substring(i, i + n), L))
                res.add(i);
        }
        return res;
    }

    private boolean isMatch(String s, String[] L) {
        ArrayList<String> l = new ArrayList<String>();
        for (int i = 0; i < L.length; i++) {
            l.add(L[i]);
        }
        int n = L[0].length();
        for (int i = 0; i < s.length(); i = i + n) {
            String s1 = s.substring(i, i + n);
            boolean matchFlag = false;
            for (int j = 0; j < l.size(); j++) {
                if (s1.equals(l.get(j))) {
                    matchFlag = true;
                    l.remove(j);
                    break;
                }
            }
            if (matchFlag == false)
                return false;
        }
        return true;
    }

}
