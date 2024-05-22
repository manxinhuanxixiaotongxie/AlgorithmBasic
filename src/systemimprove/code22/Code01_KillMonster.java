package systemimprove.code22;

/**
 * 给定3个参数，N，M，K
 * 怪兽有N滴血，等着英雄来砍自己
 * 英雄每一次打击，都会让怪兽流失[0~M]的血量
 * 到底流失多少？每一次在[0~M]上等概率的获得一个值
 * 求K次打击之后，英雄把怪兽砍死的概率
 */
public class Code01_KillMonster {

    public double killMonster(int N, int M, int k) {
        if (N < 1 || M < 1 || k < 1) {
            return 0;
        }
        return process1(N, M, k) / Math.pow(M + 1, k);
    }

    /**
     * 设计一个递归函数 返回的是 砍死怪兽的次数
     *
     * @return
     */
    public long process1(int N, int M, int k) {
        // 三个参数的含义是 N是怪兽生育的血量 M是英雄每次打击的血量 k是英雄剩余打击的次数
        if (k == 0) {
            return N <= 0 ? 1 : 0;
        }
        // 递归函数这样设计 从 0-M的血量上等概率的获得一个值
        long ans = 0;
        for (int i = 0; i <= M; i++) {
            ans += process1(N - i, M, k - 1);
        }
        return ans;
    }

    public double killMonster2(int N, int M, int k) {
        if (N < 1 || M < 1 || k < 1) {
            return 0;
        }
        return dp1(N, M, k) / Math.pow(M + 1, k);
    }

    /**
     * 在process1的基础上 将process1改成动态规划
     *
     * @param N
     * @param M
     * @param k
     * @return
     */
    public long dp1(int N, int M, int k) {
        // 三个参数的含义是 N是怪兽生育的血量 M是英雄每次打击的血量 k是英雄剩余打击的次数
        long[][] dp = new long[k + 1][N + 1];
        dp[0][0] = 1;
        for (int r = 1; r <= k; r++) {
            for (int c = 0; c <= N; c++) {
                for (int i = 0; i <= M; i++) {
                    // 这里必须要有一个转化 在原始递归的过程中 当怪兽血量的等于0的时候返回的是1  那么在改成动态规划的时候 当血量已经小于0 那么这个时候
                    // 能砍死怪兽的次数就是（m+1）的r-1次方
                    // 为什么是r-1次方 不是r次方 是因为当前刀已经砍掉了
                    dp[r][c] += c - i >= 0 ? dp[r - 1][c - i] : (long) Math.pow(M + 1, r - 1);
                }
            }
        }

        return dp[k][N];

    }

    // for test
    public static double dp2(int N, int M, int K) {
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
                    dp[times][hp] -= (long) Math.pow(M + 1, times - 1);
                }
            }
        }
        long kill = dp[K][N];
        return (double) kill / (double) all;
    }

    public static void main(String[] args) {
        int NMax = 10;
        int MMax = 10;
        int KMax = 10;
        int testTime = 10000;
        Code01_KillMonster kill = new Code01_KillMonster();
        int wrongTime = 0;
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * NMax);
            int M = (int) (Math.random() * MMax);
            int K = (int) (Math.random() * KMax);
            double ans1 = kill.killMonster(N, M, K);
            double ans2 = dp2(N, M, K);
            double ans3 = kill.killMonster2(N, M, K);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops!");
                wrongTime++;
            }
        }
        System.out.println("测试结束");
        System.out.println("错误次数" + wrongTime);
    }
}
