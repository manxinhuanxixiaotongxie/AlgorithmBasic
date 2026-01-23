package leetcode.classic150;

/**
 * 进阶：你能不将整数转为字符串来解决这个问题吗？
 *
 */
public class Code009 {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int recv = 0;
        int copy = x;
        while (x > 0) {
            recv = recv * 10 + x % 10;
            x /= 10;
        }
        return recv == copy;
    }

    static void main() {
        Code009 code009 = new Code009();
        System.out.println(code009.isPalindrome(121));
    }
}
