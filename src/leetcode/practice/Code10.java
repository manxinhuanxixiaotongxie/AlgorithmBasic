package leetcode.practice;

public class Code10 {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        return checkValid(p) && process1(s.toCharArray(), p.toCharArray(), 0, 0);
    }

    /**
     * str 从si出发 能不能匹配出 pattern 从pi出发的后续字符串
     * @param str
     * @param pattern
     * @param si
     * @param pi
     * @return
     */
    public boolean process1(char[] str, char[] pattern, int si, int pi) {
        // base case
        // 讨论能够匹配的情况
        // 如果原始串已经来到了末位位置
        // 讨论可能性
        // 匹配穿也来到了末位位置
        // 匹配穿没有来到末位位置
        if (si == str.length) {
            // si来到了末位位置 从si出发后面的子串已经是个“”了  那么从p1出发 什么样子的pattern能匹配出来
            if (pi == pattern.length) {
                return true;
            }
            // si来到了末位位置  pi没有来到末位位置
            // 只有一种情况可能的匹配出来
            if (pi + 1 < pattern.length && pattern[pi + 1] == '*') {
                return process1(str, pattern, si, pi + 2);
            }
            return false;
        }

        if (pi == pattern.length) {
            return si == str.length;
        }
        /**
         * 讨论普遍位置：
         *
         * si pi都没有来到终点位置
         * 讨论可能性
         *
         * 第一种情况：
         * 1.pi位置与si位置相等
         *   pi位置与si相等 pi+1不是*
         *   pi位置与si相等 pi+1是*
         *
         * 2.pi位置与si位置不相等
         *      pi位置是.
         *          pi+1位置不是*
         *          pi+1位置是*
         *      pi位置不是.
         *          pi+1位置不是*
         *          pi+1位置是*
         *
         * 归纳一下，简化可能性
         * 讨论pi+1位置是不是*
         *
         * pi+1位置是*
         *    pi位置与si位置相等
         *    这种情况下
         *    pi位置与si位置不相等
         *
         * pi+1位置不是*
         *  pi位置与si位置相等
         *  pi位置与si位置不相等 pi位置是.
         *
         */

        // si 没越界 pi 没越界
        /**
         *          * pi+1位置不是*
         *          *  pi位置与si位置相等
         *          *  pi位置与si位置不相等 pi位置是.
         */
        if (pi + 1 >= pattern.length || pattern[pi + 1] != '*') {
            return ((str[si] == pattern[pi]) || (pattern[pi] == '.')) && process1(str, pattern, si + 1, pi + 1);
        }

        /**
         *      * pi+1位置是*
         *         pi位置是.
         *
         *         pi位置不是.
         *         1.pi与si相等
         *         把当前位置pi当前0个字符
         *
         *         2.pi与si不相等
         *          这个只有一种考虑 把当前位置以及下一个位置的*当做0个字符
         *
         */
        // si 没越界 pi 没越界 pi+1 *
        // 当前位置不是. 下个位置是* 只有一种可能性能匹配出来  就是匹配当前位置0个字符
        // 去pi+2位置做决策

        // pi+1位置是*号
        // 能否匹配出来
        // pi位置是.
        // pi位置不是.
        // pi位置与si位置相等
        // pi位置与si位置相等
        // 其中当是pi位置不是.或者pi位置与si位置不相等的时候 只有si与pi+2的位置能够匹配上才能匹配上
        if ((pattern[pi] != '.' || str[si] != pattern[pi]) && process1(str, pattern, si, pi + 2)) {
            return true;
        }

        // si位置与pi位置匹配上了或者pi位置是.
        // 要讨论匹配一个还是匹配多个的问题
        // 前面的问题已经讨论了pi+1位置不是*的两个情况 要么pi位置与si位置匹配上了 要么Pi位置是.
        // pi+1位置是*的话  讨论了 pi位置不是. pi与si位置不相等的情形
        while (si < str.length && (str[si] == pattern[pi] || pattern[pi] == '.')) {
            // si pi匹配上了 从si+1 pi+2位置开始匹配
            //
            if (process1(str, pattern, si + 1, pi + 2)) {
                return true;
            }
            si++;
        }
        return false;

    }

    private boolean checkValid(String p) {
        for (int i = 0; i < p.length(); i++) {
            if (i == 0 && p.charAt(i) == '*') {
                return false;
            }
//            if (i > 0 && p.charAt(i) == '*' && p.charAt(i - 1) == '*') {
//                return false;
//            }
        }
        return true;
    }

    public static void main(String[] args) {
        Code10 code10 = new Code10();
        System.out.println(code10.isMatch("aa", "a"));
        System.out.println(code10.isMatch("aa", "a*"));
        System.out.println(code10.isMatch("ab", ".*"));
        System.out.println(code10.isMatch("aab", "c*a*b"));
        System.out.println(code10.isMatch("mississippi", "mis*is*p*."));
    }
}
