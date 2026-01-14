package leetcode.classic150;

public class Code209 {
    /**
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * <p>
     * 找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int minLen = Integer.MAX_VALUE;
        int r = 0;
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            while (r < n && sum < target) {
                sum += nums[r++];
            }
            // 结算
            minLen = sum >= target ? Math.min(minLen, r - i) : minLen;
            if (r == i) r++;
            sum -= nums[i];
        }
        return minLen== Integer.MAX_VALUE ? 0 : minLen;
    }

    static void main() {
        int[] nums = new int[]{2, 3, 1, 2, 4, 3};
        int target = 7;
        int i = new Code209().minSubArrayLen(target, nums);
        System.out.println(i);
    }
}
