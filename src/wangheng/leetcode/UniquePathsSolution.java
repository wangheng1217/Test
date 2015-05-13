package wangheng.leetcode;

public class UniquePathsSolution {
    
    public static void main(String[] args) {
        UniquePathsSolution solution = new UniquePathsSolution();
        System.out.println(solution.uniquePathsDP(10, 10));
    }
    
    public int uniquePaths2(int m, int n) {
        int[] map = new int[n];
        map[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                map[j] = map[j-1] + map[j];
            }
        }
        return map[n-1];
    }

    public int uniquePathsDP(int m, int n) {
        int[][] map = new int[m][n];
        for(int i = 0; i < n; i++) {
            map[0][i] = 1;
        }
        for(int i = 0; i < m; i++) {
            map[i][0] = 1;
        }
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                map[i][j] = map[i-1][j] + map[i][j-1];
            }
        }
        return map[m-1][n-1];
    }
    
    public int uniquePathsRecursion(int m, int n) {
        if (m == 1 || n == 1) return 1;
        
        return uniquePathsRecursion(m-1, n) + uniquePathsRecursion(m, n-1);
    }

    // m >= n
    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1)
            return 1;
        
        if(m < n) return uniquePaths(n, m);
        
        // a/b
        long a = 1;
        long b = 1;
        for(int i = 0; i < n-1; i++) {
            a = a*(m+n-2-i);
            b = b*(i+1);
        }
        
        return (int)(a/b);

        /*
        // C (m+n-2) (m-1)
        // x!/(y!z!)
        int x = m + n - 2;
        int y = m - 1;
        int z = n - 1;

        int X = 1;
        int Y = 1;
        int Z = 1;

        int factorial = 1;
        for (int i = 1; i <= x; i++) {
            factorial = factorial * i;
            if (i == x) {
                X = factorial;
            }
            if (i == y) {
                Y = factorial;
            }
            if (i == z) {
                Z = factorial;
            }
        }
        return X / (Y * Z);
        */
    }

}
