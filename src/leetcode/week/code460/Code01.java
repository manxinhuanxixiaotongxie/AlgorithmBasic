package leetcode.week.code460;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums，其长度可以被 3 整除。
 * <p>
 * 你需要通过多次操作将数组清空。在每一步操作中，你可以从数组中选择任意三个元素，计算它们的 中位数 ，并将这三个元素从数组中移除。
 * <p>
 * 奇数长度数组的 中位数 定义为数组按非递减顺序排序后位于中间的元素。
 * <p>
 * 返回通过所有操作得到的 中位数之和的最大值 。©leetcode
 */
public class Code01 {
    public long maximumMedianSum(int[] nums) {
        // 最大中位数之和
        Arrays.sort(nums);
        int N = nums.length;
        int left = 0;
        int right = N - 1;
        long ans = 0;
        while (left <= right - 2) {
            ans += nums[right - 1];
            left++;
            right -= 2;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2,1,3,2,1,3};
        Code01 code01 = new Code01();
        System.out.println(code01.maximumMedianSum(nums));
    }
}
