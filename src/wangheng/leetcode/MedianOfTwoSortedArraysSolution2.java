package wangheng.leetcode;

public class MedianOfTwoSortedArraysSolution2 {

    int n;
    int leftLength;
    int rightLength;

    public double findMedianSortedArrays(int[] A, int[] B) {
        n = A.length + B.length;
        if (n % 2 == 0) {
            leftLength = n / 2 - 1;
            rightLength = n / 2;
        } else {
            leftLength = n / 2;
            rightLength = n / 2;
        }

        int medianIndex = findMedianIndexSortedArrays(A, 0, A.length - 1, B);
        if (medianIndex != -1) {
            return findMedian(A, B, medianIndex);
        } else {
            medianIndex = findMedianIndexSortedArrays(B, 0, B.length - 1, A);
            return findMedian(B, A, medianIndex);
        }
    }

    private int findMedianIndexSortedArrays(int[] A, int aBegin, int aEnd,
            int[] B) {
        if (aBegin > aEnd)
            return -1;
        int medianIndexA = (aBegin + aEnd) / 2;
        int leftLengthB = leftLength - medianIndexA;
        if (leftLengthB < 0)
            return findMedianIndexSortedArrays(A, aBegin, medianIndexA - 1, B);
        if (leftLengthB > B.length)
            return findMedianIndexSortedArrays(A, medianIndexA + 1, aEnd, B);
        if (leftLengthB == 0 || B[leftLengthB - 1] <= A[medianIndexA]) {
            if (leftLengthB == B.length || A[medianIndexA] <= B[leftLengthB])
                return medianIndexA;
            else {
                return findMedianIndexSortedArrays(A, aBegin, medianIndexA - 1,
                        B);
            }
        } else {
            return findMedianIndexSortedArrays(A, medianIndexA + 1, aEnd, B);
        }

    }

    private double findMedian(int[] A, int[] B, int medianIndex) {
        if (n % 2 == 1) {
            return A[medianIndex];
        } else {
            int leftValue = A[medianIndex];
            int rightValueAIndex = medianIndex + 1;
            int rightValueBIndex = leftLength - medianIndex;
            int rightValue;
            if (rightValueAIndex > A.length - 1) {
                rightValue = B[rightValueBIndex];
            } else if (rightValueBIndex > B.length - 1) {
                rightValue = A[rightValueAIndex];
            } else {
                rightValue = min(A[rightValueAIndex], B[rightValueBIndex]);
            }
            return (leftValue + rightValue) / 2d;
        }
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }

}
