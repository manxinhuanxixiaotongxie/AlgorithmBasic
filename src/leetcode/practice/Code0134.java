package leetcode.practice;

import java.util.LinkedList;

public class Code0134 {

    public int canCompleteCircuit(int[] gas, int[] cost) {
        // 加油站的良好出发点问题

        // gas = [1,2,3,4,5], cost = [3,4,5,1,2]
        // hel = [1,2,3,4,5,1,2,3,4,5]
        // cost = [3,4,5,1,2,3,4,5,1,2]
        // delta = [-2,-2,-2,3,3,-2,-2,-2,3,3]
        // 如果要走完这一圈的话 需要什么条件
        // 加工原始数据
        int[] gasNew = getDoubleLength(gas);
        int[] costNew = getDoubleLength(cost);
        int[] hel = new int[gasNew.length];
        // 相减
        for (int i = 0; i < gasNew.length; i++) {
            hel[i] = gasNew[i] - costNew[i];
        }
        // 求和
        int[] sum = new int[gasNew.length];
        sum[0] = hel[0];
        for (int i = 1; i < gasNew.length; i++) {
            sum[i] = sum[i - 1] + hel[i];
        }
        // 要保证能通过的话  要保证窗口内的值必须都大于0
        int ans = -1;
        LinkedList<Integer> widnow = new LinkedList<>();
        // 维持一个窗口最小值的队列
        // 特征是 窗口的尾巴到头位置的大小是从大到小
        // 头一定是最小的
        // 尾巴一定是最大的
        int k = gas.length;
        // 先维持一个大小为k-1的窗口
        for (int i = 0; i < k ; i++) {
            while (!widnow.isEmpty() && sum[i] <= sum[widnow.peekLast()]) {
                widnow.pollLast();
            }
            widnow.addLast(i);
        }
        // 从0-gas.length-1开始遍历
        for (int offset = 0, i = 0;i < gas.length;offset = sum[i++]) {
            // 从0-N-1开始遍历
            // 看是哪个位置可以走到原始出发点
            while (!widnow.isEmpty() && sum[i+gas.length -1] <= sum[widnow.peekLast()]) {
                widnow.pollLast();
            }
            widnow.addLast(i+gas.length - 1);
            // 有了一个窗口之后
            // 结算
            if (sum[widnow.peekFirst()] - offset >= 0) {
                ans = i;
                break;
            }
            // 如果窗口的头部已经过了
            if (widnow.peekFirst() == i - k) {
                widnow.pollFirst();
            }

        }
        return ans;
    }

    private int[] getDoubleLength(int[] arr) {
        int[] res = new int[arr.length << 1];
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            res[i] = arr[i];
            res[i + N] = arr[i];
        }
        return res;
    }

    public static void main(String[] args) {
        Code0134 code = new Code0134();
        int[] gas = {2,3,4};
        int[] cost = {3,4,3};
        System.out.println(code.canCompleteCircuit(gas, cost));
    }
}
