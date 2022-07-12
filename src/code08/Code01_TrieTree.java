package code08;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-07-12 11:03
 */

import class03.Code03_StackAndQueueImpl;

/**
 * 前缀树
 *
 * 给定一个字符串数组
 *
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
            for (int i = 0;i<chars.length;i++) {
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

//        public
    }
}
