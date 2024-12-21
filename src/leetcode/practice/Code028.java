package leetcode.practice;

public class Code028 {
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    // 使用kmp算法
    public int strStr2(String haystack, String needle) {
        char[] si = haystack.toCharArray();
        char[] pi = needle.toCharArray();
        // 根据匹配字符串数据获取一个next数据
        // next[i]的含义 不包含当前i位置以及之前所有位置的  最长公共前后缀的长度
        int[] nexts = getNext(pi);
        int x = 0;
        int y = 0;
        while (x < si.length && y < pi.length) {
            // 根据kmp算法的流程
            // 我们现在是要算最长公共子串的最初相交的位置
            if (si[x] == pi[y]) {
                x++;
                y++;
                // 来到了第一个不相等的位置
            } else if (y == 0) {
                x++;
            } else {
                y = nexts[y];
            }
        }
        return y == pi.length ? x - y : -1;
    }

    private int[] getNext(char[] pi) {
        // 最长公共前缀数组怎么求？
        // 分情况讨论
        // 1.第一种情况
        // pi[i-1]位置与next[i-1]位置相等 那么i位置的next的长度一定是next[i-1]+1
        // 不相等的情况
        int[] next = new int[pi.length];
        if (pi.length == 0) {
            return next;
        }
        next[0] = -1;
        if (pi.length == 1) {
            return next;
        }
        next[1] = 0;
        if (pi.length == 2) {
            return next;
        }
        int cn = 0;
        for (int i = 2; i < pi.length;) {
            if (pi[i - 1] == pi[cn]) {
                next[i++] = ++cn;
            } else if (cn == 0) {
                next[i++] = 0;
            } else {
                cn = next[cn];
            }
        }
        return next;
    }

    public static void main(String[] args) {
        Code028 code028 = new Code028();
//        System.out.println(code028.strStr2("a", "a"));
//        System.out.println(code028.strStr2("mississippi", "issip"));
        System.out.println(code028.strStr2("aabaaabaaac", "aabaaac"));
//        System.out.println(code028.strStr("", ""));
    }
}
