package systemimprove.code15;

import java.util.Deque;
import java.util.LinkedList;

public class Code04_GasStation {

    /**
     * leetcode 134题测试通过
     * 加油站的良好出发点问题
     * 给定两个数组 gas 和 cost
     * 代表加油有多少油以及到达下一个加油需要多少油
     * 这是一个阉割版的题目只需要返回一个答案
     * 返回所有可能性
     * 思路：
     * 1.加工出一个新的数组 用gas数组减去cost数组
     * 2.使用窗口的最小值的更新结构
     * 3.生成一个数组长度两倍的数组 目的是为了方便计算
     * 4.将数组加工成一个前缀和数组
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0 || cost == null || gas.length != cost.length) {
            return 0;
        }

        // 窗口的最小值更新结构
        // 正常思路求解：在一个范围内 对数组中的数进行累加和 累加和不能出现小于0的数
        // 窗口最小值是怎么形成的？ 从小到大的队列    如果小的话 更新队列结构大的话  放进队列的尾巴
        // 那么在这个过程中  如果窗口的最小值比0小的话  那么这个就是不能走完整个过程
        // 暴力方法怎么求解 从i位置开始的数组的和不能小于0
        /**
         * 1.先生成一个新的数组 用gas数组减去cost数组
         * 2.生成一个了两倍长度的数组
         * 3.生成一个前缀和数组
         * 4.创建一个最小值窗口结构
         * 5.窗口最原始的大小为arr的长度
         * 6.为什么要这么做？生成前缀和数组的时候，如果要能够走完一圈，那么前缀和数组的最小值一定要大于等于0
         */
        int[] arr = new int[cost.length];
        for (int i = 0; i < gas.length; i++) {
            arr[i] = gas[i] - cost[i];
        }

        int[] sum = generateDoubleArr(arr);
        int ans = 0;
        Deque<Integer> queen = new LinkedList<Integer>();
        // 提前构建好窗口最小值结构
        for (int i = 0; i < arr.length; i++) {
            while (!queen.isEmpty() && sum[queen.peekLast()] >= sum[i]) {
                // 后续的流程一样是按照前缀和数组进行比较 只是比较的结果要减去前一位
                // 前缀和的值减去前一个数组的值 就意味着一个新的已经形成
                // 统一加上一个数的大小并没有变
                queen.pollLast();
            }
            queen.addLast(i);
        }

        if (sum[queen.peekFirst()] >= 0) {
            return 0;
        }
        // 窗口最小值更新
        if (queen.peekFirst() == 0) {
            queen.pollFirst();
        }

        for (int i = 1; i < arr.length; i++) {
            int index = i;
            // 构建窗口最小值结构
            // 窗口最小值的更新结构
            while (!queen.isEmpty() && sum[queen.peekLast()] >= sum[index + arr.length - 1]) {
                // 后续的流程一样是按照前缀和数组进行比较 只是比较的结果要减去前一位
                // 前缀和的值减去前一个数组的值 就意味着一个新的已经形成
                // 统一加上一个数的大小并没有变
                queen.pollLast();
            }

            queen.addLast(i + arr.length - 1);

            // 得到一个新的窗口最小值 但是要减去之前的一个数
            if (sum[queen.peekFirst()] - sum[i - 1] >= 0) {
                // 返回有效出发点
                return i;
            }

            // 窗口最小值更新
            if (queen.peekFirst() == i) {
                queen.pollFirst();
            }
        }
        return -1;
    }

    public int canCompleteCircuit2(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length == 0 || cost.length == 0 || gas.length != cost.length) {
            return -1;
        }
        boolean[] good = goodArray(gas, cost);
        for (int i = 0; i < gas.length; i++) {
            if (good[i]) {
                return i;
            }
        }
        return -1;

    }

    public boolean[] goodArray(int[] gas, int[] cost) {
        int N = gas.length;
        int M = N << 1;
        int[] arr = new int[M];
        for (int i = 0; i < N; i++) {
            arr[i] = gas[i] - cost[i];
            arr[i + N] = gas[i] - cost[i];
        }
        // 将数组转换成前缀和的形式
        for (int i = 1; i < M; i++) {
            arr[i] += arr[i - 1];
        }

        // 在0-N-1位置生成一个窗口最小值的更新结构
        // 窗口的长度是原始cost或者gas的长度
        LinkedList<Integer> queen = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            while (!queen.isEmpty() && arr[queen.peekLast()] >= arr[i]) {
                // 后续的流程一样是按照前缀和数组进行比较 只是比较的结果要减去前一位
                // 前缀和的值减去前一个数组的值 就意味着一个新的已经形成
                // 统一加上一个数的大小并没有变
                queen.pollLast();
            }
            queen.addLast(i);
        }

        boolean[] ans = new boolean[N];
        for (int offset = 0, i = 0, j = N; j < M; offset = arr[i++], j++) {
            if (arr[queen.peekFirst()] - offset >= 0) {
                ans[i] = true;
            }
            if (queen.peekFirst() == i) {
                queen.pollFirst();
            }
            while (!queen.isEmpty() && arr[queen.peekLast()] >= arr[j]) {
                queen.pollLast();
            }
            queen.addLast(j);
        }
        return ans;
    }

    // 转换成两倍长度的累加和数组
    public int[] generateDoubleArr(int[] arr) {
        int[] sum = new int[arr.length * 2];
        sum[0] = arr[0];
        for (int i = 1; i < sum.length; i++) {
            // 0 1 2 3  4  5
            // 6 7 8 9 10 11
            if (i < arr.length) {
                sum[i] = sum[i - 1] + arr[i % arr.length];
            } else {
                sum[i] = arr[i - arr.length] + sum[i - 1];
            }
        }
        return sum;

    }

    public static void main(String[] args) {
        Code04_GasStation code04_gasStation = new Code04_GasStation();
        int[] gas = new int[]{3, 1, 1};
        int[] cost = new int[]{1, 2, 2};
        System.out.println(code04_gasStation.canCompleteCircuit(gas, cost));
    }
}
