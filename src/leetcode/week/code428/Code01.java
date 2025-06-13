package leetcode.week.code428;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你一个二维数组 events，表示孩子在键盘上按下一系列按钮触发的按钮事件。
 *
 * 每个 events[i] = [indexi, timei] 表示在时间 timei 时，按下了下标为 indexi 的按钮。
 *
 * 数组按照 time 的递增顺序排序。
 *
 * 按下一个按钮所需的时间是连续两次按钮按下的时间差。按下第一个按钮所需的时间就是其时间戳。
 * 返回按下时间 最长 的按钮的 index。如果有多个按钮的按下时间相同，则返回 index 最小的按钮。
 *
 *
 *
 * 示例 1：
 *
 * 输入： events = [[1,2],[2,5],[3,9],[1,15]]
 *
 * 输出： 1
 *
 * 解释：
 *
 * 下标为 1 的按钮在时间 2 被按下。
 * 下标为 2 的按钮在时间 5 被按下，因此按下时间为 5 - 2 = 3。
 * 下标为 3 的按钮在时间 9 被按下，因此按下时间为 9 - 5 = 4。
 * 下标为 1 的按钮再次在时间 15 被按下，因此按下时间为 15 - 9 = 6。
 * 最终，下标为 1 的按钮按下时间最长，为 6。
 *
 * 示例 2：
 *
 * 输入： events = [[10,5],[1,7]]
 *
 * 输出： 10
 *
 * 解释：
 *
 * 下标为 10 的按钮在时间 5 被按下。
 * 下标为 1 的按钮在时间 7 被按下，因此按下时间为 7 - 5 = 2。
 * 最终，下标为 10 的按钮按下时间最长，为 5。
 *
 *
 *
 * 提示：
 *
 * 1 <= events.length <= 1000
 * events[i] == [indexi, timei]
 * 1 <= indexi, timei <= 10 ^ 5
 *
 * 输入保证数组 events 按照 timei 的递增顺序排序。
 *
 *
 */
public class Code01 {
    public int buttonWithLongestTime(int[][] events) {
        if (events == null || events.length == 0) {
            return 0;
        }
        int ans = events[0][0];
        int max = events[0][1];
        for (int i = 1; i < events.length; i++) {
            int[] event = events[i];
            int index = event[0];
            int time = event[1];
            // 如果最大值需要被更新
            if (time - events[i-1][1] > max) {
                max = time - events[i-1][1];
                ans = index;
            } else if (time - events[i-1][1] == max) {
                // 如果最大值相等，更新index
                ans = Math.min(ans, index);
            }
        }
        return ans;
    }

    /**
     * n * log(n)的时间复杂度
     *
     * @param events
     * @return
     */
    public int buttonWithLongestTime2(int[][] events) {
        // 使用小根堆
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            // [0] index
            // [1] 差值
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] == o2[1] ? o1[0] - o2[0] : o2[1] - o1[1];
            }
        });
        queue.add(new int[]{events[0][0], events[0][1]});
        for (int i = 1; i < events.length; i++) {
            // 计算差值
            queue.add(new int[]{events[i][0], events[i][1] - events[i - 1][1]});
        }

        return queue.peek()[0];
    }

    public static void main(String[] args) {
        // [[1,5],[19,9],[6,10],[6,11],[16,14],[1,16],[15,19]]
        // for test
        Code01 test = new Code01();
        int[][] events = {
                {1,5},
                {19,9},
                {6,10},
                {6,11},
                {16,14},
                {1,16},
                {15,19}
        };
        int ans = test.buttonWithLongestTime(events);
        System.out.println(ans); // 8
        int ans2 = test.buttonWithLongestTime2(events);
        System.out.println(ans2); // 8
    }
}
