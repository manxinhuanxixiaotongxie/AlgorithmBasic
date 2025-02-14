package systemimprove.dp.fromrecursiontpdp;

import java.util.Arrays;

/**
 * arr是面值数组，其中的值都是正数且没有重复。再给定一个正数aim。
 * 每个值都认为是一种面值，且认为张数是无限的。
 * 返回组成aim的最少货币数
 */
public class Code18_MinCoinsNoLimit {

    public static int minCoins(int[] arr, int aim) {
        if (aim < 0) {
            return -1;
        }
        return process(arr, 0, aim) == -1 ? Integer.MAX_VALUE : process(arr, 0, aim);
    }

    /**
     * 这个函数的含义是组成rest需要的最小张数
     * <p>
     * 在0-index上进行选择
     *
     * @param arr
     * @param index
     * @param rest
     * @return
     */
    public static int process(int[] arr, int index, int rest) {
        // 在递归的过程中，已经限制了rest >= 0
        // 这个basecase实际是走不到的
//        if (rest < 0) {
//            // 无效答案
//            return -1;
//        }

        if (index == arr.length) {
            // 使用-1标记无效答案 当且仅当做完选择之后的最终rest为0时，返回0
            // 为0说明当前需要的张数是0
            return rest == 0 ? 0 : -1;
        } else {
            // 普遍位置
            // 在当前位置选择张数
            int ans = Integer.MAX_VALUE;
            for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                // 使用张数进行递归
                int next = process(arr, index + 1, rest - zhang * arr[index]);
                if (next != -1) {
                    ans = Math.min(ans, next + zhang);
                } else {
                    // 冗余代码
                    // 当next是-1的时候 说明后续的递归是个无效的路径
                    // 无效的路径不能参与计算
                }
            }

            return ans == Integer.MAX_VALUE ? -1 : ans;
        }
    }

    public static int minCoins2(int[] arr, int aim) {
        if (aim < 0) {
            return Integer.MAX_VALUE;
        }
        return process2(arr, 0, aim);
    }

    /**
     * 换个返回值作为尝试的返回
     * 判断逻辑简单点
     *
     * @param arr
     * @param index
     * @param rest
     * @return
     */
    public static int process2(int[] arr, int index, int rest) {
        // 在递归的过程中，已经限制了rest >= 0
        // 这个basecase实际是走不到的
//        if (rest < 0) {
//            // 无效答案
//            return -1;
//        }

        if (index == arr.length) {
            // 使用-1标记无效答案 当且仅当做完选择之后的最终rest为0时，返回0
            // 为0说明当前需要的张数是0
            return rest == 0 ? 0 : Integer.MAX_VALUE;
        } else {
            // 普遍位置
            // 在当前位置选择张数
            int ans = Integer.MAX_VALUE;
            for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                // 使用张数进行递归
                int next = process2(arr, index + 1, rest - zhang * arr[index]);
                if (next != Integer.MAX_VALUE) {
                    ans = Math.min(ans, next + zhang);
                } else {
                    // 冗余代码
                    // 当next是-1的时候 说明后续的递归是个无效的路径
                    // 无效的路径不能参与计算
                }
            }

            return ans;
        }
    }

    /**
     * 将这个暴力递归改成动态规划
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int minCoins3(int[] arr, int aim) {
        if (aim < 0) {
            return Integer.MAX_VALUE;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int row = 0; row < dp.length; row++) {
            Arrays.fill(dp[row], Integer.MAX_VALUE);
        }
        // 初始化填充系统最大值
        // 最下面一行
        // 当且仅当rest==0的时候 是0 其余都是系统最大值
        dp[arr.length][0] = 0;
        // 普遍位置
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                // 在当前位置选择张数
                int ans = Integer.MAX_VALUE;
                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
                    // 使用张数进行递归
                    int next = dp[index + 1][rest - zhang * arr[index]];
                    if (next != Integer.MAX_VALUE) {
                        ans = Math.min(ans, next + zhang);
                    } else {
                        // 冗余代码
                        // 当next是-1的时候 说明后续的递归是个无效的路径
                        // 无效的路径不能参与计算
                    }
                }
                dp[index][rest] = ans;
            }
        }
        return dp[0][aim];
    }

    /**
     * 尝试使用斜率优化省掉枚举行为
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int minCoins4(int[] arr, int aim) {
        if (aim < 0) {
            return Integer.MAX_VALUE;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int row = 0; row < dp.length; row++) {
            Arrays.fill(dp[row], Integer.MAX_VALUE);
        }
        // 初始化填充系统最大值
        // 最下面一行
        // 当且仅当rest==0的时候 是0 其余都是系统最大值
        dp[arr.length][0] = 0;
        // 普遍位置
        for (int index = arr.length - 1; index >= 0; index--) {
            dp[index][0] = dp[index + 1][0] == Integer.MAX_VALUE ? Integer.MAX_VALUE : dp[index + 1][0];
            for (int rest = 1; rest <= aim; rest++) {
                // 在当前位置选择张数
                int ans = Integer.MAX_VALUE;
//                for (int zhang = 0; zhang * arr[index] <= rest; zhang++) {
//                    // 使用张数进行递归
//                    int next = dp[index + 1][rest - zhang * arr[index]];
//                    if (next != Integer.MAX_VALUE) {
//                        ans = Math.min(ans, next + zhang);
//                    } else {
//                        // 冗余代码
//                        // 当next是-1的时候 说明后续的递归是个无效的路径
//                        // 无效的路径不能参与计算
//                    }
//                }
                // 分析普遍位置的依赖尝试省掉枚举行为
                // index当前的货币的值架势3
                // 当前的rest是13
                // dp[4][13] = dp[5][13] + dp[5][10] + dp[5][7] + dp[5][4] + dp[5][1] 这里面取最小值
                // dp[4][10] = dp[5][10] + dp[5][7] + dp[5][4] + dp[5][1] 这里面取最小值
                // dp[4][13] = dp[4][10] + dp[5][13] 这里面取最小值 +1 多一张
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0
                        && dp[index][rest - arr[index]] != Integer.MAX_VALUE) {
                    dp[index][rest] = Math.min(dp[index][rest], dp[index][rest - arr[index]] + 1);
                }
            }
        }
        return dp[0][aim];
    }

    public static int right(int[] arr, int aim) {
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

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 300000;
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int N = (int) (Math.random() * maxLen);
            int[] arr = randomArray(N, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = minCoins(arr, aim);
            int ans2 = minCoins2(arr, aim);
            int ans3 = right(arr, aim);
            int ans4 = minCoins3(arr, aim);
            int ans5 = minCoins4(arr, aim);
            if (ans1 != ans2 || ans1 != ans3 || ans1 != ans4 || ans1 != ans5) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println(ans4);
                System.out.println(ans5);
                break;
            }
        }
        System.out.println("功能测试结束");
    }

}
