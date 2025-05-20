package systemimprove.code32;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * AC自动机
 * 解决在一个大的字符串中 找到多个候选字符串的问题
 */
public class Code04_AC {


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
        while (!queue.isEmpty()) {
            TrieNode cur = queue.poll();
            TrieNode cfail = cur.fail;
            for (int i = 0; i < 26; i++) {
                if (cur.nexts[i] != null) {
                    cur.nexts[i].fail = trieTree.root;
                    while (cfail != null) {
                        if (cfail.nexts[i] != null) {
                            cur.nexts[i].fail = cfail.nexts[i];
                            break;
                        }
                        cfail = cfail.fail;
                    }
                    queue.add(cur.nexts[i]);
                }
            }
        }
        List<String> ans = new ArrayList<>();
        TrieNode follow = null;
        TrieNode cur = trieTree.root;
        int index = 0;
        for (int i = 0; i < content.length(); i++) {
            index = content.charAt(i) - 'a';
            while (cur.nexts[index] == null && cur != trieTree.root) {
                cur = cur.fail;
            }
            cur = cur.nexts[index] != null ? cur.nexts[index] : trieTree.root;
            follow = cur;
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
    TrieNode fail;
    int pass;
    String end;
    boolean endUse;

    TrieNode() {
        nexts = new TrieNode[26];
        pass = 0;
        endUse = false;
    }
}
