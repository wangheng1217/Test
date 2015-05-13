package wangheng.nclaptop;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 * http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
 * 
 * Given a rod of length n inches and an array of prices 
 * that contains prices of all pieces of size smaller than n. 
 * Determine the maximum value obtainable by cutting up the rod and selling the pieces.
 * 
 * A similar solution:
 * cutRod(n) = max(price[i] + cutRod(n-i-1)) for all i in {0, 1 .. n-1}
 */
public class CuttingARod {

    @Test
    public void test() {
        CuttingARod solution = new CuttingARod();
        assertEquals(22, solution.maxPrice(new int[]{1, 5, 8, 9, 10, 17, 17, 20}));
        assertEquals(24, solution.maxPrice(new int[]{3, 5, 8, 9, 10, 17, 17, 20}));
    }
    
    public int maxPrice(int[] prices) {
        int n = prices.length;
        int[] dp = new int[n+1];
        for (int i = 1; i <= n; i++) {
            dp[i] = prices[i-1];
            if (i > 1) {
                for (int x = 1; x <= i/2; x++) {
                    dp[i] = Math.max(dp[i], dp[x]+dp[i-x]);
                }
            }
        }

        return dp[n];
    }
}
