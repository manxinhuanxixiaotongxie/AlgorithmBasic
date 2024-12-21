package leetcode.practice;

public class Code0122 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            ans += Math.max(prices[i + 1] - prices[i], 0);
        }
        return ans;
    }
}
