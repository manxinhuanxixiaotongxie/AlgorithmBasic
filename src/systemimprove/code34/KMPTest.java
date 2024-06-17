package systemimprove.code34;

public class KMPTest {
    /**
     * kmp算法 是为了解决两个字符串匹配问题
     * 给定一个字符串str1 再给定一个字符串str2 如果字符串1包含字符窜2的话 返回第一次包含的下标 如果不包含的话返回-1
     *
     * 1.暴力解法
     * 2.kmp解法
     */

    public int indexOf1(String str1,String str2) {
        if (str1 == null || str1.length() < str2.length() || str2 == null) {
            return -1;
        }

        /**
         * kmp解法 将str2进行加工 获取的next数组
         * next数组的含义是：next[i]不包含当前位置的字符 前面所有字符的最长前后缀的长度
         */
        int ans = -1;
        int[] next = getNext(str2);
        int x = 0;
        int y = 0;
        while (x<str1.length() && y < str2.length()) {
            if (str1.charAt(x) == str2.charAt(y)) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2.length() ? x - y : -1;
    }

    private int[] getNext(String str) {
        int[] next = new int[str.length()];
        /**
         * 求解next数组的加速
         * 如果i-1位置的值与next[i-1]位置的值相等的话 那么next[i] = next[i-1] + 1
         */
        int cn = 0;
        next[0] = -1;
        next[1] = 0;
        for (int i = 2; i < str.length(); i++) {
            if (str.charAt(i-1) == str.charAt(cn)) {
                next[i] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                // 如果cn=0 意味着 next[cn] = -1
                next[i] = 0;
            }
        }
        return next;
    }
}
