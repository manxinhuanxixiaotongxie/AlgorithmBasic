package codeforgreat.code03;

import java.util.HashSet;

/**
 * 只由小写字母（a~z）组成的一批字符串
 * 都放在字符类型的数组String[] arr中
 * 如果其中某两个字符串所含有的字符种类完全一样
 * 就将两个字符串算作一类
 * 比如：baacbba和bac就算作一类
 * 返回arr中有多少类？
 */
public class Code02_HowManyTypes {
    public static int types1(String[] arr) {
        HashSet<String> ans = new HashSet<>();
        for (String str : arr) {
            // 遍历数组
            // 将数组元素去重
            char[] chars = str.toCharArray();
            // 对数组中元素的数组进行去重
            // 去重的快速方式
            boolean[] visited = new boolean[26];
            for (char c : chars) {
                visited[c - 'a'] = true;
            }
            StringBuilder cur = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (visited[i]) {
                    cur.append((char) ('a' + i));
                }
            }
            ans.add(cur.toString());
        }
        return ans.size();
    }
}
