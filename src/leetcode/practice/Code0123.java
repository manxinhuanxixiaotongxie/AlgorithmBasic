package leetcode.practice;

public class Code0123 {
    public int maxProfit(int[] prices) {
        /**
         * 买卖股票的问题三
         * 做两次交易
         *
         * 问题一已经解决了做一次交易获得的最大收益的计算
         */
        int maxProfit = 0;
        int maxProfitAfterOneTran = -prices[0];
        // 做完一次交易的最大收益
        int maxProfitOnceTran = 0;
        int min = prices[0];
        // 做完一次交易的最大的收益怎么求
        // 必须在i点卖出的话
        // 最大收益就是当前位置减去之前的最小值
        for (int i = 1; i < prices.length; i++) {
            // maxProfitAfterOneTran含义是前面已经做完一次交易获取的最大收益之后再买入的最大值
            maxProfit = Math.max(maxProfit, maxProfitAfterOneTran + prices[i]);
            // 前面的最小值
            min = Math.min(min, prices[i]);
            // 之前已经做完一次交易获取的最大值
            maxProfitOnceTran = Math.max(maxProfitOnceTran, prices[i] - min);
            // 做完一次交易 并且在最低点买入的最大值
            maxProfitAfterOneTran = Math.max(maxProfitAfterOneTran, -prices[i] + maxProfitOnceTran);
        }

        return maxProfit;
    }
}
