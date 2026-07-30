package leetcode.week.code512;

/**
 * 给你两个非负整数 n 和 s。
 * <p>
 * 返回满足下述条件的 最大 整数：
 * <p>
 * 最多有 n 位数字。
 * 其各位数字之和等于 s 。
 * 如果不存在这样的整数，则返回 -1。
 *
 */
public class Code01 {
    public int largestInteger(int n, int s) {
        // 两个非负整数 n s
        // 最大整数
        int ans = 0;
        while (n > 0) {
            // 要保证返回整数最大 说明当前位数要最大
            int cur = Math.min(s, 9);
            ans = ans * 10 + cur;
            n--;
            s -= cur;
        }
        return s > 0 ? -1 : ans;
    }
}
