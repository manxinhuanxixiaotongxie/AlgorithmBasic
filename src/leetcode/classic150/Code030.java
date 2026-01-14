package leetcode.classic150;

import java.util.ArrayList;
import java.util.List;

public class Code030 {
    /**
     * 给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。
     * <p>
     * s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。
     * <p>
     * 例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。
     * "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
     * 返回所有串联子串在 s 中的开始索引。你可以以 任意顺序 返回答案。
     *
     * @param s
     * @param words
     * @return
     */

    /**
     * 超时
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        TrieTree trieTree = new TrieTree();
        process(0, words.length, words, trieTree);
        int len = s.length();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                String subStr = s.substring(i, j + 1);
                if (trieTree.search(subStr) > 0) {
                    ans.add(i);
                }
            }
        }
        return ans;
    }

    public void process(int index, int n, String[] words, TrieTree tree) {
        if (index == words.length) {
            StringBuilder sb = new StringBuilder();
            for (String word : words) {
                sb.append(word);
            }
            tree.insert(sb.toString());
        } else {
            // 任何位置都可以作为第一个数
            for (int i = index; i < n; i++) {
                swap(words, index, i);
                process(index + 1, n, words, tree);
                swap(words, index, i);
            }
        }
    }

    public void swap(String[] words, int i, int j) {
        String temp = words[i];
        words[i] = words[j];
        words[j] = temp;
    }

    class TrieTree {
        TrieNode root;

        TrieTree() {
            root = new TrieNode('\0');
        }

        void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new TrieNode(c);
                }
                node.pass++;
                node = node.nexts[index];
            }
            node.end++;
        }

        int search(String prefix) {
            TrieNode node = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                int index = c - 'a';
                if (node.nexts[index] == null) {
                    return -1;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

    }

    class TrieNode {
        char c;
        int pass = 0;
        int end = 0;
        TrieNode[] nexts = null;

        TrieNode(char c) {
            this.c = c;
            nexts = new TrieNode[26];
        }
    }

    static void main() {
        Code030 code030 = new Code030();
        String s = "bcabbcaabbccacacbabccacaababcbb";
        String[] words = {"c","b","a","c","a","a","a","b","c"};
        List<Integer> res = code030.findSubstring(s, words);
        System.out.println(res);
    }
}
