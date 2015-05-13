package wangheng.leetcode;

import java.util.ArrayList;
import java.util.List;

// no duplicates
// TODO thing about DFS
public class PermutationsSolution {

    public static void main(String[] args) {
        PermutationsSolution solution = new PermutationsSolution();

        int[] num = { 1, 2, 3 };

        ArrayList<ArrayList<Integer>> res = solution.permute(num);

        for (int i = 0; i < res.size(); i++) {
            ArrayList<Integer> list = res.get(i);
            for (int j = 0; j < list.size(); j++) {
                System.out.print(list.get(j) + " ");
            }
            System.out.println();
        }

    }
    
    public List<List<Integer>> permute2(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        boolean[] visited = new boolean[num.length];
        dfs(result, list, num, visited);
        return result;
    }
    
    private void dfs(List<List<Integer>> result, List<Integer> list, int[] num, boolean[] visited) {
        if (list.size() == num.length) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        
        for (int i = 0; i < num.length; i++) {
            if (!visited[i]) {
                list.add(num[i]);
                visited[i] = true;
                dfs(result, list, num, visited);
                list.remove(list.size()-1);
                visited[i] = false;
            }
        }
    }


    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        return permute(num, 0);
    }

    private ArrayList<ArrayList<Integer>> permute(int[] num, int beginIndex) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();

        for (int i = beginIndex; i < num.length; i++) {
            swap(num, beginIndex, i);

            ArrayList<ArrayList<Integer>> subRes = permute(num, beginIndex + 1);

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

            swap(num, beginIndex, i);
        }

        return res;
    }

    private void swap(int[] num, int a, int b) {
        if (a == b)
            return;
        int temp = num[a];
        num[a] = num[b];
        num[b] = temp;
    }

}
