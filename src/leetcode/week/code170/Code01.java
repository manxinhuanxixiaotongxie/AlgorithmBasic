package leetcode.week.code170;

/**
 * 给你一个 正 整数 n。
 *
 * 令 s 为 n 的 二进制表示（不含前导零）。
 *
 * 二进制字符串 s 的 反转 是指将 s 中的字符按相反顺序排列得到的字符串。
 *
 * 你可以翻转 s 中的任意一位（将 0 → 1 或 1 → 0）。每次翻转 仅 影响一位。
 *
 * 请返回使 s 等于其原始形式的反转所需的 最少 翻转次数。©leetcode
 */
public class Code01 {
    public int minimumFlips(int n) {
        // 查询n的最高位与最低位
        // 整数的最高位怎么计算
        int ans = 0;
        String binaryString = Integer.toBinaryString(n);
        char[] str = binaryString.toCharArray();
        int high = binaryString.length() -1;
        int low = 0;
        while (low < high) {
            // 判断low位置与high的二进制是否相等
            if (str[low] != str[high]) {
                ans += 2;
            }
            low ++;
            high--;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new  Code01().minimumFlips(10));
    }
}
