package leetcode.week.code513;

/**
 * 给你一个整数数组 nums。
 * <p>
 * 选择 恰好一对 不同下标 i 和 j。该数对的 强度 定义为：
 * <p>
 * (nums[i] * nums[j]) / gcd(nums[i], nums[j]) ^ 2
 * <p>
 * 返回所有可能数对中的 最大 强度。
 * <p>
 * gcd(a, b) 表示 a 和 b 的 最大公约数 。
 *
 */
public class Code01 {
    public long maxPairStrength(int[] nums) {
        // 可能数对中的最大强度
        // max((nums[i] * nums[j] / gcd(nums[i],nums[j])) ^ 2)
        // 暴力算
        long ans = 0;
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 1; j < n; j++) {
                long sum = (long) nums[i] * nums[j];
                int gcd = gcd(nums[i], nums[j]);
                long temp = sum / ((long) gcd *gcd);
                ans = Math.max(ans, temp);
            }
        }

        return ans;
    }

    public  int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static void main() {
        Code01 c = new Code01();
        int[] arr = {2, 3, 5};
        System.out.println(c.maxPairStrength(arr));
    }
}
