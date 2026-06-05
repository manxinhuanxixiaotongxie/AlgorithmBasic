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
     * <p>
     * 0-1背包问题：给你一个容量为budget的背包 以及N个物品 其中物品的体积为prices[i]，价值为cnt(i) 在至多装满背包的情况下
     * 所选物品的的价值之和最大是多少
     * <p>
     * 设装入背包的物品花费至多为i时，获得了f(i)个物品
     * <p>
     * 可以重复购买物品 购买重复物品时，无法免费获取物品 贪心：重复购买便宜的物品 设其价格为minPrice
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
            int price = items[i][1];
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
     * 从第 index 个物品开始，剩余预算为 rest，
     * 通过"打折购买"能获得的最多物品数（不含贪心买最便宜部分）
     * <p>
     * 从所有物品中各选至多一次 花费刚好rest 获得的最多物品数
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
        int cnt = cnts[index];

        // 选择1：不买第 index 个物品
        int skip = dfs(index + 1, rest);

        // 选择2：买第 index 个物品（预算足够才能买）
        int take = 0;
        if (rest >= price) {
            take = cnt + dfs(index + 1, rest - price);
        }

        return memo[key] = Math.max(skip, take);
    }

    /**
     * 优化成dp
     *
     * @param items
     * @param budget
     * @return
     */
    public int maximumSaleItems3(int[][] items, int budget) {
        int n = items.length;
        int[] cnts = new int[n];
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int factory = items[i][0];
            int price = items[i][1];
            minPrice = Math.min(minPrice, price);
            for (int j = 0; j < n; j++) {
                if (factory % items[j][0] == 0) {
                    cnts[j]++;
                }
            }
        }
        // 将0-1背包问题改成动态规划
        int[][] dp = new int[n + 1][budget + 1];
        // 当前行依赖下一行 dp[n][i]==0 不需要进行填充
        // 从n-1行开始填
        for (int index = n - 1; index >= 0; index--) {
            int price = items[index][1];
            // 选择当前位置能够获得的个数
            int cnt = cnts[index];
            for (int rest = 0; rest <= budget; rest++) {
                // 第一种情况 不买当前物品
                dp[index][rest] = dp[index + 1][rest];
                // 第二种情况 买当前物品 但是是有前提的
                if (rest >= price) {
                    dp[index][rest] = Math.max(dp[index][rest], cnt + dp[index + 1][rest - price]);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i <= budget; i++) {
            ans = Math.max(ans, dp[0][i] + (budget - i) / minPrice);
        }
        return ans;
    }

    public int maximumSaleItems4(int[][] items, int budget) {
        int n = items.length;
        int[] cnts = new int[n];
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int factory = items[i][0];
            int price = items[i][1];
            minPrice = Math.min(minPrice, price);
            for (int j = 0; j < n; j++) {
                if (factory % items[j][0] == 0) {
                    cnts[j]++;
                }
            }
        }
        // 将0-1背包问题改成动态规划
        int[] dp = new int[budget + 1];
        for (int index = n - 1; index >= 0; index--) {
            // 总共要循环这么多次
            int price = items[index][1];
            // 选择当前位置能够获得的个数
            int cnt = cnts[index];
            // 当前物品太贵 这一行 dp 等于上一行 直接跳过
            if (price > budget) continue;
            // helpPrice 只保存"将来会被读到"的旧值 即 dp[0 .. budget - price]
            int[] helpPrice = new int[budget - price + 1];
            for (int rest = 0; rest <= budget; rest++) {
                // 先备份：只备份会被后续 rest' = rest + price 读到的位置
                if (rest <= budget - price) {
                    helpPrice[rest] = dp[rest];
                }
                // 再更新：用之前已经备份过的旧值
                if (rest >= price) {
                    dp[rest] = Math.max(dp[rest], cnt + helpPrice[rest - price]);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i <= budget; i++) {
            ans = Math.max(ans, dp[i] + (budget - i) / minPrice);
        }
        return ans;
    }

    /**
     * 空间压缩
     * 能够优化成一维
     *
     * @param items
     * @param budget
     * @return
     */
    public int maximumSaleItems5(int[][] items, int budget) {
        int n = items.length;
        int[] cnts = new int[n];
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int factory = items[i][0];
            int price = items[i][1];
            minPrice = Math.min(minPrice, price);
            for (int j = 0; j < n; j++) {
                if (factory % items[j][0] == 0) {
                    cnts[j]++;
                }
            }
        }
        // 将0-1背包问题改成动态规划
        int[] dp = new int[budget + 1];
        for (int index = n - 1; index >= 0; index--) {
            // 总共要循环这么多次
            int price = items[index][1];
            // 选择当前位置能够获得的个数
            int cnt = cnts[index];
            // 依赖的位置是下一行
            // 从大到小 之前的位置一定是上一行的位置没有发生变化
            for (int rest = budget; rest >= 0; rest--) {
                if (rest >= price) {
                    dp[rest] = Math.max(dp[rest], cnt + dp[rest - price]);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i <= budget; i++) {
            ans = Math.max(ans, dp[i] + (budget - i) / minPrice);
        }
        return ans;
    }

    /**
     * 优化cnts的计算方式
     *
     * @param items
     * @param budget
     * @return
     */
    public int maximumSaleItems6(int[][] items, int budget) {
        int n = items.length;
        int[] cnts = new int[n];
        int minPrice = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int factory = items[i][0];
            int price = items[i][1];
            minPrice = Math.min(minPrice, price);
            for (int j = 0; j < n; j++) {
                if (factory % items[j][0] == 0) {
                    cnts[j]++;
                }
            }
        }
        // 将0-1背包问题改成动态规划
        int[] dp = new int[budget + 1];
        for (int index = n - 1; index >= 0; index--) {
            // 总共要循环这么多次
            int price = items[index][1];
            // 选择当前位置能够获得的个数
            int cnt = cnts[index];
            // 依赖的位置是下一行
            // 从大到小 之前的位置一定是上一行的位置没有发生变化
            for (int rest = budget; rest >= 0; rest--) {
                if (rest >= price) {
                    dp[rest] = Math.max(dp[rest], cnt + dp[rest - price]);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i <= budget; i++) {
            ans = Math.max(ans, dp[i] + (budget - i) / minPrice);
        }
        return ans;
    }
}
