package systemimprove.code19;

/**
 * 给定两个长度都为N的数组weights和values，
 * weights[i]和values[i]分别代表 i号物品的重量和价值。
 * 给定一个正数bag，表示一个载重bag的袋子，
 * 你装的物品不能超过这个重量。
 * 返回你能装下最多的价值是多少?
 */
public class Code01_MaxValue {

    // 返回最大价值
    public int maxValue(int[] weights, int[] values, int bag) {
        if (weights == null || values == null || weights.length == 0 || weights.length != values.length) {
            return 0;
        }

        return process1(weights, values, 0, bag);

    }

    public int maxValue2(int[] weights, int[] value, int bag) {
        if (weights == null || value == null || weights.length == 0 || weights.length != value.length) {
            return 0;
        }
        return dp(weights, value, bag);
    }

    public int dp(int[] weights, int[] value, int bag) {
        int[][] dp = new int[value.length + 1][bag + 1];
        // 以index做行 bag做列的话   当index==N的时候 全都是0 不需要额外填充
        for (int index = value.length - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = rest - weights[index] < 0 ? -1 : dp[index + 1][rest - weights[index]];
                if (p2 != -1) {
                    p2 = value[index] + p2;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    // 设计一个函数返回最大价值
    // 本题规定 当bag的容量为0的时候 可以继续选择  因为可能存在某种货物的重量为0 但是价值不为0
    public int process1(int[] weights, int[] value, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        int N = weights.length;
        // 货物已经选完了  但是依然可以继续选
        if (index == N) {
            return 0;
        }
        int p1 = process1(weights, value, index + 1, rest);
        if (index < N && rest - weights[index] >= 0) {
            int p2 = process1(weights, value, index + 1, rest - weights[index]);
            if (p2 != -1) {
                p1 = Math.max(p1, value[index] + p2);
            }
        }
        return p1;
    }

    public static void main(String[] args) {
        int[] weights = {3, 2, 4, 7, 3, 1, 7};
        int[] values = {5, 6, 3, 19, 12, 4, 2};
        int bag = 15;
        Code01_MaxValue code01_maxValue = new Code01_MaxValue();
        System.out.println(code01_maxValue.maxValue(weights, values, bag));
        System.out.println(code01_maxValue.maxValue2(weights, values, bag));
    }
}
