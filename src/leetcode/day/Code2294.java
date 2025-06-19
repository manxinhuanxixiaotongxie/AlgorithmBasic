package leetcode.day;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums 和一个整数 k 。你可以将 nums 划分成一个或多个 子序列 ，使 nums 中的每个元素都 恰好 出现在一个子序列中。
 * <p>
 * 在满足每个子序列中最大值和最小值之间的差值最多为 k 的前提下，返回需要划分的 最少 子序列数目。
 * <p>
 * 子序列 本质是一个序列，可以通过删除另一个序列中的某些元素（或者不删除）但不改变剩下元素的顺序得到。
 */
public class Code2294 {
    /**
     * 贪心
     *
     * @param nums
     * @param k
     * @return
     */
    public int partitionArray(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = 0;
        int mn = Integer.MIN_VALUE / 2; // 防止减法溢出
        for (int x : nums) {
            if (x - mn > k) { // 必须分割
                ans++;
                mn = x; // mn 是下一段的最小值
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code2294 code2294 = new Code2294();
        int[] nums = {3,6,1,2,5};
        int k = 2;
        System.out.println(code2294.partitionArray(nums, k)); // 输出 3
    }
}
