package wangheng.leetcode;

public class SortColorsSolution {
    
    public void sortColors3(int[] A) {
        int pos1 = 0, pos2 = A.length - 1;
        int i = 0;
        while (i <= pos2) {
            if (A[i] == 0) {
                swap(A, pos1, i);
                pos1++;
                i++;
            } else if (A[i] == 1) {
                i++;
            } else {
                swap(A, pos2, i);
                pos2--;
            }
        }
    }

    // one-pass, one iteration
    public void sortColors(int[] A) {
        int pos1 = 0, pos2 = 0;

        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                if (pos1 == pos2) {
                    swap(A, pos1, i);
                } else {
                    swap(A, pos1, i);
                    swap(A, pos2, i);
                }
                pos1++;
                pos2++;
            } else if (A[i] == 1) {
                swap(A, pos2, i);

                pos2++;
            } else {
                continue;
            }
        }
    }

    private void swap(int[] A, int i, int j) {
        if (i == j)
            return;
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    // two-pass, two iteration
    public void sortColors2(int[] A) {
        int count0 = 0, count1 = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                count0++;
            } else if (A[i] == 1) {
                count1++;
            }
        }

        for (int i = 0; i < A.length; i++) {
            if (count0 > 0) {
                A[i] = 0;
                count0--;
            } else if (count1 > 0) {
                A[i] = 1;
                count1--;
            } else {
                A[i] = 2;
            }
        }
    }

}
