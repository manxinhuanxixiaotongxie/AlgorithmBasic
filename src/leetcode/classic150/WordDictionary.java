package leetcode.classic150;

/**
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 * <p>
 * 实现词典类 WordDictionary ：
 * <p>
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 *
 */
public class WordDictionary {

    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        if (word == null) {
            return;
        }
        TrieNode node = root;
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
            node.pass++;
        }
        node.end++;
    }

    public boolean search(String word) {
        if (word == null) {
            return false;
        }

        return process(root, word.toCharArray(), 0);

    }

    private boolean process(TrieNode root, char[] word, int index) {
        if (root == null) {
            return false;
        }
        if (index == word.length) {
//            return true;
//            process 方法在 index == word.length 时直接返回 true，但应判断当前节点是否为单词结尾（即 end > 0），否则会把前缀也当作完整单词匹配。
//            pass 字段无实际作用
            return root.end > 0;
        }
        boolean ans = false;
//        if (word[index] == '.') {
//            for (int i = 0; i < 26;i++) {
//                // word当前位置是. 那么的在所有路径中进行匹配
//                if (root.nexts[i] != null) {
//                    ans |= process(root.nexts[i], word, index + 1);
//                }
//            }
//        }else {
//            if (root.nexts[word[index] - 'a'] != null) {
//                ans |= process(root.nexts[word[index] - 'a'], word, index + 1);
//            }
//        }
        if (word[index] == '.') {
            for (int i = 0; i < 26; i++) {
                if (root.nexts[i] != null && process(root.nexts[i], word, index + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            return root.nexts[word[index] - 'a'] != null &&
                    process(root.nexts[word[index] - 'a'], word, index + 1);
        }
//        return ans;
    }

    class TrieNode {
        int pass;
        int end;
        TrieNode[] nexts;

        public TrieNode() {
            pass = 0;
            end = 0;
            nexts = new TrieNode[26];
        }
    }
}
