package leetcode.classic150;

public class Trie {

    TrieNode root;


    public Trie() {
        root = new TrieNode('\0');
    }

    public void insert(String word) {
        TrieNode cur = root;
        char[] wordStr = word.toCharArray();
        for (int i = 0; i < wordStr.length; i++) {
            if (cur.next[wordStr[i] - 'a'] == null) {
                cur.next[wordStr[i] - 'a'] = new TrieNode(wordStr[i]);
            }
            cur.pass++;
            cur = cur.next[wordStr[i] - 'a'];
        }
        cur.pass++;
        cur.end++;
    }

    public boolean search(String word) {
        TrieNode cur = root;
        char[] wordStr = word.toCharArray();
        for (int i = 0; i < wordStr.length; i++) {
            if (cur.next[wordStr[i] - 'a'] == null) {
                return false;
            }
            cur = cur.next[wordStr[i] - 'a'];
        }
        return cur.end > 0;
    }

    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        char[] wordStr = prefix.toCharArray();
        for (int i = 0; i < wordStr.length; i++) {
            if (cur.next[wordStr[i] - 'a'] == null) {
                return false;
            }
            cur = cur.next[wordStr[i] - 'a'];
        }
        return true;
    }

    class TrieNode {
        char data;
        TrieNode[] next;
        int pass;
        int end;
        TrieNode(char data) {
            this.data = data;
            this.next = new TrieNode[26];
            this.pass = 0;
            this.end = 0;
        }
    }
}
