package leetcode.day;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 给你一个整数 n ，共有编号从 0 到 n - 1 的 n 个会议室。
 *
 * 给你一个二维整数数组 meetings ，其中 meetings[i] = [starti, endi] 表示一场会议将会在 半闭 时间区间 [starti, endi) 举办。
 * 所有 starti 的值 互不相同 。
 *
 * 会议将会按以下方式分配给会议室：
 *
 * 每场会议都会在未占用且编号 最小 的会议室举办。
 * 如果没有可用的会议室，会议将会延期，直到存在空闲的会议室。延期会议的持续时间和原会议持续时间 相同 。
 * 当会议室处于未占用状态时，将会优先提供给原 开始 时间更早的会议。
 * 返回举办最多次会议的房间 编号 。如果存在多个房间满足此条件，则返回编号 最小 的房间。
 *
 * 半闭区间 [a, b) 是 a 和 b 之间的区间，包括 a 但 不包括 b 。
 */
public class Code2402 {
    /**
     * 这个模拟过程不对
     * 只使用一个优先级队列维护正在使用的会议室 分配会议时直接取出最早结束的房间继续用的
     * 没有管理空闲房间的编号
     * @param n
     * @param meetings
     * @return
     */
    public int mostBooked(int n, int[][] meetings) {
        // 有n个会议室 meeting这么多的会议需要被安排 会议室处于未占用状态时 优先提供给原始开始时间更早的会议
        // 返回举办最多次会议的房间编号
        // 按照会议开始的时间进行排序
        // 怎么分配会议室 结束时间最早的会议室优先分配
        int[] help = new int[n];
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        // 按照结束时间进行排序 结束时间早的排在前面
        PriorityQueue<Info> priorityQueue = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                // 结束时间相等根据什么进行排序
                return o1.endTime != o2.endTime ? o1.endTime - o2.endTime : o1.meettingRoom - o2.meettingRoom;
            }
        });
        int index = 0;
        int meetingSize = meetings.length;
        for (int i = 0; i < Math.min(n,meetingSize); i++) {
            // 每个会议室安排一个Info
            int[] meeting = meetings[i];
            int startTime = meeting[0];
            int endTime = meeting[1];
            priorityQueue.offer(new Info(i, startTime, endTime));
            help[i] ++;
            index++;
        }
        // 剩下的会议室安排
        while (index < meetingSize) {
            // 安排接下来的会议
            Info info = priorityQueue.poll();
            // 将新的会议安排进来
            int newStartTime = Math.max(info.endTime, meetings[index][0]);
            int newEndTime = newStartTime + (meetings[index][1] - meetings[index][0]);
            priorityQueue.offer(new Info(info.meettingRoom, newStartTime, newEndTime));
            help[info.meettingRoom]++;
            index++;
        }
        int max = n-1;
        int maxCount = help[n-1];
        for (int i = n-1; i >=0 ; i--) {
            if (help[i] >= maxCount) {
                max = i;
                maxCount = help[i];
            }
        }
        return max;
    }

    class Info {
        int meettingRoom;
        int startTime;
        int endTime;
        Info(int meettingRoom, int startTime, int endTime) {
            this.meettingRoom = meettingRoom;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    /**
     *
     * 使用两个堆
     * 一个堆维护已经使用的会议室 根据结束时间进行排序 如果结束时间相同 那么根据会议室编号进行排序
     *
     * @param n
     * @param meetings
     * @return
     */
    public int mostBooked2(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        int[] count = new int[n];
        // 维护空闲的房间
        PriorityQueue<Integer> freeRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) freeRooms.offer(i);
        // 维护已经使用的房间 根据结束时间进行排序
        PriorityQueue<long[]> usedRooms = new PriorityQueue<>((a, b) -> a[0] == b[0] ?
                (int)(a[1] - b[1]) : Long.compare(a[0], b[0]));

        for (int[] m : meetings) {
            int start = m[0], end = m[1];
            // 模拟过程
            while (!usedRooms.isEmpty() && usedRooms.peek()[0] <= start) {
                freeRooms.offer((int)usedRooms.poll()[1]);
            }
            if (!freeRooms.isEmpty()) {
                int room = freeRooms.poll();
                usedRooms.offer(new long[]{end, room});
                count[room]++;
            } else {
                long[] next = usedRooms.poll();
                long newEnd = next[0] + (end - start);
                usedRooms.offer(new long[]{newEnd, next[1]});
                count[(int)next[1]]++;
            }
        }
        int max = 0, idx = 0;
        for (int i = 0; i < n; i++) {
            if (count[i] > max) {
                max = count[i];
                idx = i;
            }
        }
        return idx;
    }

    static void main() {
        Code2402 code2402 = new Code2402();
        int[][] meetings = {{18,19},{3,12},{17,19},{2,13},{7,10}};
        int n = 4;
        int res = code2402.mostBooked(n, meetings);
        System.out.println(res);
    }
}
