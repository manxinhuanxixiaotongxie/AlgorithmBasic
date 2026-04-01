package leetcode.classic150;

/**
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中
 * 找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
 */
public class Code028 {
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    /**
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr2(String haystack, String needle) {
        char[] str1 = haystack.toCharArray();
        char[] str2 = needle.toCharArray();
        int x = 0;
        int y = 0;
        int[] next = getNext(str2);
        while (x < str1.length && y < str2.length) {
            if (str1[x] == str2[y]) {
                x++;
                y++;
            } else if (y == 0) {
                x++;
            } else {
                y = next[y];
            }
        }
        return y == str2.length ? x - y : -1;
    }

    public int[] getNext(char[] needle) {
        if (needle == null || needle.length == 0) {
            return null;
        }
        int[] next = new int[needle.length];
        next[0] = -1;
//        next[1] = 0;
        for (int i = 2; i < needle.length; i++) {
            int x = i - 1;
            while (next[x] != -1) {
                // 这里始终是needle[i-1]和needle[next[x]]比较，因为next[x]是i-1之前的最长公共前缀的长度，所以needle[next[x]]就是最长公共前缀的最后一个字符
                if (needle[i-1] == needle[next[x]]) {
                    next[i] = next[x] + 1;
                    break;
                } else {
                    x = next[x];
                }
            }
        }
        return next;
    }
}
