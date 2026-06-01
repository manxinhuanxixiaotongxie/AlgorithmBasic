package leetcode.week.code504;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 给你一个二维整数数组 items，其中 items[i] = [factori, pricei] 表示下标为 i 的物品。同时给你一个整数 budget。
 * 每种物品都有无限个可供购买。你可以购买任意数量的任意物品，但购买物品的总花费最多为 budget。
 * <p>
 * 购买物品后，你可以根据以下规则获得免费的物品：
 * - 如果你购买了若干个物品 i，所有满足 j != i 且 factor_i 可以整除 factor_j 的物品 j ，你都能 免费 获得一份。
 * - 重复购买物品 i 不能 再获取额外的免费物品。
 * - 如果免费物品 j 是通过购买不同种类的物品获得的，那么同一种物品 j 可以被免费获得多次。
 * <p>
 * 返回你在购买物品花费最多为 budget 的前提下，能够获得的 物品最大总数 ，包括购买的物品和免费的物品。
 * <p>
 * 1 <= items.length <= 1000
 * 1 <= factor_i, price_i <= 1500
 * 1 <= budget <= 1500
 */
public class Code02 {

    /**
     * 超时
     *
     * @param items
     * @param budget
     * @return
     */
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        // 按 price 升序：这样在递归内 for 循环里用 rest >= items[i][1] 提前 break 才正确
        Arrays.sort(items, (a, b) -> a[1] - b[1]);

        // map[i] = 买物品 i 一次能免费拿到的物品下标列表
        // 触发条件：j != i 且 items[i].factor 能整除 items[j].factor
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (items[j][0] % items[i][0] == 0) {
                    map.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
                }
            }
        }

        return process(items, n, 0, budget, 0, new HashSet<>(), map);
    }

    /**
     * @param index 当前可选物品的起始下标（按 price 升序后）
     * @param rest  剩余预算
     * @param times 当前已经获得的物品总数（购买 + 免费）
     * @param set   当前路径上「已经购买过至少一次」的物品下标集合，用于避免重复送免费
     */
    public int process(int[][] items, int n, int index, int rest, int times,
                       Set<Integer> set, Map<Integer, List<Integer>> map) {
        if (index == n) return times;

        int ans = times; // 不再继续买
        for (int i = index; i < n && rest >= items[i][1]; i++) {
            if (!set.contains(i)) {
                // 第一次买物品 i
                if (map.containsKey(i)) {
                    // 有的送：购买 1 件 + 免费 map[i].size() 件
                    int freeCnt = map.get(i).size();
                    set.add(i);
                    ans = Math.max(ans,
                            process(items, n, i, rest - items[i][1], times + 1 + freeCnt, set, map));
                    set.remove(i);
                } else {
                    ans = Math.max(ans,
                            process(items, n, i, rest - items[i][1], times + 1, set, map));
                }
            } else {
                // 已经买过：重复购买只 +1，不再触发免费
                ans = Math.max(ans,
                        process(items, n, i, rest - items[i][1], times + 1, set, map));
            }
        }
        return ans;
    }

    /**
     * 0-1背包 + 枚举优化
     *
     * 0-1背包问题：给你一个容量为budget的背包 以及N个物品 其中物品的体积为prices[i]，价值为cnt(i) 在至多装满背包的情况下
     * 所选物品的的价值之和最大是多少
     *
     * 设装入背包的物品花费至多为i时，获得了f(i)个物品
     *
     * 可以重复购买物品 购买重复物品时，无法免费获取物品 贪心：重复购买便宜的物品 设其价格为minPrice
     *
     *
     *
     *
     * @param items
     * @param budget
     * @return
     */
    int n;
    int[] cnts;       // cnts[i] = 物品 i 对应的价值（因子倍数个数）
    int[][] items;
    int[] memo;       // memo 是二维的，压成一维：index * (budget+1
    public int maximumSaleItems2(int[][] items, int budget) {
        this.n = items.length;
        this.items = items;
        this.cnts = new int[n];

        int minPrice = Integer.MAX_VALUE;

        // 预处理每个物品的 cnt 和 minPrice
        for (int i = 0; i < n; i++) {
            int factor = items[i][0];
            int price  = items[i][1];
            minPrice = Math.min(minPrice, price);
            int cnt = 0;
            for (int[] q : items) {
                if (q[0] % factor == 0) cnt++;
            }
            cnts[i] = cnt;
        }

        // memo[index][rest] 压成一维
        this.memo = new int[(n + 1) * (budget + 1)];
        Arrays.fill(memo, -1);

        // 枚举"打折部分花了多少钱 i"，剩余贪心买最便宜的
        // 等价于 DP 最后的 max(f[i] + (budget-i)/minPrice)
        int ans = 0;
        for (int i = 0; i <= budget; i++) {
            // 剩余预算无限次购买最偏宜的物品 能再获得多少个物品
            ans = Math.max(ans, dfs(0, i) + (budget - i) / minPrice);
        }
        return ans;
    }

    /**
     * 递归语义：
     *   从第 index 个物品开始，剩余预算为 rest，
     *   通过"打折购买"能获得的最多物品数（不含贪心买最便宜部分）
     *
     *   从所有物品中各选至多一次 花费刚好rest 获得的最多物品数
     *
     * @param index 当前考虑的物品下标
     * @param rest  剩余预算
     * @return 最多能获得的物品数
     */
    private int dfs(int index, int rest) {
        // base case：所有物品都考虑完了
        if (index == n) return 0;

        int key = index * (/* budget+1 在外部已知，这里用 memo 数组 */ memo.length / (n + 1)) + rest;
        if (memo[key] != -1) return memo[key];

        int price = items[index][1];
        int cnt   = cnts[index];

        // 选择1：不买第 index 个物品
        int skip = dfs(index + 1, rest);

        // 选择2：买第 index 个物品（预算足够才能买）
        int take = 0;
        if (rest >= price) {
            take = cnt + dfs(index + 1, rest - price);
        }

        return memo[key] = Math.max(skip, take);
    }
}
