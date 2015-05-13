package wangheng.nclaptop;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 * http://www.geeksforgeeks.org/dynamic-programming-set-18-word-wrap/
 * 
 * O(n^3) vs O(n^2) -> minCost[n][n] vs minCost[n] -> we don't need to calculate minCost from i to j, we only need from 0 to i.
 */
public class WordWrapProblem {

    @Test
    public void test() {
        WordWrapProblem solution = new WordWrapProblem();
        assertEquals(35, solution.totalCost2(new String[] { "Geeks", "for", "Geeks", "presents", "word", "wrap", "problem" }, 15));
    }

    // O(n^2)
    public int totalCost2(String[] array, int lineWidth) {
        if (array.length == 0)
            return 0;

        int[][] length = new int[array.length][array.length];

        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = i; j < array.length; j++) {
                if (i == j) {
                    length[i][j] = array[i].length();
                } else {
                    length[i][j] = length[i][j - 1] + array[j].length() + 1;
                }
            }
        }
        
        int[] minCost = new int[array.length+1];
        for (int j = 0; j <= array.length; j++) {
            if (j == 0) minCost[j] = 0;
            else {
                int cost = Integer.MAX_VALUE;
                for (int k = 0; k < j; k++) {
                    if (length[k][j-1] <= lineWidth) {
                        int spaces = lineWidth - length[k][j-1];
                        cost = Math.min(cost, minCost[k] + spaces*spaces*spaces);
                    }
                }
                minCost[j] = cost;
            }
        }

        return minCost[array.length];
    }
    
    // O(n^3)
    public int totalCost(String[] array, int lineWidth) {
        if (array.length == 0)
            return 0;

        int[][] minCost = new int[array.length][array.length];
        int[][] length = new int[array.length][array.length];

        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = i; j < array.length; j++) {
                if (i == j) {
                    length[i][j] = array[i].length();
                } else {
                    length[i][j] = length[i][j - 1] + array[j].length() + 1;
                }

                if (length[i][j] <= lineWidth) {
                    int spaces = lineWidth - length[i][j];
                    minCost[i][j] = spaces*spaces*spaces;
                } else {
                    int cost = Integer.MAX_VALUE;
                    for (int k = i; k < j; k++) {
                        cost = Math.min(cost, minCost[i][k] + minCost[k + 1][j]);
                    }
                    minCost[i][j] = cost;
                }
            }
        }

        return minCost[0][array.length - 1];
    }
}
