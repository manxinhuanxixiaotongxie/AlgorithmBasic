package leetcode;

import java.util.List;

public class Code0139 {

    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        return process(s, 0, wordDict);
    }


    /**
     * 递归解释：
     * 从0位置开始选  只要选出来的字符串在字典中就可以
     *
     * @param s
     * @param index
     * @param wordDict
     * @return
     */
    private boolean process(String s, int index, List<String> wordDict) {
        if (index == s.length()) {
            return true;
        }
        for (int start = index; start < s.length(); start++) {
            if (wordDict.contains(s.substring(index, start + 1)) && process(s, start + 1, wordDict)) {
                return true;
            }
        }
        return false;
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[n] = true;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (wordDict.contains(s.substring(i, j + 1)) && dp[j + 1]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

    public boolean wordBreak3(String s, List<String> wordDict) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        TrieTree root = new TrieTree();
        for (String dict : wordDict) {
            char[] chars = dict.toCharArray();
            TrieTree cur = root;
            for (char c : chars) {
                if (cur.next[c - 'a'] == null) {
                    cur.next[c - 'a'] = new TrieTree();
                }
                cur = cur.next[c - 'a'];
            }
            cur.isEnd = true;
        }
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[n] = true;
        for (int i = n - 1; i >= 0; i--) {
            TrieTree cur = root;
            for (int j = i; j < n; j++) {
                cur = cur.next[s.charAt(j) - 'a'];
                if (cur == null) {
                    break;
                }
                if (cur.isEnd && dp[j + 1]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

    static class TrieTree {
        TrieTree[] next;
        boolean isEnd;

        public TrieTree() {
            next = new TrieTree[26];
            isEnd = false;
        }
    }


}
