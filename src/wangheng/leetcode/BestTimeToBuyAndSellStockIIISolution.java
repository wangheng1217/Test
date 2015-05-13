package wangheng.leetcode;

public class BestTimeToBuyAndSellStockIIISolution {
    
    public int maxProfit2(int[] prices) {
        // Start typing your Java solution below
        // DO NOT write main() function
        int length = prices.length;
        if (length == 0) return 0;
        
        int[] maxProfitSellBeforeDate = new int[length];
        int[] maxProfitBuyAfterDate = new int[length];
        
        maxProfitSellBeforeDate[0] = 0;
        int minPriceBefore = prices[0];
        for (int i = 1; i < length; i++) {
            minPriceBefore = min(minPriceBefore, prices[i]);
            maxProfitSellBeforeDate[i] = max(maxProfitSellBeforeDate[i-1], prices[i]-minPriceBefore);
        }
        
        maxProfitBuyAfterDate[length-1] = 0;
        int maxPriceAfter = prices[length-1];
        for (int i = length-2; i >= 0; i--) {
            maxPriceAfter = max(maxPriceAfter, prices[i]);
            maxProfitBuyAfterDate[i] = max(maxProfitBuyAfterDate[i+1], maxPriceAfter-prices[i]);
        }
        
        int maxProfit = 0;
        for (int i = 0; i < length; i++) {
            maxProfit = max(maxProfit, maxProfitSellBeforeDate[i]+maxProfitBuyAfterDate[i]);
        }
        
        return maxProfit;
    }

    public int maxProfit(int[] prices) {
        int[] minPriceLeft = new int[prices.length];
        int[] maxProfitLeft = new int[prices.length + 1];
        int[] maxPriceRight = new int[prices.length];
        int[] maxProfitRight = new int[prices.length + 1];
        maxProfitLeft[0] = 0;
        maxProfitRight[prices.length] = 0;

        for (int i = 0; i < prices.length; i++) {
            if (i == 0) {
                minPriceLeft[i] = prices[i];
                maxProfitLeft[i + 1] = 0;
            } else {
                minPriceLeft[i] = min(minPriceLeft[i - 1], prices[i]);
                maxProfitLeft[i + 1] = max(maxProfitLeft[i], prices[i]
                        - minPriceLeft[i]);
            }
        }

        for (int i = prices.length - 1; i >= 0; i--) {
            if (i == prices.length - 1) {
                maxPriceRight[i] = prices[i];
                maxProfitRight[i] = 0;
            } else {
                maxPriceRight[i] = max(maxPriceRight[i + 1], prices[i]);
                maxProfitRight[i] = max(maxProfitRight[i + 1], maxPriceRight[i]
                        - prices[i]);
            }
        }

        int maxProfit = 0;
        for (int i = 0; i <= prices.length; i++) {
            maxProfit = max(maxProfit, maxProfitLeft[i] + maxProfitRight[i]);
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
