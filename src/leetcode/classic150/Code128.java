package leetcode.classic150;

import java.util.Arrays;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * <p>
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 */
public class Code128 {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        Arrays.sort(nums);
        int index = 0;
        int r = 1;
        int ans = nums[1] - nums[0] == 1 ? 2 : 1;
        int n = nums.length;
        while (index < n) {
            int count = 0;
            // 要注意重复数字的处理
            while (r < n && (nums[r - 1] == nums[r] - 1 || nums[r - 1] == nums[r])) {
                if (nums[r - 1] == nums[r] ) {
                    count++;
                }
                r++;
            }
            // 结算
            ans = Math.max(ans, r - index - count);
            // 推动下标
            if (index == r) {
                index++;
                r++;
            } else {
                index = r;
                r++;
            }
        }

        return ans;

    }

    static void main() {
        Code128 code128 = new Code128();
        int[] nums = {1,0,1,2};
        int ans = code128.longestConsecutive(nums);
        System.out.println(ans);
    }
}
