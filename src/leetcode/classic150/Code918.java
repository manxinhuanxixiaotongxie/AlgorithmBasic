package leetcode.classic150;

/**
 * 环形子数组的最大和
 *
 */
public class Code918 {
    public int maxSubarraySumCircular(int[] nums) {
        int maxF = 0; // 计算最大子数组和的 DP 数组（空间优化成一个变量）
        int maxS = Integer.MIN_VALUE; // 最大子数组和，不能为空
        int minF = 0; // 计算最小子数组和的 DP 数组（空间优化成一个变量）
        int minS = 0; // 最小子数组和，可以为空（元素和为 0）
        int sum = 0; // nums 的元素和

        for (int x : nums) {
            // 53. 最大子数组和（空间优化写法）
            maxF = Math.max(maxF, 0) + x;
            maxS = Math.max(maxS, maxF);
            minF = Math.min(minF, 0) + x;
            minS = Math.min(minS, minF);
            sum += x;
        }

        return maxS < 0 ? maxS : Math.max(maxS, sum - minS);
    }


    static void main() {
        Code918 code = new Code918();
        int[] nums = {5,-3,5};
        int ans = code.maxSubarraySumCircular(nums);
        System.out.println(ans);
    }
}
