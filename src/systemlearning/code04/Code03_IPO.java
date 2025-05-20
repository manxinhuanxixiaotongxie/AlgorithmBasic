package systemlearning.code04;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @Description https://leetcode.cn/problems/ipo/
 * <p>
 * 假设 力扣（LeetCode）即将开始 IPO 。为了以更高的价格将股票卖给风险投资公司，力扣 希望在 IPO 之前开展一些项目以增加其资本。 由于资源有限，它只能在 IPO 之前完成最多 k 个不同的项目。帮助 力扣 设计完成最多 k 个不同项目后得到最大总资本的方式。
 * <p>
 * 给你 n 个项目。对于每个项目 i ，它都有一个纯利润 profits[i] ，和启动该项目需要的最小资本 capital[i] 。
 * <p>
 * 最初，你的资本为 w 。当你完成一个项目时，你将获得纯利润，且利润将被添加到你的总资本中。
 * <p>
 * 总而言之，从给定项目中选择 最多 k 个不同项目的列表，以 最大化最终资本 ，并输出最终可获得的最多资本。
 * <p>
 * 答案保证在 32 位有符号整数范围内。
 * @Author Scurry
 * @Date 2023-10-08 11:04
 */
public class Code03_IPO {


    /**
     * 贪心算法
     * <p>
     * 这个算法在leetcode数据量大跑不过
     *
     * @param k
     * @param w
     * @param profits
     * @param capital
     * @return
     */
    public int findMaximizedCapital1(int k, int w, int[] profits, int[] capital) {
        if (k <= 0) {
            return w;
        }
        if (profits == null || profits.length == 0 || capital == null || capital.length == 0 || profits.length != capital.length) {
            return 0;
        }

        /**
         * k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
         *
         * k = 10,w = 0,profits = [1,2,3],capital = [0,1,2]
         *
         * 过程：
         * 1.创建大跟堆，
         * 2.将能做的项目放入大根堆
         * 3.弹出大根堆的堆顶元素，将w加上堆顶元素的利润，k--
         */
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        Set<Integer> set = new HashSet<>();
        while (k > 0) {
            for (int i = 0; i < profits.length; i++) {
                if (capital[i] <= w && !set.contains(i)) {
                    queue.add(profits[i]);
                    set.add(i);
                }
            }
            if (queue.isEmpty()) {
                return w;
            }
            w += queue.poll();
            k--;
        }
        return w;
    }

    /**
     * 可以跑通
     *
     * @param k
     * @param w
     * @param profits
     * @param capital
     * @return
     */
    public int findMaximizedCapital2(int k, int w, int[] profits, int[] capital) {
        if (k <= 0) {
            return w;
        }
        if (profits == null || profits.length == 0 || capital == null || capital.length == 0 || profits.length != capital.length) {
            return 0;
        }

        /**
         * k = 2, w = 0, profits = [1,2,3], capital = [0,1,1]
         *
         * k = 10,w = 0,profits = [1,2,3],capital = [0,1,2]
         *
         * 过程：
         * 1.创建小跟堆 将能做的项目放入小根堆
         * 2.弹出小根堆的堆顶元素，将w加上堆顶元素的利润，k--
         *
         *
         */

        PriorityQueue<Programe> min = new PriorityQueue<>(minCom());

        PriorityQueue<Programe> max = new PriorityQueue<>(maxCom());

        for (int i = 0; i < capital.length; i++) {
            min.add(new Programe(profits[i], capital[i]));
        }

        while (k > 0) {

            while (!min.isEmpty() && min.peek().capital <= w) {
                max.add(min.poll());
            }

            if (max.isEmpty()) {
                return w;
            }
            w += max.poll().profit;
            k--;
        }
        return w;
    }


    class Programe {
        int profit;
        int capital;

        public Programe(int profit, int capital) {
            this.profit = profit;
            this.capital = capital;
        }
    }


    public Comparator<Programe> minCom() {
        return (o1, o2) -> o1.capital - o2.capital;
    }

    public Comparator<Programe> maxCom() {
        return (o1, o2) -> o2.profit - o1.profit;
    }
}
