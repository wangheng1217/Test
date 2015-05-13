package wangheng.leetcode;

import java.util.Stack;

public class LongestValidParenthesesSolution {
    public int longestValidParentheses2(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty() || s.charAt(stack.peek()) == ')') {
                    stack.push(i);
                } else {
                    stack.pop();
                    if (stack.isEmpty()) {
                        result = max(result, i+1);
                    } else {
                        result = max(result, i-stack.peek());
                    }
                }
            }
        }
        return result;
    }
    
    private int max(int a, int b) {
        return a > b ? a : b;
    }

    public static void main(String[] args) {
        LongestValidParenthesesSolution solution = new LongestValidParenthesesSolution();
        String s = ")()())";
        System.out.println(solution.longestValidParentheses(s));
    }

    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int[] dp = new int[s.length()];
        int maxLength = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ')') {
                dp[i] = 0;
            } else {
                if (i + 1 < s.length() && i + dp[i + 1] + 1 < s.length()
                        && s.charAt(i + dp[i + 1] + 1) == ')') {
                    dp[i] = dp[i + 1] + 2;
                    if (i + dp[i + 1] + 2 < s.length()) {
                        dp[i] = dp[i] + dp[i + dp[i + 1] + 2];
                    }
                }
            }
            if (dp[i] > maxLength)
                maxLength = dp[i];
        }
        return maxLength;
    }

}
