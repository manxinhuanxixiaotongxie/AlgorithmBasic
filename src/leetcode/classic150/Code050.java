package leetcode.classic150;

/**
 * x的N次幂
 *
 */
public class Code050 {
    /**
     * 超时
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        double ans = 1;
        int cur = Math.abs(n);
        while (cur > 0) {
            ans *= x;
            cur--;
        }
        return n < 0? 1/ans : ans;
    }

    /**
     * 利用二进制的特性
     * @param x
     * @param N
     * @return
     */
    public double myPow2(double x, int N) {
        double ans = 1;
        long n = N;
        if (n < 0) { // x^-n = (1/x)^n
            n = -n;
            x = 1 / x;
        }
        while (n != 0) { // 从低到高枚举 n 的每个比特位
            if ((n & 1) == 1) { // 这个比特位是 1
                ans *= x; // 把 x 乘到 ans 中
            }
            x *= x; // x 自身平方
            n >>= 1; // 继续枚举下一个比特位
        }
        return ans;
    }
}
