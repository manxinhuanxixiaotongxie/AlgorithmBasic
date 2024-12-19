package leetcode.dp;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * <p>
 * <p>
 * 讨论情况：
 * 第一步走一个台阶 剩下n-1个台阶
 * 第一次走两个台阶 剩下n-2个台阶
 * <p>
 * <p>
 * 对于每一步来说，只有两种选择，要么走一步，要么走两步
 * <p>
 * 有且仅有剩余的台阶数为0时，才是一种走法
 * <p>
 * <p>
 * 这两种情况都会把原问题变成一个和原问题相似  规模更小的解
 * <p>
 * 所以可用递推解决
 * <p>
 * <p>
 * 动态规划有选或者不选以及枚举用哪个  两种基本思考方式
 * <p>
 * 根据题目状态进行相应的选择
 * <p>
 * 本题的采用枚举的方式
 * <p>
 * <p>
 * 时间复杂度：近似于搜索树
 * <p>
 * 空间复杂度 递归需要栈空间O（N）
 * <p>
 * <p>
 * 整个递归有大量的重复调用  入参相同
 * 递归函数特性：相同的入参无论计算多少次 算出来的结果都是一样
 * <p>
 * 这样的题目可以使用记忆化搜索进行优化
 * <p>
 * 本题使用记忆化搜索优化之后
 * 时间复杂度O(N)  每个状态只会计算一次
 * 空间复杂度O(N)  记录每个状态的结果
 */
public class Code0070 {

    /**
     * 暴力递归改成动态规划
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        //return process(n);
        return dp(n);
    }

    public int process(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        return process(n - 1) + process(n - 2);
    }

    public int dp(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int climbStairs2(int n) {
        //return process(n);
        return dp(n);
    }

    public int dp2(int n) {
        int pre = 1;
        int prePre = 1;
        // 空间优化
        // dp[2] = dp[1] + dp[0]
        // dp[3] = dp[2] + dp[1]
        // dp[4] = dp[3] + dp[2]
        for (int i = 2; i <= n; i++) {
            int ans = pre + prePre;
            prePre = pre;
            pre = ans;
        }

        return pre;
    }
}
