package leetcode.practice;

import java.util.HashMap;
import java.util.Map;

public class Code0395 {
    public int longestSubstring(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int ans = 0;

        int[] dp = new int[s.length()];
        map.put(s.charAt(0), map.getOrDefault(s.charAt(0), 0) + 1);
        dp[0] = map.get(s.charAt(0)) >= k ? 1 : 0;
        for (int i = 1; i < s.length(); i++) {
            if (map.get(s.charAt(i)) != null && map.get(s.charAt(i)) + 1 < k) {
                map.clear();
            }
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            dp[i] = map.get(s.charAt(i)) >= k ? dp[i - 1] + 1 : 0;
        }

        for (int i = 0; i < s.length(); i++) {
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        Code0395 code0395 = new Code0395();
        System.out.println(code0395.longestSubstring("ababacb", 3));
    }
}
