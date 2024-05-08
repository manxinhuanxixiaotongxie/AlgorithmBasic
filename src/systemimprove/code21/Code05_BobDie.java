package systemimprove.code21;

/**
 * 给定5个参数，N，M，row，col，k
 * 表示在N*M的区域上，醉汉Bob初始在(row,col)位置
 * Bob一共要迈出k步，且每步都会等概率向上下左右四个方向走一个单位
 * 任何时候Bob只要离开N*M的区域，就直接死亡
 * 返回k步之后，Bob还在N*M的区域的概率
 */
public class Code05_BobDie {

    public static double bobDie(int N, int M, int row, int col, int k) {
        System.out.println("方法1次数:" + process1(N, M, row, col, k));
        return (double) process1(N, M, row, col, k) / Math.pow(4, k);
    }

    public static long process1(int N, int M, int row, int col, int rest) {
        // 这个条件不加会有问题 可能会导致结果算多了
        // 为什么算多了呢？ bob已经走出了棋盘外 但是步数还没有走完 接着走 又走回了期盼内
        if (row < 0 || row == N || col < 0 || col == M) {
            return 0;
        }
        // 还在棋盘中！
        if (rest == 0) {
            return row < N && col < M ? 1 : 0;
        }
        return process1(N, M, row + 1, col, rest - 1)
                + process1(N, M, row - 1, col, rest - 1)
                + process1(N, M, row, col + 1, rest - 1)
                + process1(N, M, row, col - 1, rest - 1);
    }

    public static double dp(int N, int M, int row, int col, int k) {
        // 三个可变参数
        long[][][] dp = new long[N][M][k + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    dp[r][c][rest] = pick(dp, N, M, r - 1, c, rest - 1);
                    dp[r][c][rest] += pick(dp, N, M, r + 1, c, rest - 1);
                    dp[r][c][rest] += pick(dp, N, M, r, c - 1, rest - 1);
                    dp[r][c][rest] += pick(dp, N, M, r, c + 1, rest - 1);
                }
            }
        }
        System.out.println("方法3次数" + dp[row][col][k]);
        return (double) dp[row][col][k] / Math.pow(4, k);
    }

    public int pick(int[][][] dp, int N, int M, int row, int col, int k) {
        if (row < 0 || row == N || col < 0 || col == M) {
            return 0;
        }
        return dp[row][col][k];
    }

    // for test
    public static double livePosibility2(int row, int col, int k, int N, int M) {
        long[][][] dp = new long[N][M][k + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = 1;
            }
        }
        for (int rest = 1; rest <= k; rest++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < M; c++) {
                    dp[r][c][rest] = pick(dp, N, M, r - 1, c, rest - 1);
                    dp[r][c][rest] += pick(dp, N, M, r + 1, c, rest - 1);
                    dp[r][c][rest] += pick(dp, N, M, r, c - 1, rest - 1);
                    dp[r][c][rest] += pick(dp, N, M, r, c + 1, rest - 1);
                }
            }
        }
        System.out.println("方法2次数" + dp[row][col][k]);
        return (double) dp[row][col][k] / Math.pow(4, k);
    }

    public static long pick(long[][][] dp, int N, int M, int r, int c, int rest) {
        if (r < 0 || r == N || c < 0 || c == M) {
            return 0;
        }
        return dp[r][c][rest];
    }

    public static void main(String[] args) {
        // 6 6 10 50 50
        System.out.println(bobDie(50, 50, 6, 6, 10));
        System.out.println(dp(50, 50, 6, 6, 10));
        System.out.println(livePosibility2(6, 6, 10, 50, 50));
    }


}
