package leetcode.classic150;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Code909 {
    /**
     * 不能AC
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
