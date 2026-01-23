package leetcode.classic150;

public class Code221 {
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row + 1][col + 1];
        int ans = 0;
        // 填写dp
        // 第一行
        for (int i = 0; i < col; i++) {
            dp[0][i] = matrix[0][i] - '0';
        }
        // 第一列
        for (int i = 0; i < row; i++) {
            dp[i][0] = matrix[i][0] - '0';
        }
        // 普遍位置
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (matrix[i][j] == '0') {
                    continue;
                }
                // 当前位置是
                int left = dp[i][j - 1];
                int up = dp[i - 1][j];
                // 左上角
                int leftUp = dp[i - 1][j - 1];
                dp[i][j] = Math.min(Math.min(left, up), leftUp) + 1;
            }
        }
        // 遍历dp，找出最大值
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ans = Math.max(ans, dp[i][j] * dp[i][j]);
            }
        }
        return ans;
    }
}
