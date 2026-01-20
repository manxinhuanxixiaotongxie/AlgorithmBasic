package leetcode.classic150;

import java.util.Set;

public class Code079 {

    // 定义两个辅助数组用于标识四个方向 上 右 下 左
    private static final int[] xDir = {0, 1, 0, -1};
    private static final int[] yDir = {1, 0, -1, 0};

    public boolean exist(char[][] board, String word) {
        // 从任一位置出发
        boolean ans = false;
        char[] wordStr = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (process(board, i, j, wordStr, new java.util.HashSet<>(), 0)) {
                    return true;
                }
            }
        }
        return ans;
    }

//    public boolean process(char[][] board, int r, int col, char[] wordStr, Set<String> set, int index) {
//        // 从 r col出发 是否能够找到
//        if (index == wordStr.length - 1) {
//            return board[r][col] == wordStr[index];
//        }
//        boolean ans = false;
//        if (set.contains(r + "," + col)) {
//            return false;
//        }
//        for (int i = 0; i < 4; i++) {
//            if (isValid(r + xDir[i], col + yDir[i], board.length, board[0].length, set, wordStr, index)) {
//                // 有效
//                if (board[r][col] == wordStr[index]) {
//                    String posKey = r + "," + col;
//                    set.add(posKey);
//                    if (process(board, r + xDir[i], col + yDir[i], wordStr, set, index + 1)) {
//                        return true;
//                    }
//                    set.remove(posKey);
//                }
//            }
//        }
//        return ans;
//    }

    public boolean process(char[][] board, int r, int col, char[] wordStr, Set<String> set, int index) {
        // 越界或已访问直接返回
        if (r < 0 || r >= board.length || col < 0 || col >= board[0].length) return false;
        String posKey = r + "," + col;
        if (set.contains(posKey)) return false;
        // 字符不匹配直接返回
        if (board[r][col] != wordStr[index]) return false;
        // 匹配到最后一个字符
        if (index == wordStr.length - 1) return true;
        set.add(posKey);
        for (int i = 0; i < 4; i++) {
            int nr = r + xDir[i], nc = col + yDir[i];
            if (process(board, nr, nc, wordStr, set, index + 1)) {
                set.remove(posKey);
                return true;
            }
        }
        set.remove(posKey);
        return false;
    }

    private boolean isValid(int r, int col, int rows, int cols, Set<String> visited, char[] wordStr, int index) {
        // 判断是否越界或已访问
        if (r < 0 || r >= rows || col < 0 || col >= cols || index > wordStr.length) {
            return false;
        }
        return true;
    }
}
