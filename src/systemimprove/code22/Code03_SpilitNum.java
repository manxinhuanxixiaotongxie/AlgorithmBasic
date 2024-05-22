package systemimprove.code22;

/**
 * 给定一个正数n，求n的裂开方法数，
 * 规定：后面的数不能比前面的数小
 * 比如4的裂开方法有：
 * 1+1+1+1、1+1+2、1+3、2+2、4
 * 5种，所以返回5
 */
public class Code03_SpilitNum {

    public int splitNum(int num) {
        if (num <= 0) {
            return 0;
        }
        if (num == 1) {
            return 1;
        }
        return process1(num, 1);
    }

    public int process1(int rest, int pre) {
        if (rest == 0) {
            return 1;
        }
        int ans = 0;
        for (int i = pre; i <= rest; i++) {
            ans += process1(rest - i, i);
        }
        return ans;
    }

    public int dp(int num) {
        int[][] dp = new int[num + 1][num + 1];
        // 先填写第一列
        for (int r = 0; r <= num; r++) {
            dp[r][0] = 1;
        }
        // 从最后

        return dp[num][1];
    }

    public static int dp2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest];
                dp[pre][rest] += dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int testTimes = 39;
        Code03_SpilitNum test = new Code03_SpilitNum();
        for (int times = 1; times <= testTimes; times++) {
            int ans1 = test.splitNum(times);
            int ans2 = dp2(times);
            int ans3 = test.dp(times);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
