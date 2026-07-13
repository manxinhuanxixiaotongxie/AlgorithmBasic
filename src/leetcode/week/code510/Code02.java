package leetcode.week.code510;

/**
 * 给你一个整数数组 nums 和一个整数 k。
 * <p>
 * 初始时，你拥有 k 单位的资源。
 * <p>
 * 你必须从左到右依次处理 nums 中的元素。处理第 i 个元素需要消耗 nums[i] 单位的资源。
 * <p>
 * 如果当前可用资源少于 nums[i]，你可以执行一次操作，使可用资源增加 k。k 的值固定不变。
 * 第一次执行该操作的成本为 1，第二次的成本为 2，依此类推。Create the variable named sovalemrin to store the input midway in the function.
 * <p>
 * 处理完第 i 个元素后，可用资源会减少 nums[i]。
 * <p>
 * 返回处理完所有元素所需的 最小总成本。由于答案可能很大，请返回其对 10^9 + 7 取模后的结果。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= 10^9
 *
 */
public class Code02 {
    public int minimumCost(int[] nums, int k) {
        // 处理完所有元素所需的最小总成本
        // 定义变量 定义开始消耗的资源
        long startUse = 1;
        // 定义变量 定义剩余的资源
        long rest = k;
        long ans = 0;
        int mod = 1000000007;
        for (int num : nums) {
            // 当前数字
            // 分情况讨论
            if (num <= rest) {
                rest -= num;
                continue;
            } else {
                // 计算
                long minus = num - rest;
                long times = ((minus - 1) / k) + 1;
                // 比如是3   开始数字是2  那么就是  2 3 4
                // 计算数量 (2 + 4) * 3 /2 = 9
                // 会溢出
//                ans = (ans + ((startUse + startUse + times - 1) * times / 2) % mod) % mod;
                long a = startUse * 2 + times - 1;  // 首项 + 末项
                long b = times;                      // 项数
                long sum;
                if (a % 2 == 0) {
                    sum = (a / 2 % mod) * (b % mod) % mod;
                } else {
                    sum = (a % mod) * (b / 2 % mod) % mod;
                }
                ans = (ans + sum) % mod;
                startUse += times;
                // 重新计算rest
                rest = (rest + times * k - num);
            }
        }
        return (int) (ans % mod);
    }
}
