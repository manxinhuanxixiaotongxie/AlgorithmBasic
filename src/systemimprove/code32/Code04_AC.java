package systemimprove.code32;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * AC自动机
 *
 * 解决在一个大的字符串（文章）中 找到多个候选字符串的问题
 *
 */
public class Code04_AC {

    /**
     * ac自动机步骤：
     * 1.将敏感词数组构建成一颗前缀树
     * 2，设置fail指针
     * 2.1：头节点的fail节点指向null
     * 2.2头节点的第一级子节点的fail指针指向头节点
     * 2.3：使用宽度有限遍历 遍历整个前缀树
     * 2.4：沿着父亲节点fail指针向上进行查找 直到找到第一个fail节点的子节点中有当前字符的节点
     * 2.5：如果没有找到，fail指针指向头节点
     * 3.遍历大字符串，找到敏感词
     *
     * @param content
     * @param words
     * @return
     */
    public List<String> ac(String content, List<String> words) {
        if (content == null || words == null || words.isEmpty()) {
            return null;
        }
        TrieTree trieTree = new TrieTree();
        // 1.将敏感词构造成一颗前缀树
        // 每个节点设置fail节点 头结点设置为null
        // 第一级子节点设置成头结点
        for (int i = 0; i < words.size(); i++) {
            // 建成前缀树
            trieTree.insert(words.get(i));
        }
        // 设置fail节点
        Queue<TrieNode> queue = new LinkedList<>();
        queue.add(trieTree.root);
        TrieNode cfail;
        while (!queue.isEmpty()) {
            TrieNode cur = queue.poll();
            // 在遍历当前节点的过程中
            // 把当前节点作为父亲节点进行查找 设置子节点的fail节点
            cfail = cur.fail;
            // 遍历当前节点的所有子节点
            for (int i = 0; i < 26; i++) {
                // 子节点存在
                if (cur.nexts[i] != null) {
                    // 先设置成头结点
                    // 如果后续找到了合适的fail节点，会覆盖掉这个值
                    cur.nexts[i].fail = trieTree.root;
                    // 沿着fail节点一路向上查找
                    while (cfail != null) {
                        // 找到了应该设置的fail节点
                        // 设置子节点的fail节点
                        if (cfail.nexts[i] != null) {
                            cur.nexts[i].fail = cfail.nexts[i];
                            break;
                        }
                        // 一路向上查找
                        cfail = cfail.fail;
                    }
                    // 宽度有限遍历
                    queue.add(cur.nexts[i]);
                }
            }
        }

        // 前缀树构建好之后 开始查找匹配的敏感词
        /**
         * 匹配敏感词的过程：
         * 1。遍历大文章的字符
         * 2.沿着前缀树向下查找 并且根据当前节点遍历一遍所有的fail节点 找到匹配的敏感词
         * 3.敏感词的前缀树找不到大文章的节点，cur来到沿着fail节点向上查找
         */
        List<String> ans = new ArrayList<>();
        // follow节点的含义是cur沿着fail节点查找匹配敏感词的过程的节点
        TrieNode follow;
        TrieNode cur = trieTree.root;
        int index = 0;
        for (int i = 0; i < content.length(); i++) {
            index = content.charAt(i) - 'a';
            // 大文章的字符在前缀树中没有找到
            // 沿着fail节点一路查找
            // 如果找到头节点了依然没找到
            // 那么就回到头节点
            while (cur.nexts[index] == null && cur != trieTree.root) {
                cur = cur.fail;
            }

            cur = cur.nexts[index] != null ? cur.nexts[index] : trieTree.root;
            follow = cur;
            // 沿着fail节点向上查找

            while (follow != trieTree.root) {
                if (follow.endUse) {
                    break;
                }

                if (follow.end != null) {
                    ans.add(follow.end);
                    follow.endUse = true;
                }

                follow = follow.fail;
            }
        }
        return ans;

    }

    public static void main(String[] args) {
        Code04_AC ac = new Code04_AC();
        List<String> words = new ArrayList<>();
        words.add("dhe");
        words.add("he");
        words.add("abcdheks");
        List<String> contains = ac.ac("abcdhekskdjfafhasldkflskdjhwqaeruv", words);

        for (String word : contains) {
            System.out.println(word);
        }
    }
}

class TrieTree {
    TrieNode root;

    TrieTree() {
        root = new TrieNode();
    }

    public void insert(String s) {
        if (s == null || s.isEmpty()) {
            return;
        }
        char[] str = s.toCharArray();
        TrieNode node = root;
        for (int i = 0; i < str.length; i++) {
            if (node.nexts[str[i] - 'a'] == null) {
                node.nexts[str[i] - 'a'] = new TrieNode();
            }
            node = node.nexts[str[i] - 'a'];
            node.pass++;
        }
        node.end = s;
    }
}

class TrieNode {
    // 节点的路径
    TrieNode[] nexts;
    // fail节点
    TrieNode fail;
    // 经过这个节点的次数
    int pass;
    // 敏感词的结束，存放敏感词字符串
    String end;
    // 敏感词是否已经被使用过标记
    boolean endUse;

    TrieNode() {
        nexts = new TrieNode[26];
        pass = 0;
        endUse = false;
    }
}
