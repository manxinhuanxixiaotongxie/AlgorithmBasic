package leetcode.classic150;

import java.io.FileOutputStream;

public class Code014 {
    /**
     * 暴力做法
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder(strs[0]);
        // 以第一个为基准 开始收缩
        while (sb.length() > 0) {
            String base = sb.toString();
            boolean isMatch = true;
            for (int i = 1; i < strs.length; i++) {
                if (!strs[i].startsWith(base)) {
                    isMatch = false;
                    break;
                }
            }
            if (isMatch) {
                return base;
            }else {
                sb.deleteCharAt(sb.length() - 1);
            }
        }

        return sb.toString();
    }

    /**
     * 前缀树
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix2(String[] strs) {
        StringBuilder sb = new StringBuilder(strs[0]);
        StringBuilder ans = new StringBuilder();
        TrieTree trieTree = new TrieTree();

        for (int i = 0; i < strs.length; i++) {
            trieTree.insert(strs[i]);
            if (strs[i].length() < sb.length()) {
                sb = new StringBuilder(strs[i]);
            }
        }
        for (int i = 1; i <= sb.length(); i++) {
            if (trieTree.search(sb.substring(0, i))== strs.length) {
                ans = new StringBuilder(sb.substring(0, i));
            }
        }
        return ans.toString();
    }


    class TrieTree {
        TrieNode root;
        TrieTree() {
            root = new TrieNode();
        }

        public void insert(String word) {
            char[] str = word.toCharArray();
            TrieNode cur = root;
            // 从跟节点开始插入
            for (int i = 0; i < str.length; i++) {
                cur.pass++;
                if (cur.next[str[i]-'a'] == null) {
                    TrieNode node = new TrieNode();
                    node.currChar = str[i];
                    cur.next[str[i]-'a'] = node;
                }
                cur = cur.next[str[i]-'a'];
            }
            cur.pass++;
            cur.end++;
        }

        public int search(String word) {
            // 是否包含
            TrieNode cur = root;
            char[] str = word.toCharArray();
            for (int i = 0; i < str.length; i++) {
                if (cur.next[str[i] - 'a'] == null) {
                    return 0;
                }
                cur = cur.next[str[i]-'a'];
            }
            return cur.pass;
        }
    }

    class TrieNode{
        TrieNode[] next;
        char currChar;
        int pass;
        int end;

        TrieNode() {
            this.next = new TrieNode[26];
            this.currChar = 0;
            this.pass = 0;
            this.end = 0;
        }
    }

    static void main() {
        Code014 code014 = new Code014();
        String[] strs = new String[]{"flower","flow","flight"};
        String res = code014.longestCommonPrefix2(strs);
        System.out.println(res);
    }

}
