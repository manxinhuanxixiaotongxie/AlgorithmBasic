package leetcode.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的
 * 异位词
 * 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 */
public class Code0438 {
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        // 分组
        int pN = p.length();
        char[] pChar = p.toCharArray();
        int[] help = new int[26];
        for (int i = 0; i < pN; i++) {
            help[pChar[i] - 'a']++;
        }
        char[] sChar = s.toCharArray();
        // 以p的长度为一组
        // s的长度
        // s 0 1 2 3 4 5 长度是6
        // p 0 1 2       长度是3
        // 那么初始位置  i能的位置6-3
        for (int i = 0; i < sChar.length - p.length() + 1; i++) {
            int[] temp = new int[26];
            for (int j = i; j < pN + i; j++) {
                temp[sChar[j] - 'a']++;
            }
            if (Arrays.equals(help, temp)) {
                ans.add(i);
            }
        }

        return ans;
    }
}
