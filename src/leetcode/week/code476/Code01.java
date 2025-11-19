package leetcode.week.code476;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给你一个整数数组 nums。
 *
 * 从 nums 中选择三个元素 a、b 和 c，它们的下标需 互不相同 ，使表达式 a + b - c 的值最大化。
 *
 * 返回该表达式可能的 最大值 。©leetcode
 */
public class Code01 {
    public int maximizeExpressionOfThree(int[] nums) {
        // 排序 从大到小进行排序
        Integer[] arr = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(arr, Comparator.reverseOrder());
        return arr[0] + arr[1] - arr[arr.length - 1];
    }
}
