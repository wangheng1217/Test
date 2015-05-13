package wangheng.nclaptop;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 * http://www.geeksforgeeks.org/dynamic-programming-set-11-egg-dropping-puzzle/
 * 
 * When we drop an egg from a floor x, there can be two cases (1) The egg breaks (2) The egg doesn¡¯t break.

1) If the egg breaks after dropping from xth floor, then we only need to check for floors lower than x with remaining eggs; so the problem reduces to x-1 floors and n-1 eggs
2) If the egg doesn¡¯t break after dropping from the xth floor, then we only need to check for floors higher than x; so the problem reduces to k-x floors and n eggs.

Since we need to minimize the number of trials in worst case, we take the maximum of two cases. We consider the max of above two cases for every floor and choose the floor which yields minimum number of trials.

  k ==> Number of floors
  n ==> Number of Eggs
  eggDrop(n, k) ==> Minimum number of trails needed to find the critical
                    floor in worst case.
  eggDrop(n, k) = 1 + min{max(eggDrop(n - 1, x - 1), eggDrop(n, k - x)): 
                 x in {1, 2, ..., k}}
 */
public class EggDrop {
    
    @Test
    public void test() {
        EggDrop solution = new EggDrop();
        assertEquals(8, solution.eggDrop(2, 36));
    }

    public int eggDrop(int n, int k) {
        int[][] dp = new int[n+1][k+1];
        
        // dp[i][j] = 1 + min{max(dp[i-1][x-1], dp[i][j-x])} (x: 1 -> j)
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                if (i == 1) dp[i][j] = j;
                else if (j == 0 || j == 1) dp[i][j] = j;
                else {
                    int minValue = Integer.MAX_VALUE;
                    for (int x = 1; x <= j; x++) {
                        minValue = Math.min(minValue, 1 + Math.max(dp[i-1][x-1], dp[i][j-x]));
                    }
                    dp[i][j] = minValue;
                }
            }
        }
        
        return dp[n][k];
    }
}
