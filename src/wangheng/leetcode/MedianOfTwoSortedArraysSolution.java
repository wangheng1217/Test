package wangheng.leetcode;

public class MedianOfTwoSortedArraysSolution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        return findMedianSortedArrays(A, 0, A.length - 1, B, 0, B.length - 1);
    }

    private double findMedianSortedArrays(int[] A, int aBegin, int aEnd,
            int[] B, int bBegin, int bEnd) {
        if (aBegin > aEnd) {
            return mid(B, bBegin, bEnd);
        }
        if (bBegin > bEnd) {
            return mid(A, aBegin, aEnd);
        }
        if (aEnd - aBegin <= 1) {
            if (bEnd - bBegin <= 1) {
                int[] C = merge(B, bBegin, bEnd, A, aBegin, aEnd);
                return mid(C, 0, C.length - 1);
            } else {
                int[] C;
                if ((bBegin + bEnd) % 2 == 0) {
                    C = new int[3];
                    C[0] = B[(bBegin + bEnd) / 2 - 1];
                    C[1] = B[(bBegin + bEnd) / 2];
                    C[2] = B[(bBegin + bEnd) / 2 + 1];
                } else {
                    C = new int[4];
                    C[0] = B[(bBegin + bEnd) / 2 - 1];
                    C[1] = B[(bBegin + bEnd) / 2];
                    C[2] = B[(bBegin + bEnd) / 2 + 1];
                    C[3] = B[(bBegin + bEnd) / 2 + 2];
                }
                int[] D = merge(C, 0, C.length - 1, A, aBegin, aEnd);
                return mid(D, 0, D.length - 1);
            }
        } else {
            if (bEnd - bBegin <= 1) {
                return findMedianSortedArrays(B, bBegin, bEnd, A, aBegin, aEnd);
            }
        }

        double aMid = mid(A, aBegin, aEnd);
        double bMid = mid(B, bBegin, bEnd);

        if (aMid == bMid) {
            return aMid;
        } else if (aMid < bMid) {
            int aLeftLength = (aBegin + aEnd) / 2 - aBegin;
            int bRightLength;
            if ((bBegin + bEnd) % 2 == 0) {
                bRightLength = bEnd - (bBegin + bEnd) / 2;
            } else {
                bRightLength = bEnd - ((bBegin + bEnd) / 2 + 1);
            }
            int minLength = min(aLeftLength, bRightLength);
            return findMedianSortedArrays(A, aBegin + minLength, aEnd, B,
                    bBegin, bEnd - minLength);
        } else {
            return findMedianSortedArrays(B, bBegin, bEnd, A, aBegin, aEnd);
        }
    }

    private double mid(int[] A, int begin, int end) {
        if ((begin + end) % 2 == 0)
            return A[(begin + end) / 2];
        else
            return (A[(begin + end) / 2] + A[(begin + end) / 2 + 1]) / 2d;
    }

    private int[] merge(int[] A, int aBegin, int aEnd, int[] B, int bBegin,
            int bEnd) {
        int aLength = aEnd - aBegin + 1;
        int bLength = bEnd - bBegin + 1;
        int[] C = new int[aLength + bLength];

        int i = aBegin, j = bBegin, k = 0;
        while (i <= aEnd && j <= bEnd) {
            if (A[i] < B[j]) {
                C[k] = A[i];
                i++;
            } else {
                C[k] = B[j];
                j++;
            }
            k++;
        }

        if (i > aEnd) {
            while (j <= bEnd) {
                C[k] = B[j];
                j++;
                k++;
            }
        } else {
            while (i <= aEnd) {
                C[k] = A[i];
                i++;
                k++;
            }
        }
        return C;
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }
}