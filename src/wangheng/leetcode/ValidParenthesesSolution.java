package wangheng.leetcode;

import java.util.Stack;

public class ValidParenthesesSolution {
    
    public boolean isValid(String s) {
        // Start typing your Java solution below
        // DO NOT write main() function
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (isOpen(c)) {
                stack.push(c);
            }
            else {
                if (stack.isEmpty() || !isValid(stack.pop(), c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    
    private boolean isOpen(char c) {
        return c == '(' || c == '[' || c == '{';
    }
    
    private boolean isValid(char open, char close) {
        return (open == '(' && close == ')')
                || (open == '[' && close == ']')
                || (open == '{' && close == '}');
    }

    // stack, LIFO
    public boolean isValid3(String s) {
        java.util.Stack<Character> stack = new java.util.Stack<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.empty())
                    return false;
                char cExpected;
                if (c == ')')
                    cExpected = '(';
                else if (c == ']')
                    cExpected = '[';
                else
                    cExpected = '{';
                if (stack.pop() != cExpected)
                    return false;
            }
        }
        return stack.empty();
    }

    public boolean isValid2(String s) {
        java.util.ArrayList<Character> list = new java.util.ArrayList<Character>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                list.add(c);
            } else {
                if (list.size() == 0)
                    return false;
                char c0;
                if (c == ')')
                    c0 = '(';
                else if (c == ']')
                    c0 = '[';
                else
                    c0 = '{';
                if (list.get(list.size() - 1).charValue() != c0)
                    return false;
                else
                    list.remove(list.size() - 1);
            }
        }

        if (list.size() == 0)
            return true;
        else
            return false;
    }

}
