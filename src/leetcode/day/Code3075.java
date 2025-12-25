package leetcode.day;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你一个长度为 n 的数组 happiness ，以及一个 正整数 k 。
 *
 * n 个孩子站成一队，其中第 i 个孩子的 幸福值 是 happiness[i] 。你计划组织 k 轮筛选从这 n 个孩子中选出 k 个孩子。
 *
 * 在每一轮选择一个孩子时，所有 尚未 被选中的孩子的 幸福值 将减少 1 。注意，幸福值 不能 变成负数，且只有在它是正数的情况下才会减少。
 *
 * 选择 k 个孩子，并使你选中的孩子幸福值之和最大，返回你能够得到的 最大值 。
 *
 * 提示：
 *
 * 1 <= n == happiness.length <= 2 * 10^5
 * 1 <= happiness[i] <= 10^8
 * 1 <= k <= n
 *
 */
public class Code3075 {
    public long maximumHappinessSum(int[] happiness, int k) {
        long ans = 0;
        // k轮筛选 选出k个孩子 只有幸福值大于0才能减少
        int del = 0;
        // 大根堆
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < happiness.length; i++) {
            pq.offer(happiness[i]);
        }
        while (k > 0 && !pq.isEmpty() && pq.peek() - del > 0) {
            int top = pq.poll();
            ans += top - del;
            del++;
            k--;
        }
        return ans;
    }
}
