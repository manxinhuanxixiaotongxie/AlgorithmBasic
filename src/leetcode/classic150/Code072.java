package leetcode.classic150;

public class Code072 {
    /**
     * 超时
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        if (word2 == null || word2.length() == 0) {
            return word1 == null ? 0 : word1.length();
        }
        if (word1 == null || word1.length() == 0) {
            return word2.length();
        }
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        return process(w1, w2, w1.length - 1, w2.length - 1);
    }

    // 0-k1范围  0到i2范围
    public int process(char[] w1,char[] w2,int i1,int i2) {
        if (i1 == 0 && i2 == 0) {
            return w1[i1] == w2[i2] ? 0 : 1;
        }else if (i1 == 0) {
            // i1为0 但是i2不为0
            return w1[i1] == w2[i2] ? i2 : process(w1, w2, i1, i2 - 1) + 1;
        }else if (i2 == 0) {
            return w1[i1] == w2[i2] ? i1 : process(w1, w2, i1 - 1, i2) + 1;
        }else {
            // 普遍位置
            int ans = Integer.MAX_VALUE;
            if (w1[i1] == w2[i2]) {
                ans = process(w1, w2, i1 - 1, i2 - 1);
            }else {
                // 不相等
                // 替换
                ans = Math.min(ans,process(w1, w2, i1 - 1, i2 - 1) + 1);
                // 删除 或者新增
                ans = Math.min(ans,process(w1, w2, i1 - 1, i2) + 1);
                ans = Math.min(ans,process(w1, w2, i1, i2 - 1) + 1);
            }
            return ans;
        }
    }

    /**
     * 改成动态规划
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance2(String word1, String word2) {
        if (word2 == null || word2.length() == 0) {
            return word1 == null ? 0 : word1.length();
        }
        if (word1 == null || word1.length() == 0) {
            return word2.length();
        }
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        int w1Len = w1.length;
        int w2Len = w2.length;
        int[][] dp = new int[w1Len][w2Len];
        dp[0][0] = w1[0] == w2[0] ? 0 : 1;
        // 第一行
        for (int i2 = 1; i2 < w2Len; i2++) {
            dp[0][i2] = w1[0] == w2[i2] ? i2 : dp[0][i2 - 1] + 1;
        }
        // 第一列
        for (int i1 = 1; i1 < w1Len; i1++) {
            dp[i1][0] = w1[i1] == w2[0] ? i1 : dp[i1 - 1][0] + 1;
        }

        // 普遍位置
        for (int i1 = 1; i1 < w1Len; i1++) {
            for (int i2 = 1; i2 < w2Len; i2++) {
                if (w1[i1] == w2[i2]) {
                    dp[i1][i2] = dp[i1 - 1][i2 - 1];
                }else {
                    // 不相等
                    // 替换
                    int ans = dp[i1 - 1][i2 - 1] + 1;
                    // 删除 或者新增
                    ans = Math.min(ans,dp[i1 - 1][i2] + 1);
                    ans = Math.min(ans,dp[i1][i2 - 1] + 1);
                    dp[i1][i2] = ans;
                }
            }
        }

        return dp[w1Len - 1][w2Len - 1];
    }
}
