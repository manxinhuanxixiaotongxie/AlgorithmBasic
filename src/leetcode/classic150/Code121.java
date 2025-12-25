package leetcode.classic150;

public class Code121 {
    public int maxProfit(int[] prices) {
        // 必须在今天卖掉 那么在之前哪一天买
        int ans = 0;
        int n = prices.length;
        int min = prices[0];
        for (int i = 1;i < n;i++) {
            ans = Math.max(ans,prices[i] - min);
            min = Math.min(min,prices[i]);
        }
        return ans;
    }
}
