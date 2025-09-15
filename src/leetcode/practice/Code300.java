package leetcode.practice;

import java.util.Arrays;

public class Code300 {

    public static void main(String[] args) {
        Code300 code300 = new Code300();
//        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int[] nums = {0, 1, 0, 3, 2, 3};
        int res2 = code300.lengthOfLIS1(nums);
        int res3 = code300.lengthOfLIS(nums);
        System.out.println(res2);
        System.out.println(res3);
    }

    public int lengthOfLIS1(int[] nums) {
        // 以i开头的位置的最长递增子序列的长度
        int ans = 0;
        for (int i = 0, len = nums.length; i < len; i++) {
            ans = Math.max(ans, process1(nums, i, Integer.MIN_VALUE));
        }
        return ans;
    }

    /**
     * 题目要求的是最长的递增子序列
     * <p>
     * 讨论从i位置开头的最长递增子序列的长度
     *
     * @param nums
     * @param index
     * @return
     */
    public int process(int[] nums, int index, int pre) {
        if (index == nums.length) {
            return 0;
        }
        // 分析可能性
        for (int i = index; i < nums.length; i++) {
            if (nums[i] > pre) {
                return Math.max(process(nums, i + 1, nums[i]) + 1, process(nums, i + 1, pre));
            }
        }
        return 0;
    }

    public int process1(int[] nums, int index, int pre) {
        if (index == nums.length) {
            return 0;
        }
        // 分析可能性
        for (int i = 0; i < index; i++) {
            if (nums[i] > pre) {
                return Math.max(process1(nums, i + 1, nums[i]) + 1, process1(nums, i + 1, pre));
            }
        }
        return 0;
    }

    /**
     * 记忆化搜索
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
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
