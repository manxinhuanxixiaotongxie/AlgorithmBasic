package leetcode.day;

import java.util.HashMap;
import java.util.Map;

/**
 * 和谐数组是指一个数组里元素的最大值和最小值之间的差别 正好是 1 。
 * <p>
 * 给你一个整数数组 nums ，请你在所有可能的 子序列 中找到最长的和谐子序列的长度。
 * <p>
 * 数组的 子序列 是一个由数组派生出来的序列，它可以通过删除一些元素或不删除元素、且不改变其余元素的顺序而得到。
 */
public class Code594 {
    public int findLHS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            map.merge(nums[i], 1, Integer::sum);
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (map.containsKey(nums[i] - 1) || map.containsKey(nums[i] + 1)) {
                ans = Math.max(ans, Math.max(map.getOrDefault(nums[i] - 1, 0),
                        map.getOrDefault(nums[i] + 1, 0)) + map.getOrDefault(nums[i], 0));
            }
            map.merge(nums[i], -1, Integer::sum);
            if (map.getOrDefault(nums[i], 0) == 0) {
                map.remove(nums[i]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code594 code594 = new Code594();
        int[] nums = {1,2,1,3,0,0,2,2,1,3,3};
        System.out.println(code594.findLHS(nums)); // 输出：5
    }
}
