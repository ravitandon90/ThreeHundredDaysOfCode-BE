package best_time_to_buy_and_sell_stocks.Java;

class Solution {
    public int maxProfit(int[] prices) {
        int max = 0;
        int min = 100000000;
        for(int i = 1; i<prices.length; i++)
        {
            min = Math.min(prices[i-1],min);
            max = Math.max(prices[i]-min,max);
        }
        return max;
    }
}
