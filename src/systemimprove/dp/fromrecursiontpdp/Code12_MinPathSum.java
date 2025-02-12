package systemimprove.dp.fromrecursiontpdp;

/**
 * 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * 返回最小距离累加和
 * <p>
 * <p>
 * 返回最小距离累加和
 */
public class Code12_MinPathSum {

    public static int minPathSum1(int[][] m) {
        return process(m, m.length - 1, m[0].length - 1, 0, 0);
    }


    public static int process(int[][] m, int row, int col, int x, int y) {
        // row col 是固定参数
        if (x == row && y == col) {
            return m[x][y];
        }

        if (x == row) {
            // 只能往右走
            return m[x][y] + process(m, row, col, x, y + 1);
        }
        if (y == col) {
            // 只能往下走
            return m[x][y] + process(m, row, col, x + 1, y);
        }
        // 普遍位置
        int right = process(m, row, col, x, y + 1);
        int down = process(m, row, col, x + 1, y);
        return m[x][y] + Math.min(right, down);
    }

    /**
     * 改成DP
     *
     * @param m
     * @return
     */
    public static int minPathSum2(int[][] m) {
        // x y 的范围是0-row-1 0-col-1
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        // 那么这张表格的位置就是够的
        // 右下角的位置是确定的
        dp[row - 1][col - 1] = m[row - 1][col - 1];
        // 最下面一行的位置依赖
        for (int j = col - 2; j >= 0; j--) {
            dp[row - 1][j] = dp[row - 1][j + 1] + m[row - 1][j];
        }
        // 最后一列依赖下面的位置
        for (int j = row - 2; j >= 0; j--) {
            dp[j][col - 1] = dp[j + 1][col - 1] + m[j][col - 1];
        }
        // 普遍位置的依赖 依赖下一行的位置 或者是右边的位置
        // 从倒数第二行的位置开始填起 列从右往左开始填写
        for (int i = row - 2; i >= 0; i--) {
            for (int j = col - 2; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + m[i][j];
            }
        }
        return dp[0][0];
    }

    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }

    public static int right(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    public static void main(String[] args) {
        int rowSize = 10;
        int colSize = 10;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int time = 0; time < testTimes; time++) {
            int[][] m = generateRandomMatrix(rowSize, colSize);
            int ans1 = minPathSum1(m);
            int ans2 = minPathSum2(m);
            int ans3 = right(m);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
