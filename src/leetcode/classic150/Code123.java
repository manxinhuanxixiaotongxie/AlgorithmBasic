package leetcode.classic150;

/**
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 */
public class Code123 {
    /**
     * 暴力递归
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        // 最多做两笔交易
        return process(prices, 0, true, 2);
    }

    public int process(int[] prices, int index, boolean canBuy, int rest) {
//        if (index == prices.length) {
//            return rest >= 0 ? 0 : Integer.MIN_VALUE;
        /**
         * 当 rest == 0 时，还是可以进入 canBuy 分支买入，买入后 canBuy=false，
         * 然后进入卖出分支，卖出时 rest - 1 = -1，到边界返回 Integer.MIN_VALUE，
         * 再 + prices[index]，Integer.MIN_VALUE + 正数 会发生整数溢出变成一个很大的负数，
         * 但不是 Integer.MIN_VALUE，导致 Math.max 可能错误选择了这条路。
         *
         */
        if (index == prices.length || rest == 0) {
            return 0;
        } else {
            if (canBuy) {
                // 能买 但是不一定买
                // 不买
                int p1 = process(prices, index + 1, true, rest);
                // 买
                int p2 = process(prices, index + 1, false, rest) - prices[index];
                return Math.max(p1, p2);
            } else {
                // 不能买 是不是能卖呢
                // 可以卖  也可以不卖
                // 不卖
                int p1 = process(prices, index + 1, false, rest);
                // 卖了
                int p2 = process(prices, index + 1, true, rest - 1) + prices[index];
                return Math.max(p1, p2);
            }
        }
    }

    public int maxProfit2(int[] prices) {
        // 最多做两笔交易
        // 改动态规划
        // 两列 第一列0 代表false 第二列1 代表true
        // 总共有3行
        int[][][] dp = new int[prices.length + 1][2][3];
        // 最后一个平面的值已经是0了 不需要处理
        // 每一个平台的第一列都是0了 不需要处理
        for (int index = prices.length - 1; index >= 0; index--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                for (int rest = 1; rest <= 2; rest++) {
                    if (canBuy == 1) {
                        // 能买 但是不一定买
                        // 不买
                        int p1 = dp[index + 1][1][rest];
                        // 买
                        int p2 = dp[index + 1][0][rest] - prices[index];
                        dp[index][canBuy][rest] = Math.max(p1, p2);
                    } else {
                        // 不能买 是不是能卖呢
                        // 可以卖  也可以不卖
                        // 不卖
                        int p1 = dp[index + 1][0][rest];
                        // 卖了
                        int p2 = dp[index + 1][1][rest - 1] + prices[index];
                        dp[index][canBuy][rest] = Math.max(p1, p2);
                    }
                }
            }
        }
        return dp[0][1][2];
    }
}
