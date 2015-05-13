package wangheng.leetcode;

public class SearchForARangeSolution {
    
    public int[] searchRange2(int[] A, int target) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        int i = 0, j = A.length-1;
        while (i <= j) {
            if (A[i] > target || A[j] < target) {
                break;
            }
            
            int mid = (i+j)/2;
            if (A[mid] > target) {
                j = mid-1;
            } else if (A[mid] < target) {
                i = mid+1;
            } else {
                int k = mid;
                while (i <= k) {
                    int m = (i+k)/2;
                    if (A[m] == target) {
                        k = m-1;
                    } else {
                        // A[m] < target
                        i = m+1;
                    }
                }
                result[0] = i;
                
                k = mid;
                while (k <= j) {
                    int m = (k+j)/2;
                    if (A[m] == target) {
                        k = m+1;
                    } else {
                        // A[m] > target
                        j = m-1;
                    }
                }
                result[1] = j;
                
                break;
            }
        }
        return result;
    }


    public int[] searchRange(int[] A, int target) {
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;

        int start = 0, end = A.length - 1;

        // search for lower bound
        while (start < end) {
            int mid = (start + end) / 2;
            if (A[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        if (A[start] != target) {
            return res;
        }

        res[0] = start;

        end = A.length;

        // search for upper bound
        while (start < end) {
            int mid = (start + end) / 2;
            if (A[mid] > target)
                end = mid;
            else {
                // A[mid] == target
                start = mid + 1;
            }
        }

        res[1] = end - 1;

        return res;
    }

    public int[] searchRangeRecursion(int[] A, int target) {
        return searchRange(A, 0, A.length - 1, target);
    }

    private int[] searchRange(int[] A, int start, int end, int target) {
        int[] res = new int[2];
        if (A[start] > target || A[end] < target) {
            res[0] = -1;
            res[1] = -1;
            return res;
        }

        if (A[start] == target && A[end] == target) {
            res[0] = start;
            res[1] = end;
            return res;
        }

        if (end - start <= 1) {
            if (A[start] == target) {
                res[0] = start;
                res[1] = (A[end] == target ? end : start);
            } else {
                if (A[end] == target) {
                    res[0] = end;
                    res[1] = end;
                } else {
                    res[0] = -1;
                    res[1] = -1;
                }
            }
            return res;
        }

        int mid = (start + end) / 2;

        int[] res1 = searchRange(A, start, mid, target);
        int[] res2 = searchRange(A, mid + 1, end, target);

        if (res1[0] == -1) {
            return res2;
        } else {
            res[0] = res1[0];
            res[1] = (res2[0] == -1) ? res1[1] : res2[1];
            return res;
        }
    }

}
