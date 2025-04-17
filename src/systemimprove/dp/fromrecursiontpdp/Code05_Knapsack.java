package systemimprove.dp.fromrecursiontpdp;

/**
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 */
public class Code05_Knapsack {

    public int maxVlaue(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length != values.length || bag < 0 || weights.length == 0) {
            return 0;
        }
        return process1(weights, values, 0, bag);
    }

    // 定义一个递归函数
    // 返回装下物品不超过bag的情况下 最大的价值
    public int process1(int[] weights, int[] values, int index, int rest) {
        if (rest < 0) {
            return Integer.MIN_VALUE;
        }
        if (index == weights.length) {
            return 0;
        } else {
            // 当前货物不装包
            int p1 = process1(weights, values, index + 1, rest);
            // 当前货物装包
            int p2 = values[index] + process1(weights, values, index + 1, rest - weights[index]);
            return Math.max(p1, p2);
        }
    }

    // 改成动态规划
    public int maxValue2(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length != values.length || bag < 0 || weights.length == 0) {
            return 0;
        }
        int[][] dp = new int[weights.length + 1][bag + 1];
        for (int row = weights.length - 1; row >= 0; row--) {
            // 从倒数第二行开始填写
            for (int col = 0; col <= bag; col++) {
                // 第0列开始填写
                int p1 = dp[row + 1][col];
                int p2 = col - weights[row] < 0 ? Integer.MIN_VALUE : values[row] + dp[row + 1][col - weights[row]];
                dp[row][col] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        Code05_Knapsack code05_knapsack = new Code05_Knapsack();
        int[] weights = {3, 2, 4, 7, 3, 1, 7 };
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        System.out.println(code05_knapsack.maxVlaue(weights, values, bag));
        System.out.println(code05_knapsack.maxValue2(weights, values, bag));
    }
}
