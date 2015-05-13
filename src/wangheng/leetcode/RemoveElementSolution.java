package wangheng.leetcode;

public class RemoveElementSolution {

    public int removeElement(int[] A, int elem) {
        int i = 0, j = A.length - 1;
        while (i <= j) {
            if (A[i] == elem) {
                swap(A, i, j);
                j--;
            } else {
                i++;
            }
        }
        return i;
    }

    public void swap(int[] A, int a, int b) {
        if (a == b)
            return;
        int temp = A[a];
        A[a] = A[b];
        A[b] = temp;
    }
}
