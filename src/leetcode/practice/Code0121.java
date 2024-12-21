package leetcode.practice;

public class Code0121 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        // 股票的买卖问题
        // 获取一次交易的最大收益
        // 设计流程
        // 必须在i位置卖出 那么最大收益就是当前位置减去之前的最小值
        // 为什么对呢？
        // 必须要在i位置卖出 那么最大收益就是当前位置减去之前的最小值
        /**
         * 客观上来说
         * 要做完一次交易 必须要在某一个时间点卖出
         * 我们把这个问题的流程定义成：必须在i位置卖出
         * 那么就是枚举了所有的卖出时机
         *
         * 在这所有的卖出时机中 获取的最大收益怎么求
         * 一定是当前位置的价格减去之前的最小值
         */
        int ans = 0;
        int min = prices[0];
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            ans = Math.max(ans, prices[i] - min);
        }
        return ans;
    }
}
