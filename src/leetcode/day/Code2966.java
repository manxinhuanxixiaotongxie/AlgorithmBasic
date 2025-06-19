package leetcode.day;

import java.util.Arrays;

/**
 * 给你一个长度为 n 的整数数组 nums，以及一个正整数 k 。
 * <p>
 * 将这个数组划分为 n / 3 个长度为 3 的子数组，并满足以下条件：
 * <p>
 * 子数组中 任意 两个元素的差必须 小于或等于 k 。
 * 返回一个 二维数组 ，包含所有的子数组。如果不可能满足条件，就返回一个空数组。如果有多个答案，返回 任意一个 即可。
 */
public class Code2966 {
    /**
     * 注意：本题的子数组是子集的意思，不是连续子数组。
     *
     * 对于 nums 的最小值 x 来说，应该把 x 与次小值 y 和第三小值 z 放在同一组。如果不这样做，其他某些组就不得不与更小的 y,z 在一起了，这可能无法满足要求。
     *
     * 因此做法是，把数组排序后，三个三个地切分。（注意题目保证数组长度是 3 的倍数）
     *
     * 如果同一组中的最大最小之差大于 k，则无法满足要求，返回空列表。
     *
     * @param nums
     * @param k
     * @return
     */
    public int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        // 总共有多少组
        int group = nums.length / 3;
        int[][] result = new int[group][3];
        for (int i = 0; i < group; i++) {
            // 每一组的起始位置
            int begin = i * 3;
            int end = begin + 2;
            if (nums[end] - nums[begin] > k) {
                return new int[][]{};
            }
            result[i] = new int[]{nums[begin], nums[begin + 1], nums[end]};
        }
        return result;
    }
}
