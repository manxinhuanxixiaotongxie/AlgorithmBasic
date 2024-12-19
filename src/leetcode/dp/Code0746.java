package leetcode.dp;

public class Code0746 {
    public int minCostClimbingStairs1(int[] cost) {
        return Math.min(process(cost, cost.length, 0), process(cost, cost.length, 1));
    }

    private int process(int[] cost, int aim, int cur) {
        if (cur >= aim) {
            return 0;
        }
        // 走一步
        int p1 = process(cost, aim, cur + 1) + cost[cur];
        int p2 = process(cost, aim, cur + 2) + cost[cur];
        return Math.min(p1, p2);
    }

    public int minCostClimbingStairs2(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 2];
        for (int j = n - 1; j >= 0; j--) {
            dp[j] = Math.min(dp[j + 1] + cost[j], dp[j + 2] + cost[j]);
        }
        return Math.min(dp[0], dp[1]);
    }

    /**
     * 分析规律
     * dp[5] pre
     * dp[6] prePre
     * dp[4] = dp[5] + dp[6]
     * dp[3] = dp[4] + dp[5]
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs3(int[] cost) {
        int n = cost.length - 1;
        int pre = 0;
        int prePre = 0;
        // 从n位置开始
        while (n >= 0) {
            int temp = Math.min(pre, prePre) + cost[n];
            prePre = pre;
            pre = temp;
            n--;
        }
        return Math.min(pre, prePre);
    }
}
