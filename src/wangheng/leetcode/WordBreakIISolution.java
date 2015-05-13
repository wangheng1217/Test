package wangheng.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WordBreakIISolution {

    // DP
    public List<String> wordBreak(String s, Set<String> dict) {
        if (s == null || s.length() == 0)
            return new ArrayList<String>();

        int length = s.length();
        List<List<String>>[] dp = new List[length];
        for (int i = length - 1; i >= 0; i--) {
            List<List<String>> solutions = new ArrayList<List<String>>();
            List<String> solution = null;

            if (dict.contains(s.substring(i, length))) {
                solution = new ArrayList<String>();
                solution.add(s.substring(i, length));
                solutions.add(solution);
            }

            for (int j = i + 1; j < length; j++) {
                if (dp[j].size() > 0 && dict.contains(s.substring(i, j))) {
                    for (List<String> jSolution : dp[j]) {
                        solution = new ArrayList<String>(jSolution);
                        solution.add(0, s.substring(i, j));
                        solutions.add(solution);
                    }
                }
            }

            dp[i] = solutions;
        }

        List<String> result = new ArrayList<String>();
        for (List<String> solution : dp[0]) {
            result.add(toString(solution));
        }
        return result;
    }

    private String toString(List<String> solution) {
        String s = "";
        for (String word : solution) {
            if (s.length() > 0) {
                s = s + " ";
            }
            s = s + word;
        }
        return s;
    }

    // DFS, Time Limit Exceeded
    public List<String> wordBreak2(String s, Set<String> dict) {
        List<String> solutions = new ArrayList<String>();
        if (s == null || s.length() == 0)
            return solutions;
        List<String> solution = new ArrayList<String>();
        dfs(s, dict, 0, solution, solutions);
        return solutions;
    }

    private void dfs(String s, Set<String> dict, int p, List<String> solution, List<String> solutions) {
        if (p == s.length()) {
            solutions.add(toString(solution));
            return;
        }

        for (int i = p; i < s.length(); i++) {
            if (dict.contains(s.substring(p, i + 1))) {
                solution.add(s.substring(p, i + 1));
                dfs(s, dict, i + 1, solution, solutions);
                solution.remove(solution.size() - 1);
            }
        }
    }

}
