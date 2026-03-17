package leetcode.classic150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 给你一个大小为 n x n 的整数矩阵 board ，方格按从 1 到 n^2 编号，编号遵循 转行交替方式 ，从左下角开始 （即，从 board[n - 1][0] 开始）的每一行改变方向。
 * <p>
 * 你一开始位于棋盘上的方格  1。每一回合，玩家需要从当前方格 curr 开始出发，按下述要求前进：
 * <p>
 * 选定目标方格 next ，目标方格的编号在范围 [curr + 1, min(curr + 6, n^2)] 。
 * 该选择模拟了掷 六面体骰子 的情景，无论棋盘大小如何，玩家最多只能有 6 个目的地。
 * 传送玩家：如果目标方格 next 处存在蛇或梯子，那么玩家会传送到蛇或梯子的目的地。否则，玩家传送到目标方格 next 。
 * 当玩家到达编号 n^2 的方格时，游戏结束。
 * <p>
 * 如果 board[r][c] != -1 ，位于 r 行 c 列的棋盘格中可能存在 “蛇” 或 “梯子”。那个蛇或梯子的目的地将会是 board[r][c]。编号为 1 和 n^2 的方格不是任何蛇或梯子的起点。
 * <p>
 * 注意，玩家在每次掷骰的前进过程中最多只能爬过蛇或梯子一次：就算目的地是另一条蛇或梯子的起点，玩家也 不能 继续移动。
 * <p>
 * 举个例子，假设棋盘是 [[-1,4],[-1,3]] ，第一次移动，玩家的目标方格是 2 。那么这个玩家将会顺着梯子到达方格 3 ，但 不能 顺着方格 3 上的梯子前往方格 4 。（简单来说，类似飞行棋，玩家掷出骰子点数后移动对应格数，遇到单向的路径（即梯子或蛇）可以直接跳到路径的终点，但如果多个路径首尾相连，也不能连续跳多个路径）
 * 返回达到编号为 n^2 的方格所需的最少掷骰次数，如果不可能，则返回 -1。
 */
public class Code909 {
    /**
     * 不能AC
     * <p>
     * 没有用BFS
     *
     * @param board
     * @return
     */
    public int snakesAndLadders(int[][] board) {
        int times = -1;
        int next = 1;
        int row = board.length;
        int col = board[0].length;
        int size = row * col;
        // 遍历数组 构建图的节点
        // 描述图的连通性
        Map<Integer, List<Integer>> map = new HashMap<>();
        Map<Integer, Node> nodeMap = new HashMap<>();
        int index = 1;
        boolean leftToRight = true;
        for (int i = row - 1; i >= 0; i--) {
            if (leftToRight) {
                for (int j = 0; j < col; j++) {
                    nodeMap.put(index, new Node(index, i, j, board[i][j]));
                    if (board[i][j] != -1) {
                        map.computeIfAbsent(index, k -> new java.util.ArrayList<>()).add(board[i][j]);
                    }
                    index++;
                }
                leftToRight = false;
            } else {
                for (int j = col - 1; j >= 0; j--) {
                    nodeMap.put(index, new Node(index, i, j, board[i][j]));
                    if (board[i][j] != -1) {
                        map.computeIfAbsent(index, k -> new java.util.ArrayList<>()).add(board[i][j]);
                    }
                    index++;
                }
                leftToRight = true;
            }
        }

        while (next != (size - 1)) {
            if (nodeMap.containsKey(next)) {
                // 只能走一步
            } else {
                // 不包含 直接去直接去下一个节点
                next = next + 1;
            }
        }


        return times;
    }

    /**
     * 正确做法：
     *
     * 每一步怎么走？ 假设在当前编号curr,那么可以移动到[curr + 1, min(curr + 6, n^2)]中的编号y
     * 如果编号y对应的board[r][c] > 0 那么继续移动到编号board[r][c]
     * 终点n^2
     * 如果无法到达终点（有环） 返回-1
     *
     * 细节： 如何把编号y转换成board[r][c]的坐标？ 反过来又如何把board[r][c]转换成编号y？ 这两者是相互的
     * 先确认行号 在确认列号
     *
     * 如何把编号 y 转成行号 r 和列号 c？
     *
     * 先确定行号 r，再确定列号 c。
     *
     * 把编号减一可以看得更清楚，比如示例 1：
     *
     * n=6。
     * 编号 [0,5] 在倒数 0 行。
     * 编号 [6,11] 在倒数 1 行。
     * 编号 [12,17] 在倒数 2 行。
     * ……
     * 一般地，y−1 在倒数 r′=⌊(y-1)/n⌋ 行，即正数 r=n−1−r′行。
     *在 y−1 下面有完整的 r′行，所以在 r′行还剩下 c′=y−1−r′ ⋅n=(y−1)mod n：
     * 如果 r ′是偶数，那么列号 c=c ′。
     * 如果 r′是奇数，那么列号 c=n−1−c′
     *
     *
     * @param board
     * @return
     */
    public int snakesAndLadders2(int[][] board) {
        int n = board.length;
        boolean[] vis = new boolean[n * n + 1];
        vis[1] = true; // 题目保证起点没有蛇梯，不写也可以
        List<Integer> q = new ArrayList<>();
        q.add(1); // 起点
        for (int step = 0; !q.isEmpty(); step++) {
            // 使用双集合模拟队列层级
            List<Integer> tmp = q;
            q = new ArrayList<>();
            for (int x : tmp) {
                if (x == n * n) { // 终点
                    return step;
                }
                for (int y = x + 1; y <= Math.min(x + 6, n * n); y++) {
                    int r = (y - 1) / n;
                    int c = (y - 1) % n;
                    if (r % 2 > 0) {
                        c = n - 1 - c; // 奇数行从右到左
                    }
                    int nxt = board[n - 1 - r][c];
                    if (nxt < 0) {
                        nxt = y;
                    }
                    if (!vis[nxt]) {
                        vis[nxt] = true; // 有环的情况下，避免死循环
                        q.add(nxt);
                    }
                }
            }
        }
        return -1; // 无法到达终点
    }

    /**
     * 图的宽度优先遍历 使用队列实现
     *
     * @param board
     * @return
     */
    public int snakesAndLadders3(int[][] board) {
        int n = board.length;
        boolean[] vis = new boolean[n * n + 1];
        vis[1] = true; // 题目保证起点没有蛇梯，不写也可以
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        for (int step = 0; !queue.isEmpty(); step++) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int x = queue.poll();
                if (x == n * n) { // 终点
                    return step;
                }
                for (int y = x + 1; y <= Math.min(x + 6, n * n); y++) {
                    int r = (y - 1) / n;
                    int c = (y - 1) % n;
                    if (r % 2 > 0) {
                        c = n - 1 - c; // 奇数行从右到左
                    }
                    int nxt = board[n - 1 - r][c];
                    if (nxt < 0) {
                        nxt = y;
                    }
                    if (!vis[nxt]) {
                        vis[nxt] = true; // 有环的情况下，避免死循环
                        queue.add(nxt);
                    }
                }
            }
        }
        return -1; // 无法到达终点
    }

    /**
     * 定义图的节点
     *
     */
    class Node {
        int index;
        int row;
        int col;
        int value;

        Node(int index, int row, int col, int value) {
            this.index = index;
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }
}

