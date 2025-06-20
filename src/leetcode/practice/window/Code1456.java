package leetcode.practice.window;

import java.util.LinkedList;

/**
 * 周赛190 Q2
 * <p>
 * 给你字符串 s 和整数 k 。
 * <p>
 * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
 * <p>
 * 英文中的 元音字母 为（a, e, i, o, u）。
 */
public class Code1456 {
    public int maxVowels(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0 || k > s.length()) return 0;
        LinkedList<Integer> window = new LinkedList<>();
        char[] str = s.toCharArray();
        for (int i = 0; i < k - 1; i++) {
            if (isVowel(str[i])) {
                window.addFirst(i);
            }
        }
        int ans = window.size();
        for (int i = k - 1; i < str.length; i++) {
            while (!window.isEmpty() && window.peekLast() < i - k + 1) {
                window.pollLast();
            }
            if (isVowel(str[i])) {
                window.addFirst(i);
            }
            // 结算
            ans = Math.max(ans, window.size());
        }
        return ans;
    }

    public boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public static void main(String[] args) {
        Code1456 code1456 = new Code1456();
        System.out.println(code1456.maxVowels("abciiidef", 3));
    }
}
