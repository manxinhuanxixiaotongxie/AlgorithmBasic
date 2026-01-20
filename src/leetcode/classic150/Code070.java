package leetcode.classic150;

public class Code070 {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = i + 1 <= n ? dp[i + 1] : 0;
            dp[i] += i + 2 <= n ? dp[i + 2] : 0;
        }
        return dp[0];
    }

    /**
     * 空间优化
     *
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        int afterOne = 1;
        int afterTwo = 1;
        for (int i = n - 2; i >= 0; i--) {
            int temp = afterOne;
            afterOne = afterOne + afterTwo;
            afterTwo = temp;
        }
        return afterOne;
    }
}
