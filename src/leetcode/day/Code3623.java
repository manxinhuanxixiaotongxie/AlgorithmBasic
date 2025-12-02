package leetcode.day;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个二维整数数组 points，其中 points[i] = [xi, yi] 表示第 i 个点在笛卡尔平面上的坐标。
 *
 * 水平梯形 是一种凸四边形，具有 至少一对 水平边（即平行于 x 轴的边）。两条直线平行当且仅当它们的斜率相同。
 *
 * 返回可以从 points 中任意选择四个不同点组成的 水平梯形 数量。
 *
 * 由于答案可能非常大，请返回结果对 109 + 7 取余数后的值。
 */
public class Code3623 {
    private static final int MOD = 1_000_000_007;


    /**

     枚举右 维护左

     首先 统计每一行点的个数 如果这一行有c个点 那么从c个点中选2个点
     有 c*(c-1)/2种选法 可以组成一条水平边 即梯形的顶边或者底边


     枚举每一行 设这一行的有 k= c*(c-1)/2挑水平边 那么另外一边的就是之前遍历过的行的
     边数s 根据乘法原理 之前遍历过的行与这一行 一共可以组成 sS*kK个水平梯形，加入答案

     注意：另外一条边不能是其余所有的行 这会导致重复计算

     在最坏的情况下 有两行 每行n/2个点 组成约n^2/8条线段 答案约为n^4/64  不会超过64位整数最大值
     所以无需在循环中取模


     */
    public int countTrapezoids(int[][] points) {
        Map<Integer, Integer> cnt = new HashMap<>(points.length, 1); // 预分配空间
        for (int[] p : points) {
            // 统计每一行（水平线）有多少个点
            cnt.merge(p[1], 1, Integer::sum);
        }

        long ans = 0, s = 0;
        for (int c : cnt.values()) {
            long k = (long) c * (c - 1) / 2;
            ans += s * k;
            s += k;
        }
        return (int) (ans % MOD);
    }
}
