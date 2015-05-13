package wangheng.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubsetsIISolution {
    
    // worse than dfs when all of the numbers are the same
    public List<List<Integer>> subsetsWithDup3(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int n = 1 << num.length;
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<Integer>();
            for (int p = i, c = 0; p > 0; p >>= 1, c++) {
                if ((p & 1) == 1) list.add(num[c]);
                else {
                    while (c+1 < num.length && num[c+1] == num[c]) {
                        if ( ((p>>1) & 1) == 1 ) {
                            list = null;
                            break;
                        } else {
                            p >>= 1;
                            c++;
                        }
                    }
                    
                    if (list == null) break;
                }
            }
            
            if (list != null) result.add(list);
        }
        return result;
    }

    public List<List<Integer>> subsetsWithDup2(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        dfs(num, result, list, 0);
        return result;
    }
    
    private void dfs(int[] num, List<List<Integer>> result, List<Integer> list, int p) {
        if (p == num.length) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        
        list.add(num[p]);
        dfs(num, result, list, p+1);
        list.remove(list.size()-1);
        
        p++;
        while (p < num.length && num[p] == num[p-1]) p++;
        dfs(num, result, list, p);
    }

    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        java.util.Arrays.sort(num);
        return subsetsWithDup(num, 0);
    }

    private ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num,
            int startIndex) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (startIndex == num.length - 1) {
            ArrayList<Integer> list1 = new ArrayList<Integer>();
            res.add(list1);
            ArrayList<Integer> list2 = new ArrayList<Integer>();
            list2.add(num[startIndex]);
            res.add(list2);
            return res;
        }
        if (startIndex >= num.length) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            res.add(list);
            return res;
        }
        if (num[startIndex] == num[startIndex + 1]) {
            ArrayList<ArrayList<Integer>> subres1 = subsetsWithDup(num,
                    startIndex + 1);
            for (int i = 0; i < subres1.size(); i++) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(num[startIndex]);
                list.addAll(subres1.get(i));
                res.add(list);
            }

            int i = startIndex + 1;
            for (; i < num.length && num[i] == num[i - 1]; i++)
                ;
            if (i == num.length) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                res.add(list);
            } else {
                ArrayList<ArrayList<Integer>> subres2 = subsetsWithDup(num, i);
                res.addAll(subres2);
            }
            return res;
        } else {
            ArrayList<ArrayList<Integer>> subres = subsetsWithDup(num,
                    startIndex + 1);
            res.addAll(subres);
            for (int i = 0; i < subres.size(); i++) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(num[startIndex]);
                list.addAll(subres.get(i));
                res.add(list);
            }
            return res;
        }
    }

}
