package leetcode.day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 给你一个数组 events，其中 events[i] = [startDayi, endDayi] ，表示会议 i 开始于 startDayi ，结束于 endDayi 。
 *
 * 你可以在满足 startDayi <= d <= endDayi 中的任意一天 d 参加会议 i 。在任意一天 d 中只能参加一场会议。
 *
 * 请你返回你可以参加的 最大 会议数目。
 *
 */
public class Code1353 {

    /**
     * 按开始时间分组+最小堆
     *
     * 1.第一天一定参加会议
     *
     * 2.参加结束时间最小的会议 参加结束时间最小的会议之后 问题变成从第二天开始 解决剩余n-1会议的子问题
     *
     *
     * 需要知道哪些信息？
     * 在第一天，可以参加哪些会议？
     *
     * 在第二天，可以参加哪些会议？
     *
     * 在第 i 天，可以参加哪些会议？
     *
     * 若按照开始时间分组，那么在第 i 天，开始时间为 i 的会议就是新增的可以参加的会议。
     *
     * 此外，还需要知道在剩余可以参加的会议中，结束时间最小的会议。根据观察二，参加这个会议。
     *
     * 算法
     * 把会议按照开始时间分组，用 groups[i] 表示所有开始时间为 i 的会议的结束时间。
     *
     * 我们需要一个数据结构维护结束时间。这个数据结构需要支持如下操作：
     *
     * 添加结束时间。
     * 查询结束时间的最小值。
     * 删除最小的结束时间，表示我们参加这个会议，或者这个会议已过期（结束时间小于当前时间）。
     * 最小堆完美符合要求。
     *
     * 在第 i 天：
     *
     * 删除最小堆中结束时间小于 i 的会议（已过期）。
     * 把开始时间为 i 的会议的结束时间，加到最小堆中。
     * 如果最小堆不为空，那么弹出堆顶（参加会议），把答案加一。
     *
     * 作者：灵茶山艾府
     * 链接：https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended/solutions/3707151/liang-chong-fang-fa-zui-xiao-dui-bing-ch-ijbf/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param events
     * @return
     */
    public int maxEvents(int[][] events) {
        int mx = 0;
        for (int[] e : events) {
            mx = Math.max(mx, e[1]);
        }

        // 按照开始时间分组
        List<Integer>[] groups = new ArrayList[mx + 1];
        Arrays.setAll(groups, i -> new ArrayList<>());
        for (int[] e : events) {
            groups[e[0]].add(e[1]);
        }

        int ans = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i <= mx; i++) {
            // 删除过期会议
            while (!pq.isEmpty() && pq.peek() < i) {
                pq.poll();
            }
            // 新增可以参加的会议
            for (int endDay : groups[i]) {
                pq.offer(endDay);
            }
            // 参加一个结束时间最早的会议
            if (!pq.isEmpty()) {
                ans++;
                pq.poll();
            }
        }
        return ans;
    }

}
