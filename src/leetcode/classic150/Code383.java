package leetcode.classic150;

import java.util.HashMap;
import java.util.Map;

public class Code383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        // 字符 数量
        Map<Character, Integer> map = new HashMap<>();
        char[] maStr = magazine.toCharArray();
        for (char c : maStr) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        char[] raStr = ransomNote.toCharArray();
        for (char c : raStr) {
            if (!map.containsKey(c)) return false;
            if (map.get(c) < 1) return false;
            map.put(c, map.get(c) - 1);
        }
        return true;
    }

    /**
     * 小写英文字符
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct2(String ransomNote, String magazine) {
        // 字符 数量
        int[] nums = new int[26];
        char[] maStr = magazine.toCharArray();
        for (char c : maStr) {
            nums[c - 'a']++;
        }
        char[] raStr = ransomNote.toCharArray();
        for (char c : raStr) {
            if (nums[c - 'a'] == 0) return false;
            nums[c - 'a']--;
        }
        return true;
    }
}
