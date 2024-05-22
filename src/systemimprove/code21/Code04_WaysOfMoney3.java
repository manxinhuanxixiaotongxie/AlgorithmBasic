package systemimprove.code21;

import java.util.HashMap;
import java.util.Map;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 认为值相同的货币没有任何不同，
 * 返回组成aim的方法数
 * 例如：arr = {1,2,1,1,2,1,2}，aim = 4
 * 方法：1+1+1+1、1+1+2、2+2
 * 一共就3种方法，所以返回3
 */
public class Code04_WaysOfMoney3 {

    public int ways(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        // 将原始数组进行加工
        Map<Integer, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
            max = Math.max(max, arr[i]);
        }
        int[] newArr = new int[max + 1];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            newArr[entry.getKey()] = entry.getValue();
        }
        return process1(newArr, 0, aim);
    }

    public int process1(int[] arr, int index, int rest) {
        if (rest == 0) {
            // 找到一种方法数
            return 1;
        }
        if (rest < 0) {
            return 0;
        }
        if (index == arr.length) {
            return 0;
        }
        // 对原始数组的张数进行尝试
        int ans = 0;
        for (int i = 0; i <= arr[index] && i * index <= rest; i++) {
            ans += process1(arr, index + 1, rest - i * index);
        }
        return ans;
    }

    public int dp1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        // 将原始数组进行加工
        Map<Integer, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
            max = Math.max(max, arr[i]);
        }
        int[] newArr = new int[max + 1];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            newArr[entry.getKey()] = entry.getValue();
        }

        int N = newArr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 1; rest <= aim; rest++) {
                int ways = 0;
                // 这里的index是货币面值 newArr[index]表示的是货币的张数
                for (int zhang = 0; zhang * index <= rest && zhang <= newArr[index]; zhang++) {
                    ways += dp[index + 1][rest - zhang * index];
                }
                dp[index][rest] = ways;
            }
        }
        return dp[0][aim];
    }

    /**
     * 斜率优化   分析严格位置依赖的动态规划  找到可能进行优化的机会
     *
     *
     * 目前这个斜率优化 还有问题  待后续优化
     * @param arr
     * @param aim
     * @return
     */
    public int dp2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        // 将原始数组进行加工
        Map<Integer, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
            max = Math.max(max, arr[i]);
        }
        int[] newArr = new int[max + 1];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            newArr[entry.getKey()] = entry.getValue();
        }

        int N = newArr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {
                int ways = 0;
                // 这里的index是货币面值 newArr[index]表示的是货币的张数
//                for (int zhang = 0; zhang * index <= rest && zhang <= newArr[index]; zhang++) {
//                    ways += dp[index + 1][rest - zhang * index];
//                }
                // 讨论优化的可能性
                // index rest位置依赖下一行位置
                //          具体依赖的位置 rest  rest- 1*index  rest - 2*index ... rest - zhang*index
                // index rest-1依赖的位置 rest-1 rest-1-1*index rest-1-2*index ... rest-1-zhang*index
                ways = dp[index + 1][rest];
                if (rest - index >= 0) {
                    ways += dp[index][rest - index];
                }
                if (rest - index * (newArr[index] + 1) >= 0) {
                    ways -= dp[index + 1][rest - index * (newArr[index] + 1)];
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
        Info info = getInfo(arr);
        int[] coins = info.coins;
        int[] zhangs = info.zhangs;
        int N = coins.length;
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

    public static Info getInfo(int[] arr) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        for (int value : arr) {
            if (!counts.containsKey(value)) {
                counts.put(value, 1);
            } else {
                counts.put(value, counts.get(value) + 1);
            }
        }
        int N = counts.size();
        int[] coins = new int[N];
        int[] zhangs = new int[N];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            coins[index] = entry.getKey();
            zhangs[index++] = entry.getValue();
        }
        return new Info(coins, zhangs);
    }


    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 10;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * 100);
            int ans1 = new Code04_WaysOfMoney3().ways(arr, aim);
            int ans2 = dp(arr, aim);
            int ans3 = new Code04_WaysOfMoney3().dp1(arr, aim);
            int ans4 = new Code04_WaysOfMoney3().dp2(arr, aim);
            if (ans1 != ans2 || ans2 != ans3 || ans2 != ans4 ) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finished!");
    }

}

class Info {
    public int[] coins;
    public int[] zhangs;

    public Info(int[] c, int[] z) {
        coins = c;
        zhangs = z;
    }
}


