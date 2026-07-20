package leetcode.week.code511;

/**
 * 给你两个整数数组 start 和 target，每个数组的形式均为 [x, y]，表示标准 8 x 8 国际象棋棋盘上的一个格子。
 * <p>
 * 如果骑士可以用 偶数 次移动从 start 到达 target，则返回 true；否则返回 false。
 * <p>
 * 注意：骑士的一次合法移动是：沿一个方向移动两格，再沿与其垂直的方向移动一格。下图展示了骑士从一个格子出发时所有 8 种可能的移动方式。
 *
 */
public class Code01 {
    public boolean canReach(int[] start, int[] target) {
//        int x1 = start[0], y1 = start[1], x2 = target[0], y2 = target[1];
//        int x = Math.abs(x1 - x2);
//        int y = Math.abs(y1 - y2);
//        return (x == y * 2);
        // 核心规律是棋盘颜色（奇偶性）：
        //
        //骑士每次移动，坐标之和 (x+y) 的奇偶性必然改变（因为 1+2=3 是奇数）
        //走偶数步后，奇偶性回到原点的奇偶性
        //所以只要 start 和 target 的 (x+y) 奇偶性相同，就能用偶数步到达
        return ((start[0] + start[1]) % 2) == ((target[0] + target[1]) % 2);

    }
}
