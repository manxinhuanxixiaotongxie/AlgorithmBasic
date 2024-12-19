package leetcode.dp;

import java.util.Arrays;

/**
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * <p>
 * 题目数据保证答案符合 32 位整数范围。
 */
public class Code0377 {
    public int combinationSum4(int[] nums, int target) {
        return process(nums, target);
    }

    private int process(int[] nums, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (rest < 0) {
            return 0;
        }
        // 当前index选择张数
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans += process(nums, rest - nums[i]);
        }
        return ans;

    }

    public int combinationSum42(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }

        return dp[target];
    }

    public int combinationSum43(int[] nums, int target) {
        int[] dp = new int[target + 1];
        Arrays.sort(nums);
        dp[0] = 1;
        // dp[1] == dp[1-x]...+dp[1-y]...+dp[1-z]...
        // dp[2] == dp[2-x]...+dp[2-y]...+dp[2-z]...
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }

        return dp[target];
    }
}
