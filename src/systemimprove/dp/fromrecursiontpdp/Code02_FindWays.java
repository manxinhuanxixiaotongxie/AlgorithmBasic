package systemimprove.dp.fromrecursiontpdp;

/**
 * 假设有排成一行的N个位置，记为1~N，N 一定大于或等于 2
 * 开始时机器人在其中的M位置上(M 一定是 1~N 中的一个)
 * 如果机器人来到1位置，那么下一步只能往右来到2位置；
 * 如果机器人来到N位置，那么下一步只能往左来到 N-1 位置；
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走；
 * 规定机器人必须走 K 步，最终能来到P位置(P也是1~N中的一个)的方法有多少种
 * 给定四个参数 N、M、K、P，返回方法数。
 */
public class Code02_FindWays {

    /**
     * 使用暴力递归的方式进行求解
     *
     * @return
     */
    public int ways1(int N, int M, int k, int p) {
        if (M > N || M < 1 || p < 1 || p > N || k < 0) {
            return -1;
        }
        // 基础条件过滤
        if (k == 0) {
            // 一步都不能走
            if (M == p) {
                return 1;
            } else {
                return 0;
            }
        }
        // 暴力递归的参数定义之后
        // 主函数的调用方式
        // 当前位置在M 剩余的rest的步数是k

        return process(N, p, M, k);
    }

    /**
     * 设计一个暴力递归函数
     * 必须要走rest步
     * <p>
     * 返回最终走到p位置的方法数
     *
     * @return
     */
    public int process(int N, int p, int cur, int rest) {
        // 如果rest等于0
        // 意味着已经走了k步
        // 如果当前位置已经来到了p位置上
        // 说明已经找到了一种步行的方式
        if (rest == 0) {
            return cur == p ? 1 : 0;
        }
        // 如果cur来到了1位置
        // 只能向右走
        if (cur == 1) {
            return process(N, p, cur + 1, rest - 1);
        }
        // 如果cur来到了N位置
        // 只能向左走
        if (cur == N) {
            return process(N, p, cur - 1, rest - 1);
        }
        // 如果cur来到了中间位置
        // 可以向左走或者向右走
        return process(N, p, cur + 1, rest - 1) + process(N, p, cur - 1, rest - 1);
    }

    /**
     * 将上述暴力递归改成动态规划
     * 1.有重复解
     * 2.分析严格位置依赖关系
     *
     * @param N
     * @param M
     * @param k
     * @param P
     * @return
     */
    public int ways2(int N, int M, int k, int P) {
        if (N < 2 || k < 1 || M < 1 || M > N || P < 1 || P > N) {
            return 0;
        }
        // 使用rest做行
        // cur做列
        // 我们最终要求的位置是
        // dp[start][k]
        // 整张表右下角的额位置
        // 根据basecase 当rest==0时
        // 只有cur==P的时候才是1

        // rest cur依赖的位置
        // 当cur==1的时候  依赖的位置是右上角
        // 当cur==N的时候  依赖的位置是左上角
        // 普遍位置依赖的位置是左上角和右上角
        // 从第1行开始填写
        // 从最后一列开始填写
        // M是开始位置
        // N是固定参数
        // 可变参数   cur 开始的位置是M    rest剩余的步数刚开始是k
        int[][] dp = new int[k + 1][N + 1];
        // 严格位置依赖 当前位置依赖左上角和右上角
        dp[0][P] = 1;
        // 当前位置从1开始
        for (int i = 1; i <= k; i++) {
            // 第一列
            dp[i][1] = dp[i - 1][2];
            // 普遍位置
            for (int j = 2; j < N; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j + 1];
            }
            // 最后一列的
            dp[i][N] = dp[i - 1][N - 1];
        }

        return dp[k][M];
    }

    public static void main(String[] args) {
        Code02_FindWays code02_findWays = new Code02_FindWays();
        System.out.println(code02_findWays.ways1(5, 2, 6, 4));
        System.out.println(code02_findWays.ways2(5, 2, 6, 4));
    }
}
