package leetcode.day;

import java.util.HashSet;
import java.util.Set;

/**
 * 最长子串
 *
 */
public class Code003 {
    public int lengthOfLongestSubstring(String s) {
        char[] str = s.toCharArray();
        Set<Character> set = new HashSet<>();
        int right = 0;
        int ans = 0;
        for (int i = 0; i < str.length; i++) {
            while (right < str.length && !set.contains(str[right])) {
                set.add(str[right]);
                right++;
            }
            // 结算
            ans = Math.max(ans, right - i);
            set.remove(str[i]);
        }

        return ans;

    }
}
