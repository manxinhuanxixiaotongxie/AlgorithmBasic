package systemimprove.code20;

/**
 * 给定一个字符串str，返回这个字符串的最长回文子序列长度
 * 比如 ： str = “a12b3c43def2ghi1kpm”
 * 最长回文子序列是“1234321”或者“123c321”，返回长度7
 */
public class Code01_LongestPalindromeSubseq {

    /**
     * 有一个投机取巧的方法  利用最长公共子序列 将字符串原地翻转
     * <p>
     * 这里提供一个新的思路
     * 利用范围尝试模型进行尝试
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();

        return process(str, 0, str.length - 1);
    }

    public int longestPalindromeSubseq2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[][] dp = new int[str.length][str.length];
        // 先填对角线的位置
        for (int i = 0; i < str.length; i++) {
            dp[i][i] = 1;
        }

        // 分析一下依赖的位置
        // 如果是str[L]==str[R]  依赖的位置是左边的位置以及下面的位置
        // 如果不相等话 依赖的位置 是左边的位置和下面的位置的最大值
        for (int i = str.length - 2; i >= 0; i--) {
            for (int j = i + 1; j < str.length; j++) {
                if (str[i] == str[j]) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[0][str.length - 1];
    }

    /**
     * 对角线向上爬
     *
     * @param s
     * @return
     */
    public int longestPalindromeSubseq3(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[][] dp = new int[str.length][str.length];
        // 先填对角线的位置
        for (int i = 0; i < str.length; i++) {
            dp[i][i] = 1;
        }

        // 分析一下依赖的位置
        // 如果是str[L]==str[R]  依赖的位置是左边的位置以及下面的位置
        // 如果不相等话 依赖的位置 是左边的位置和下面的位置的最大值
        for (int i = str.length - 3; i >= 0; i--) {
            // 当前位置在i行
            // 我们要计算的列是i+1列到str.length-1列
            int col = i + 1;
            for (int j = col; j < str.length; j++) {
                if (str[i] == str[j]) {
                    dp[i][j] = 2 + dp[i + 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][str.length - 1];
    }

    /**
     * 使用范围上的尝试模型
     * <p>
     * 这个递归函数的含义是在LR 范围上求最长回文子序列的长度
     *
     * @param str
     * @param L
     * @param R
     * @return
     */
    public int process(char[] str, int L, int R) {
        if (L == R) {
            return 1;
        }
        // 这个basecase是因为当时R = L+1的时候如果不相等会导致错位
        if (L > R) {
            return 0;
        }

        if (str[L] == str[R]) {
            return 2 + process(str, L + 1, R - 1);
        } else {
            return Math.max(process(str, L + 1, R), process(str, L, R - 1));
        }

    }

    public static void main(String[] args) {
        Code01_LongestPalindromeSubseq test = new Code01_LongestPalindromeSubseq();
        String str = "a12b3c43def2ghi1kpm";
        System.out.println(test.longestPalindromeSubseq1(str));
        System.out.println(test.longestPalindromeSubseq2(str));
        System.out.println(test.longestPalindromeSubseq3(str));
    }
}
