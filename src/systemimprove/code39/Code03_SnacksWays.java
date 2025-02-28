package systemimprove.code39;

/**
 * 牛牛家里一共有n袋零食, 第i袋零食体积为v[i]，背包容量为w。
 * 牛牛想知道在总体积不超过背包容量的情况下,
 * 一共有多少种零食放法，体积为0也算一种放法
 * <p>
 * <p>
 * <p>
 * 1 <= n <= 30, 1 <= w <= 2 * 10^9
 * <p>
 * <p>
 * v[i] (0 <= v[i] <= 10^9）
 */
public class Code03_SnacksWays {

    public static int ways1(int[] arr, int w) {
        return process(arr, 0, w);
    }

    /**
     * 经典的从左往右的尝试模型
     * <p>
     * 根据数据量猜解法
     * 当背包容量非常大的时候 可能会导致计算量超过10^8指令集
     *
     * @param arr
     * @param index
     * @param rest
     * @return
     */
    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return 1;
        }
        // 当前位置不放进背包
        int p1 = process(arr, index + 1, rest);
        // 当前位置放进背包
        // 但是当前容量不能超过背包的剩余容量
        if (arr[index] <= rest) {
            p1 += process(arr, index + 1, rest - arr[index]);
        }
        return p1;
    }

    /**
     * 改成动态规划
     *
     * @param arr
     * @param w
     * @return
     */
    public static int ways2(int[] arr, int w) {
        // 两个参数
        int N = arr.length;
        int[][] dp = new int[N + 1][w + 1];
        for (int i = 0; i < w + 1; i++) {
            dp[N][i] = 1;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest < w + 1; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (arr[index] <= rest) {
                    dp[index][rest] += dp[index + 1][rest - arr[index]];
                }
            }
        }
        return dp[0][w];
    }

    public static int right(int[] arr, int w) {
        int N = arr.length;
        int[][] dp = new int[N][w + 1];
        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
        }
        if (arr[0] <= w) {
            dp[0][arr[0]] = 1;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= w; j++) {
                dp[i][j] = dp[i - 1][j] + ((j - arr[i]) >= 0 ? dp[i - 1][j - arr[i]] : 0);
            }
        }
        int ans = 0;
        for (int j = 0; j <= w; j++) {
            ans += dp[N - 1][j];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {4, 3, 2, 9};
        int w = 8;
        System.out.println(ways1(arr, w));
        System.out.println(ways2(arr, w));
        System.out.println(right(arr, w));
    }
}
