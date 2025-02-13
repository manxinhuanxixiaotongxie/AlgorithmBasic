package systemimprove.dp.fromrecursiontpdp;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的方法数
 * 例如：arr = {1,2}，aim = 4
 * 方法如下：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 */
public class Code14_CoinsWayNoLimit {
    // arr数组 正数 无重复 正数aim
    // 张数无限 返回组成aim的方法数
    public int coinsWay(int[] arr, int aim) {
        return process1(arr, 0, aim);
    }

    /**
     * 定义一个递归方法
     * 含义是组成rest的方法数
     * 从左往右的尝试模型
     *
     * @return
     */
    public int process1(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        } else {
            // 暴力递归张数
            // 从当前位置的0张 1 张 等进行遍历
            int ways = 0;
            for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                ways += process1(arr, index + 1, rest - zhang * arr[index]);
            }
            return ways;
        }
    }

    // 改成动态规划
    public int coinsWay2(int[] arr, int aim) {
        int[][] dp = new int[arr.length + 1][aim + 1];
        // 最下面一行开始填写
        dp[arr.length][0] = 1;
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
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
     * 讨论进一步的优化可能性
     *
     * @param arr
     * @param aim
     * @return
     */
    public int coinsWay3(int[] arr, int aim) {
        int[][] dp = new int[arr.length + 1][aim + 1];
        // 最下面一行开始填写
        dp[arr.length][0] = 1;
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = dp[index + 1][rest];
                if (rest - arr[index] >= 0) {
                    ways += dp[index][rest - arr[index]];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public  int[] randomArray(int maxLen, int maxValue) {
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

    // 为了测试
    public  void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Code14_CoinsWayNoLimit code14_coinsWayNoLimit = new Code14_CoinsWayNoLimit();
        int maxLen = 10;
        int maxValue = 10;
        int testTimes = 100000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = code14_coinsWayNoLimit.randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = code14_coinsWayNoLimit.coinsWay(arr, aim);
            int ans2 = code14_coinsWayNoLimit.coinsWay2(arr, aim);
            int ans3 = code14_coinsWayNoLimit.coinsWay3(arr, aim);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                code14_coinsWayNoLimit.printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                break;
            }
        }
        System.out.println("test end");
    }
}
