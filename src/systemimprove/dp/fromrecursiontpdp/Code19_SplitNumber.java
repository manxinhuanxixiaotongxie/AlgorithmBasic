package systemimprove.dp.fromrecursiontpdp;

/**
 * 给定一个正数n，求n的裂开方法数，
 * 规定：后面的数不能比前面的数小
 * 比如4的裂开方法有：
 * 1+1+1+1、1+1+2、1+3、2+2、4
 * 5种，所以返回5
 */
public class Code19_SplitNumber {

    /**
     * 这里如果从0开始递归的话 会算多一种情况
     *
     * 比如7    0 7  7 0这种情况会多算一种
     *
     *
     * @param num
     * @return
     */
    public static int splitNum(int num) {
        return process(num, 1);
    }

    /**
     * 裂开的方式 后面的数不能比前面的数要小
     */

    public static int process(int rest, int pre) {
        if (rest < 0) {
            return 0;
        }
        if (rest == 0) {
            // 找到一种裂开的方式
            return 1;
        }
        // 对当前位置进行枚举
        int ways = 0;
        for (int cur = pre; cur <= rest; cur++) {
            ways += process(rest - cur, cur);
        }
        return ways;
    }

    /**
     * 将上述方法改成动态规划
     *
     * @param num
     * @return
     */
    public static int splitNum2(int num) {
        int[][] dp = new int[num + 1][num + 1];
        // 假设一rest做行 pre做列
        // 第一行的值都是1
        for (int pre = 1; pre <= num; pre++) {
            dp[0][pre] = 1;
        }
        // 普遍位置的依赖关系
        for (int rest = 1; rest <= num; rest++) {
            for (int pre = 0; pre <= num; pre++) {
                int ways = 0;
                for (int cur = pre; cur <= rest; cur++) {
                    ways += dp[rest - cur][cur];
                }
                dp[rest][pre] = ways;
            }
        }

        return dp[num][1];
    }

    /**
     * 寻找消除枚举行为的优化点
     * 使用斜率优化
     *
     * @param num
     * @return
     */
    public static int splitNum3(int num) {
        int[][] dp = new int[num + 1][num + 1];
        // 假设一rest做行 pre做列
        // 第一行的值都是1
        for (int pre = 1; pre <= num; pre++) {
            dp[0][pre] = 1;
            dp[pre][pre] = 1;
        }
        // 普遍位置的依赖关系
        for (int rest = 1; rest <= num; rest++) {
//            for (int pre = 0; pre <= rest; pre++) {
//                int ways = 0;
//                for (int cur = pre; cur <= rest; cur++) {
//                    ways += dp[rest - cur][cur];
//                }
//                dp[rest][pre] = ways;

            /**
             *                   分析普遍位置依赖：
             *                   dp[10][5]  pre = 5 rest = 10
             *                   dp[10][5] = dp[10-5][5] + dp[10-6][6] + dp[10-7][7] + dp[10-8][8] + dp[10-9][9] + dp[10-10][10]
             *                   dp[10][6] =               dp[10-6][6] + dp[10-7][7] + dp[10-8][8] + dp[10-9][9] + dp[10-10][10]
             *
             *                   dp[11][5] = dp[11-5][5] + dp[11-6][6] + dp[11-7][7] + dp[11-8][8] + dp[11-9][9] + dp[11-10][10]
             *                   dp[11][6] =               dp[11-6][6] + dp[11-7][7] + dp[11-8][8] + dp[11-9][9] + dp[11-10][10]
             *
             *                   dp[rest][pre] = dp[rest][pre-1] - dp[rest-pre-1][pre-1]
             *
             *                   dp[rest][0] = dp[rest-0][0] + dp[rest-1][1] + dp[rest-2][2] + dp[rest-3][3] + ... + dp[rest-rest][rest]
             *                   dp[rest][1] =                 dp[rest-1][1] + dp[rest-2][2] + dp[rest-3][3] + ... + dp[rest-rest][rest]
             *                   第0列的位置 是对角线位置相加
             *                   这种方式无法省掉枚
             *
             *
             *                   换种方式
             *                   先计算右边
             *
             *                   dp[10][5] = dp[10][6] + dp[10-5][5]
             */
            for (int pre = rest - 1; pre > 0; pre--) {
                // 从右往左填写
                dp[rest][pre] = dp[rest][pre + 1] + dp[rest - pre][pre];
            }
        }

        return dp[num][1];
    }

    /**
     * 这个方法的的动态规划是使用pre做行 rest做列改出来的动态规划
     * 斜率优化之后的样子
     *
     *
     * @param n
     * @return
     */
    public static int right(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest];
                dp[pre][rest] += dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int testTimes = 100;
        System.out.println("test begin");
        for (int times = 1; times < testTimes; times++) {
            int splitNum = times;
            int ans1 = splitNum(splitNum);
            int ans2 = right(splitNum);
            int ans3 = splitNum2(splitNum);
            int ans4 = splitNum3(splitNum);
            if (ans1 != ans2 || ans1 != ans3 || ans1 != ans4) {
                System.out.println("Oops!");
                System.out.println(splitNum);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println(ans4);
                break;
            }
        }
        System.out.println("test finish");
    }
}
