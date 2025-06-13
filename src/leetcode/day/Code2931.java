package leetcode.day;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 购买物品的最大开销
 * 给你一个下标从 0 开始大小为 m * n 的整数矩阵 values ，表示 m 个不同商店里 m * n 件不同的物品。每个商店有 n 件物品，
 * 第 i 个商店的第 j 件物品的价值为 values[i][j] 。除此以外，第 i 个商店的物品已经按照价值非递增排好序了，
 * 也就是说对于所有 0 <= j < n - 1 都有 values[i][j] >= values[i][j + 1] 。
 * <p>
 * 每一天，你可以在一个商店里购买一件物品。具体来说，在第 d 天，你可以：
 * <p>
 * 选择商店 i 。
 * 购买数组中最右边的物品 j ，开销为 values[i][j] * d 。换句话说，选择该商店中还没购买过的物品中最大的下标 j ，并且花费 values[i][j] * d 去购买。
 * 注意，所有物品都视为不同的物品。比方说如果你已经从商店 1 购买了物品 0 ，你还可以在别的商店里购买其他商店的物品 0 。
 * <p>
 * <p>
 * <p>
 * 请你返回购买所有 m * n 件物品需要的 最大开销 。
 * <p>
 * 思路：参考合并K个升序链表的思路，使用优先队列来维护每个商店中未购买的物品。
 * <p>
 * 这是一个贪心
 * <p>
 * <p>
 * 将数组按照物品的价值进行维护成每次获取最小的商品进行购买。
 */

/**
 * 时间复杂度：O(mnlogm)，其中 m 和 n 分别为 values 的行数和列数。
 * 空间复杂度：O(m)。
 */
public class Code2931 {
    public long maxSpending(int[][] values) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> values[o.row][o.col]));
        // 最后一列入队列
        for (int i = 0; i < values.length; i++) {
            Node node = new Node(i, values[i].length - 1);
            queue.add(node);
        }
        long ans = 0;
        int index = 1;

        while (!queue.isEmpty()) {
            Node pop = queue.poll();
            int r = pop.row;
            int c = pop.col;
            ans += (long) values[r][c] * (index++);
            if (c != 0) {
                queue.add(new Node(r, c - 1));
            }
        }
        return ans;
    }

    public long maxSpending2(int[][] values) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> values[a[0]][a[1]]));
        // 最后一列入队列
        for (int i = 0; i < values.length; i++) {
            queue.add(new int[]{i, values[i].length - 1});
        }
        long ans = 0;
        int index = 1;

        while (!queue.isEmpty()) {
            int[] pop = queue.poll();
            int r = pop[0];
            int c = pop[1];
            ans += (long) values[r][c] * (index++);
            if (c != 0) {
                queue.add(new int[]{r, c - 1});
            }
        }
        return ans;
    }


    class Node {
        int row;
        int col;

        Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
