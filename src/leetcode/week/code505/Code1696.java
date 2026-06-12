package leetcode.week.code505;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 跳跃游戏
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
 * <p>
 * 一开始你在下标 0 处。每一步，你最多可以往前跳 k 步，但你不能跳出数组的边界。也就是说，你可以从下标 i 跳到 [i + 1， min(n - 1, i + k)]
 * 包含 两个端点的任意位置。
 * <p>
 * 你的目标是到达数组最后一个位置（下标为 n - 1 ），你的 得分 为经过的所有数字之和。
 * <p>
 * 请你返回你能得到的 最大得分 。
 *
 */
public class Code1696 {
    public int maxResult(int[] nums, int k) {
        return process(nums, nums.length, k, 0);
    }

    public int process(int[] nums, int n, int k, int index) {
        if (index >= n) return Integer.MIN_VALUE;
        if (index == n - 1) {
            return nums[index];
        }
        int ans = nums[index];
        // 枚举向前跳1步 2步 k步的得分
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= k; i++) {
            max = Math.max(max, process(nums, n, k, index + i));
        }
        return ans + max;
    }

    /**
     * 超时
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxResult2(int[] nums, int k) {
        // 改动态规划
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[n] = Integer.MIN_VALUE;
        dp[n - 1] = nums[n - 1];
        for (int index = n - 2; index >= 0; index--) {
            dp[index] = nums[index];
            int max = Integer.MIN_VALUE;
            for (int i = 1; i <= k; i++) {
                max = Math.max(max, (index + i > n ? Integer.MIN_VALUE : dp[index + i]));
            }
            dp[index] += max;
        }
        return dp[0];
    }

    /**
     * 优化
     *
     * @param nums
     * @param k
     * @return
     */
    public int maxResult3(int[] nums, int k) {
        // 改动态规划
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[n] = Integer.MIN_VALUE;
        dp[n - 1] = nums[n - 1];
        Deque<Integer> window = new ArrayDeque<>();
        window.addLast(n - 1);
        for (int index = n - 2; index >= 0; index--) {
            if (window.peekFirst() - index > k) {
                window.pollFirst();
            }
            dp[index] = nums[index] +  dp[window.peekFirst()];
            while (!window.isEmpty() && dp[window.peekLast()] <= dp[index]) {
                window.pollLast();
            }
            window.addLast(index);
        }
        return dp[0];
    }
}
