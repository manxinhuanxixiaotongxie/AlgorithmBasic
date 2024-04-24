package systemimprove.code19;

/**
 * 给定str1 str2 求str1 str2的最长公共子序列
 * <p>
 * 子串要求连续     子序列不要求连续
 * <p>
 * 给定两个字符串str1和str2，
 * 返回这两个字符串的最长公共子序列长度
 * <p>
 * 比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
 * 最长公共子序列是“123456”，所以返回长度6
 * https://leetcode.com/problems/longest-common-subsequence/
 * 测试通过  但是时间复杂度不好 待后续分析
 *
 */
public class Code04_LargestCommonSubSequence {

    public int longestCommonSubsequence1(String str1, String str2) {
        return process(str1.toCharArray(), str2.toCharArray(), 0, 0);
    }

    public int longestCommonSubsequence2(String str1, String str2) {
        int[][] dp = new int[str1.length()][str2.length()];
        // 行为str1 列为str2
        dp[str1.length()-1][str2.length()-1] = str1.charAt(str1.length()-1) == str2.charAt(str2.length()-1) ? 1 : 0;
        // 最后一行
        for (int i = str2.length()-2; i >= 0; i--) {
            dp[str1.length()-1][i] = str1.charAt(str1.length()-1) == str2.charAt(i) ? 1 : dp[str1.length()-1][i+1];
        }
        // 最后一列
        for (int i = str1.length()-2; i >= 0; i--) {
            dp[i][str2.length()-1] = str1.charAt(i) == str2.charAt(str2.length()-1) ? 1 : dp[i+1][str2.length()-1];
        }
        // 普遍位置的依赖是什么
        // 根据暴力递归的过程 普遍位置依赖 下一行数 右一列的数 右下角数数 并且 当str1[i] == str2[j]时 还要依赖右下角数数+1
        for (int i = str1.length()-2; i >= 0; i--) {
            for (int j = str2.length()-2; j >= 0; j--) {
                int p1 = dp[i][j+1];
                int p2 = dp[i+1][j];
                int p3 = dp[i+1][j+1];
                int p4 = str1.charAt(i) == str2.charAt(j) ? 1 + dp[i+1][j+1] : 0;
                dp[i][j] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }
        return dp[0][0];
    }

    // 这个递归函数的含义就是给定两个字符串str1和str2，返回这两个字符串的最长公共子序列长度
    // L的长度是str1的长度  意味着str1能达到范围
    // R的长度是str2的长度  意味着str2能达到范围
    public int process(char[] str1, char[] str2, int L, int R) {
        // basecase
        if (L == str1.length - 1 && R == str2.length - 1) {
            return str1[L] == str2[R] ? 1 : 0;
        }
        if (L==str1.length-1) {
            return str1[L] == str2[R] ? 1 : process(str1, str2, L, R + 1);
        }
        if (R==str2.length-1) {
            return str1[L] == str2[R] ? 1 : process(str1, str2, L + 1, R);
        }

        // 讨论可能性
        // 第一种可能性 可能考虑L位置 但是一定会不考虑R位置
        int p1 = process(str1, str2, L, R + 1);
        // 第二种可能性 可能考虑R位置 但是一定会不考虑L位置
        int p2 = process(str1, str2, L + 1, R);
        // 第三种情况 L位置与R位置都不考虑
        int p3 = process(str1, str2, L + 1, R + 1);
        // 第四种情况 L位置与R位置都考虑
        int p4 = str1[L] == str2[R] ? 1 + process(str1, str2, L + 1, R + 1) : 0;
        return Math.max(Math.max(p1, p2), Math.max(p3, p4));
    }

    public static void main(String[] args) {
        String str1 = "abcde";
        String str2 = "ace";
//        String str1 = "a12b3c456d";
//        String str2 = "1ef23ghi4j56k";
        Code04_LargestCommonSubSequence code04_largestCommonSubSequence = new Code04_LargestCommonSubSequence();
        System.out.println(code04_largestCommonSubSequence.longestCommonSubsequence1(str1, str2));
    }
}
