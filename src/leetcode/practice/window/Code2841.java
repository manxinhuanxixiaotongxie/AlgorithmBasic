package leetcode.practice.window;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个整数数组 nums 和两个正整数 m 和 k 。
 * <p>
 * 请你返回 nums 中长度为 k 的 几乎唯一 子数组的 最大和 ，如果不存在几乎唯一子数组，请你返回 0 。
 * <p>
 * 如果 nums 的一个子数组有至少 m 个互不相同的元素，我们称它是 几乎唯一 子数组。
 * <p>
 * 子数组指的是一个数组中一段连续 非空 的元素序列。
 */
public class Code2841 {
    // 一个子数组至少有M个互不相同的元素
    public long maxSum(List<Integer> nums, int m, int k) {
        // 长度为K的子数组
        long maxSum = Integer.MIN_VALUE;
        Map<Integer, Integer> map = new HashMap<>();
        long sum = 0;
        // k-1长度的子数组
        for (int i = 0; i < k - 1; i++) {
            map.merge(nums.get(i), 1, Integer::sum);
            sum += nums.get(i);
        }
        // 从k-1出发
        for (int i = k - 1; i < nums.size(); i++) {
            map.merge(nums.get(i), 1, Integer::sum);
            sum += nums.get(i);
            if (map.size() >= m) {
                maxSum = Math.max(maxSum, sum);
            }
            // 去除i-k+1位置的元素
            // 怎么更新set2
            map.merge(nums.get(i - k + 1), -1, Integer::sum);
            if (map.get(nums.get(i - k + 1)) == 0) {
                map.remove(nums.get(i - k + 1));
            }
            sum -= nums.get(i - k + 1);
        }
        return maxSum == Integer.MIN_VALUE ? 0 : maxSum;
    }


    public static void main(String[] args) {
        Code2841 code2841 = new Code2841();
        List<Integer> nums = List.of(2, 6, 3, 9, 7, 3, 9, 9, 9);
        int m = 3;
        int k = 5;
        long result = code2841.maxSum(nums, m, k);
        System.out.println("Maximum sum of almost unique subarray: " + result); // Expected output: 18
    }
}
