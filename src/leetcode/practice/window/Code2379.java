package leetcode.practice.window;

/**
 * 给你一个长度为 n 下标从 0 开始的字符串 blocks ，blocks[i] 要么是 'W' 要么是 'B' ，表示第 i 块的颜色。字符 'W' 和 'B' 分别表示白色和黑色。
 * <p>
 * 给你一个整数 k ，表示想要 连续 黑色块的数目。
 * <p>
 * 每一次操作中，你可以选择一个白色块将它 涂成 黑色块。
 * <p>
 * 请你返回至少出现 一次 连续 k 个黑色块的 最少 操作次数。
 */
public class Code2379 {
    public int minimumRecolors(String blocks, int k) {
        // 滑动窗口 窗口大小固定为K 白色数量最少的窗口就是最少的操作次数
        if (blocks == null || blocks.length() == 0 || k <= 0) {
            return 0;
        }
        int N = blocks.length();
        char[] str = blocks.toCharArray();
        int countW = 0;
        for (int i = 0; i < k - 1; i++) {
            if (str[i] == 'W') {
                countW++;
            }
        }
        int minCountW = Integer.MAX_VALUE;
        for (int i = k - 1; i < str.length; i++) {
            // 窗口开始滑动
            if (str[i] == 'W') {
                countW++;
            }
            minCountW = Math.min(minCountW, countW);
            if (str[i - k + 1] == 'W') {
                countW--;
            }
        }

        return minCountW;
    }
}
