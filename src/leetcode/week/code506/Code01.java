package leetcode.week.code506;

/**
 * 给你一个正整数 n。
 * <p>
 * 令 digitSum 表示 n 的各位数字之和，令 squareSum 表示 n 的各位数字平方之和。
 * <p>
 * 如果一个整数满足 squareSum - digitSum >= 50，则称它是 好整数 。
 * <p>
 * 如果 n 是好整数，返回 true；否则，返回 false。
 *
 */
public class Code01 {
    public boolean checkGoodInteger(int n) {
        int digitSum = 0;
        int squareSum = 0;
        while (n != 0) {
            // 当前数位
            int temp = n % 10;
            digitSum += temp;
            squareSum += temp * temp;
            n = n / 10;
        }
        return squareSum - digitSum >= 50;
    }
}
