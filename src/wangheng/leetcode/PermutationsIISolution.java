package wangheng.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

// with duplicates
public class PermutationsIISolution {

    public static void main(String[] args) {
        PermutationsIISolution solution = new PermutationsIISolution();

        int[] num = { -1, 2, 0, -1, 1, 0, 1 };

        ArrayList<ArrayList<Integer>> res = solution.permuteUnique(num);

        for (int i = 0; i < res.size(); i++) {
            ArrayList<Integer> list = res.get(i);
            for (int j = 0; j < list.size(); j++) {
                System.out.print(list.get(j) + " ");
            }
            System.out.println();
        }
    }

    public ArrayList<ArrayList<Integer>> permuteUnique2(int[] num) {
        // Start typing your Java solution below
        // DO NOT write main() function
        Arrays.sort(num);
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        boolean[] visited = new boolean[num.length];
        dfs(num, new ArrayList<Integer>(), res, visited);
        return res;
    }
    
    private void dfs(int[] num, ArrayList<Integer> intList, ArrayList<ArrayList<Integer>> res, boolean[] visited) {
        if (intList.size() == num.length) {
            res.add(new ArrayList<Integer>(intList));
            return;
        }
        
        for (int i = 0; i < num.length; i++) {
            if (!visited[i]) {
                if (i > 0 && !visited[i-1] && num[i]==num[i-1]) continue;
                intList.add(num[i]);
                visited[i] = true;
                dfs(num, intList, res, visited);
                intList.remove(intList.size()-1);
                visited[i] = false;
            }
        }
    }

    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        // sort the array
        Arrays.sort(num);
        return permuteUnique(num, 0);
    }

    private ArrayList<ArrayList<Integer>> permuteUnique(int[] num,
            int beginIndex) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

        for (int i = beginIndex; i < num.length; i++) {
            if (i != beginIndex) {
                if (num[beginIndex] == num[i] || num[i] == num[i - 1]) {
                    continue;
                }
            }

            // move the element num[i] to num[beginIndex], and keep the subarray sorted
            if (i != beginIndex) {
                int temp = num[i];
                for (int j = i; j > beginIndex; j--) {
                    num[j] = num[j - 1];
                }
                num[beginIndex] = temp;
            }

            ArrayList<ArrayList<Integer>> subRes = permuteUnique(num,
                    beginIndex + 1);

            if (subRes.size() == 0) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(num[beginIndex]);
                res.add(list);
            }

            for (int j = 0; j < subRes.size(); j++) {
                ArrayList<Integer> list = new ArrayList<Integer>();
                list.add(num[beginIndex]);
                list.addAll(subRes.get(j));
                res.add(list);
            }

            // move the num[beginIndex] back to num[i]
            if (i != beginIndex) {
                int temp = num[beginIndex];
                for (int j = beginIndex; j < i; j++) {
                    num[j] = num[j + 1];
                }
                num[i] = temp;
            }
        }

        return res;
    }
}
