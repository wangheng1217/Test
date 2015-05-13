package wangheng.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

public class CombinationSumSolution {

    public static void main(String[] args) {
        int[] candidates = { 2, 3, 6, 7 };
        int target = 7;
        ArrayList<ArrayList<Integer>> res = new CombinationSumSolution()
                .combinationSum(candidates, target);
        for (int i = 0; i < res.size(); i++) {
            ArrayList<Integer> list = res.get(i);
            for (int j = 0; j < list.size(); j++) {
                System.out.print(list.get(j) + " ");
            }
            System.out.println();
        }
    }
    
    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        // Start typing your Java solution below
        // DO NOT write main() function
        Arrays.sort(candidates);
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        dfs(candidates, target, 0, new ArrayList<Integer>(), res);
        return res;
    }
    
    private void dfs(int[] candidates, int target, int startIndex, ArrayList<Integer> intList, ArrayList<ArrayList<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<Integer>(intList));
        }
        
        for (int i = startIndex; i < candidates.length; i++) {
            if (candidates[i] <= target) {
                intList.add(candidates[i]);
                dfs(candidates, target-candidates[i], i, intList, res);
                intList.remove(intList.size()-1);
            }
            else {
                return;
            }
        }
    }

    public ArrayList<ArrayList<Integer>> combinationSum2(int[] candidates,
            int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (candidates.length == 0 || target <= 0)
            return res;

        java.util.Arrays.sort(candidates);

        res = combinationSum(candidates, 0, target);

        return res;
    }

    private ArrayList<ArrayList<Integer>> combinationSum(int[] candidates,
            int minIndex, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

        for (int i = minIndex; i < candidates.length && candidates[i] <= target; i++) {
            if (candidates[i] <= target / 2) {
                ArrayList<ArrayList<Integer>> subRes = combinationSum(
                        candidates, i, target - candidates[i]);

                if (subRes.size() > 0) {
                    for (int j = 0; j < subRes.size(); j++) {
                        ArrayList<Integer> list = new ArrayList<Integer>();
                        list.add(candidates[i]);
                        list.addAll(subRes.get(j));
                        res.add(list);
                    }
                }
            } else if (candidates[i] == target) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(target);
                res.add(list);
            }
        }

        return res;
    }

}
