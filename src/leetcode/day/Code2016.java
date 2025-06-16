package leetcode.day;

/**
 * 给你一个下标从 0 开始的整数数组 nums ，该数组的大小为 n ，请你计算 nums[j] - nums[i] 能求得的 最大差值 ，其中 0 <= i < j < n 且 nums[i] < nums[j] 。
 * <p>
 * 返回 最大差值 。如果不存在满足要求的 i 和 j ，返回 -1 。
 */
public class Code2016 {
    public int maximumDifference(int[] nums) {
        int ans = -1;
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return ans;
        }
        int N = nums.length;
        int[] help = new int[N];
        help[help.length - 1] = nums[N - 1];

        for (int i = N - 2; i >= 1; i--) {
            help[i] = Math.max(nums[i], help[i + 1]);
        }
        for (int i = 0; i < N - 1; i++) {
            if (help[i + 1] > nums[i]) {
                ans = Math.max(ans, help[i + 1] - nums[i]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // [1,5,2,10]
        Code2016 code2016 = new Code2016();
        int[] nums = {1, 5, 2, 10};
        System.out.println(code2016.maximumDifference(nums));
    }
}
