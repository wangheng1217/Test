package wangheng.leetcode;

public class BestTimeToBuyAndSellStockSolution {

    public int maxProfit(int[] prices) {
        if (prices.length == 0)
            return 0;
        int minPriceLeft = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            minPriceLeft = min(minPriceLeft, prices[i]);
            maxProfit = max(maxProfit, prices[i] - minPriceLeft);
        }
        return maxProfit;
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }

    private int max(int a, int b) {
        return a > b ? a : b;
    }

}
