package leetcode.day;

import java.util.Arrays;

/**
 * 一家商店正在打折销售糖果。每购买 两个 糖果，商店会 免费 送一个糖果。
 * <p>
 * 免费送的糖果唯一的限制是：它的价格需要小于等于购买的两个糖果价格的 较小值 。
 * <p>
 * 比方说，总共有 4 个糖果，价格分别为 1 ，2 ，3 和 4 ，一位顾客买了价格为 2 和 3 的糖果，那么他可以免费获得价格为 1 的糖果，但不能获得价格为 4 的糖果。
 * 给你一个下标从 0 开始的整数数组 cost ，其中 cost[i] 表示第 i 个糖果的价格，请你返回获得 所有 糖果的 最小 总开销。
 *
 */
public class Code2144 {
    // 获得所有糖果的最小总花销
    public int minimumCost(int[] cost) {
        if (cost == null || cost.length == 0) return 0;
        if (cost.length == 1) return cost[0];
        if (cost.length == 2) return cost[0] + cost[1];
        // 贪心
        Arrays.sort(cost);
        int n = cost.length - 1;
        int ans = 0;
        // 一次推两位
        while (n >= 0) {
            ans += cost[n];
            n--;
            if (n < 0) {
                break;
            }
            ans += cost[n];
            n -= 2;
        }
        return ans;
    }
}
