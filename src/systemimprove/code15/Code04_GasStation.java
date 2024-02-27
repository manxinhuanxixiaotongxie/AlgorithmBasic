package systemimprove.code15;

import java.util.Deque;
import java.util.LinkedList;

public class Code04_GasStation {

    /**
     * leetcode 134题测试通过
     * 这是一个阉割版的题目只需要返回一个答案
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

        for (int i = 1; i < arr.length ; i++) {
            int index = i;
            // 构建窗口最小值结构
            while (!queen.isEmpty() && sum[queen.peekLast()] >= sum[index + arr.length - 1]) {
                // 后续的流程一样是按照前缀和数组进行比较 只是比较的结果要减去前一位
                // 前缀和的值减去前一个数组的值 就意味着一个新的已经形成
                // 统一加上一个数的大小并没有变
                queen.pollLast();
            }

            queen.addLast(i + arr.length - 1);

            if (sum[queen.peekFirst()] - sum[i - 1] >= 0) {
                return i;
            }

            // 窗口最小值更新
            if (queen.peekFirst() == i) {
                queen.pollFirst();
            }
        }
        return -1;
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
        int[] gas = new int[]{3,1,1};
        int[] cost = new int[]{1,2,2};
        System.out.println(code04_gasStation.canCompleteCircuit(gas, cost));
    }
}
