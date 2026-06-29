package leetcode.week.code508;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给你一个整数数组 nums，以及两个整数 k 和 mul。
 * <p>
 * 从 nums 中选出 恰好 k 个元素。你可以按照任意顺序逐个处理这些元素。
 * <p>
 * 对于每个被选择的元素，都可以 独立地 选择以下两种操作之一：
 * <p>
 * 将该元素的值 加 到总和中；或
 * 将该元素乘以 mul 的 当前 值，并将结果 加 到总和中。
 * 每处理一个被选择的元素后，无论选择哪种操作，mul 都会 减少 1。mul 的当前值可能变为 0 或负数。
 * <p>
 * 返回一个整数，表示可能得到的 最大 总和。
 *
 */
public class Code01 {
    // 可能得到的最大总和
    public long maxSum(int[] nums, int k, int mul) {
        Arrays.sort(nums);
        // 从小到大进行排序
        // 如果要总和最大
        long sum = 0;
        int n = nums.length;
        for (int i = n - 1; i >= n - k; i--) {
            // 选择k个数
            // 分类讨论
            //
            if (nums[i] < 0) {
                if (mul > 0) {
                    sum += nums[i];
                } else {
                    sum += (long) nums[i] * mul;
                }
            } else {
                if (mul > 0) {
                    sum += (long) nums[i] * mul;
                } else {
                    sum += nums[i];
                }
            }
            mul--;
        }
        return sum;
    }

    static void main() {
        Code01 c = new Code01();
        System.out.println(c.maxSum(new int[]{6, 1, 2, 9}, 3, 2));
    }
}
