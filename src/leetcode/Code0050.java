package leetcode;

public class Code0050 {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1D;
        }
        // 使用二进制进行过滤
        double ans = 1D;
        int pow = 0;
        if (n == Integer.MIN_VALUE) {
            pow = Integer.MAX_VALUE;
        } else {
            pow = Math.abs(n);
        }
        double temp = x;
        while (pow != 0) {
            if ((pow & 1) == 1) {
                ans *= temp;
            }
            temp *= temp;
            pow >>= 1;
        }
        return n < 0 ? n == Integer.MIN_VALUE ? (1 / ans) * x : 1 / ans : ans;
    }
}
