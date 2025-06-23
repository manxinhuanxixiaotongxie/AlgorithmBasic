package leetcode.week.code159;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个二维数组 coords，大小为 n x 2，表示一个无限笛卡尔平面上 n 个点的坐标。
 *
 * 找出一个 最大 三角形的 两倍 面积，其中三角形的三个顶点来自 coords 中的任意三个点，并且该三角形至少有一条边与 x 轴或 y 轴平行。严格地说，如果该三角形的最大面积为 A，则返回 2 * A。
 *
 * 如果不存在这样的三角形，返回 -1。
 *
 * 注意，三角形的面积 不能 为零。
 *
 */
public class Code02 {

    /**
     * 讨论三角形底边与 y 轴平行的情况（如示例 1）。
     *
     * 假设底边横坐标 x=1，为了最大化三角形面积，我们需要最大化三角形的底边长，以及三角形的高，二者相乘，就是三角形面积乘二的最大值。
     *
     * 底边：我们需要知道所有 x=1 的点中，y 的最小值 minY[x] 和最大值 maxY[x]，取这两个点的连线作为底边，长度为 maxY[x]−minY[x]。
     * 高：我们需要知道 x 的最小值 minX 和最大值 maxX，那么高就是 max(x−minX,maxX−x)。
     * 三角形面积乘二为
     *
     * (maxY[x]−minY[x])⋅max(x−minX,maxX−x)
     * 对于三角形底边与 x 轴平行的情况，我们只需交换每个点的横纵坐标，就可以复用上面的逻辑了。
     *
     * 作者：灵茶山艾府
     * 链接：https://leetcode.cn/problems/find-maximum-area-of-a-triangle/solutions/3705572/wei-hu-heng-zong-zuo-biao-de-zui-xiao-zh-rhdf/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *
     * @param coords
     * @return
     */
    public long maxArea(int[][] coords) {
        calc(coords);

        for (int[] p : coords) {
            int tmp = p[0]; // 交换横纵坐标
            p[0] = p[1];
            p[1] = tmp;
        }
        calc(coords);

        return ans > 0 ? ans : -1;
    }

    private long ans = 0;

    private void calc(int[][] coords) {
        int minX = Integer.MAX_VALUE;
        int maxX = 0;
        Map<Integer, Integer> minY = new HashMap<>();
        Map<Integer, Integer> maxY = new HashMap<>();

        for (int[] p : coords) {
            int x = p[0];
            int y = p[1];
            // x水平方向上的最大值与最小值
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            // x为横坐标 Y方向上的最大值与最小值
            maxY.put(x, Math.max(maxY.getOrDefault(x, 0), y));
            minY.put(x, Math.min(minY.getOrDefault(x, y), y));
        }

        for (Map.Entry<Integer, Integer> e : minY.entrySet()) {
            int x = e.getKey();
            int y = e.getValue();
            ans = Math.max(ans, (long) (maxY.get(x) - y) * Math.max(maxX - x, x - minX));
        }
    }

}
