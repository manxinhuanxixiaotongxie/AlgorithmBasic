package leetcode.day;

import java.util.*;

/**
 * 折扣价交易股票获取的最大利润
 *
 * 树形DP
 *
 */
public class Code3562 {
    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] e : hierarchy) {
            g[e[0] - 1].add(e[1] - 1);
        }

        int[][] f0 = dfs(0, g, present, future, budget);
        return f0[budget][0];
    }

    private int[][] dfs(int x, List<Integer>[] g, int[] present, int[] future, int budget) {
        // 计算从 x 的所有儿子子树 y 中，能得到的最大利润之和（x 不买，x 买）
        int[][] subF = new int[budget + 1][2];
        for (int y : g[x]) {
            int[][] fy = dfs(y, g, present, future, budget);
            for (int j = budget; j >= 0; j--) {
                // 枚举子树 y 的预算为 jy
                // 当作一个体积为 jy，价值为 fy[jy][k] 的物品
                for (int jy = 0; jy <= j; jy++) {
                    for (int k = 0; k < 2; k++) { // k=0 表示 x 不买，k=1 表示 x 买
                        subF[j][k] = Math.max(subF[j][k], subF[j - jy][k] + fy[jy][k]);
                    }
                }
            }
        }

        // 计算从子树 x 中，能得到的最大利润之和（x 父节点不买，x 父节点买）
        int[][] f = new int[budget + 1][2];
        for (int j = 0; j <= budget; j++) {
            for (int k = 0; k < 2; k++) { // k=0 表示 x 父节点不买，k=1 表示 x 父节点买
                int cost = present[x] / (k + 1);
                if (j >= cost) {
                    // 不买 x，转移来源是 subF[j][0]
                    // 买 x，转移来源为 subF[j-cost][1]，因为对于子树来说，父节点一定买
                    f[j][k] = Math.max(subF[j][0], subF[j - cost][1] + future[x] - cost);
                } else { // 只能不买 x
                    f[j][k] = subF[j][0];
                }
            }
        }
        return f;
    }

    /**
     * 题目理解错误 可以回头选 不是固定的一步步的进行选择
     */
//    public int process(int n, int[] present, int[] future, Map<Integer, List<Integer>> map, int index, int rest, Set<Integer> set) {
//        // 剩余的预算必须要覆盖当前节点的花费
//        if (index == n) {
//            return 0;
//        }
//        // 当前位置进行选择
//        int ans = 0;
//        // 当前节点是优惠节点 可以享受政策优惠
//        if (set.contains(index)) {
//
//            if (rest - (present[index] / 2) < 0) {
//                // 预算不够 只能跳过
//                return process(n, present, future, map, index + 1, rest, set);
//
//            }
//            // 子节点加入优惠集合
//            if (map.containsKey(index+ 1)) {
//                for (int child : map.get(index + 1)) {
//                    set.add(child - 1);
//                }
//            }
//            ans = Math.max(ans,future[index] - (present[index] / 2) + process(n, present, future, map, index + 1, rest - (present[index] / 2), set));
//            // 恢复现场
//            if (map.containsKey(index + 1)) {
//                for (int child : map.get(index + 1)) {
//                    set.remove(child - 1);
//                }
//            }
//            ans = Math.max(ans,process(n, present, future, map, index + 1, rest, set));
//        }else {
//            // 不是优惠节点
//            if (rest - present[index] < 0) {
//                // 预算不够 只能跳过
//                return process(n, present, future, map, index + 1, rest, set);
//
//            }
//            if (map.containsKey(index + 1)) {
//                for (int child : map.get(index + 1)) {
//                    set.add(child - 1);
//                }
//            }
//            ans = Math.max(ans,future[index] - present[index] + process(n, present, future, map, index + 1, rest - present[index], set));
//            // 恢复现场
//            if (map.containsKey(index + 1)) {
//                for (int child : map.get(index + 1)) {
//                    set.remove(child - 1);
//                }
//            }
//            ans = Math.max(ans,process(n, present, future, map, index + 1, rest, set));
//        }
//
//        return ans;
//    }
    static void main() {
        int n = 3;
        int[] present = {6, 4, 23};
        int[] future = {50, 48, 17};
        int[][] hierarchy = {{1, 3}, {1, 2}};
        int budget = 28;
        Code3562 code3562 = new Code3562();
        int ans = code3562.maxProfit(n, present, future, hierarchy, budget);
        System.out.println(ans);
    }
}

