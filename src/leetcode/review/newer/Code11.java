package leetcode.review.newer;

public class Code11 {
    // 3的幂
    public boolean isPowerOfThree(int n) {
        while (n != 0 && n % 3 == 0) {
            n = n / 3;
        }
        return (n == 1);
    }

    static void main() {
        int n = 45;
        Code11 c = new Code11();
        System.out.println(c.isPowerOfThree(n));
    }
}
