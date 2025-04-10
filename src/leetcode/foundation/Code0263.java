package leetcode.foundation;

/**
 * 依次除掉
 * 将 n 除以 2、3、5，直到 n 不能被整除为止
 *
 */
public class Code0263 {
    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 2 == 0) {
            n /= 2;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }

        return n == 1;
    }
}
