package leetcode.practice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Code044 {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();
        boolean[][] dp = new boolean[str.length + 1][pattern.length + 1];
        dp[str.length][pattern.length] = true;
        for (int i = pattern.length - 1; i >= 0; i--) {
            if (pattern[i] == '*') {
                dp[str.length][i] = dp[str.length][i + 1];
            }
        }
        // 从倒数第二行开始填
        // 从右往左填写
        for (int r = str.length - 1; r >= 0; r--) {
            for (int c = pattern.length - 1; c >= 0; c--) {
                if (str[r] == pattern[c] || pattern[c] == '?') {
                    dp[r][c] = dp[r + 1][c + 1];
                } else if (pattern[c] == '*') {
                    dp[r][c] = dp[r][c + 1] || dp[r + 1][c];
                } else {
                    int index = r;
                    while (index + 1 < str.length && str[index + 1] == str[index]) {
                        if (dp[index + 1][c]) {
                            dp[index][c] = true;
                            break;
                        }
                        index++;
                    }
                }
            }
        }
        return dp[0][0];
//        return process(str, pattern, 0, 0);
    }

    /**
     * 暴力递归
     *
     * @param str
     * @param pattern
     * @param si
     * @param pi
     * @return
     */
    private boolean process(char[] str, char[] pattern, int si, int pi) {
        // 讨论可能性
        // 如果原始字符串已经来到了末位位置
        if (si == str.length) {
            // 讨论能够匹配出来的情形
            // 当匹配串也来到了末位的位置 那么说明之前已经匹配完了 说明是可以匹配出来
            if (pi == pattern.length) {
                return true;
            }
            // pi没来到末位位置
            // 讨论能匹配出来的场景
            // 能匹配出来的第一种场景
            // pi位置是? 并且后序的流程能够匹配出来
            if (pattern[pi] == '?') {
                return false;
            }
            // pi位置是*也可以匹配出来
            if (pattern[pi] == '*') {
                return process(str, pattern, si, pi + 1);
            }
            // pi位置是普通字符
            return false;
        }
        // str没来到末位位置
        if (pi == pattern.length) {
            return si == str.length;
        }
        // 普遍位置的可能性
        // 第一种情况si位置与pi位置相等
        if (str[si] == pattern[pi]) {
            return process(str, pattern, si + 1, pi + 1);
        }
        if (pattern[pi] == '?') {
            return process(str, pattern, si + 1, pi + 1);
        }
        if (pattern[pi] == '*') {
            return str[si] != pattern[pi] && (process(str, pattern, si, pi + 1) || process(str, pattern, si + 1, pi));
        }
        // si与pi位置不相等 并且pi位置不是？
        // 只有一种可能性能够匹配出来
        // pi位置是* 并且后续的流程能够匹配出来
        while (si + 1 < str.length && str[si + 1] == str[si]) {
            if (process(str, pattern, si + 1, pi)) {
                return true;
            }
            si++;
        }

        return false;
    }

    public static void main(String[] args) throws ParseException {
        /**
         * 获取次日00:00时间
         * @return
         */
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse("2024-12-31 20:59:59");
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println(ca.getTime());

        String s1 = "";
        System.out.println(s1.length());

    }
}
