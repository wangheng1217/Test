package wangheng.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsSolution {
    public List<List<Integer>> subsets3(int[] S) {
        Arrays.sort(S);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int n = 1 << S.length;
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<Integer>();
            for (int p = i, c = 0; p > 0; p >>= 1, c++) {
                if ((p & 1) == 1) list.add(S[c]);
            }
            result.add(list);
        }
        return result;
    }
    
    public List<List<Integer>> subsets2(int[] S) {
        Arrays.sort(S);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        dfs(result, list, 0, S);
        return result;
    }
    
    private void dfs(List<List<Integer>> result, List<Integer> list, int i, int[] S) {
        if (i == S.length) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        
        dfs(result, list, i+1, S);
        
        list.add(S[i]);
        dfs(result, list, i+1, S);
        list.remove(list.size()-1);
    }

    public ArrayList<ArrayList<Integer>> subsets(int[] S) {
        java.util.Arrays.sort(S);

        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

        int n = (int) Math.pow(2, S.length);
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> list = new ArrayList<Integer>();

            int m = i;

            for (int j = 0; j < S.length && m > 0; j++) {
                if (m % 2 == 1) {
                    list.add(S[j]);
                }

                m = m / 2;
            }

            res.add(list);
        }

        return res;
    }

}
