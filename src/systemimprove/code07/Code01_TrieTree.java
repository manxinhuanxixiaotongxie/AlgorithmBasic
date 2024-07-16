package systemimprove.code07;

import java.util.HashMap;
import java.util.Map;

/**
 * 前缀树
 */
public class Code01_TrieTree {

    // 前缀树

    private Node root;

    class Node {
        private int pass;
        private int end;
        private Map<Integer,Node> next;

        Node(int pass,int end) {
            this.pass = pass;
            this.end = end;
            this.next = new HashMap<>();
        }
    }

    public Code01_TrieTree() {
        this.root = new Node(0,0);
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() ==0) {
            return;
        }

        char[] charArray = word.toCharArray();
        Node cur = root;
        for (int i = 0; i < charArray.length;i++) {
            root.pass++;
            if (cur.next.get(charArray[i] - 'A') == null) {
                cur.next.put(charArray[i] - 'A',new Node(1,0));
            } else {
                cur.next.get(charArray[i] -'A').pass++;
            }
            cur = cur.next.get(charArray[i] - 'A');

        }
        cur.end++;

    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null || word.length() ==0) {
            return false;
        }
        char[] charArray = word.toCharArray();
        Node cur = root;
        for (int i = 0; i < charArray.length;i++) {
            if (cur.next.get(charArray[i]-'A') == null) {
                return false;
            }
            cur = cur.next.get(charArray[i] - 'A');
        }
        return cur.end > 0;

    }

    public void delete(String word) {
        if (search(word)) {
            char[] charArray = word.toCharArray();
            Node cur = root;
            for (int i = 0; i < charArray.length; i++) {
                // 沿途将pass--
                root.pass--;
                if (cur.next.get(charArray[i] - 'A').pass-- == 1) {
                    cur.next.remove(charArray[i] - 'A');
                    return;
                }
                cur = cur.next.get(charArray[i] - 'A');
            }
            cur.end--;

        }
    }


    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        char[] charArray = prefix.toCharArray();
        Node cur = root;
        for (int i = 0; i < charArray.length;i++) {
            if (cur.next.get(charArray[i] - 'A') == null) {
                return false;
            }
            cur = cur.next.get(charArray[i] -'A');
        }
        return true;

    }
}
