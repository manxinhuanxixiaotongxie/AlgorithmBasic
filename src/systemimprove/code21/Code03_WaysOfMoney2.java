package systemimprove.code21;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的方法数
 * 例如：arr = {1,2}，aim = 4
 * 方法如下：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 */
public class Code03_WaysOfMoney2 {

    public int wasyOfMoney(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

    public int process1(int[] arr, int index, int rest) {

        if (rest == 0) {
            return 1;
        }
        if (rest < 0) {
            return 0;
        }
        if (index == arr.length) {
            return 0;
        }
        int ans = 0;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            ans += process1(arr, index + 1, rest - zhang * arr[index]);
        }
        return ans;
    }

    public int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= aim; rest++) {
                int ways = 0;
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    ways += dp[index + 1][rest - zhang * arr[index]];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    /**
     * 斜率优化
     * <p>
     * 我们分析一下严格的位置依赖
     *
     * @param arr
     * @param aim
     * @return
     */
    public int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= aim; rest++) {
                int ways = 0;
//                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
//                    ways += dp[index + 1][rest - zhang * arr[index]];
//                }
                // 分析严格意义的位置依赖
                ways = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    ways += dp[index][rest - arr[index]];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    // for test
    public static int dp(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }

    public int[] generateArray(int maxValue, int maxLength) {
        int N = (int) (Math.random() * maxLength);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 1000000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = new Code03_WaysOfMoney2().generateArray(maxValue, maxLen);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = new Code03_WaysOfMoney2().wasyOfMoney(arr, aim);
            int ans2 = dp(arr, aim);
            int ans3 = new Code03_WaysOfMoney2().dp1(arr, aim);
            int ans4 = new Code03_WaysOfMoney2().dp2(arr, aim);
            if (ans1 != ans2 || ans2 != ans3 || ans2 != ans4) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finished!");
    }
}
