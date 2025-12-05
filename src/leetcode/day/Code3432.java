package leetcode.day;

/**
 * 给你一个长度为 n 的整数数组 nums 。
 * <p>
 * 分区 是指将数组按照下标 i （0 <= i < n - 1）划分成两个 非空 子数组，其中：
 * <p>
 * 左子数组包含区间 [0, i] 内的所有下标。
 * 右子数组包含区间 [i + 1, n - 1] 内的所有下标。
 * 对左子数组和右子数组先求元素 和 再做 差 ，统计并返回差值为 偶数 的 分区 方案数。
 *
 */
public class Code3432 {
    public int countPartitions(int[] nums) {

        int n = nums.length;
        int[] help = new int[n];
        help[0] = nums[0];
        for (int i = 1; i < n; i++) {
            help[i] = help[i - 1] + nums[i];
        }
        // 拿到前缀和数组之后
        int ans = 0;
        // 要保证差值为偶数 前面部分为偶数 后面部分为偶数 或者 前面部分为奇数 后面部分为奇数
        // 那么就以i为分割点 保证[0,i]  [i+1,n-1]部分进行拆分 i的范围是 0-n-2 最少要保证有一个数
        for (int i = 0; i < n - 1; i++) {
            int left = help[i];
            int right = help[n - 1] - help[i];
            // left与right都为奇数 或者都是偶数
            if (((left & 1) == (right & 1))) {
                ans++;
            }
        }

        return ans;
    }

    /**
     * 上面的辅助数组不是必要的
     *
     * @param nums
     * @return
     */
    public int countPartitions2(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        // 拿到前缀和数组之后
        int ans = 0;
        int leftSum = 0;
        int rightSum = 0;
        // 要保证差值为偶数 前面部分为偶数 后面部分为偶数 或者 前面部分为奇数 后面部分为奇数
        // 那么就以i为分割点 保证[0,i]  [i+1,n-1]部分进行拆分 i的范围是 0-n-2 最少要保证有一个数
        for (int i = 0; i < n - 1; i++) {
            leftSum += nums[i];
            rightSum = sum - leftSum;
            // left与right都为奇数 或者都是偶数
            if (((leftSum & 1) == (rightSum & 1))) {
                ans++;
            }
        }

        return ans;
    }

    /**
     * 脑筋急转弯的解法
     * 设数组的总和为S  左边部分的和为L 右边部分的和为R 其中 R = S- L
     *
     * 题目要求差值是偶数 那么有  L - （S- L） = 2L -S 是偶数  或者是（S-L） - L = S - 2L 是偶数
     *
     * 其中2L一定是偶数 只要保证数组的和是偶数 那么数组任意划分都是满足条件的
     *
     * @param nums
     * @return
     */
    public int countPartitions3(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        // 拿到前缀和数组之后
        return sum % 2 == 0 ? n - 1 : 0;
    }

    static void main() {
        Code3432 code3432 = new Code3432();
        int[] nums = new int[]{1, 1};
        System.out.println(code3432.countPartitions(nums));
    }
}
