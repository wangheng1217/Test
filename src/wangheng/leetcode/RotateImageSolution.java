package wangheng.leetcode;

public class RotateImageSolution {
    
    public void rotateInPlace2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n/2; i++) {
            for (int j = i; j < n-1-i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = temp;
            }
        }
    }
    
    public void rotateInPlace(int[][] matrix) {
        int n = matrix.length;
        for (int depth = 0; depth < n-depth-1; depth++) {
            for (int i = 0; i < n-2*depth-1; i++) {
                int temp = matrix[depth][depth+i];
                matrix[depth][depth+i] = matrix[n-1-(depth+i)][depth];
                matrix[n-1-(depth+i)][depth] = matrix[n-1-depth][n-1-(depth+i)];
                matrix[n-1-depth][n-1-(depth+i)] = matrix[depth+i][n-1-depth];
                matrix[depth+i][n-1-depth] = temp;
            }
        }        
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] matrix2 = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = j;
                int y = n - i - 1;
                matrix2[x][y] = matrix[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = matrix2[i][j];
            }
        }
    }

}
