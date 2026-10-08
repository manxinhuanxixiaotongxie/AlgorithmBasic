package leetcode.review.newer;

public class Code14 {
    public int[][] transpose(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] ans = new int[col][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ans[j][i] = matrix[i][j];
            }
        }
        return ans;
    }
}
