package wangheng.leetcode;

public class SingleNumberSolution {
    public int singleNumber(int[] A) {
        int xor = 0;
        for (int i = 0; i < A.length; i++) {
            xor = xor ^ A[i];
        }
        return xor;
    }
}
