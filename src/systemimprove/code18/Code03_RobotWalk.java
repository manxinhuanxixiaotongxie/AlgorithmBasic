package systemimprove.code18;

/**
 * 本阶段开始进行从暴力递归到动态规划的系列题目 旨在简历从暴力递归到动态规划的过程
 * 清楚动态转移方程的建立过程
 * <p>
 * 以这个题目为例：
 * <p>
 * 机器人走路问题
 * 给定四个参数：
 * 1.N 总共有1-N  N个位置 机器人可以在这个N个位置行自由行走
 * 2.start机器人的初始位置 机器人一开始所处的位置 在1-N上
 * 3.aim是机器人走完之后 希望停留在的点位
 * 4.机器人总共需要走的步数K
 * 规定：机器人走到1位置之后只能向2位置走
 * 机器走到N位置之后 只能往N-1位置走
 */
public class Code03_RobotWalk {

    /**
     * 暴力递归
     *
     * @param N
     * @param start
     * @param aim
     * @param K
     * @return
     */
    public static int ways1(int N, int start, int aim, int K) {
        if (N <= 1 || start < 0 || start > N || aim < 0 || aim > N || K < 0) {
            return 0;
        }
        return process1(start, aim, K, N);

    }

    /**
     * 我们规定这个函数的意义就是从start位置出发，走rest步之后，最终停在aim位置的方法数是多少
     *
     * @param cur
     * @param aim
     * @param rest
     * @return
     */
    public static int process1(int cur, int aim, int rest, int N) {
        // 如果剩余步数为0 已经没有步数可以走 那么只有当的aim与当前走到的位置是一个位置的时候可以返回1 其他返回0
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }
        // 当前节点已经走到了1位置 按照题目题目规定 只能往2位置走
        if (cur == 1) {
            return process1(2, aim, rest - 1, N);
        }
        // 当前位置已经走到了N位置 按照题目规定 只能往N-1位置走
        if (cur == N) {
            return process1(N - 1, aim, rest - 1, N);
        }
        return process1(cur - 1, aim, rest - 1, N) + process1(cur + 1, aim, rest - 1, N);
    }

    /**
     * 记忆化搜索
     * 待着一个缓存表进行处理
     *
     * @param N
     * @param start
     * @param aim
     * @param K
     * @return
     */
    public static int ways2(int N, int start, int aim, int K) {
        if (N <= 1 || start < 0 || start > N || aim < 0 || aim > N || K < 0) {
            return 0;
        }
        int[][] dp = new int[K + 1][N + 1];
        for (int i = 0; i <= K; i++) {
            for (int j = 0; j <= N; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(start, aim, K, N, dp);
    }

    public static int process2(int cur, int aim, int rest, int N, int[][] dp) {
        if (dp[rest][cur] != -1) {
            return dp[rest][cur];
        }
        if (rest == 0) {
            dp[rest][cur] = cur == aim ? 1 : 0;
        } else if (cur == 1) {
            dp[rest][cur] = process2(2, aim, rest - 1, N, dp);
        } else if (cur == N) {
            dp[rest][cur] = process2(N - 1, aim, rest - 1, N, dp);
        } else {
            dp[rest][cur] = process2(cur - 1, aim, rest - 1, N, dp) + process2(cur + 1, aim, rest - 1, N, dp);
        }
        return dp[rest][cur];
    }

    public static int ways3(int N, int start, int aim, int K) {
        if (N <= 1 || start < 0 || start > N || aim < 0 || aim > N || K < 0) {
            return 0;
        }
        // 以rest做行 cur做列 分析位置依赖
        // 当rest==0 的时候 只有cur = aim = 1  其余位置0 第一行的数很好填
        // cur==1的时候 依赖的位置是cur=2的以及rest-1 右上角的位置
        //cur ==N 依赖的是cur=N-1的位置以及rest-1的位置 左上角的位置
        //普遍位置依赖的是左上角和右上角的位置
        int[][] dp = new int[K + 1][N + 1];
        // 第一行的位置  只有cur==aim时是1 其余是0 也就是dp[0][aim] = 1
        dp[0][aim] = 1;
        // 使用rest做行 cur做列的时候  我们先从行开始填
        for (int i = 1; i <= K; i++) {
            dp[i][1] = dp[i - 1][2];
            for (int j = 2; j < N; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
            }
            dp[i][N] = dp[i - 1][N - 1];
        }
        return dp[K][start];
    }

    public static int ways4(int N, int start, int aim, int K) {
        if (N <= 1 || start < 0 || start > N || aim < 0 || aim > N || K < 0) {
            return 0;
        }
        // 用cur做行 rest做列
        // 当rest= 0的时候  第一列数的位置只有cur=aim的时候是1 其余是0 dp[aim][0] = 1
        // cur==1的时候 依赖的是cur=2的位置以及rest-1的位置 左下角的位置
        // cur==N的时候依赖的位是N-1 rest-1的位置  左上角的位置
        // 普遍位置 依赖的是 左上角  左下角的位置
        int[][] dp = new int[N + 1][K + 1];
        // rest==0的时候  只有当cur == aim的时候才是1 其余是0
        dp[aim][0] = 1;
        // 使用cur做行 rest做列的时候 当cur==1的时候
        for (int i = 1; i <= K; i++) {
            dp[1][i] = dp[2][i - 1];
            for (int j = 2; j < N; j++) {
                dp[j][i] = dp[j - 1][i - 1] + dp[j + 1][i - 1];
            }
            dp[N][i] = dp[N - 1][i - 1];
        }
        return dp[start][K];

    }

    public static void main(String[] args) {
        int N = 5;
        int start = 2;
        int aim = 4;
        int K = 6;
        System.out.println(ways1(N, start, aim, K));
        System.out.println(ways2(N, start, aim, K));
        System.out.println(ways3(N, start, aim, K));
        System.out.println(ways4(N, start, aim, K));
    }

}
