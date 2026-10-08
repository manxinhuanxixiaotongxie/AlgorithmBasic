package leetcode.review.newer;

public class Code12 {
    public boolean isUgly(int n) {
        // 丑数 只包含质因数2 3 5

        while (n != 0 && (n % 2 == 0 || n % 3 == 0 || n % 5 == 0)) {
            if (n % 2 == 0) {
                n /= 2;
            }
            if (n % 3 == 0) {
                n /= 3;
            }
            if (n % 5 == 0) {
                n /= 5;
            }
        }
        return n == 1;
    }
}
