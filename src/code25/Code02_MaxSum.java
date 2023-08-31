package code25;

import java.util.Stack;

/**
 * @Description 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 * 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 * 那么所有子数组中，这个值最大是多少？
 * @Author Scurry
 * @Date 2023-08-30 10:26
 */
public class Code02_MaxSum {


    /**
     * 一个数组的 最小乘积 定义为这个数组中 最小值 乘以 数组的 和 。
     * <p>
     * 比方说，数组 [3,2,5] （最小值是 2）的最小乘积为 2 * (3+2+5) = 2 * 10 = 20 。
     * 给你一个正整数数组 nums ，请你返回 nums 任意 非空子数组 的最小乘积 的 最大值 。由于答案可能很大，请你返回答案对  109 + 7 取余 的结果。
     * <p>
     * 请注意，最小乘积的最大值考虑的是取余操作 之前 的结果。题目保证最小乘积的最大值在 不取余 的情况下可以用 64 位有符号整数 保存。
     * <p>
     * 子数组 定义为一个数组的 连续 部分。
     *
     * 1 2 3 2
     * 1 3 6 8
     *
     * @param nums
     * @return
     */

    public int maxSumMinProduct1(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0;i< nums.length;i++) {

            for (int j = i;j<nums.length;j++) {
                int min = 0;
                int sum = 0;
                for (int k = i;k<=j;k++) {
                    sum += nums[k];
                    min = Math.min(min,nums[k]);
                }
                ans = Math.max(ans,min * sum);

            }
        }
        return ans;
    }
    public int maxSumMinProduct2(int[] nums) {

        if (nums == null || nums.length <= 0) {
            return 0;
        }

        int size = nums.length;

        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        Stack<Integer> stack = new Stack<>();
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] >= nums[i]) {
                int j = stack.pop();
                // 左边小的数 右边小的数是i
                int k = stack.isEmpty() ? -1 : stack.peek();
                ans = Math.max((k == -1 ? sum[i - 1] : (sum[i - 1] - sum[k])) * nums[j], ans);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            ans = Math.max((k == -1 ? sum[size - 1] : sum[size - 1] - sum[k]) * nums[j], ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] num = new int[4];
        num[0] = 1;
        num[1] = 2;
        num[2] = 3;
        num[3] = 2;
        System.out.println(new Code02_MaxSum().maxSumMinProduct2(num));
    }

}
