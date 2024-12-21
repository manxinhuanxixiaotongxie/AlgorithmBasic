package leetcode.week.code425;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums 和 两个 整数 l 和 r。你的任务是找到一个长度在 l 和 r 之间（包含）且和大于 0 的 子数组 的 最小 和。
 * <p>
 * 返回满足条件的子数组的 最小 和。如果不存在这样的子数组，则返回 -1。
 * <p>
 * 子数组 是数组中的一个连续 非空 元素序列。
 */
public class Code0001 {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int ans = Integer.MAX_VALUE;
        List<Integer> helpSum = new ArrayList<>(nums.size());
        helpSum.add(nums.get(0));
        for (int i = 1; i < nums.size(); i++) {
            helpSum.add(helpSum.get(i - 1) + nums.get(i));
        }

        for (int i = 0; i < nums.size(); i++) {
            int left = l + i - 1;
            int right = i + r;
            int begin = i;
            while (left < nums.size() && left < right) {
                int sum = helpSum.get(left) - (i == 0 ? 0 : helpSum.get(begin - 1));
                if (sum > 0) {
                    ans = Math.min(ans, sum);
                }
                left++;
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        Code0001 code0001 = new Code0001();
        List<Integer> nums = new ArrayList<>();
        nums.add(3);
        nums.add(-2);
        nums.add(1);
        nums.add(4);
        System.out.println(code0001.minimumSumSubarray(nums, 2, 3));
    }
}
