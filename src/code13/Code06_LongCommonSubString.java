/**
 * @desc:
 * @author: Scurry
 * @date: 2023/8/15 23:37
 */

package code13;

/**
 * @desc:最长公共子序列测试 https://leetcode.cn/problems/qJnOS7/description/
 * @author: Scurry
 * @date: 2023/8/15 23:37
 */
public class Code06_LongCommonSubString {
    private static int index = 0;

    public static void main(String[] args) {
        System.out.println(new Code06_LongCommonSubString().longestCommonSubsequence("ABC", "ACD"));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
            return 0;
        }
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
//        return process2(chars1, chars2, chars1.length - 1, chars2.length - 1);
        return dp2(chars1,chars2);
    }

    //ABCD ACDF
    //  ABC ACDF
    //  AB  ACDF
    //    A   ACDF
    //    A   ACD
    //    A   AC
    //    A   A
    //
    public int process(char[] chars1, char[] chars2, int i, int j) {


        if (i == 0 && j == 0) {
            return chars1[i] == chars2[j] ? 1 : 0;
        } else if (i == 0) {
            if (chars1[i] == chars2[j]) {
                return 1;
            } else {
                return process(chars1, chars2, i, j - 1);
            }
        } else if (j == 0) {
            if (chars1[i] == chars2[j]) {
                return 1;
            } else {
                return process(chars1, chars2, i - 1, j);
            }
        } else {
            int p1 = process(chars1, chars2, i - 1, j);
            int p2 = process(chars1, chars2, i, j - 1);
            int p3 = chars1[i] == chars2[j] ? (1 + process(chars1, chars2, i - 1, j - 1)) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }

    }

    public int process2(char[] chars1, char[] chars2, int i, int j) {

        if (i < 0 || j < 0) {
            return 0;
        }

        if (chars1[i] == chars2[j]) {
            return 1 + process2(chars1, chars2, i - 1, j - 1);
        } else {
            int p1 = process2(chars1, chars2, i - 1, j);
            int p2 = process2(chars1, chars2, i, j - 1);
            return Math.max(p1, p2);
        }

    }


    public int dp2(char[] chars1, char[] chars2) {
        int m = chars1.length;
        int n = chars2.length;
        int[][] dp = new int[m][n];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (chars1[i-1] == chars2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[m+1][n+1];
    }




    public int dp(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }

}
