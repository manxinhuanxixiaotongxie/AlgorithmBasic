package leetcode.classic150;

import java.util.LinkedList;

/**
 * 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
 */
public class Code073 {
    /**
     * 并查集
     *
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        LinkedList<int[]> queue = new LinkedList<>();
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    queue.add(new int[] {i, j});
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            // 将对应的行 列都置换成0
            int r = cur[0];
            int c = cur[1];
            for (int i = 0; i < col; i++) {
                matrix[r][i] = 0;
            }
            for (int i = 0; i < row; i++) {
                matrix[i][c] = 0;
            }
        }
    }


}
