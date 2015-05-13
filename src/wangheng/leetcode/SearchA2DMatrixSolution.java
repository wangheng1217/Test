package wangheng.leetcode;

public class SearchA2DMatrixSolution {
    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int l = 0, r = m*n-1;
        while (l <= r) {
            if (get(matrix, l) > target || get(matrix, r) < target) return false;
            int mid = (l+r)/2;
            int midValue = get(matrix, mid);
            if (midValue == target) return true;
            else if (midValue > target) r = mid-1;
            else l = mid+1;
        }
        return false;
    }
    
    private int get(int[][] matrix, int i) {
        return matrix[i/matrix[0].length][i%matrix[0].length];
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0)
            return false;
        if (matrix[0].length == 0)
            return false;

        int row;
        int rowStart = 0, rowEnd = matrix.length - 1;
        while (rowEnd - rowStart > 1) {
            int rowMid = (rowStart + rowEnd) / 2;
            if (matrix[rowMid][0] == target) {
                return true;
            } else if (matrix[rowMid][0] > target) {
                rowEnd = rowMid - 1;
            } else {
                rowStart = rowMid;
            }
        }

        if (rowEnd - rowStart == 1) {
            if (matrix[rowStart][0] == target || matrix[rowEnd][0] == target)
                return true;
            if (matrix[rowStart][0] > target)
                return false;
            if (matrix[rowEnd][0] < target) {
                row = rowEnd;
            } else {
                row = rowStart;
            }
        } else {
            if (matrix[rowStart][0] == target)
                return true;
            if (matrix[rowStart][0] > target)
                return false;
            row = rowStart;
        }

        int colStart = 0, colEnd = matrix[0].length - 1;

        while (colEnd >= colStart) {
            int colMid = (colStart + colEnd) / 2;
            if (matrix[row][colMid] == target)
                return true;
            if (matrix[row][colMid] < target) {
                colStart = colMid + 1;
            } else {
                colEnd = colMid - 1;
            }
        }

        return false;
    }

}
