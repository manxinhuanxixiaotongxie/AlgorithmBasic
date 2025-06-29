package leetcode.practice.window;

/**
 * 给你一个由 n 个元素组成的整数数组 nums 和一个整数 k 。
 * <p>
 * 请你找出平均数最大且 长度为 k 的连续子数组，并输出该最大平均数。
 * <p>
 * 任何误差小于 10-5 的答案都将被视为正确答案。
 */
public class Code643 {
    public double findMaxAverage(int[] nums, int k) {
        double ans = Integer.MIN_VALUE;
        double sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                sum += nums[i];
            } else {
                // 如果i==k-1
                // 从0到k-1意味着已经有k个数了
                // 开始结算
                sum += nums[i];
                ans = Math.max(ans, sum / k);
                sum -= nums[i - k + 1];
            }
        }
        return ans;
    }

    public double findMaxAverage2(int[] nums, int k) {
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                sum += nums[i];
            } else {
                // 如果i==k-1
                // 从0到k-1意味着已经有k个数了
                // 开始结算
                sum += nums[i];
                maxSum = Math.max(maxSum, sum);
                sum -= nums[i - k + 1];
            }
        }
        return (double) maxSum / k;
    }

    public static void main(String[] args) {
        int[] nums = {1, 12, -5, -6, 50, 3};
        System.out.println(new Code643().findMaxAverage(nums, 4));
    }
}
