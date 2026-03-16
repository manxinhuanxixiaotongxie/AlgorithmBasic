package leetcode.classic150;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个字符串 s 和 t，长度分别是 m 和 n，返回 s 中的 最短窗口 子串，使得该子串包含 t 中的每一个字符（包括重复字符）。如果没有这样的子串，返回空字符串 ""。
 * <p>
 * 测试用例保证答案唯一。
 *
 */
public class Code076 {
    public String minWindow(String s, String t) {
        // 滑动窗口
        int m = s.length();
        int n = t.length();
        if (m == 0 || n == 0) return "";
        if (m < n) return "";
        String ans = "";
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.merge(t.charAt(i), 1, Integer::sum);
        }
        int minLen = Integer.MAX_VALUE, startIdx = 0;
        int right = 0;
        Map<Character, Integer> window = new HashMap<>();
        int match = 0;
        for (int i = 0; i < m; i++) {
            // 入窗口
            while (right < m && match < n) {
                char c = s.charAt(right);
                if (map.containsKey(c)) {
                    window.merge(c, 1, Integer::sum);
                    if (window.get(c) <= map.get(c)) {
                        match++;
                    }
                }
                right++;
            }

            // 结算
            if (match == n) {
                if (right - i < minLen) {
                    minLen = right - i;
                    startIdx = i;
                }
            }

            // 出窗口
            char out = s.charAt(i);
            if (map.containsKey(out)) {
                // 为什么要这么一段呢？ 有可能有重复的字符 比如t是aa  s是aaa  这时候窗口里有3个a  出一个a之后 窗口里还有2个a 仍然满足条件 只有当窗口里a的数量小于t里a的数量时 才说明不满足条件了
                if (window.get(out) <= map.get(out)) {
                    match--;
                }
                window.merge(out, -1, Integer::sum);
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(startIdx, startIdx + minLen);
    }
}
