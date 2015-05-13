package wangheng.leetcode;

import java.util.Stack;

public class SimplifyPathSolution {

    public static void main(String[] args) {
        SimplifyPathSolution solution = new SimplifyPathSolution();
        System.out.println(solution.simplifyPath("/home//foo/"));
    }
    
    public String simplifyPath2(String path) {
        String[] p = path.split("/");
        Stack<String> stack = new Stack<String>();
        for (int i = 0; i < p.length; i++) {
            if ("".equals(p[i]) || ".".equals(p[i])) continue;
            if ("..".equals(p[i])) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(p[i]);
            }
        }
        if (stack.isEmpty()) return "/";
        else {
            String res = "";
            while (!stack.isEmpty()) {
                res = "/" + stack.pop() + res;
            }
            return res;
        }
    }

    public String simplifyPath(String path) {
        path = path.trim();
        if (path.equals("/"))
            return path;

        String[] ss = path.split("/");

        java.util.List<String> res = new java.util.ArrayList<String>();

        for (int i = 1; i < ss.length; i++) {
            // "/home//foo/"
            if (ss[i].equals(""))
                continue;

            if (ss[i].equals("..")) {
                // "/../"
                if (res.size() == 0)
                    continue;
                res.remove(res.size() - 1);
            } else if (ss[i].equals(".") == false) {
                res.add(ss[i]);
            }
        }

        StringBuffer pathSb = new StringBuffer();
        pathSb.append("/");
        for (int i = 0; i < res.size(); i++) {
            pathSb.append(res.get(i));
            if (i != res.size() - 1)
                pathSb.append("/");
        }

        return pathSb.toString();
    }

}
