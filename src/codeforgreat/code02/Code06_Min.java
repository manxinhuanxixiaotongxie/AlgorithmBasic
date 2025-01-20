package codeforgreat.code02;

/**
 * 给定一个数组arr，只能对arr中的一个子数组排序，
 * 但是想让arr整体都有序
 * 返回满足这一设定的子数组中，最短的是多长
 * <p>
 * <p>
 * 选择对一个子数组进行排序 未必要整体都排序
 * <p>
 * 3 4 1 4 6 2  7 8 9
 * 0 1 2 3 4 5 6 7  8
 * <p>
 * 我们肯定是可以在 0-8位置撒好难过进行排序 这是可以做到的
 * 但是肯定是不如在 0-6范围上进行排序
 * <p>
 * <p>
 * 将整个数组都变有序，最短的排序子数组的长度是多少？
 * <p>
 * 步骤：
 * 1.从左到右进行遍历  维护一个最大值
 * 如果最大值大于当前数 right更新成当前数 当前位置打叉
 * 如果最大值小于当前数 说明当前数是最大值 当前位置打√
 * <p>
 * 2.从右到左进行遍历 维护一个最小值
 * 如果最小值小于当前数 left更新成当前数 当前位置打叉 将left更新成当前数
 * 如果最小值大于当前数 说明当前数是最小值 当前位置打√
 */
public class Code06_Min {
    public static int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }
        // 需要排序的位置
        int right = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (max > nums[i]) {
                right = i;
            } else {
                max = nums[i];
            }
        }
        int left = nums.length;
        int min = Integer.MAX_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (min < nums[i]) {
                left = i;
            } else {
                min = nums[i];
            }
        }
        // 极端情况下 0-8位置都是有序的
        return Math.max(right - left + 1, 0);
    }
}
