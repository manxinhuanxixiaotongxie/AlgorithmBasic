package leetcode.day;

import java.util.Arrays;

public class Code0935 {
    public int knightDialer(int n) {
        if (n == 1) {
            return 10;
        }
        int ans = 0;
        for (int i = 0; i < 10; i++) {
            ans += process(n - 1, i);
        }
        return ans;
    }

    public int knightDialer2(int n) {
        int[][] dp = new int[n][10];
        for (int i = 0; i < 10; i++) {
            dp[0][i] = 1;
        }
        int ans = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < 10; j++) {
                dp[i][j] = 0;
                if (j == 0) {
                    dp[i][j] += (((dp[i - 1][4]) % 1000000007 + (dp[i - 1][6]) % 1000000007)) % 1000000007;
                }
                if (j == 1) {
                    dp[i][j] += ((dp[i - 1][6]) % 1000000007 + (dp[i - 1][8]) % 1000000007) % 1000000007;
                }
                if (j == 2) {
                    dp[i][j] += ((dp[i - 1][7]) % 1000000007 + (dp[i - 1][9]) % 1000000007) % 1000000007;
                }
                if (j == 3) {
                    dp[i][j] += ((dp[i - 1][4]) % 1000000007 + (dp[i - 1][8]) % 1000000007) % 1000000007;
                }
                if (j == 4) {
                    dp[i][j] += ((dp[i - 1][3]) % 1000000007 + (dp[i - 1][9] + dp[i - 1][0]) % 1000000007) % 1000000007;
                }
                if (j == 6) {
                    dp[i][j] += ((dp[i - 1][1]) % 1000000007 + (dp[i - 1][7] + dp[i - 1][0]) % 1000000007) % 1000000007;
                }
                if (j == 7) {
                    dp[i][j] += ((dp[i - 1][2]) % 1000000007 + (dp[i - 1][6]) % 1000000007) % 1000000007;
                }
                if (j == 8) {
                    dp[i][j] += ((dp[i - 1][1]) % 1000000007 + (dp[i - 1][3]) % 1000000007) % 1000000007;
                }
                if (j == 9) {
                    dp[i][j] += ((dp[i - 1][2]) % 1000000007 + (dp[i - 1][4]) % 1000000007) % 1000000007;
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            ans = (ans + (dp[n - 1][i])) % 1000000007;
        }
        return ans;

    }

    public int knightDialer4(int n) {
        int MOD = 1000000007;
        int[][] help = new int[][]{
                {4, 6},
                {6, 8},
                {7, 9},
                {4, 8},
                {0, 3, 9},
                {},
                {0, 1, 7},
                {2, 6},
                {1, 3},
                {2, 4}
        };
        int[][] dp = new int[2][10];
        // 空间压缩
        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            int x = i & 1;
            // 如果x==1 说明x是奇数
            // 奇数使用【1】行
            // 偶数使用【0】行
            for (int j = 0; j < 10; j++) {
                int[] helpArr = help[j];
                dp[x][j] = 0;
                for (int k : helpArr) {
                    dp[x][j] = (dp[x][j] + dp[x ^ 1][k]) % MOD;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 10; i++) {
            int x = n & 1;
            ans = (ans + (dp[x][i])) % MOD;
        }
        return ans;
    }
    //136006598
    //1024771795

    static final int MOD = 1000000007;

    public int knightDialer3(int n) {
        int[][] moves = {
                {4, 6},
                {6, 8},
                {7, 9},
                {4, 8},
                {3, 9, 0},
                {},
                {1, 7, 0},
                {2, 6},
                {1, 3},
                {2, 4}
        };
        int[][] d = new int[2][10];
        Arrays.fill(d[1], 1);
        for (int i = 2; i <= n; i++) {
            int x = i & 1;
            for (int j = 0; j < 10; j++) {
                d[x][j] = 0;
                for (int k : moves[j]) {
                    d[x][j] = (d[x][j] + d[x ^ 1][k]) % MOD;
                }
            }
        }
        int res = 0;
        for (int x : d[n % 2]) {
            res = (res + x) % MOD;
        }
        return res;
    }


    private int process(int rest, int i) {
        if (rest == 0) {
            return 1;
        }
        int ans = 0;
        if (i == 0) {
            ans += process(rest - 1, 4) + process(rest - 1, 6);
        }
        if (i == 1) {
            ans += process(rest - 1, 6) + process(rest - 1, 8);
        }
        if (i == 2) {
            ans += process(rest - 1, 7) + process(rest - 1, 9);
        }
        if (i == 3) {
            ans += process(rest - 1, 4) + process(rest - 1, 8);
        }
        if (i == 4) {
            ans += process(rest - 1, 3) + process(rest - 1, 9) + process(rest - 1, 0);
        }
        if (i == 6) {
            ans += process(rest - 1, 1) + process(rest - 1, 7) + process(rest - 1, 0);
        }
        if (i == 7) {
            ans += process(rest - 1, 2) + process(rest - 1, 6);
        }
        if (i == 8) {
            ans += process(rest - 1, 1) + process(rest - 1, 3);
        }
        if (i == 9) {
            ans += process(rest - 1, 2) + process(rest - 1, 4);
        }
        return ans;
    }

    public static void main(String[] args) {
        Code0935 code0935 = new Code0935();
//        System.out.println(code0935.knightDialer(3131));
        System.out.println(code0935.knightDialer2(100));
        System.out.println(code0935.knightDialer3(100));
    }
}
