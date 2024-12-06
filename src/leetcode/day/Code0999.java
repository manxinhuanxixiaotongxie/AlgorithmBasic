package leetcode.day;

public class Code0999 {
    public int numRookCaptures(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return 0;
        }
        int ans = 4;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'R') {
                    int row = i;
                    int col = j;
                    // 四个方向进行遍历
                    // 向上
                    boolean findP = false;
                    boolean findB = false;
                    while (--row >= 0) {
                        if (board[row][col] == 'p') {
                            findP = true;
                            break;
                        } else if (board[row][col] == 'B') {
                            findB = true;
                            ans--;
                            break;
                        }
                    }
                    if (!findP && !findB) {
                        ans--;
                    }
                    row = i;
                    findP = false;
                    findB = false;
                    // 向下
                    while (++row < board.length) {
                        if (board[row][col] == 'p') {
                            findP = true;
                            break;
                        } else if (board[row][col] == 'B') {
                            ans--;
                            findB = true;
                            break;
                        }
                    }
                    if (!findP && !findB) {
                        ans--;
                    }
                    row = i;
                    findP = false;
                    findB = false;
                    // 向左
                    while (--col >= 0) {
                        if (board[row][col] == 'p') {
                            findP = true;
                            break;
                        } else if (board[row][col] == 'B') {
                            ans--;
                            findB = true;
                            break;
                        }
                    }
                    if (!findP && !findB) {
                        ans--;
                    }
                    col = j;
                    findP = false;
                    findB = false;
                    // 向右
                    while (++col < board[0].length) {
                        if (board[row][col] == 'p') {
                            findP = true;
                            break;
                        } else if (board[row][col] == 'B') {
                            ans--;
                            findB = true;
                            break;
                        }
                    }
                    if (!findP && !findB) {
                        ans--;
                    }
                    return ans;

                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code0999 solution = new Code0999();
        System.out.println(solution.numRookCaptures(new char[][]{{'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', 'R', '.', '.', '.', 'p'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', 'p', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.'}}));
    }
}

