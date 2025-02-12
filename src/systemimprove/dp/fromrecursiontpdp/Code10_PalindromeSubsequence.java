package systemimprove.dp.fromrecursiontpdp;

/**
 * 给定一个字符串str，返回这个字符串的最长回文子序列长度
 * 比如 ： str = “a12b3c43def2ghi1kpm”
 * 最长回文子序列是“1234321”或者“123c321”，返回长度7
 * <p>
 * <p>
 * code09已经求最长公共子序列
 * 将字符串倒转过来变成s2  就变化了与第9题一样的现象
 */
public class Code10_PalindromeSubsequence {

    public int longestPalindromeSubseq1(String s) {
        // 倒转字符串
        // 就变成与第9题一样的现象
        // 解题方法略
        return 0;
    }

    public int longestPalindromeSubseq2(String s) {
        // 范围上的尝试模型
        return process(s.toCharArray(), 0, s.length() - 1);
    }

    public int process(char[] str, int l, int r) {
        if (l > r) {
            return 0;
        }
        if (l == r) {
            return 1;
        }
        // 普遍现象
        int ans = Integer.MIN_VALUE;
        // 第一种情况
        if (str[l] == str[r]) {
            ans = Math.max(ans, process(str, l + 1, r - 1) + 2);
        } else {
            // 不相等
            // 第一种情况
            int p1 = process(str, l + 1, r);
            // 第二种情况
            int p2 = process(str, l, r - 1);
            ans = Math.max(p1, p2);
        }
        return ans;
    }

    /**
     * 改成动态规划
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq3(String s) {
        // 范围上的尝试模型
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        // 整张表格的左下角默认都是0 不需要填写
        // 对角线填写 对角线都是1
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
        }
        // 普遍位置
        // 依赖的是左下角的位置
        for (int row = N - 2; row >= 0; row--) {
            int col = row + 1;
            while (col < N) {
                if (str[row] == str[col]) {
                    dp[row][col] = dp[row + 1][col - 1] + 2;
                } else {
                    dp[row][col] = Math.max(dp[row + 1][col], dp[row][col - 1]);
                }
                col++;
            }
        }
        return dp[0][N - 1];
    }

}
