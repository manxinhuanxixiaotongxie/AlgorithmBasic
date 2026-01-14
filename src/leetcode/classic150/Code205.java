package leetcode.classic150;

import java.util.HashMap;
import java.util.Map;

public class Code205 {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> map = new HashMap<>();
        Map<Character, Character> map2 = new HashMap<>();
        char[] sStr = s.toCharArray();
        char[] tStr = t.toCharArray();
        for (int i = 0; i < sStr.length; i++) {
            if (!map.containsKey(sStr[i])) {
                map.put(sStr[i], tStr[i]);
            }else {
                if (map.get(sStr[i]) != tStr[i]) {
                    return false;
                }
            }
            if (!map2.containsKey(tStr[i])) {
                map2.put(tStr[i], sStr[i]);
            }else {
                if (map2.get(tStr[i]) != sStr[i]) {
                    return false;
                }
            }


        }
        return true;
    }
}
