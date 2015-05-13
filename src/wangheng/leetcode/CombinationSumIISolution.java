package wangheng.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

public class CombinationSumIISolution {

    public static void main(String[] args) {
        int[] candidates = { 10, 1, 2, 7, 6, 1, 5 };
        int target = 8;
        ArrayList<ArrayList<Integer>> res = new CombinationSumIISolution()
                .combinationSum2(candidates, target);
        for (int i = 0; i < res.size(); i++) {
            ArrayList<Integer> list = res.get(i);
            for (int j = 0; j < list.size(); j++) {
                System.out.print(list.get(j) + " ");
            }
            System.out.println();
        }

    }

    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (num.length == 0)
            return res;

        Arrays.sort(num);

        res = combinationSum2(num, 0, target);

        return res;
    }

    private ArrayList<ArrayList<Integer>> combinationSum2(int[] num,
            int minIndex, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

        for (int i = minIndex; i < num.length && num[i] <= target; i++) {
            if (i > minIndex && num[i - 1] == num[i])
                continue;

            if (num[i] <= target / 2) {
                ArrayList<ArrayList<Integer>> subRes = combinationSum2(num,
                        i + 1, target - num[i]);
                if (subRes.size() > 0) {
                    for (int j = 0; j < subRes.size(); j++) {
                        ArrayList<Integer> list = new ArrayList<Integer>();
                        list.add(num[i]);
                        list.addAll(subRes.get(j));
                        res.add(list);
                    }
                }
            } else if (num[i] == target) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(target);
                res.add(list);
            }
        }

        return res;
    }

}
