package wangheng.leetcode;

//Your algorithm should run in O(n) time and uses constant space.
public class FirstMissingPositiveSolution {

    //put 1 to A[0], 2 to A[1] ...
    public int firstMissingPositive(int[] A) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0 && A[i] <= A.length && A[i] != i + 1) {
                if (A[A[i] - 1] != A[i]) {
                    int temp = A[A[i] - 1];
                    A[A[i] - 1] = A[i];
                    A[i] = temp;
                    i--;
                }
            }
        }

        for (int i = 0; i < A.length; i++) {
            if (A[i] != i + 1)
                return i + 1;
        }
        return A.length + 1;
    }

}
