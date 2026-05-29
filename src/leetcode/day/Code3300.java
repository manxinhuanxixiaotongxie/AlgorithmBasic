package leetcode.day;

/**
 * 给你一个整数数组 nums 。
 * <p>
 * 请你将 nums 中每一个元素都替换为它的各个数位之 和 。
 * <p>
 * 请你返回替换所有元素以后 nums 中的 最小 元素。
 *
 */
public class Code3300 {
    public int minElement(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            // 计算数值
            int temp = 0;
            while (num != 0) {
                temp += num % 10;
                num /= 10;
            }
            min = Math.min(min, temp);
        }
        return min;
    }
}
