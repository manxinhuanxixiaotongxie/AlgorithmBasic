package systemimprove.code21;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 */
public class Code02_WaysOfMoney {

    public int ways1(int[] arr, int aim) {
//        if (arr == null || arr.length == 0 || aim < 0) {
//            return 0;
//        }
        return process1(arr, 0, aim);
    }

    /**
     * 设计一个暴力递归函数 用于返回组成aim的方法数
     *
     * @return
     */
    public int process1(int[] arr, int inedx, int rest) {
        if (rest < 0) {
            return 0;
        }
        if (rest == 0) {
            return 1;
        }
        if (inedx == arr.length) {
            return 0;
        }
        int p1 = process1(arr, inedx + 1, rest);
        int p2 = process1(arr, inedx + 1, rest - arr[inedx]);
        return p1 + p2;
    }

    public int dp1(int[] arr, int aim) {
        // 将上面暴力函数改成动态规划
        // 分析位置依赖关系
        // 以index做行的话 每一个行都依赖index+1行的位置
        // 从下向上进行填充表数据
        // 第一列位置都是1
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];

        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }

        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= aim; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = rest - arr[index] >= 0 ? dp[index + 1][rest - arr[index]] : 0;
                dp[index][rest] = p1 + p2;
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

    // for test
    public static int dp(int[] arr, int aim) {
        if (aim == 0) {
            return 1;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest] + (rest - arr[index] >= 0 ? dp[index + 1][rest - arr[index]] : 0);
            }
        }
        return dp[0][aim];
    }

    public static void main(String[] args) {
        int maxValue = 30;
        int maxLength = 20;
        int testTimes = 10000;
        Code02_WaysOfMoney code02 = new Code02_WaysOfMoney();
        for (int i = 0; i < testTimes; i++) {
            int[] arr = code02.generateArray(maxValue, maxLength);
            int aim = (int) (Math.random() * 100);
            int ans1 = code02.ways1(arr, aim);
            int ans2 = code02.dp1(arr, aim);
            int ans3 = code02.dp(arr, aim);
            if (code02.ways1(arr, aim) != dp(arr, aim)) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("finished!");

    }
}
