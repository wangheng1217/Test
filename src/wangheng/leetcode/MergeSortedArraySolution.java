package wangheng.leetcode;

public class MergeSortedArraySolution {
    public void merge2(int A[], int m, int B[], int n) {
        int i = m-1;
        int j = n-1;
        int k = m+n-1;
        while (i >= 0 && j >= 0) {
            if (A[i] < B[j]) {
                A[k] = B[j];
                j--;
            } else {
                A[k] = A[i];
                i--;
            }
            k--;
        }
        
        if (i < 0) {
            while (k >= 0) {
                A[k] = B[k];
                k--;
            }
        }
    }

    public void merge(int A[], int m, int B[], int n) {
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (A[i] <= B[j]) {
                i++;
            } else {
                for (int k = m; k > i; k--) {
                    A[k] = A[k - 1];
                }
                A[i] = B[j];
                i++;
                m++;
                j++;
            }
        }
        if (i == m) {
            for (int k = 0; j + k < n; k++) {
                A[m + k] = B[j + k];
            }
        }
    }

}
