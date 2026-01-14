package leetcode.classic150;

import java.util.HashMap;
import java.util.Map;

public class Code290 {
    public boolean wordPattern(String pattern, String s) {
        char[] paStr = pattern.toCharArray();
        String[] sArr = s.split(" ");
        if (paStr.length != sArr.length) {
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        Map<String, Character> map2 = new HashMap<>();
        for (int i = 0; i < paStr.length; i++) {
            if (!map.containsKey(paStr[i])) {
                map.put(paStr[i], sArr[i]);
            } else {
                if (!map.get(paStr[i]).equals(sArr[i])) {
                    return false;
                }
            }
            if (!map2.containsKey(sArr[i])) {
                map2.put(sArr[i], paStr[i]);
            } else {
                if ((map2.get(sArr[i]) != paStr[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    static void main() {
        Code290 code290 = new Code290();
        System.out.println(code290.wordPattern("abba", "dog dog dog dog"));
    }
}
