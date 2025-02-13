package systemimprove.dp.fromrecursiontpdp;

import java.util.HashMap;
import java.util.Map;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 认为值相同的货币没有任何不同，
 * 返回组成aim的方法数
 * <p>
 * 例如：arr = {1,2,1,1,2,1,2}，aim = 4
 * 方法：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 */
public class Code15_CoinsWaySameValueSamePapper {

    /**
     * 这个题目需要将原始数据加工一下 原始的数组中有重复的货币
     * 直接使用从租往右的尝试模型 会导致答案有重复计算
     *
     * @param arr
     * @param aim
     * @return
     */
    public int coinsWay(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        // 加工数组
        Info[] info = getInfo(arr);
        return process(info, 0, aim);
    }

    // 函数的含义是 组成rest的方法数
    public int process(Info[] infos, int index, int rest) {
        if (index == infos.length) {
            return rest == 0 ? 1 : 0;
        } else {
            int ways = 0;
            for (int zhang = 0; zhang <= infos[index].times && zhang * infos[index].coin <= rest; zhang++) {
                // 从张数开始讨论
                ways += process(infos, index + 1, rest - zhang * infos[index].coin);
            }
            return ways;
        }
    }

    /**
     * 改成动态规划
     *
     * @param arr
     * @param aim
     * @return
     */
    public int coinsWay2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        // 加工数组
        Info[] infos = getInfo(arr);
        int[][] dp = new int[infos.length + 1][aim + 1];
        dp[infos.length][0] = 1;
        for (int index = infos.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                for (int zhang = 0; zhang <= infos[index].times && zhang * infos[index].coin <= rest; zhang++) {
                    // 从张数开始讨论
                    ways += dp[index + 1][rest - zhang * infos[index].coin];
                }
                dp[index][rest] = ways;
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
    public int coinsWay3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        // 加工数组
        Info[] infos = getInfo(arr);
        int[][] dp = new int[infos.length + 1][aim + 1];
        dp[infos.length][0] = 1;
        for (int index = infos.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
//                int ways = 0;
//                for (int zhang = 0; zhang <= infos[index].times && zhang * infos[index].coin <= rest; zhang++) {
//                    // 从张数开始讨论
//                    ways += dp[index + 1][rest - zhang * infos[index].coin];
//                }
                /**
                 * 分析上述位置的依赖
                 */
                dp[index][rest] = dp[index + 1][rest];
                if (rest - infos[index].coin >= 0) {
                    dp[index][rest] += dp[index][rest - infos[index].coin];
                }
                // 还需要减去多余计算的部分
                // 哪部分是多余计算的
                // 考虑是越界位置
                // 为什么会多算
                // 假设当前行有四张纸币
                // 递推之前已经用完了 那么递推之前的0张纸币就是多余计算的
                if ((rest - (infos[index].times + 1) * infos[index].coin) >= 0) {
                    dp[index][rest] -= dp[index + 1][rest - (infos[index].times + 1) * infos[index].coin];
                }
            }
        }
        return dp[0][aim];
    }

    public static Info[] getInfo(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.putIfAbsent(arr[i], 0);
            map.put(arr[i], map.get(arr[i]) + 1);
        }
        Info[] infos = new Info[map.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            infos[index++] = new Info(entry.getKey(), entry.getValue());
        }
        return infos;
    }


    public static int right(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        Info[] info = getInfo(arr);
        int N = info.length;
        int[] coins = new int[N];
        int[] zhangs = new int[N];
        for (int i = 0; i < N; i++) {
            coins[i] = info[i].coin;
            zhangs[i] = info[i].times;
        }

        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                dp[index][rest] = dp[index + 1][rest];
                if (rest - coins[index] >= 0) {
                    dp[index][rest] += dp[index][rest - coins[index]];
                }
                if (rest - coins[index] * (zhangs[index] + 1) >= 0) {
                    dp[index][rest] -= dp[index + 1][rest - coins[index] * (zhangs[index] + 1)];
                }
            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
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
        int maxLen = 10;
        int maxValue = 20;
        int testTime = 1000000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * 100);
            int ans1 = new Code15_CoinsWaySameValueSamePapper().coinsWay(arr, aim);
            int ans2 = right(arr, aim);
            int ans3 = new Code15_CoinsWaySameValueSamePapper().coinsWay2(arr, aim);
            int ans4 = new Code15_CoinsWaySameValueSamePapper().coinsWay3(arr, aim);
            if (ans1 != ans2 || ans1 != ans3 || ans1 != ans4) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test finish");
    }
}


class Info {
    // 币值
    int coin;
    // 币数
    int times;

    Info(int coin, int times) {
        this.coin = coin;
        this.times = times;
    }
}
