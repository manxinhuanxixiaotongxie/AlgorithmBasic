package foundation;

import code08.Code01_TrieTree;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-06-11 16:46
 */
public class Trie {

    /**
     * 前缀树节点类型
     */
    public static class Node1 {
        private int pass;
        private int end;
        private Node1[] nexts;
        private Map<Integer, Node1> map;

        Node1() {
//            this.nexts = new Node1[26];
            this.map = new HashMap<>();
        }
    }

    public static class Trie1 {
        private Node1 root;

        public Trie1() {
            root = new Node1();
        }

        public void insert(String word) {

            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            Node1 cur = root;
            cur.pass++;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (cur.map.get(index) == null) {
                    Node1 node1 = new Node1();

                    cur.map.put(index, node1);
                    cur = node1;
                } else {
                    cur = cur.map.get(index);
                }
                cur.pass++;
            }
            cur.end++;

        }

        // word这个单词之前有没有加入过
        public boolean searchExist(String word) {
            char[] chars = word.toCharArray();
            Node1 cur = root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (cur.map.get(index) == null) {
                    return false;
                }
                cur = cur.map.get(index);
            }
            return cur.end > 0;
        }

        // word这个单词加入过几次
        public boolean search(String word) {
            char[] chars = word.toCharArray();
            Node1 cur = root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (cur.map.get(index) == null) {
                    return false;
                }
                cur = cur.map.get(index);
            }
            return cur.end > 0;
        }

        public boolean startsWith(String prefix) {
            char[] chars = prefix.toCharArray();
            Node1 cur = root;
            for (int i = 0; i < chars.length; i++) {
                int index = chars[i] - 'a';
                if (cur.map.get(index) == null) {
                    return false;
                }
                cur = cur.map.get(index);
            }
            return cur.pass > 0;
        }

        // 删除Word
        public boolean delete(String word) {

            if (searchExist(word)) {
                char[] chars = word.toCharArray();
                Node1 cur = root;
                for (int i = 0; i < chars.length; i++) {
                    int index = chars[i] - 'a';
                    if (--cur.pass == 0) {
                        cur.nexts[index] = null;
                        break;
                    }
                    cur = cur.nexts[index];
                }
                cur.end--;
            }
            return false;
        }

    }

}
