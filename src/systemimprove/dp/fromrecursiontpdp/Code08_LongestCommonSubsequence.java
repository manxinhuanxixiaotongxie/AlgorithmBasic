package systemimprove.dp.fromrecursiontpdp;

/**
 * 给定两个字符串str1和str2，
 * 返回这两个字符串的最长公共子序列长度
 * <p>
 * 比如 ： str1 = “a12b3c456d”,str2 = “1ef23ghi4j56k”
 * 最长公共子序列是“123456”，所以返回长度6
 * <p>
 * <p>
 * 最长公共子序列问题
 */
public class Code08_LongestCommonSubsequence {
    public int longestCommonSubsequence1(String s1, String s2) {
        // 给定两个字符串 找到最长公共子序列的长度
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        return process1(str1, str2, str1.length - 1, str2.length - 1);
    }

    // 设计递归函数 返回最长公共子序列的长度
    // 总共有四种模型
    // 从左到右的尝试模型
    // 样本对应模型
    // 业务限制模型
    // 范围上的尝试模型
    // 这个递归函数的含义是 str1[0...l1] 和 str2[0...l2]的最长公共子序列的长度
    public int process1(char[] str1, char[] str2, int l1, int l2) {
        // 使用范围上的尝试模型
        // 最长公共子序列的长度
        // base case
        if (l1 == 0 && l2 == 0) {
            return str1[l1] == str2[l2] ? 1 : 0;
        } else if (l1 == 0) {
            return str1[l1] == str2[l2] ? 1 : process1(str1, str2, l1, l2 - 1);
        } else if (l2 == 0) {
            return str1[l1] == str2[l2] ? 1 : process1(str1, str2, l1 - 1, l2);
        } else {
            // 普遍位置
            // 第一种情况
            int ans = Integer.MIN_VALUE;
            if (str1[l1] == str2[l2]) {
                ans = Math.max(ans, 1 + process1(str1, str2, l1 - 1, l2 - 1));
            } else {
                // 两者不相等
                int p1 = process1(str1, str2, l1 - 1, l2);
                int p2 = process1(str1, str2, l1, l2 - 1);
                int p3 = process1(str1, str2, l1 - 1, l2 - 1);
                ans = Math.max(p3, Math.max(p1, p2));
            }
            return ans;
        }
    }

    /**
     * 改成动态规划
     *
     * @param s1
     * @param s2
     * @return
     */
    public int longestCommonSubsequence2(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int n1 = str1.length;
        int n2 = str2.length;
        int[][] dp = new int[n1][n2];
        // 一个样本做行 一个样本做列
        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        // 第一行
        for (int i = 1; i < n2; i++) {
            dp[0][i] = str1[0] == str2[i] ? 1 : dp[0][i - 1];
        }
        // 第一列
        for (int i = 1; i < n1; i++) {
            dp[i][0] = str1[i] == str2[0] ? 1 : dp[i - 1][0];
        }
        // 普遍位置
        // 左边 左上角 上面 三者求最大
        for (int row = 1; row < n1; row++) {
            for (int col = 1; col < n2; col++) {
                if (str1[row] == str2[col]) {
                    dp[row][col] = 1 + dp[row - 1][col - 1];
                } else {
                    dp[row][col] = Math.max(dp[row - 1][col], dp[row][col - 1]);
                }
            }
        }
        return dp[n1 - 1][n2 - 1];
    }
}
