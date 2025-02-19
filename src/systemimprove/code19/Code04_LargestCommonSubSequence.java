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
 * <p>
 * https://leetcode.com/problems/longest-common-subsequence/
 * <p>
 * 测试通过  但是longestCommonSubsequence2时间复杂度不好 待后续分析：
 * 是因为charAt方法耗时
 * <p>
 * 最长个公共组序列的另外一种写法见从暴力递归到动态规划专题
 */
public class Code04_LargestCommonSubSequence {

    public int longestCommonSubsequence1(String str1, String str2) {
        return process(str1.toCharArray(), str2.toCharArray(), 0, 0);
    }

    public int longestCommonSubsequence2(String str1, String str2) {
        int n1 = str1.length();
        int n2 = str2.length();
        int[][] dp = new int[n1][str2.length()];
        // 行为str1 列为str2
        dp[n1 - 1][n2 - 1] = str1.charAt(n1 - 1) == str2.charAt(n2 - 1) ? 1 : 0;
        // 最后一行
        for (int i = n2 - 2; i >= 0; i--) {
            dp[n1 - 1][i] = str1.charAt(n1 - 1) == str2.charAt(i) ? 1 : dp[n1 - 1][i + 1];
        }
        // 最后一列
        for (int i = n1 - 2; i >= 0; i--) {
            dp[i][n2 - 1] = str1.charAt(i) == str2.charAt(n2 - 1) ? 1 : dp[i + 1][n2 - 1];
        }
        // 普遍位置的依赖是什么
        // 根据暴力递归的过程 普遍位置依赖 下一行数 右一列的数 右下角数数 并且 当str1[i] == str2[j]时 还要依赖右下角数数+1
        for (int i = n1 - 2; i >= 0; i--) {
            for (int j = n2 - 2; j >= 0; j--) {
                int p1 = dp[i][j + 1];
                int p2 = dp[i + 1][j];
                int p3 = dp[i + 1][j + 1];
                int p4 = str1.charAt(i) == str2.charAt(j) ? 1 + dp[i + 1][j + 1] : 0;
                dp[i][j] = Math.max(Math.max(p1, p2), Math.max(p3, p4));
            }
        }
        return dp[0][0];
    }

    /**
     * 优化一点longestCommonSubsequence2的动态规划
     *
     * @param str1
     * @param str2
     * @return
     */
    public int longestCommonSubsequence3(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int n1 = s1.length;
        int n2 = s2.length;
        int[][] dp = new int[n1][n2];
        // 行为str1 列为str2
        dp[n1 - 1][n2 - 1] = s1[n1 - 1] == s2[n2 - 1] ? 1 : 0;
        // 最后一行
        for (int i = n2 - 2; i >= 0; i--) {
            dp[n1 - 1][i] = s1[n1 - 1] == s2[i] ? 1 : dp[n1 - 1][i + 1];
        }
        // 最后一列
        for (int i = n1 - 2; i >= 0; i--) {
            dp[i][n2 - 1] = s1[i] == s2[n2 - 1] ? 1 : dp[i + 1][n2 - 1];
        }
        // 普遍位置的依赖是什么
        // 根据暴力递归的过程 普遍位置依赖 下一行数 右一列的数 右下角数数 并且 当str1[i] == str2[j]时 还要依赖右下角数数+1
        for (int i = n1 - 2; i >= 0; i--) {
            for (int j = n2 - 2; j >= 0; j--) {
                int p1 = dp[i][j + 1];
                int p2 = dp[i + 1][j];
                int p3 = dp[i + 1][j + 1];
                int p4 = s1[i] == s2[j] ? 1 + p3 : 0;
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
        if (L == str1.length - 1) {
            return str1[L] == str2[R] ? 1 : process(str1, str2, L, R + 1);
        }
        if (R == str2.length - 1) {
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
