package wangheng.leetcode;

public class SearchInRotatedSortedArrayIISolution {
    public boolean search3(int[] A, int target) {
        int l = 0, r = A.length-1;
        while (l <= r) {
            int mid = (l+r)/2;
            if (A[mid] == target) return true;
            
            if (A[l] == A[mid] && A[mid] == A[r]) {
                l++;
                r--;
            } else {
                if (A[mid] > target) {
                    if (A[l] <= A[mid] && target < A[l]) l = mid+1;
                    else r = mid-1;
                } else {
                    if (A[mid] <= A[r] && A[r] < target) r = mid-1;
                    else l = mid+1;
                }
            }
        }
        return false;
    }

    public boolean search(int[] A, int target) {
        int begin = 0, end = A.length - 1;
        while (begin <= end) {
            int mid = begin + (end - begin) / 2;
            if (target == A[mid])
                return true;
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
            } else { // A[begin] == A[mid]
                // begin = begin + 1;
                if (A[end] == A[mid]) {
                    begin = begin + 1;
                    end = end - 1;
                } else if (A[end] > A[mid]) {
                    if (A[mid] < target && target <= A[end]) {
                        begin = mid + 1;
                    } else {
                        return false;
                    }
                } else {
                    begin = mid + 1;
                }
            }
        }
        return false;
    }

    public boolean search2(int[] A, int target) {
        return search(A, target, 0, A.length - 1);
    }

    private boolean search(int[] A, int target, int start, int end) {
        if (start > end || start < 0 || end >= A.length)
            return false;
        if (A[start] == target || A[end] == target)
            return true;
        int mid = (start + end) / 2;
        if (A[mid] == target)
            return true;

        if (A[start] == A[end]) {
            if (A[mid] == A[start]) {
                boolean exist = search(A, target, start + 1, mid - 1);
                if (exist)
                    return true;
                exist = search(A, target, mid + 1, end - 1);
                if (exist)
                    return true;
                else
                    return false;
            } else if (A[mid] < A[start]) {
                if (target < A[mid]) {
                    return search(A, target, start + 1, mid - 1);
                } else {
                    if (target < A[start]) {
                        return search(A, target, mid + 1, end - 1);
                    } else {
                        return search(A, target, start + 1, mid - 1);
                    }
                }
            } else {
                if (target > A[mid]) {
                    return search(A, target, mid + 1, end - 1);
                } else {
                    if (target > A[start]) {
                        return search(A, target, start + 1, mid - 1);
                    } else {
                        return search(A, target, mid + 1, end - 1);
                    }
                }
            }
        } else if (A[start] < A[end]) {
            if (target < A[mid]) {
                return search(A, target, start + 1, mid - 1);
            } else {
                return search(A, target, mid + 1, end - 1);
            }
        } else {
            if (target < A[mid]) {
                if (target > A[start]) {
                    return search(A, target, start + 1, mid - 1);
                } else {
                    if (A[start] <= A[mid]) {
                        return search(A, target, mid + 1, end - 1);
                    } else {
                        return search(A, target, start + 1, mid - 1);
                    }
                }
            } else {
                if (target < A[end]) {
                    return search(A, target, mid + 1, end - 1);
                } else {
                    if (A[end] >= A[mid]) {
                        return search(A, target, start + 1, mid - 1);
                    } else {
                        return search(A, target, mid + 1, end - 1);
                    }
                }
            }
        }
    }

}
