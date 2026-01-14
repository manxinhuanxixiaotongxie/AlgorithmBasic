package leetcode.classic150;

import java.util.HashSet;
import java.util.Set;

/**
 * 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 *
 */
public class Code036 {
    /**
     * 暴力解法
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {

        int row = board.length;
        int col = board[0].length;
        for (int i = 0; i < row; i++) {
            Set<Character> rowSet = new HashSet<>();
            Set<Character> colSet = new HashSet<>();
            for (int j = 0; j < col; j++) {
                if (rowSet.contains(board[i][j])) {
                    return false;
                }
                if (board[i][j] != '.') {
                    rowSet.add(board[i][j]);
                }
                if (colSet.contains(board[j][i])) {
                    return false;
                }
                if (board[j][i] != '.') {
                    colSet.add(board[j][i]);
                }
            }
        }

        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                Set<Character> boxSet = new HashSet<>();
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        char c = board[i + k][j + l];
                        if (boxSet.contains(c)) {
                            return false;
                        }
                        if (c != '.') {
                            boxSet.add(c);
                        }
                    }
                }
            }
        }


        return true;
    }

    static void main() {
        Code036 code036 = new Code036();
        //[[".",".",".",".","5",".",".","1","."],[".","4",".","3",".",".",".",".","."],[".",".",".",".",".","3",".",".","1"],["8",".",".",".",".",".",".","2","."],[".",".","2",".","7",".",".",".","."],[".","1","5",".",".",".",".",".","."],[".",".",".",".",".","2",".",".","."],[".","2",".","9",".",".",".",".","."],[".",".","4",".",".",".",".",".","."]]

        char[][] board = {
                {'.', '.', '.', '.', '5', '.', '.', '1', '.'},
                {'.', '4', '.', '3', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '3', '.', '.', '1'},
                {'8', '.', '.', '.', '.', '.', '.', '2', '.'},
                {'.', '.', '2', '.', '7', '.', '.', '.', '.'},
                {'.', '1', '5', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '2', '.', '.', '.'},
                {'.', '2', '.', '9', '.', '.', '.', '.', '.'},
                {'.', '.', '4', '.', '.', '.', '.', '.', '.'}
        };
        boolean validSudoku = code036.isValidSudoku(board);
        System.out.println(validSudoku);
    }
}
