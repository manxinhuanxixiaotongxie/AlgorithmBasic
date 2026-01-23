package leetcode.classic150;

public class Code063 {
    /**
     * 返回机器人能够到达右下角的不同路径数量
     *
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0 || obstacleGrid[0][0] == 1) {
            return 0;
        }
        int row = obstacleGrid.length - 1;
        int col = obstacleGrid[0].length - 1;
        return process(obstacleGrid, row, col, 0, 0);
    }

    public int process(int[][] obstacleGrid, int row, int col, int curRow, int curCol) {
        if (curRow == row && curCol == col) {
            // 能够到最后一个位置说明找到一种方式
            return obstacleGrid[row][col] == 1 ? 0 : 1;
        } else if (curRow == row) {
            // 只能向右走
            return obstacleGrid[curRow][curCol + 1] == 1 ? 0 : process(obstacleGrid, row, col, curRow, curCol + 1);
        } else if (curCol == col) {
            // 只能向下走
            return obstacleGrid[curRow + 1][curCol] == 1 ? 0 : process(obstacleGrid, row, col, curRow + 1, curCol);
        } else {
            // 普遍位置 可以向右走 可以向下走 这里要注意运算符的优先级
            return (obstacleGrid[curRow][curCol + 1] == 1 ? 0 : process(obstacleGrid, row, col, curRow, curCol + 1))
                    + (obstacleGrid[curRow + 1][curCol] == 1 ? 0 : process(obstacleGrid, row, col, curRow + 1, curCol));
        }
    }

    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0 || obstacleGrid[0][0] == 1) {
            return 0;
        }
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        dp[row - 1][col - 1] = obstacleGrid[row-1][col-1] == 1 ? 0 : 1;
        // 最后一行
        for (int i = col - 2; i >= 0; i--) {
            dp[row - 1][i] = obstacleGrid[row-1][i + 1] == 1 ? 0 : dp[row - 1][i + 1];
        }


        // 最后一列
        for (int i = row - 2; i >= 0; i--) {
            dp[i][col - 1] = obstacleGrid[i + 1][col - 1] == 1 ? 0 : dp[i + 1][col - 1];
        }

        // 普遍位置
        for (int curRow = row - 2; curRow >= 0; curRow--) {
            for (int curCol = col - 2; curCol >= 0; curCol--) {
                dp[curRow][curCol] = (obstacleGrid[curRow][curCol + 1] == 1 ? 0 : dp[curRow][curCol + 1])
                        + (obstacleGrid[curRow + 1][curCol] == 1 ? 0 : dp[curRow + 1][curCol]);
            }
        }

        return dp[0][0];
    }

    static void main() {
        int[][] obstacleGrid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        Code063 code063 = new Code063();
        int result = code063.uniquePathsWithObstacles(obstacleGrid);
        System.out.println(result);
    }
}
