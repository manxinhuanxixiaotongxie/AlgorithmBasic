package leetcode.classic150;

import java.util.Map;

/**
 * 罗马数字转整数
 * 给定一个罗马数字 将其转化成整数
 */
public class Code013 {
    static Map<String,Integer> map = null;
    static {
        map = new java.util.HashMap<>();
        map.put("I",1);
        map.put("IV",4);
        map.put("V",5);
        map.put("IX",9);
        map.put("X",10);
        map.put("XL",40);
        map.put("L",50);
        map.put("XC",90);
        map.put("C",100);
        map.put("CD",400);
        map.put("D",500);
        map.put("CM",900);
        map.put("M",1000);
    }
    public int romanToInt(String s) {
        int ans = 0;
        char[] str = s.toCharArray();
        // 可以放在前面代表减法
        for (int i = 0; i < str.length; i++) {
            // 获取子串
            String sub = s.substring(i, i + 2 <= str.length ? i + 2 : i + 1);
            if (map.containsKey(sub)) {
                ans += map.get(sub);
                i++;
            } else {
                ans += map.get(String.valueOf(str[i]));
            }
        }

        return ans;
    }
}
