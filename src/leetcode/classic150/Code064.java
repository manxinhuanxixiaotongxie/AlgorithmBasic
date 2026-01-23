package leetcode.classic150;

public class Code064 {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length - 1;
        int col = grid[0].length - 1;
        return process(grid, row, col, 0, 0);
    }

    public int process(int[][] grid, int row, int col, int curRow, int curCol) {
        if (curRow == row && curCol == col) {
            return grid[row][col];
        } else if (col == curCol) {
            // 向下走
            return grid[curRow][curCol] + process(grid, row, col, curRow + 1, curCol);
        } else if (row == curRow) {
            // 向右走
            return grid[curRow][curCol] + process(grid, row, col, curRow, curCol + 1);
        } else {
            return grid[curRow][curCol] + Math.min(process(grid, row, col, curRow + 1, curCol),
                    process(grid, row, col, curRow, curCol + 1));
        }
    }

    /**
     * 改成动态规划
     *
     * @param grid
     * @return
     */
    public int minPathSum2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int row = grid.length ;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[row - 1][col - 1] = grid[row - 1][col - 1];
        // 最后一行
        for (int j = col - 2; j >= 0; j--) {
            dp[row - 1][j] = grid[row - 1][j] + dp[row - 1][j + 1];
        }
        // 最后一列
        for (int i = row - 2; i >= 0; i--) {
            dp[i][col - 1] = grid[i][col - 1] + dp[i + 1][col - 1];
        }
        // 普遍位置
        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                dp[i][j] = grid[i][j] + Math.min(dp[i + 1][j], dp[i][j + 1]);
            }
        }
        return dp[0][0];
    }
}
