package systemimprove.code22;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的最少货币数
 */
public class Code02_MinCurrnecy {

    public int minCurrnecy(int[] arr, int aim) {

        return process1(arr, 0, aim);
    }

    /**
     * 这个尝试函数有问题 问题出在什么地方？
     * 针对无效解的时候有问题
     *
     * @param arr
     * @param index
     * @param rest
     * @return
     */
    public int process(int[] arr, int index, int rest) {

        if (index == arr.length) {
            return rest == 0 ? 0 : -1;
        }
        if (rest == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            int next = process(arr, index + 1, rest - zhang * arr[index]);
            if (next != -1) {
                ans = Math.min(ans, zhang + next);
            }
        }
        return ans;

    }

    public int process1(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        }
        if (rest == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
            int next = process1(arr, index + 1, rest - zhang * arr[index]);
            if (next != Integer.MAX_VALUE) {
                ans = Math.min(ans, zhang + next);
            }
        }
        return ans;

    }

    // for test
    public static int dp2(int[] arr, int aim) {

        if (aim == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 0;
        for (int j = 1; j <= aim; j++) {
            dp[N][j] = Integer.MAX_VALUE;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0
                        && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
                }
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        boolean[] has = new boolean[maxValue + 1];
        for (int i = 0; i < N; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) + 1;
            } while (has[arr[i]]);
            has[arr[i]] = true;
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTimes = 10000;
        for (int times = 0;times < testTimes;times++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            Code02_MinCurrnecy code02_minCurrnecy = new Code02_MinCurrnecy();
            int ans1 = code02_minCurrnecy.minCurrnecy(arr, aim);
            int ans2 = dp2(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }



}
