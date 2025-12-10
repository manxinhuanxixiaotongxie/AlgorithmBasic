package leetcode.week.code171;

/**
 * 给你一个整数 num。
 *
 * 如果一个数 num 的每一个 前缀 和每一个 后缀 都是 质数，则称该数为 完全质数。
 *
 * 如果 num 是完全质数，返回 true，否则返回 false。
 *
 * 注意：
 *
 * 一个数的 前缀 是由该数的 前 k 位数字构成的。
 * 一个数的 后缀 是由该数的 后 k 位数字构成的。
 * 质数 是大于 1 且只有两个因子（1 和它本身）的自然数。
 * 个位数只有在它是 质数 时才被视为完全质数。©leetcode
 *
 * 1 <= num <= 10^9
 * ©leetcode
 *
 */
public class Code01 {

    public boolean completePrime(int num) {
        boolean ans = true;

        // 转换成字符数组
        String numStr = String.valueOf(num);
        // 最长为9
        int n = numStr.length();
        for (int i = 0;i < n;i++) {
            // 第一个数字 [0,i]前缀
            // 第二个数字 [n-1-i,n-1]后缀
            // 判断这两个数是否是质数
            boolean before = isPrime(Integer.parseInt(numStr.substring(0,i+1)));
            boolean after = isPrime(Integer.parseInt(numStr.substring(n-1-i,n)));
            if (!before || !after) {
                ans = false;
                break;
            }
        }

        return ans;
    }

    // 给你一个数 判断该数字是不是质数
    public boolean isPrime(int num) {
        if (num == 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }
        // 从3开始到开方数字
        int x = (int) Math.sqrt(num);
        for (int i = 3;i <= x;i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    static void main() {
        int num = 23;
        System.out.println(new Code01().completePrime(num));
    }

}
