package leetcode;

/**
 * 最长回文子串
 */
public class Code05 {

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
        char[] newStr = new char[s.length() * 2 + 1];
        char[] str = s.toCharArray();
        for (int i = 0; i < newStr.length; i++) {
            if (i % 2 == 0) {
                newStr[i] = '#';
            } else {
                newStr[i] = str[i >> 1];
            }
        }
        return newStr;
    }
}
