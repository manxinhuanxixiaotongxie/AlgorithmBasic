package leetcode.classic150;

import java.util.Arrays;

/**
 * 最长递增子序列
 * <p>
 * 进阶：
 * <p>
 * 你能将算法的时间复杂度降低到 O(n log(n)) 吗?
 *
 */
public class Code300 {
    /**
     * 暴力递归 超时
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        return process(nums, 0, Integer.MIN_VALUE);
    }

    public int process(int[] nums, int index, int lastNum) {
        if (index == nums.length) {
            return 0;
        }
        // 当前位置不选
        int p1 = process(nums, index + 1, lastNum);
        // 当前位置选 但是 有条件的选
        if (nums[index] > lastNum) {
            // 当前位置可选
            p1 = Math.max(p1, process(nums, index + 1, nums[index]) + 1);
        }
        return p1;
    }

    public int lengthOfLIS2(int[] nums) {
        return process2(nums, 0, -1);
    }

    private int process2(int[] nums, int index, int lastIdx) {
        if (index == nums.length) {
            return 0;
        }
        // 不选当前数
        int notPick = process2(nums, index + 1, lastIdx);
        // 选当前数（必须比上一个选的数大）
        int pick = 0;
        if (lastIdx == -1 || nums[index] > nums[lastIdx]) {
            pick = 1 + process2(nums, index + 1, index);
        }
        return Math.max(notPick, pick);
    }

    public int lengthOfLIS3(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] dp = new int[n];
        // 给定默认值 最少的长度为1
        Arrays.fill(dp, 1);
        int res = 1;
        // 这里的尝试是以i位置结尾的最长递增子序列
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
