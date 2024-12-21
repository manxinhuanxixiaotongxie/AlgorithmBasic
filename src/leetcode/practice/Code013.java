package leetcode.practice;

import java.util.HashMap;
import java.util.Map;

public class Code013 {
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        char[][] value = {
                {'I', 'V', 'X'},
                {'X', 'L', 'C'},
                {'C', 'D', 'M'}
        };
        // 分情况讨论
        int ans = 0;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (i + 1 < chars.length && map.get(chars[i]) < map.get(chars[i + 1])) {
                ans -= map.get(chars[i]);
            } else {
                ans += map.get(chars[i]);
            }
        }
        return ans;
    }
}
