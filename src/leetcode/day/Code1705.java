package leetcode.day;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 有一棵特殊的苹果树，一连 n 天，每天都可以长出若干个苹果。在第 i 天，树上会长出 apples[i] 个苹果，
 * 这些苹果将会在 days[i] 天后（也就是说，第 i + days[i] 天时）腐烂，变得无法食用。也可能有那么几天，树上不会长出新的苹果，
 * 此时用 apples[i] == 0 且 days[i] == 0 表示。
 * <p>
 * 你打算每天 最多 吃一个苹果来保证营养均衡。注意，你可以在这 n 天之后继续吃苹果。
 * <p>
 * 给你两个长度为 n 的整数数组 days 和 apples ，返回你可以吃掉的苹果的最大数目。
 */
public class Code1705 {

    /**
     * 核心思路：对于两个苹果 A 和 B，设 A 更早腐烂，那么应该先吃 A。如果先吃 B，可能下一天 A 就烂了。
     * <p>
     * 用最小堆维护苹果的腐烂日期和个数，模拟一天吃一个苹果。
     * <p>
     * 注意第 n 天之后还可以继续吃苹果，所以要一直模拟到堆为空为止。
     *
     * @param apples
     * @param days
     * @return
     */
    public int eatenApples(int[] apples, int[] days) {
        int ans = 0;
        // 小根堆，按照腐烂日期排序
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < apples.length || !pq.isEmpty(); i++) {
            // 已腐烂
            while (!pq.isEmpty() && pq.peek()[0] == i) {
                pq.poll();
            }
            if (i < apples.length && apples[i] > 0) {
                pq.offer(new int[]{i + days[i], apples[i]});
            }
            if (!pq.isEmpty()) {
                // 吃一个最早腐烂的苹果
                ans++;
                // i >= pq.peek()[0] 这个判断其实是没有必要的，因为我们已经在上面处理了腐烂日期等于 i 的情况。
                // --pq.peek()[1] 这个操作会修改堆里面实际的苹果数量
                if (--pq.peek()[1] == 0 || i >= pq.peek()[0]) {
                    pq.poll();
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code1705 code1705 = new Code1705();
        int[] apples = {3, 1, 1, 0, 0, 2};
        int[] days = {3, 1, 1, 0, 0, 2};
//        int[] apples = {1, 2, 3, 5, 2};
//        int[] days = {3, 2, 1, 4, 2};
        System.out.println(code1705.eatenApples(apples, days));
    }
}
