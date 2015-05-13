package wangheng.nclaptop;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class LongestIncreasingSubsequence {

    @Test
    public void test() {
        LongestIncreasingSubsequence solution = new LongestIncreasingSubsequence();
        assertEquals(0, solution.maxLength(new int[] {}));
        assertEquals(1, solution.maxLength(new int[] { 1 }));
        assertEquals(5, solution.maxLength(new int[] { 1, 2, 3, 4, 5 }));
        assertEquals(1, solution.maxLength(new int[] { 5, 4, 3, 2, 1 }));
        assertEquals(6, solution.maxLength(new int[] { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 }));
        assertEquals(6, solution.maxLength(new int[] { 10, 22, 9, 33, 21, 50, 41, 60, 80 }));
        assertEquals(5, solution.maxLength(new int[] { 4, 5, 6, 8, 1, 2, 3, 5, 9 }));
    }
    
    // O(NlogN)
    // http://www.felix021.com/blog/read.php?1587
    public int maxLength(int[] array) {
        if (array.length == 0) return 0;
        
        // l[i] means, in all of the current increasing subsequences with length i, the minimal value of the last element is l[i]
        // l[0] is not used here
        int[] L = new int[array.length+1];
        int maxLength = 0;
        
        L[1] = array[0];
        maxLength = 1;
        
        // to print the longest increasing subsequence
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        List<Integer> list = new ArrayList<Integer>();
        list.add(array[0]);
        map.put(1, list);
        
        for (int i = 1; i < array.length; i++) {
            if (array[i] > L[maxLength]) {
                list = new ArrayList<Integer>(map.get(maxLength));
                list.add(array[i]);
                map.put(maxLength+1, list);
                
                maxLength++;
                L[maxLength] = array[i];
            } else if (array[i] < L[maxLength]) {
                int l = 1, r = maxLength;
                boolean found = false;
                while (l <= r) {
                    int mid = (l+r)/2;
                    if (array[i] == L[mid]) {
                        found = true;
                        break;
                    }
                    
                    if (array[i] > L[mid]) {
                        l = mid+1;
                    } else {
                        r = mid-1;
                    }
                }
                
                if (!found) {
                    if (l == 1) list = new ArrayList<Integer>();
                    else list = new ArrayList<Integer>(map.get(l-1));
                    list.add(array[i]);
                    map.put(l, list);
                    
                    L[l] = array[i];
                }
            }
        }
        
        System.out.println(map.get(maxLength));
        
        return maxLength;
    }

    // DP, O(n^2)
    public int maxLength2(int[] array) {
        if (array.length == 0)
            return 0;
        int[] maxLength = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            maxLength[i] = 1;
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] < array[j] && maxLength[i] < maxLength[j] + 1) {
                    maxLength[i] = maxLength[j] + 1;
                }
            }
        }
        return maxLength[0];
    }
}
