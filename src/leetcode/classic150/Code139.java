package leetcode.classic150;

import java.util.List;

public class Code139 {
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
            if (wordDict.contains(s.substring(index, start + 1))
                    && process(s, start + 1, wordDict)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 改动态规划
     *
     * @param s
     * @param wordDict
     * @return
     */
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

    /**
     * 前缀树优化  递归版本  超时
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak3(String s, List<String> wordDict) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        TrieTree root = new TrieTree();
        root.buildTree(wordDict);
        return process2(s, 0, root);
    }

    /**
     * 改成DP
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak4(String s, List<String> wordDict) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        TrieTree root = new TrieTree();
        root.buildTree(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[n] = true;
        for (int j = n - 1; j >= 0; j--) {
            TrieNode node = root.root;
            for (int i = j; i < s.length(); i++) {
                int charIndex = s.charAt(i) - 'a';
                if (node.next[charIndex] == null) {
                    dp[j] = false;
                    break;
                }
                node = node.next[charIndex];
                if (node.end > 0 && dp[i + 1]) {
                    dp[j] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

    public boolean process2(String s, int index,TrieTree root) {
        if (index == s.length()) {
            return true;
        }
        TrieNode node = root.root;
        for (int i = index; i < s.length(); i++) {
            int charIndex = s.charAt(i) - 'a';
            if (node.next[charIndex] == null) {
                return false;
            }
            node = node.next[charIndex];
            if (node.end > 0 && process2(s, i + 1, root)) {
                return true;
            }
        }
        return false;
    }

    class TrieTree {
        TrieNode root;
        TrieTree() {
            this.root = new TrieNode('\0');
        }

        public void buildTree(List<String> wordDict) {
            for (String word : wordDict) {
                insert(word);
            }
        }

        public boolean search(String word) {
            char[] wordStr = word.toCharArray();
            TrieNode node = root;
            for (int i = 0; i < wordStr.length; i++) {
                int index = wordStr[i] - 'a';
                if (node.next[index] == null) {
                    return false;
                }
                node = node.next[index];
            }
            return node.end > 0;
        }

        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int index = c - 'a';
                if (node.next[index] == null) {
                    node.next[index] = new TrieNode(c);
                }
                node = node.next[index];
            }
            node.end++;
        }
    }

    class TrieNode {
        char val;
        int end;
        TrieNode[] next;
        public TrieNode(char val) {
            this.val = val;
            this.end = 0;
            this.next = new TrieNode[26];
        }
    }
}
