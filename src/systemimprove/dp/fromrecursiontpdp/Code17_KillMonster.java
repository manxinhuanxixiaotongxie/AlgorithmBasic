package systemimprove.dp.fromrecursiontpdp;

/**
 * 给定3个参数，N，M，K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次在[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 */
public class Code17_KillMonster {

    /**
     * 给定三个参数 N M K
     * <p>
     * 英雄每次打击 都会让怪兽在[0-M]等概率流失血量
     * k次打击之后 英雄把怪兽砍死的概率
     *
     * @param N
     * @param M
     * @param k
     * @return
     */
    public static double killMonster(int N, int M, int k) {
        if (N < 1 || M < 1 || k < 1) {
            return 0;
        }
        int ways = process(M, N, k);
        // 总共可以砍的刀数是
        // M+1的k次方
        int all = (int) Math.pow(M + 1, k);
        return ways / (double) all;
    }

    /**
     * 在0-M上等概率丢失的血量
     * 血量还剩rest,还剩下times刀没有砍
     *
     * @param rest
     * @return
     */
    public static int process(int M, int rest, int times) {
        if (times == 0) {
            // 如果血量已经没有了 说明怪兽已经被砍死
            // 找到一种砍死怪兽的方式
            return rest <= 0 ? 1 : 0;
        }
        // 还有一个隐藏的公式计算
        // 如果刀数还没有砍完 但是怪兽可以使用的血量已经没有了
        // 那么可以快速求得剩下的刀数都是0的情况下的砍死怪兽的方式
        if (rest <= 0) {
            return (int) Math.pow(M + 1, times);
        }

        // 当前刀能够损失的血量
        int ways = 0;
        for (int i = 0; i <= M; i++) {
            ways += process(M, rest - i, times - 1);
        }
        return ways;
    }

    /**
     * 将这个方法改成动态规划
     */
    public static double killMonster2(int N, int M, int k) {
        if (N < 1 || M < 1 || k < 1) {
            return 0;
        }
        int[][] dp = new int[k + 1][N + 1];
        dp[0][0] = 1;
        // 普遍位置
        for (int times = 1; times <= k; times++) {
            for (int rest = 0; rest <= N; rest++) {
                for (int i = 0; i <= M; i++) {
                    dp[times][rest] += ((rest - i <= 0) ? (int) Math.pow(M + 1, times - 1) : dp[times - 1][rest - i]);
                }
            }
        }
        int ways = dp[k][N];
        // 总共可以砍的刀数是
        // M+1的k次方
        int all = (int) Math.pow(M + 1, k);
        return ways / (double) all;
    }

    /**
     * 寻找进一步优化
     * 看能不能省掉枚举行为
     */
    public static double killMonster3(int N, int M, int k) {
        if (N < 1 || M < 1 || k < 1) {
            return 0;
        }
        int[][] dp = new int[k + 1][N + 1];
        dp[0][0] = 1;
        // 普遍位置
//        for (int times = 1; times <= k; times++) {
//            for (int rest = 0; rest <= N; rest++) {
//                for (int i = 0; i <= M; i++) {
//                    dp[times][rest] += ((rest - i <= 0) ? (int) Math.pow(M + 1, times-1) : dp[times - 1][rest - i]);
//                }
        // 分析是否可以优化掉枚举行为
        // 当前行依赖上一行的枚举行为
        // 血量是7
        // 行数刀数 列是剩余的血量
        // dp[5][4] = dp[4][4] + dp[4][3] + dp[4][2] + dp[4][1] + dp[4][0] + Math.pow(M+1, 4)
        // dp[5][5] = dp[4][5] + dp[4][4] + dp[4][3] + dp[4][2] + dp[4][1] + dp[4][0] + Math.pow(M+1, 4)
        // 总共加M+1个
        // 左边位置已经计算了上一行的所有的枚举行为
        //
        //
        // 依赖上面的位置以及左边的位置
        //
//            }
        for (int times = 1; times <= k; times++) {
            dp[times][0] = (int) Math.pow(M + 1, times);
            for (int rest = 1; rest <= N; rest++) {
                dp[times][rest] = dp[times][rest - 1] + dp[times - 1][rest];
                // 要减去多余的部分
                if (rest - 1 - M >= 0) {
                    dp[times][rest] -= dp[times - 1][rest - 1 - M];
                } else {
                    dp[times][rest] -= Math.pow(M + 1, times - 1);
                }
            }
        }

        int ways = dp[k][N];
        // 总共可以砍的刀数是
        // M+1的k次方
        int all = (int) Math.pow(M + 1, k);
        return ways / (double) all;
    }

    public static double right(int N, int M, int K) {
        if (N < 1 || M < 1 || K < 1) {
            return 0;
        }
        long all = (long) Math.pow(M + 1, K);
        long[][] dp = new long[K + 1][N + 1];
        dp[0][0] = 1;
        for (int times = 1; times <= K; times++) {
            dp[times][0] = (long) Math.pow(M + 1, times);
            for (int hp = 1; hp <= N; hp++) {
                dp[times][hp] = dp[times][hp - 1] + dp[times - 1][hp];
                if (hp - 1 - M >= 0) {
                    dp[times][hp] -= dp[times - 1][hp - 1 - M];
                } else {
                    dp[times][hp] -= Math.pow(M + 1, times - 1);
                }
            }
        }
        long kill = dp[K][N];
        return (double) ((double) kill / (double) all);
    }

    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 200;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = right(N, M, K);
            double ans2 = killMonster(N, M, K);
            double ans3 = killMonster2(N, M, K);
            double ans4 = killMonster3(N, M, K);
            if (ans1 != ans2 || ans1 != ans3 || ans1 != ans4) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
