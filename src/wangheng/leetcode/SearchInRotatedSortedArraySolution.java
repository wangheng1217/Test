package wangheng.leetcode;

public class SearchInRotatedSortedArraySolution {
    public int search3(int[] A, int target) {
        int l = 0, r = A.length-1;
        while (l <= r) {
            int mid = (l+r)/2;
            if (A[mid] == target) return mid;
            else if(A[mid] > target) {
                if (A[l] <= A[mid] && target < A[l]) l = mid+1;
                else r = mid-1;
            } else {
                if (A[mid] <= A[r] && target > A[r]) r = mid-1;
                else l = mid+1;
            }
        }
        return -1;
    }

    public int search(int[] A, int target) {
        int begin = 0, end = A.length - 1;
        while (begin <= end) {
            int mid = begin + (end - begin) / 2;
            if (target == A[mid])
                return mid;
            if (A[begin] < A[mid]) { // left part is sorted
                if (A[begin] <= target && target < A[mid]) {
                    end = mid - 1;
                } else {
                    begin = mid + 1;
                }
            } else if (A[begin] > A[mid]) { // right part is sorted
                if (A[mid] < target && target <= A[end]) {
                    begin = mid + 1;
                } else {
                    end = mid - 1;
                }
            } else { // begin == mid
                begin = begin + 1;
            }
        }
        return -1;
    }

    public int search2(int[] A, int target) {
        int begin = 0, end = A.length - 1;
        while (begin <= end) {
            if (target == A[begin])
                return begin;
            if (target == A[end])
                return end;
            int mid = (begin + end) / 2;
            if (target == A[mid])
                return mid;
            if (A[begin] < A[end]) {
                if (target < A[mid]) {
                    begin = begin + 1;
                    end = mid - 1;
                } else {
                    begin = mid + 1;
                    end = end - 1;
                }
            } else {
                if (target < A[mid]) {
                    if (target > A[begin]) {
                        begin = begin + 1;
                        end = mid - 1;
                    } else {
                        if (A[begin] <= A[mid]) {
                            begin = mid + 1;
                            end = end - 1;
                        } else {
                            begin = begin + 1;
                            end = mid - 1;
                        }
                    }
                } else {
                    if (target < A[end]) {
                        begin = mid + 1;
                        end = end - 1;
                    } else {
                        if (A[end] >= A[mid]) {
                            begin = begin + 1;
                            end = mid - 1;
                        } else {
                            begin = mid + 1;
                            end = end - 1;
                        }
                    }
                }
            }
        }
        return -1;
    }

}
