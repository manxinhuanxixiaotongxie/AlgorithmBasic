package systemimprove.code11;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入: 正数数组costs、正数数组profits、正数K、正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明: 每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目。
 * 输出：你最后获得的最大钱数。
 */
public class Code05_IPO {

    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        return process(profits, capital, k, w);
    }

    public int process(int[] profits, int[] capital, int rest, int w) {

        if (rest <= 0 || capital.length == 0) {
            return w;
        }
        int ans = w;
        for (int i = 0; i < profits.length; i++) {
            if (w >= capital[i]) {
                int[] capNew = copyAndExcept(capital, i);
                int[] proNew = copyAndExcept(profits, i);
                ans = Math.max(ans, process(proNew, capNew, rest - 1, w + profits[i]));
            }
        }
        return ans;
    }

    public int[] copyAndExcept(int[] arr, int i) {
        int[] ans = new int[arr.length - 1];
        int ansi = 0;
        for (int j = 0; j < arr.length; j++) {
            if (j != i) {
                ans[ansi++] = arr[j];
            }
        }
        return ans;
    }

    // 贪心解法 使用大根堆
    public static int findMaximizedCapital2(int K, int W, int[] Profits, int[] Capital) {
        PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < Profits.length; i++) {
            minCostQ.add(new Program(Profits[i], Capital[i]));
        }
        for (int i = 0; i < K; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()) {
                return W;
            }
            W += maxProfitQ.poll().p;
        }
        return W;
    }

    public static class Program {
        public int p;
        public int c;

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MinCostComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.c - o2.c;
        }

    }

    public static class MaxProfitComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o2.p - o1.p;
        }

    }


}
