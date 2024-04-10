package leetcode;

public class Code009 {
    /**
     * 乘积小于K的子数组的数量
     */

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0 |  k <= 1) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int cur = 1;
        int res = 0;
        /**
         * 使用滑动窗口
         *
         * 这个滑动窗口的更新是按照right来更新的
         * 流程如下：
         * 当前窗口的乘积小于k的时候，right++
         * 当前窗口的乘积大于等于k的时候，left++
         *
         */
        for (int i = 0; i < nums.length; i++) {
            cur *= nums[i];
            while (cur >= k) {
                cur /= nums[left];
                left++;
            }
            right++;
            res += right - left;
        }
        return res;
    }
}
