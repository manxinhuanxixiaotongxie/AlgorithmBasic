package leetcode.classic150;

/**
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中
 * 找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
 */
public class Code028 {
    public int strStr1(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    /**
     * kmp
     *
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr2(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}
