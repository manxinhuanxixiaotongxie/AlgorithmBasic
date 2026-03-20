package leetcode.classic150;

public class Code188 {

    public int maxProfit(int k, int[] prices) {
        // 最多做两笔交易
        // 改动态规划
        // 两列 第一列0 代表false 第二列1 代表true
        // 总共有3行

        int[][][] dp = new int[prices.length + 1][2][k+1];
        // 最后一个平面的值已经是0了 不需要处理
        // 每一个平台的第一列都是0了 不需要处理
        for (int index = prices.length - 1; index >= 0; index--) {
            for (int canBuy = 0; canBuy <= 1; canBuy++) {
                for (int rest = 1; rest <= k; rest++) {
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
        return dp[0][1][k];
    }
}
