package codeforgreat.code03;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串的长度。
 * <p>
 * 0 <= s.length <= 5 * 104
 * <p>
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 */
public class Code01_LongestSubstringWithoutRepeatingCharacters {
    /**
     * 思路：
     * 1.暴力解法 暴力遍历所有子串 时间复杂度O(n^2) 过不了
     * 2.动态规划
     * 必须要以i位置结尾的情况下 最长的子串的长度
     * 讨论情况：
     * 如果前一个位置的长度是dp[i-1]，那么如果s[i]不在前一个位置的子串中，那么dp[i] = dp[i-1] + 1
     * 有移除的行为，所以需要一个map来存储每个字符的位置
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {

        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) {
            return 1;
        }
        int[] dp = new int[s.length()];
        dp[0] = 1;
        Map<Character, Integer> map = new HashMap<>();
        map.put(s.charAt(0), 0);
        dp[1] = s.charAt(0) == s.charAt(1) ? 1 : 2;
        map.put(s.charAt(1), 1);
        int ans = dp[1];
        for (int i = 2; i < s.length(); i++) {
            int pre = dp[i - 1];
            // 前一个位置的长度
            // 当前位置上一次出现的位置
            int preIndex = map.getOrDefault(s.charAt(i), -1);
            // 判断当前位置能够获取到的长度
            // 第一种情况 当前位置的上一次出现的位置在的之前的长度之前
            if (preIndex < i - pre) {
                dp[i] = pre + 1;
            } else {
                // 第二种情况 当前位置的上一次出现的位置在的之前的长度之后
                dp[i] = i - preIndex;
            }
            ans = Math.max(ans, dp[i]);
            map.put(s.charAt(i), i);
        }
        return ans;
    }

    /**
     * 针对上述方法进行优化
     * 1.优化点1 不需要实际生成dp数组 空间压缩
     * 2.判断长度进行优化
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {

        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) {
            return 1;
        }
        // 不需要实际数组
//        int[] dp = new int[s.length()];
//        dp[0] = 1;
        Map<Character, Integer> map = new HashMap<>();
        map.put(s.charAt(0), 0);
//        dp[1] = s.charAt(0) == s.charAt(1) ? 1 : 2;
        map.put(s.charAt(1), 1);
        // 代表dp[i-1]
//        int pre
        char[] chars = s.toCharArray();
        int pre = chars[0] == chars[1] ? 1 : 2;
        int ans = pre;
        for (int i = 2; i < s.length(); i++) {
            // 滚动更新pre
//            int pre = dp[i - 1];
            // 前一个位置的长度
            // 代表dp[i]
            int cur = 0;
            // 当前位置上一次出现的位置
            int preIndex = map.getOrDefault(s.charAt(i), -1);
            // 判断当前位置能够获取到的长度
            // 第一种情况 当前位置的上一次出现的位置在的之前的长度之前
            if (preIndex < i - pre) {
                cur = pre + 1;
            } else {
                // 第二种情况 当前位置的上一次出现的位置在的之前的长度之后
                cur = i - preIndex;
            }
            ans = Math.max(ans, cur);
            pre = cur;
            map.put(s.charAt(i), i);
        }
        return ans;
    }

    /**
     * 题目给定 字符串只有小写字符与空格
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring3(String s) {

        if (s == null || s.length() == 0) return 0;
        if (s.length() == 1) {
            return 1;
        }
        // 不需要实际数组
        // 使用固定长度的数组来代替map
        int[] map = new int[256];
        for (int i = 0; i < 256; i++) {
            map[i] = -1;
        }
        map[s.charAt(0)] = 0;
        map[s.charAt(1)] = 1;
        char[] chars = s.toCharArray();
        int pre = chars[0] == chars[1] ? 1 : 2;
        int ans = pre;
        for (int i = 2; i < s.length(); i++) {
            // 滚动更新pre
            int preIndex = map[s.charAt(i)];
            // 判断当前位置能够获取到的长度
            // 第一种情况 当前位置的上一次出现的位置在的之前的长度之前
            pre = Math.min(i - preIndex, pre + 1);
            ans = Math.max(ans, pre);
            map[s.charAt(i)] = i;
        }
        return ans;
    }
}
