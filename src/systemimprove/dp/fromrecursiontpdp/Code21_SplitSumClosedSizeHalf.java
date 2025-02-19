package systemimprove.dp.fromrecursiontpdp;

/**
 * 给定一个正数数组arr，请把arr中所有的数分成两个集合
 * 如果arr长度为偶数，两个集合包含数的个数要一样多
 * 如果arr长度为奇数，两个集合包含数的个数必须只差一个
 * 请尽量让两个集合的累加和接近
 * 返回：
 * 最接近的情况下，较小集合的累加和
 */
public class Code21_SplitSumClosedSizeHalf {

    /**
     * 整数数组arr
     * 将数组拆分成两个集合
     * 偶数 两个集合的个数要一样多
     * 奇数 两个集合的个数只差一个
     * 尽量让两个集合的累加和接近
     * 返回最接近情况下 较小的累加和
     *
     * @param arr
     * @return
     */
    public static int minSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 0;
        }
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];
        }
        // 两个集合的累加和都趋近于sum/2 返回较小的那个
        int pick = arr.length / 2;
        if ((arr.length & 1) == 0) {
            // 偶数
            return process(arr, 0, pick, sum / 2);
        } else {
            // 奇数
            // 哪个最靠近一半但是又不超过一半 就取哪边
            // 两边取最大值
            return Math.max(process(arr, 0, pick, sum / 2), process(arr, 0, pick + 1, sum / 2));
        }
    }

    // 必须要挑选pick个
    // 在不超过sum的情况下
    // 最接近sum的累加和最大值是多少
    public static int process(int[] arr, int index, int pick, int rest) {
        if (pick == 0) {
            return 0;
        }
        // 定义无效解
        if (index == arr.length) {
            return pick == 0 ? 0 : Integer.MIN_VALUE;
        } else {
            // 一定要挑选pick个数
            // 第一种情况 不挑选当前数
            int p1 = process(arr, index + 1, pick, rest);
            // 第二种情况挑选当前数
            // 注意这里不能用0 基础条件已经使用了系统最小值作为无效解
            // 如果使用0的话 会导致答案变多
            int p2 = Integer.MIN_VALUE;
            if (arr[index] <= rest) {
                int next = process(arr, index + 1, pick - 1, rest - arr[index]);
                if (next != Integer.MIN_VALUE) {
                    p2 = arr[index] + next;
                }
            }
            return Math.max(p1, p2);
        }
    }

    /**
     * 改动态规划
     *
     * @param arr
     * @return
     */
    public static int minSum2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return 0;
        }
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];
        }
        sum /= 2;
        // 两个集合的累加和都趋近于sum/2 返回较小的那个
        int N = arr.length;
        int M = (N + 1) / 2;
        int K = sum;
        int[][][] dp = new int[N + 1][M + 1][K + 1];
        // 三维的动态规划
        // rest作为最下面一层
        // x是是pick y是rest
        // 根据递归
        // 最下面一层 当且仅当pick等于0的时候才有值 其余是系统最小值
        for (int index = 0; index <= N; index++) {
            for (int j = 0; j <= M; j++) {
                for (int k = 0; k <= sum; k++) {
                    // 先默认都是系统最小值
                    dp[index][j][k] = Integer.MIN_VALUE;
                    if (j == 0) {
                        dp[index][j][k] = 0;
                    }

                }
            }
        }
        // x 是pick
        // y 是rest
        for (int index = N - 1; index >= 0; index--) {
            for (int pick = 1; pick <= M; pick++) {
                for (int rest = 0; rest <= sum; rest++) {
                    dp[index][pick][rest] = dp[index + 1][pick][rest];
                    if (arr[index] <= rest) {
                        int next = dp[index + 1][pick - 1][rest - arr[index]];
                        if (next != Integer.MIN_VALUE) {
                            dp[index][pick][rest] = Math.max(dp[index][pick][rest], arr[index] + next);
                        }
                    }
                }
            }
        }
        // 根据递归函数分析位置依赖
        if ((arr.length & 1) == 0) {
            // 偶数
            return dp[0][arr.length / 2][sum];
        } else {
            // 奇数
            return Math.max(dp[0][arr.length / 2][sum], dp[0][arr.length / 2 + 1][sum]);
        }
    }

    public static int right(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum /= 2;
        int N = arr.length;
        int M = (N + 1) / 2;
        int[][][] dp = new int[N + 1][M + 1][sum + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        for (int rest = 0; rest <= sum; rest++) {
            dp[N][0][rest] = 0;
        }
        for (int i = N - 1; i >= 0; i--) {
            for (int picks = 0; picks <= M; picks++) {
                for (int rest = 0; rest <= sum; rest++) {
                    int p1 = dp[i + 1][picks][rest];
                    // 就是要使用arr[i]这个数
                    int p2 = -1;
                    int next = -1;
                    if (picks - 1 >= 0 && arr[i] <= rest) {
                        next = dp[i + 1][picks - 1][rest - arr[i]];
                    }
                    if (next != -1) {
                        p2 = arr[i] + next;
                    }
                    dp[i][picks][rest] = Math.max(p1, p2);
                }
            }
        }
        if ((arr.length & 1) == 0) {
            return dp[0][arr.length / 2][sum];
        } else {
            return Math.max(dp[0][arr.length / 2][sum], dp[0][(arr.length / 2) + 1][sum]);
        }
    }

    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = right(arr);
            int ans2 = minSum(arr);
            int ans3 = minSum2(arr);
            if (ans1 != ans2 || ans1 != ans3) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
