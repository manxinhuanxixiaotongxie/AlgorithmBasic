package leetcode.classic150;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 假设 力扣（LeetCode）即将开始 IPO 。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。
 * 由于资源有限，它只能在 IPO 之前完成最多 k 个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。
 * <p>
 * 给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。
 * <p>
 * 最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。
 * <p>
 * 总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。
 * <p>
 * 答案保证在 32 位有符号整数范围内。
 *
 */
public class Code502 {
    /**
     * 超时
     *
     * @param k
     * @param w
     * @param profits
     * @param capital
     * @return
     */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // 排序规则 启动资金小 收益大的排在前面 启动资金大 收益大的排在后面
                // int[][0] 启动资金 int[][1] 收益
                return Integer.compare(o2[1], o1[1]);
            }
        });
        for (int i = 0; i < profits.length; i++) {
            priorityQueue.offer(new int[]{capital[i], profits[i]});
        }
        while (!priorityQueue.isEmpty() && k > 0) {
            PriorityQueue<int[]> temp = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    // 排序规则 启动资金小 收益大的排在前面 启动资金大 收益大的排在后面
                    // int[][0] 启动资金 int[][1] 收益
                    return Integer.compare(o2[1], o1[1]);
                }
            });
            while (!priorityQueue.isEmpty() && priorityQueue.peek()[0] > w) {
                temp.offer(priorityQueue.poll());
            }
            if (priorityQueue.isEmpty()) {
                break;
            }
            w += priorityQueue.poll()[1];
            k--;
            while (!temp.isEmpty()) {
                priorityQueue.offer(temp.poll());
            }
        }

        return w;
    }

    /**
     * 双堆方案
     *
     * @param k
     * @param w
     * @param profits
     * @param capital
     * @return
     */
    public int findMaximizedCapital2(int k, int w, int[] profits, int[] capital) {
        // 收益 大根堆
        PriorityQueue<int[]> maxProfit = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[1], o1[1]);
            }
        });
        // 启动资金 小根堆
        PriorityQueue<int[]> minCapital = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        // int[][0] 启动资金 int[][1] 收益
        for (int i = 0; i < profits.length; i++) {
            minCapital.offer(new int[]{capital[i], profits[i]});
        }
//        while (!minCapital.isEmpty() && k > 0) {
        while (k > 0) {
            while (!minCapital.isEmpty() && minCapital.peek()[0] <= w) {
                maxProfit.offer(minCapital.poll());
            }
            if (maxProfit.isEmpty()) {
                return w;
            }
            w += maxProfit.poll()[1];
            k--;
        }
        return w;
    }
}
