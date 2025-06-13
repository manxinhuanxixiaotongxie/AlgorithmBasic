package leetcode.week.code428;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code3386 {
    /**
     * n * logn时间复杂度
     *
     * @param events
     * @return
     */
    public int buttonWithLongestTime(int[][] events) {
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

    /**
     * n 时间复杂度
     *
     * @param events
     * @return
     */
    public int buttonWithLongestTime2(int[][] events) {
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
            if (time - events[i - 1][1] > max) {
                max = time - events[i - 1][1];
                ans = index;
            } else if (time - events[i - 1][1] == max) {
                // 如果最大值相等，更新index
                ans = Math.min(ans, index);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] events = new int[][]{{12, 15}};
        System.out.println(new Code3386().buttonWithLongestTime(events));
    }
}
