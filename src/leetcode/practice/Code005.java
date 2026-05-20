package leetcode.practice;

/**
 * 最长回文子串
 */
public class Code005 {

    /**
     * 使用manacher算法解决这个问题
     *
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        char[] newStr = getDouble(s);
        int[] pNext = new int[newStr.length];
        int r = 0;
        int c = 0;
        String ans = "";
        for (int i = 0; i < newStr.length; i++) {
            pNext[i] = r > i ? Math.min(pNext[2 * c - i], r - i) : 1;
            while (i + pNext[i] < newStr.length && i - pNext[i] > -1) {
                if (newStr[i + pNext[i]] == newStr[i - pNext[i]]) {
                    pNext[i]++;
                } else {
                    break;
                }
            }
            if (i + pNext[i] > r) {
                r = i + pNext[i];
                c = i;
            }
            if (pNext[i] > ans.length()) {
                ans = s.substring((i - pNext[i] + 1) / 2, (i + pNext[i] - 1) / 2);
            }
        }
        return ans;

    }

    /**
     * 将数组加工成原字符串的两倍
     *
     * @param s
     * @return
     */
    private char[] getDouble(String s) {
        if (s == null || s.isEmpty()) {
            return null;
        }
        char[] str = s.toCharArray();
        int n = str.length;
        char[] ans = new char[(n << 1) + 1];

        for (int i = 0; i < n; i++) {
            ans[2 * i + 1] = str[i];
            ans[2 * i] = '#';
        }
        ans[(2 * n)] = '#';
        return ans;
    }
}
