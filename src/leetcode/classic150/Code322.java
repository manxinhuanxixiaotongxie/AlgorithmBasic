package leetcode.classic150;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * <p>
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * <p>
 * 你可以认为每种硬币的数量是无限的。
 */
public class Code322 {
    public int coinChange(int[] coins, int amount) {
        // 排序
        Arrays.sort(coins);
        int n = coins.length;
        int ans = process(coins, amount, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public int process(int[] coins, int amount, int index) {
        if (index == coins.length) {
            return amount == 0 ? 0 : Integer.MAX_VALUE;
        }
        if (amount == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int zhang = 0; zhang * coins[index] <= amount && index < coins.length; zhang++) {
            int next = process(coins, amount - coins[index] * zhang, index + 1);
            if (next != Integer.MAX_VALUE) {
                ans = Math.min(ans, next + zhang);
            }
        }
        return ans;
    }

    /**
     * 该动态规划
     *
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange2(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        int N = coins.length;
        int[][] dp = new int[N + 1][amount + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 1; j < amount+1; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= amount; rest++) {
                int ans = Integer.MAX_VALUE;
                // 使用当前货币的张数
                for (int zhang = 0; zhang * coins[index] <= rest; zhang++) {
                    int next = dp[index + 1][rest - zhang * coins[index]];
                    if (next != Integer.MAX_VALUE) {
                        ans = Math.min(ans, next + zhang);
                    }
                }
                dp[index][rest] = ans;
            }
        }
        return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
    }

    public int coinChange3(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        int N = coins.length;
        int[][] dp = new int[N + 1][amount + 1];
        for (int i = 0; i < N + 1; i++) {
            for (int j = 1; j < amount + 1; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= amount; rest++) {
                int ans = Integer.MAX_VALUE;
                // 使用当前货币的张数
                if (rest - coins[index] >= 0 && dp[index][rest - coins[index]] != Integer.MAX_VALUE) {
                    ans = dp[index][rest - coins[index]] + 1;
                }
                ans = Math.min(ans, dp[index + 1][rest]);
                dp[index][rest] = ans;
            }
        }
        return dp[0][amount] == Integer.MAX_VALUE ? -1 : dp[0][amount];
    }
}
