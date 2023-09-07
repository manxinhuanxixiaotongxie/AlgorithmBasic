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
     * <p>
     * <p>
     * 1.暴力方法
     * 列举出所有的子数组
     * 从i开始到N-1位置开始遍历
     * 那么子数组的构成i到N-1就是所有子数组的数量
     *
     * 以0开头  0-0 0-1 0-2 0-N-1
     * 以1开头  1-1 1-2 1-3 1-N-1
     *
     * 以N-1开头 N-1 N-1
     *
     * 以上就能列举出所有的子数组
     *
     *
     * 2.暴力方法优化
     * 单调栈实现
     * 以i为底的情况下，左边最小值和右边最小值
     *
     * 以 1 3 5 3 2为例子
     *
     * 对数组进行加工
     * 1 3 5  3  2
     * 1 4 9 12 14
     *
     * 使用1向右走 本身就是最小值 乘积 1*1
     * 使用3向右走 本身就是最小值 乘积 3*3
     * 使用5向右走 本身就是最小值 乘积 5*5
     * 使用3向右走 本身就是最小值 乘积 3*12
     * 使用2向右走 本身就是最小值 乘积 2*14
     *
     *
     *
     *
     * @param nums
     * @return
     */

    public int maxSumMinProduct1(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int min = 0;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                    min = Math.min(min, nums[k]);
                }
                ans = Math.max(ans, min * sum);

            }
        }
        return ans;
    }

    /**
     *
     * 1 2 1 2
     *
     * 找到所有的子数组里面的最小值  然后找到这些子数组最小值*总和的最大值
     *
     *
     *
     *
     * @param nums
     * @return
     */
    public int maxSumMinProduct2(int[] nums) {

        if (nums == null || nums.length <= 0) {
            return 0;
        }

        int size = nums.length;

        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        Stack<Integer> stack = new Stack<>();
        long ans = 0;

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
        return (int) (ans % 1000000007);
    }

    /**
     * 自己实现一个栈（使用数组模拟节约空间）
     * @param nums
     * @return
     */
    public int maxSumMinProduct3(int[] nums) {

        if (nums == null || nums.length <= 0) {
            return 0;
        }

        int size = nums.length;

        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        int[] stack = new int[nums.length];
        long ans = 0;
        int stackSize = 0;
        for (int i = 0; i < nums.length; i++) {
            while (stackSize != 0 && nums[stack[stackSize - 1]] >= nums[i]) {
                int j = stack[--stackSize];
                // 左边小的数 右边小的数是i
                int k = stackSize == 0 ? -1 : stack[stackSize - 1];
                ans = Math.max((k == -1 ? sum[i - 1] : (sum[i - 1] - sum[k])) * nums[j], ans);
            }
            stack[stackSize++] = i;
        }

        while (stackSize != 0) {
            int j = stack[--stackSize];
            int k = stackSize == 0 ? -1 : stack[stackSize - 1];
            ans = Math.max((k == -1 ? sum[size - 1] : sum[size - 1] - sum[k]) * nums[j], ans);
        }
        // 这里注意写法 (int) (ans % 1000000007);  写成(int) ans % 1000000007;会出现因为优先级问题导致的错误
        /**
         * 在Java中，类型转换运算符(int)的优先级比算术运算符%高，因此(int) ans % 1000000007会先将ans转换为int类型，然后再进行取模运算。
         *
         * 而(int) (ans % 1000000007)使用了括号，明确了取模运算的优先级高于类型转换运算符，因此会先计算ans % 1000000007，然后再将结果转换为int类型。
         *
         * 例如，假设ans的值为2147483648，即超出了int类型的范围，那么(int) ans % 1000000007的结果将是-2147483647，因为(int) ans将会截断高位的部分，得到的结果是-2147483648，然后再进行取模运算，得到的结果是-2147483647。
         *
         * 而(int) (ans % 1000000007)的结果将是147483641，因为先进行取模运算，得到的结果是147483648，然后再进行类型转换，将高位截断得到147483641。
         *
         * 因此，在进行类型转换和算术运算时，建议使用括号明确运算顺序，以避免出现意外的结果。
         *
         */
        return (int) (ans % 1000000007);
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
