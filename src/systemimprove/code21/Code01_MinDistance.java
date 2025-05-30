package systemimprove.code21;

/**
 * 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * 返回最小距离累加和
 */
public class Code01_MinDistance {

    public static int minDistance(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        return process(matrix, 0, 0);
    }

    public static int process(int[][] matrix, int row, int col) {
        if (row == matrix.length - 1 && col == matrix[0].length - 1) {
            return matrix[row][col];
        }

        if (row == matrix.length - 1) {
            return matrix[row][col] + process(matrix, row, col + 1);
        }
        if (col == matrix[0].length - 1) {
            return matrix[row][col] + process(matrix, row + 1, col);
        }
        // 讨论可能性
        // 向下走
        int down = process(matrix, row + 1, col);
        // 向右走
        int right = process(matrix, row, col + 1);
        return matrix[row][col] + Math.min(down, right);

    }

    public static int minDistanceDp(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] dp = new int[row][col];
        dp[row - 1][col - 1] = matrix[row - 1][col - 1];
        // 先填最后一行
        for (int c = col - 2; c >= 0; c--) {
            dp[row - 1][c] = matrix[row - 1][c] + dp[row - 1][c + 1];
        }
        // 填最后一列
        for (int r = row - 2; r >= 0; r--) {
            dp[r][col - 1] = matrix[r][col - 1] + dp[r + 1][col - 1];
        }
        for (int r = row - 2; r >= 0; r--) {
            for (int c = col - 2; c >= 0; c--) {
                dp[r][c] = matrix[r][c] + Math.min(dp[r + 1][c], dp[r][c + 1]);
            }
        }
        return dp[0][0];

    }

    /**
     * 空间压缩技巧
     * <p>
     * 使用一维数组
     *
     * @param matrix
     * @return
     */
    public static int minDistanceDp2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        int row = matrix.length;
        int col = matrix[0].length;
//        int[][] dp = new int[row][col];
//        dp[row - 1][col - 1] = matrix[row - 1][col - 1];
//        // 先填最后一行 只能向右走
//        for (int c = col - 2; c >= 0; c--) {
//            dp[row - 1][c] = matrix[row - 1][c] + dp[row - 1][c + 1];
//        }
//        // 填最后一列 只能像下走
//        for (int r = row - 2; r >= 0; r--) {
//            dp[r][col - 1] = matrix[r][col - 1] + dp[r + 1][col - 1];
//        }
//        for (int r = row - 2; r >= 0; r--) {
//            for (int c = col - 2; c >= 0; c--) {
//                dp[r][c] = matrix[r][c] + Math.min(dp[r + 1][c], dp[r][c + 1]);
//            }
//        }
        // 只使用一个数组代替原有的二位数组 进行数组压缩
        // 创建一个与matrix数组列数相同的一维数组
        int[] dp = new int[col];
        // 将最后一行的数据填充到dp数组中
        dp[col - 1] = matrix[row - 1][col - 1];
        for (int i = col - 2; i >= 0; i--) {
            dp[i] = matrix[row - 1][i] + dp[i + 1];
        }
        // dp数组已经有了一个的原始数据
        // 从倒数第第二行开始填充
        for (int r = row - 2; r >= 0; r--) {
            // 最后一列的数 原始动态规划 最后一列的值依赖的是下一列与当前值相加
            dp[col - 1] += matrix[r][col - 1];
            for (int c = col - 2; c >= 0; c--) {
                dp[c] = matrix[r][c] + Math.min(dp[c], dp[c + 1]);
            }
        }
        return dp[0];

    }

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

    public static int minPathSum2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[] dp = new int[col];
        dp[0] = m[0][0];
        for (int j = 1; j < col; j++) {
            dp[j] = dp[j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            dp[0] += m[i][0];
            for (int j = 1; j < col; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + m[i][j];
            }
        }
        return dp[col - 1];
    }

    public static void main(String[] args) {

        int rowSize = 10;
        int colSize = 10;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            int[][] m = generateRandomMatrix(rowSize, colSize);
            int ans1 = minDistance(m);
            int ans2 = minPathSum2(m);
            int ans3 = minDistanceDp(m);
            int ans4 = minDistanceDp2(m);
            if (ans1 != ans2 || ans1 != ans3 || ans1 != ans4) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }
}
