package leetcode.foundation;

import java.util.HashSet;

public class Code2586 {
    public int vowelStrings(String[] words, int left, int right) {
        int ans = 0;
        HashSet<Character> characters = new HashSet<>();
        characters.add('a');
        characters.add('e');
        characters.add('i');
        characters.add('o');
        characters.add('u');
        while (left <= right) {
            String cur = words[left];
            if (characters.contains(cur.charAt(0)) && characters.contains(cur.charAt(cur.length() - 1))) {
                ans++;
            }
            left++;
        }
        return ans;
    }
}
