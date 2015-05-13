package wangheng.leetcode;

import java.util.ArrayList;
import java.util.List;

public class CombinationsSolution {
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (n < k) return result;
        dfs(result, new ArrayList<Integer>(), 1, n, k);
        return result;
    }
    
    private void dfs(List<List<Integer>> result, List<Integer> list, int i, int n, int k) {
        if (list.size() == k) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        
        if (i > n) return;
        
        list.add(i);
        dfs(result, list, i+1, n, k);
        list.remove(list.size()-1);
        dfs(result, list, i+1, n, k);
    }

    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (k == 0)
            return res;

        if (n == k) {
            if (n == 0)
                return res;

            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 1; i <= n; i++) {
                list.add(i);
            }
            res.add(list);
            return res;
        } else if (n > k) {
            ArrayList<ArrayList<Integer>> subRes = combine(n - 1, k);
            res.addAll(subRes);

            ArrayList<ArrayList<Integer>> subRes2 = combine(n - 1, k - 1);
            if (subRes2.size() == 0) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(n);
                res.add(list);
            } else {
                for (int i = 0; i < subRes2.size(); i++) {
                    ArrayList<Integer> list = subRes2.get(i);
                    list.add(n);
                    res.add(list);
                }
            }

            return res;
        } else {
            return res;
        }
    }

}
