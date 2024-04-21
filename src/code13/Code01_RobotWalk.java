package code13;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-07-15 15:10
 */
public class Code01_RobotWalk {

    public static int ways1(int N, int start, int aim, int k) {
        if (N <= 1 || start < 1 || start > N || aim < 1 || aim > N || k < 0) {
            return 0;
        }
        return process1(N, start, k, aim);
    }

    /**
     * 对于process1函数，其递归调用的二叉树形式如下：
     * <p>
     * process1(4, 1, 2, 3)
     * |
     * process1(4, 2, 1, 3)
     * /                   \
     * process1(4, 3, 0, 3)        process1(4, 1, 1, 3)
     * |                             |
     * 1                         process1(4, 2, 0, 3)
     * |
     * 1
     * 对于process2函数，其递归调用的二叉树形式如下：
     * <p>
     * process2(4, 1, 2, 3)
     * |
     * process2(4, 2, 1, 3)
     * /                   \
     * process2(4, 1, 1, 3)        process2(4, 3, 0, 3)
     * |                             |
     * 1                         process2(4, 2, 0, 3)
     * |
     * 0
     * 可以看到，在这个例子中，process1和process2函数的递归调用的二叉树形式也是不同的。在process1函数中，先递归到了左子树，然后再递归到右子树；而在process2函数中，先递归到了右子树，然后再递归到左子树。这导致了最后一层的叶子节点返回值的差异，即process1函数返回1，而process2函数返回0。
     *
     * @param N
     * @param cur
     * @param rest
     * @param aim
     * @return
     */
    public static int process1(int N, int cur, int rest, int aim) {
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }
        if (cur == 1) {
            return process1(N, 2, rest - 1, aim);
        }
        if (cur == N) {
            return process1(N, N - 1, rest - 1, aim);
        }
        return process1(N, cur + 1, rest - 1, aim) + process1(N, cur - 1, rest - 1, aim);
    }

    /**
     * 这个递归没错
     * 这个跟上面的递归是一样
     *
     * @param N
     * @param cur
     * @param rest
     * @param aim
     * @return
     */
    public static int process2(int N, int cur, int rest, int aim) {
        if (rest == 0) {
            return cur == aim ? 1 : 0;
        }
        if (cur == 1) {
            return process2(N, cur + 1, rest - 1, aim);
        }
        if (cur == N) {
            return process2(N, N - 1, rest - 1, aim);
        }
        return process2(N, cur + 1, rest - 1, aim) + process2(N, cur - 1, rest - 1, aim);
    }

    public static int ways2(int N, int start, int aim, int K) {
        if (N <= 1 || start < 1 || start > N || aim < 1 || aim > N || K < 0) {
            return 0;
        }
        return process2(N, start, K, aim);
    }

    public static int ways3(int N, int start, int aim, int k) {
        if (N <= 1 || start < 1 || start > N || aim < 1 || aim > N || k < 0) {
            return 0;
        }

        int[][] dp = new int[N + 1][k + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = -1;
            }
        }
        return process3(N, start, aim, k, dp);

    }

    /**
     * 这个函数会发生变化的变量只有两个 一个是cur 一个是rest
     * 其中cur的位置可以从0-N
     * rest的位置可以从0-K
     * 记忆化搜索 带着一个缓存表进行递归动作
     *
     * @param N
     * @param cur
     * @param aim
     * @param rest
     * @return
     */
    public static int process3(int N, int cur, int aim, int rest, int[][] dp) {
        if (dp[cur][rest] != -1) {
            return dp[cur][rest];
        }
        int ans;
        if (rest == 0) {
            ans = cur == aim ? 1 : 0;
            dp[cur][rest] = ans;
            return ans;
        }
        if (cur == 1) {
            ans = process3(N, 2, aim, rest - 1, dp);
            dp[cur][rest] = ans;
            return ans;
        }
        if (cur == N) {
            ans = process3(N, N - 1, aim, rest - 1, dp);
            dp[cur][rest] = ans;
            return ans;
        }
        ans = process3(N, cur + 1, aim, rest - 1, dp) + process3(N, cur - 1, aim, rest - 1, dp);
        dp[cur][rest] = ans;
        return ans;
    }

    public static int ways4(int N, int start, int aim, int K) {
        if (N <= 1 || start < 1 || start > N || aim < 1 || aim > N || K < 0) {
            return 0;
        }
        int[][] dp = new int[N + 1][K + 1];
        dp[aim][0] = 1;
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
        System.out.println(ways1(5, 2, 4, 6));
        System.out.println(ways2(5, 2, 4, 6));
        System.out.println(ways3(5, 2, 4, 6));
        System.out.println(ways4(5, 2, 4, 6));
    }

}
