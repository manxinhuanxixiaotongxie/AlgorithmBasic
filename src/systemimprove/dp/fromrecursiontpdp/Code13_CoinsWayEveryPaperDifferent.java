package systemimprove.dp.fromrecursiontpdp;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 */
public class Code13_CoinsWayEveryPaperDifferent {
    // 返回组成aim的方法数
    public int coinWays(int[] arr, int aim) {
        if (arr == null || aim < 0) {
            return 0;
        }
        if (aim == 0) {
            return 1;
        }
        return process1(arr, 0, aim);
    }

    // 定义一个递归函数
    // 含义是 返回组成aim的方法数
    public int process1(int[] arr, int index, int rest) {
        if (index == arr.length) {
            // 已经没有数可以进行选择了
            // 只要剩下的余数是0 的时候表示已经找到了一个拆分方法
            return rest == 0 ? 1 : 0;
        }
        // 第一种情况 当前位置不进行挑选
        int p1 = process1(arr, index + 1, rest);
        // 第二种情况 当前位置的数 需要进行挑选的时候
        int p2 = process1(arr, index + 1, rest - arr[index]);
        return p1 + p2;
    }

    // 改成动态规划

    public int coinWays2(int[] arr, int aim) {
        if (arr == null || aim < 0) {
            return 0;
        }
        if (aim == 0) {
            return 1;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        // 最下面一行 有且仅有dp[N][0] = 1 其余位置都是0
        dp[N][0] = 1;
        // 从n-1开始往上填写
        for (int row = N - 1; row >= 0; row--) {
            // 从左往右填写
            for (int col = 0; col <= aim; col++) {
                // 当前位置的值
                int p1 = dp[row + 1][col];
                int p2 = col - arr[row] >= 0 ? dp[row + 1][col - arr[row]] : 0;
                dp[row][col] = p1 + p2;
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // 为了测试
    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

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
        int maxLen = 20;
        int maxValue = 30;
        int testTimes = 100000;
        Code13_CoinsWayEveryPaperDifferent code13 = new Code13_CoinsWayEveryPaperDifferent();
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = code13.randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * 100);
            int ans1 = code13.coinWays(arr, aim);
            int ans2 = code13.coinWays2(arr, aim);
            int ans3 = dp(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                code13.printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test end");
    }

}
