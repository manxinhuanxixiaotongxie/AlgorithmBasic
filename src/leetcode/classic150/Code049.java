package leetcode.classic150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 */
public class Code049 {
    public List<List<String>> groupAnagrams(String[] strs) {
        // 进行分组
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.computeIfAbsent(key, k -> new java.util.ArrayList<>()).add(str);
        }
        List<List<String>> ans = new ArrayList<>();
        for (List<String> group : map.values()) {
            ans.add(group);
        }
        return ans;
    }
}
