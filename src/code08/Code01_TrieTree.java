package code08;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-07-12 11:03
 */

import class03.Code03_StackAndQueueImpl;

/**
 * 前缀树
 * <p>
 * 给定一个字符串数组
 * <p>
 * 1.遍历每一个字符串
 * 2.遍历每一个字符，如果在前缀树上有值的话复用，没有值的话新建
 */
public class Code01_TrieTree {

    /**
     * 前缀树节点类型
     */
    public static class Node1 {
        private int pass;
        private int end;
        private Node1[] nexts;

        Node1() {
            this.nexts = new Node1[26];
        }
    }

    public static class Trie1 {
        private Node1 root;

        public Trie1() {
            root = new Node1();
        }

        public void insert(String insert) {
            char[] chars = insert.toCharArray();
            Node1 cur = root;
            cur.pass++;
            for (int i = 0; i < chars.length; i++) {
                int way = chars[i] - 'a';
                if (cur.nexts[way] == null) {
                    cur.nexts[way] = new Node1();
                }
                cur = cur.nexts[way];
                cur.pass++;
            }
            cur.end++;
//            cur = root;
        }

        // word这个单词之前有没有加入过(子串)
        public boolean searchExist(String word) {
            char[] chars = word.toCharArray();
            Node1 cur = root;
            for (int i = 0; i < chars.length; i++) {
                int way = chars[i] - 'a';
                if (cur.nexts[way] == null) {
                    return false;
                }
                cur = cur.nexts[way];
            }
//            return true;
            return cur.end >0;
        }

        // word这个单词加入过几次
        public int search(String word) {
            char[] chars = word.toCharArray();
            Node1 cur = root;
            for (int i = 0; i < chars.length; i++) {
                int way = chars[i] - 'a';
                if (cur.nexts[way] == null) {
                    return 0;
                }
                cur = cur.nexts[way];
            }
            return cur.end;
        }

        // 删除Word
        public boolean delete(String word) {
            if (search(word) > 0) {
                Node1 cur = root;
                cur.pass--;
                char[] chars = word.toCharArray();
                for (int i = 0; i < word.length(); i++) {
                    int index = chars[i] - 'a';
//                    if (cur.nexts[index] != null) {
//                        cur.pass--;
//                    }
//                    if (cur.nexts[index].pass == 0) {
//                        cur.nexts[index] = null;
//                        return true;
//                    }

                    if (--cur.nexts[index].pass == 0) {
                        cur.nexts[index] = null;
                        return true;
                    }
                    cur = cur.nexts[index];
                }
                cur.end--;
                return true;
            }
            return false;
        }
    }
}
