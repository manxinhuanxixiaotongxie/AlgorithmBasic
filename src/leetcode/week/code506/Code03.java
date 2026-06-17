package leetcode.week.code506;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 给你一个大小为 m × n 的二维整数数组 units，其中 units[i][j] 表示第 i 个设备中第 j 个单元的容量。每个设备 恰好 包含 n 个单元。
 * <p>
 * 设备的 评分 是其所有单元中的 最小 容量。
 * <p>
 * 你可以执行以下操作任意次（包括零次）：
 * <p>
 * 选择一个以前 从未 被用作源的设备 i。
 * 从设备 i 中 恰好 移除一个单元，并将其添加到 任意 其他设备中。
 * 然后将设备 i 标记为已使用，这样它就不能再被选作源。
 * 返回在进行任意次数的此类操作后，所有设备的评分之和的 最大 可能值。
 * <p>
 * 注意：
 * <p>
 * 设备可以接收来自多个设备的单元，无论它们是否已被选择过。
 * 空设备的评分为 0。
 */
public class Code03 {
    public long maxRatings(int[][] units) {
        int m = units.length;
        long ans = 0;
        // 枚举源设备子集
        for (int mask = 0; mask < (1 << m); mask++) {
            // 枚举集中站 作为源移走最小值
            for (int station = 0; station < m; station++) {
                ans = Math.max(ans, calcScore(units, mask, station));
            }
        }
        return ans;
    }

    private static long calcScore(int[][] units, int mask, int station) {
        int m = units.length;
        List<Integer> movedMins = new ArrayList<>();
        List<List<Integer>> deviceUnits = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> list = new ArrayList<>();
            for (int x : units[i]) list.add(x);
            deviceUnits.add(list);
        }
        // 源设备移走最小值
        for (int i = 0; i < m; i++) {
            if ((mask & (1 << i)) != 0) {
                List<Integer> list = deviceUnits.get(i);
                int minVal = Collections.min(list);
                list.remove(Integer.valueOf(minVal));
                movedMins.add(minVal);
            }
        }
        // 集中站接收所有移来的最小值
        deviceUnits.get(station).addAll(movedMins);
        // 计算总评分
        long total = 0;
        for (List<Integer> list : deviceUnits) {
            total += list.isEmpty() ? 0 : Collections.min(list);
        }
        return total;
    }

    /**
     * 贪心：
     *
     * @param units
     * @return
     */
    public long maxRatings2(int[][] units) {
        long ans = 0;
        if (units[0].length == 1) {
            // 每个设备都只有一个单元
            for (int[] unit : units) {
                ans += unit[0];
            }
            return ans;
        }

        int mn = Integer.MAX_VALUE;
        int mn2 = Integer.MAX_VALUE;
        for (int[] unit : units) {
            // 计算最小次小
            int unitMin = Integer.MAX_VALUE;
            int unitMin2 = Integer.MAX_VALUE;
            for (int x : unit) {
                if (x < unitMin) {
                    unitMin2 = unitMin;
                    unitMin = x;
                } else if (x < unitMin2) {
                    unitMin2 = x;
                }
            }

            ans += unitMin2; // 先加上次小
            mn2 = Math.min(mn2, unitMin2);
            mn = Math.min(mn, unitMin);
        }

        // 把包含 mn2 的那个设备作为集中站，存放每个设备的最小值
        ans += mn - mn2; // 把 ans 中的 mn2 替换成 mn
        return ans;
    }

}
