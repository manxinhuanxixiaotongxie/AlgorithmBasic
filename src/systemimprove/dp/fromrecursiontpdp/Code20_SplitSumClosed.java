package systemimprove.dp.fromrecursiontpdp;

/**
 * 给定一个正数数组arr，
 * 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回：
 * 最接近的情况下，较小集合的累加和
 */
public class Code20_SplitSumClosed {

    public static int minSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];
        }
        // 将数组所有元素求和之后
        // 题目要求的是最接近情况下 较小集合的累加和
        // 那么转化一下就是求最接近sum/2但是小于sum/2的最大值
        sum /= 2;
        return process(arr, 0, sum);
    }

    /**
     * 这个递归的含义是
     * 最接近sum的情况下 但是小于等于sum的情况下 能够获取的最大累加和
     *
     * @return
     */
    public static int process(int[] arr, int index, int rest) {
        if (index == arr.length) {
            return 0;
        }
        /**
         * 这个递归为什么不行呢？
         * 根本原因是因为sum没有更新 导致后续的递归过程无法读取到sum的值
         * 举个例子：
         * 16 14 14
         * 靠近的值就是22
         * 因为在后续的递归过程中sum一直是22对后续进行递归
         * 当回到index==0 时 当前的值是16 后续的过程最靠近22的值是14
         * 两者相加超过了22 并不会走到分支判断
         * 当数组是 16 14 14 14 又是可以的
         * 因为这里是在凑29 但是不能超过29 实际答案是的28   刚好卡在了两个14
         */
//        int p1 = process(arr, index + 1, rest);
//        int p2 = 0;
//        if (arr[index] + process(arr, index + 1, rest) <= rest) {
//
//            p2 = arr[index] + process(arr, index + 1, rest);
//        }
//        return Math.max(p1, p2);
        int p1 = process(arr, index + 1, rest);
        int p2 = 0;
        if (arr[index] <= rest) {
            p2 = arr[index] + process(arr, index + 1, rest - arr[index]);
        }
        return Math.max(p1, p2);
    }

    public static int minSum2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum += arr[i];
        }
        // 将数组所有元素求和之后
        // 题目要求的是最接近情况下 较小集合的累加和
        // 那么转化一下就是求最接近sum/2但是小于sum/2的最大值
        sum /= 2;
        // 将这个动态规划改成dp
        int N = arr.length;
        int[][] dp = new int[N + 1][sum + 1];
        // 普遍位置依赖
        // 根据暴力递归的过程
        // 每个位置依赖下一行的某两个位置的最大值
        // 当index == arr.length时 值全都默认是0
        for (int index = arr.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= sum; rest++) {
                dp[index][rest] = (rest - arr[index] >= 0) ? Math.max(arr[index] + dp[index + 1][rest - arr[index]], dp[index + 1][rest]) :
                        dp[index + 1][rest];
            }
        }
        return dp[0][sum];
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
        int[][] dp = new int[N + 1][sum + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= sum; rest++) {
                // 可能性1，不使用arr[i]
                int p1 = dp[i + 1][rest];
                // 可能性2，要使用arr[i]
                int p2 = 0;
                if (arr[i] <= rest) {
                    p2 = arr[i] + dp[i + 1][rest - arr[i]];
                }
                dp[i][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][sum];
    }

    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
//            int[] arr = new int[]{16, 14, 14, 14};
            int ans1 = minSum(arr);
            int ans2 = right(arr);
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
