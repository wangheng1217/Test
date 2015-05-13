package wangheng.leetcode;

public class SearchInsertPositionSolution {
    
    public int searchInsert2(int[] A, int target) {
        int start = 0;
        int end = A.length-1;
        while (start <= end) {
            int mid = (start+end)/2;
            if (A[mid] == target) return mid;
            else if (A[mid] < target) {
                start = mid+1;
            } else {
                end = mid-1;
            }
        }
        return start;
    }

    public int searchInsert(int[] A, int target) {
        if (A.length == 0)
            return 0;
        return searchInsertWithoutRecursion(A, 0, A.length - 1, target);
    }

    private int searchInsert(int[] A, int begin, int end, int target) {
        if (target <= A[begin])
            return begin;
        if (target == A[end])
            return end;
        if (target > A[end])
            return end + 1;
        int medianIndex = (begin + end) / 2;
        if (target == A[medianIndex])
            return medianIndex;
        else if (target < A[medianIndex]) {
            return searchInsert(A, begin, medianIndex - 1, target);
        } else {
            return searchInsert(A, medianIndex + 1, end, target);
        }
    }

    private int searchInsertWithoutRecursion(int[] A, int begin, int end,
            int target) {
        while (begin <= end) {
            if (target <= A[begin])
                return begin;
            if (target == A[end])
                return end;
            if (target > A[end])
                return end + 1;
            int medianIndex = (begin + end) / 2;
            if (target == A[medianIndex])
                return medianIndex;
            else if (target < A[medianIndex]) {
                end = medianIndex - 1;
            } else {
                begin = medianIndex + 1;
            }
        }
        return -1;
    }

}
