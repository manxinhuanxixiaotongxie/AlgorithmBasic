package leetcode.week.code505;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 滑动窗口最大值
 *
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回 滑动窗口中的最大值 。
 *
 */
public class Code239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 数组的长度
        int len = nums.length;
        int[] ans = new int[len - k + 1];
        // 总共会产生这么多个答案
        // 存放的是下标
        Deque<Integer> window  = new ArrayDeque<>();
        int index = 0;
        for (int i = 0; i < len;i++) {
            // 开始滑动窗口 窗口的头就是最大的 窗口从头到尾是单调递减的
            while (!window.isEmpty() && nums[window.peekLast()] <= nums[i]) {
                window.pollLast();
            }
            window.addLast(i);
            if (i < k - 1) {
                continue;
            }
            // 开始结算
            ans[index++] = nums[window.peek()];
            if (i - window.peekFirst() >= k -1) {
                window.pollFirst();
            }
        }

        return ans;
    }
}
