package leetcode.practice;


public class Code0188 {
    public static int maxProfit(int K, int[] arr) {
        return process(arr, 0, K * 2, false);
    }

    // 最多可以做K次交易 不一定要做足k次交易
    // rest剩下可以操作交易的次数 最大为K * 2次 一次交易为买入、卖出 操作两次 k次交易  次数为2k
    // 当前交易已经来到了index位置
    // 返回不超过K次的情况下，能够获取的最大收益
    private static int process(int[] arr, int index, int rest, boolean canSell) {
        if (index == arr.length || rest == 0) {
            return 0;
        }
        int ans = 0;
        // 在index位置做决定
        // index位置可以选择买入、不做任何动作
        // 1选择买入
        // 什么情况下 可以买入 手中已经没有股票 意味着canSell是个false
        if (!canSell) {
            ans = Math.max(process(arr, index + 1, rest - 1, true) - arr[index], ans);
        }
        // 不做任何操作
        ans = Math.max(process(arr, index + 1, rest, canSell), ans);
        if (canSell) {
            // 能卖出的话
            ans = Math.max(process(arr, index + 1, rest - 1, false) + arr[index], ans);
        }
        return ans;
    }

    // 改成动态规划
    public static int maxProfit2(int K, int[] arr) {
        // 三维的动态规划
        // index rest casnSell
        // 怎么填满这张表
        int[][][] dp = new int[arr.length + 1][2 * K + 1][2];
        for (int r = arr.length - 1; r >= 0; r--) {
            // 列
            for (int c = 1; c <= 2 * K; c++) {
                // dp[r][c][0] = dp[r][c][false]
                // dp[r][c][1] = dp[r][c][true]
                // 先填true
                for (int sell = 1; sell >= 0; sell--) {
                    int ans = 0;
                    if (sell == 1) {
                        ans = Math.max(dp[r + 1][c - 1][0] + arr[r], ans);
                    }
                    if (sell == 0) {
                        ans = Math.max(dp[r + 1][c - 1][1] - arr[r], ans);
                    }
                    ans = Math.max(dp[r + 1][c][sell], ans);
                    dp[r][c][sell] = ans;
                }
            }
        }
        return dp[0][2 * K][0];

    }

}
