package leetcode.day;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你一个正整数数组 nums ，请你从中删除一个含有 若干不同元素 的子数组。删除子数组的 得分 就是子数组各元素之 和 。
 * <p>
 * 返回 只删除一个 子数组可获得的 最大得分 。
 * <p>
 * 如果数组 b 是数组 a 的一个连续子序列，即如果它等于 a[l],a[l+1],...,a[r] ，那么它就是 a 的一个子数组。
 */
public class Code1695 {
    public int maximumUniqueSubarray(int[] nums) {
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        // 得到前缀和数组之后 滑动窗口
        // 必须以i开头的子数组
        Set<Integer> set = new HashSet<>();
        int N = nums.length;
        int end = 0;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            while (end < N && !set.contains(nums[end])) {
                set.add(nums[end++]);
            }
            if (end == i) {
                ans = Math.max(ans, nums[end++]);
            } else {
                ans = Math.max(ans, sum[end - 1] - (i == 0 ? 0 : sum[i - 1]));
            }
            set.remove(nums[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Code1695 code1695 = new Code1695();
        int[] nums = {4, 2, 4, 5, 6};
        System.out.println(code1695.maximumUniqueSubarray(nums)); // 输出：17
        nums = new int[]{1, 2, 3, 4, 5};
        System.out.println(code1695.maximumUniqueSubarray(nums)); // 输出：15
    }
}
