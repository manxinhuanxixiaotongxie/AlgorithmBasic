package systemreview.code07;

/**
 * leetcode :
 * <p>
 * Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。
 * <p>
 * 请你实现 Trie 类：
 * <p>
 * Trie() 初始化前缀树对象。
 * void insert(String word) 向前缀树中插入字符串 word 。
 * boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
 * boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。
 * 示例：
 * <p>
 * 输入
 * inputs = ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * inputs = [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * 输出
 * [null, null, true, false, true, null, true]
 * <p>
 * 解释
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 True
 * trie.search("app");     // 返回 False
 * trie.startsWith("app"); // 返回 True
 * trie.insert("app");
 * trie.search("app");     // 返回 True
 * 提示：
 * <p>
 * 1 <= word.length, prefix.length <= 2000
 * word 和 prefix 仅由小写英文字母组成
 * insert、search 和 startsWith 调用次数 总计 不超过 3 * 104 次
 * <p>
 * 测试通过
 */
public class Code01_TrieTree {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("abc");
        trie.insert("abcd");
        trie.insert("abd");
        trie.insert("bcd");
        System.out.println(trie.search("abc"));
        System.out.println(trie.search("abcd"));
        System.out.println(trie.search("abd"));
        System.out.println(trie.search("bc"));
        System.out.println(trie.startsWith("a"));
        System.out.println(trie.startsWith("b"));
        System.out.println(trie.startsWith("c"));
    }
}

class Trie {

    Node root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new Node(' ');
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (cur.next[c - 'a'] == null) {
                cur.next[c - 'a'] = new Node(c);

            }
            cur.next[c - 'a'].pass++;
            cur = cur.next[c - 'a'];
        }
        cur.end++;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        Node cur = root;
        for (char c : word.toCharArray()) {
            if (cur.next[c - 'a'] == null) {
                return false;
            }
            cur = cur.next[c - 'a'];
        }
        return cur.end > 0;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return false;
        }
        Node cur = root;
        for (char c : prefix.toCharArray()) {
            if (cur.next[c - 'a'] == null) {
                return false;
            }
            cur = cur.next[c - 'a'];
        }
        return cur.pass > 0;
    }
}

class Node {
    char value;
    Node[] next;
    int pass;
    int end;

    Node(char value) {
        this.value = value;
        next = new Node[26];
        pass = 0;
        end = 0;
    }
}
