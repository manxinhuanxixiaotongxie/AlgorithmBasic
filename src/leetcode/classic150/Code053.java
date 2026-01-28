package leetcode.classic150;

/**
 * 最大子数组的和
 */
public class Code053 {

    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] help = new int[n];
        help[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            help[i] = help[i + 1] > 0 ? help[i + 1] + nums[i] : nums[i];
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, help[i]);
        }
        return ans;
    }

    /**
     * 少一次遍历
     *
     * @param nums
     * @return
     */
    public int maxSubArray2(int[] nums) {
        int n = nums.length;
        int[] help = new int[n];
        help[n - 1] = nums[n - 1];
        int ans = help[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            help[i] = help[i + 1] > 0 ? help[i + 1] + nums[i] : nums[i];
            ans = Math.max(ans, help[i]);
        }
        return ans;
    }

    /**
     * 空间优化
     *
     * @param nums
     * @return
     */
    public int maxSubArray3(int[] nums) {
        int n = nums.length;
        int next = nums[n - 1];
        int ans = next;
        for (int i = n - 2; i >= 0; i--) {
            int cur = next > 0 ? next + nums[i] : nums[i];
            ans = Math.max(ans, cur);
            next = cur;
        }
        return ans;
    }
}
