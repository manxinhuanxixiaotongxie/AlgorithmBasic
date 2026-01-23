package leetcode.classic150;

/**
 * 进阶：您能否仅使用 O(s2.length) 额外的内存空间来解决它?
 *
 */
public class Code097 {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        return process(s1, s2, s3, 0, 0, 0);
    }

    public boolean process(String s1, String s2, String s3, int i1, int i2, int i3) {
        if (s1.length() == i1 && s2.length() == i2 && s3.length() == i3) {
            return true;
        } else if (s1.length() == i1) {
            return s2.substring(i2).equals(s3.substring(i3));
        } else if (s2.length() == i2) {
            return s1.substring(i1).equals(s3.substring(i3));
        } else if (s3.length() == i3) {
            return false;
        } else {
            // 普遍位置
            boolean ans = false;
            if (s1.charAt(i1) == s3.charAt(i3)) {
                if (s2.charAt(i2) == s3.charAt(i3)) {
                    ans |= process(s1, s2, s3, i1 + 1, i2, i3 + 1);
                    ans |= process(s1, s2, s3, i1, i2 + 1, i3 + 1);
                } else {
                    ans |= process(s1, s2, s3, i1 + 1, i2, i3 + 1);
                }
            } else {
                if (s2.charAt(i2) == s3.charAt(i3)) {
                    ans |= process(s1, s2, s3, i1, i2 + 1, i3 + 1);
                } else {
                    return false;
                }
            }
            return ans;
        }
    }

    /**
     * 改动态规划
     *
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) {
            return false;
        }
        // 三维
        int s1Len = s1.length();
        int s2Len = s2.length();
        int s3Len = s3.length();
        boolean[][][] dp = new boolean[s1Len + 1][s2Len + 1][s3Len + 1];
        dp[s1Len][s2Len][s3Len] = true;
        // 最后一个的平面
        for (int i2 = 0; i2 < s2Len; i2++) {
            for (int i3 = 0; i3 < s3Len; i3++) {
                dp[s1Len][i2][i3] = s2.substring(i2).equals(s3.substring(i3));
            }
        }

        // 立体面面向我们的第一个平面
        for (int i1 = s1Len - 1; i1 >= 0; i1--) {
            for (int i3 = 0; i3 < s3Len; i3++) {
                dp[i1][s2Len][i3] = s1.substring(i1).equals(s3.substring(i3));
            }
        }
        // 普遍位置
        for (int i1 = s1Len - 1; i1 >= 0; i1--) {
            for (int i2 = s2Len - 1; i2 >= 0; i2--) {
                for (int i3 = s3Len - 1; i3 >= 0; i3--) {
                    boolean ans = false;
                    if (s1.charAt(i1) == s3.charAt(i3)) {
                        if (s2.charAt(i2) == s3.charAt(i3)) {
                            ans |= dp[i1 + 1][i2][i3 + 1];
                            ans |= dp[i1][i2 + 1][i3 + 1];
                        } else {
                            ans |= dp[i1 + 1][i2][i3 + 1];
                        }
                    } else {
                        if (s2.charAt(i2) == s3.charAt(i3)) {
                            ans |= dp[i1][i2 + 1][i3 + 1];
                        } else {
                            ans = false;
                        }
                    }
                    dp[i1][i2][i3] = ans;
                }
            }
        }
        return dp[0][0][0];
    }
}
