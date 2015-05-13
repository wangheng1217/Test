package wangheng.leetcode;

public class NQueensIISolution {

    int count = 0;

    public static void main(String[] args) {
        System.out.println(new NQueensIISolution().totalNQueens(2));
    }

    public int totalNQueens(int n) {
        if (n <= 0) {
            return 0;
        }

        Integer[] sol = new Integer[n];
        dfs(sol, 1, n);

        int res = count;
        count = 0;
        return res;
    }

    private void dfs(Integer[] sol, int depth, int n) {
        if (depth > n) {
            if (isValid(sol, depth - 2)) {
                count++;
            }
        } else {
            if (isValid(sol, depth - 2)) {
                for (int i = 0; i < n; i++) {
                    sol[depth - 1] = i;
                    dfs(sol, depth + 1, n);
                }
            }
        }
    }

    private boolean isValid(Integer[] sol, int pos) {
        for (int i = 0; i < pos; i++) {
            if (sol[pos] == sol[i])
                return false;
            if ((sol[pos] - sol[i]) == (pos - i))
                return false;
            if ((sol[pos] - sol[i]) == (i - pos))
                return false;
        }
        return true;
    }

}
