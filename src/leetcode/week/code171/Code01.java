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
 */
public class Code01 {
    public boolean completePrime(int num) {
        // 转为字符串，方便截取前缀和后缀
        String numStr = String.valueOf(num);
        int length = numStr.length();

        // 检查所有前缀
        for (int k = 1; k <= length; k++) {
            // 截取前k位并转为整数
            int prefix = Integer.parseInt(numStr.substring(0, k));
            if (!isPrime(prefix)) {
                return false;
            }
        }

        // 检查所有后缀
        for (int k = 1; k <= length; k++) {
            // 截取后k位并转为整数（起始索引 = 总长度 - k）
            int suffix = Integer.parseInt(numStr.substring(length - k));
            if (!isPrime(suffix)) {
                return false;
            }
        }

        // 所有前缀和后缀都是质数
        return true;
    }

    // 辅助方法：判断一个数是否为质数
    private static boolean isPrime(int n) {
        // 小于2的数不是质数
        if (n < 2) {
            return false;
        }
        // 2是唯一的偶质数
        if (n == 2) {
            return true;
        }
        // 偶数直接返回false
        if (n % 2 == 0) {
            return false;
        }
        // 只检查到平方根，步长为2（跳过偶数）
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int num = 7;
        Code01 code = new Code01();
        System.out.println(code.completePrime(num));
    }
}
