package leetcode.week.code505;

/**
 * 给你两个整数 n 和 k。
 * <p>
 * 如果一个 正 整数 x 同时满足以下两个条件，则称其为 兼容 整数：
 * <p>
 * abs(n - x) <= k
 * (n & x) == 0
 * 返回所有 兼容 整数 x 的总和。
 * <p>
 * 注意：
 * <p>
 * 这里，& 表示 按位与 运算符。
 * 整数 i 和 j 之间的 绝对 差定义为 abs(i - j)。
 *
 */
public class Code01 {
    public int sumOfGoodIntegers(int n, int k) {
        // 返回所有兼容整数总和
        int ans = 0;
        // abs(n-x) <=k
        // x 范围 n + k   n-k
        for (int i = 0; i <= k; i++) {
            int left = n - i;
            int right = n + i;
            if (left >= 0 && (n & left) == 0) {
                ans += left;
            }
            if (right > 0 && (n & right) == 0) {
                ans += right;
            }
        }

        return ans;
    }
}
