package leetcode.practice;

/**
 * 有多少子数组的乘积小于K
 * 提供两种写法 思路是一样的  写法不同
 */
public class Code713 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 1) {
            return 0;
        }
        int sum = 1;
        int left = -1;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            sum *= nums[i];
            while (sum >= k) {
                sum /= nums[++left];
            }
            ans += i - left;
        }
        return ans;
    }

    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 1) {
            return 0;
        }
        int sum = 1;
        int left = -1;
        int right = 0;
        int ans = 0;
        while (right < nums.length) {
            sum *= nums[right];
            while (sum >= k) {
                sum /= nums[++left];
            }
            ans += right - left;
            right++;
        }
        return ans;

    }

    public static void main(String[] args) {
        Code713 code713 = new Code713();
        int[] nums = {10, 5, 2, 6};
        int k = 100;
        System.out.println(code713.numSubarrayProductLessThanK(nums, k));
        System.out.println(code713.numSubarrayProductLessThanK2(nums, k));
    }
}
