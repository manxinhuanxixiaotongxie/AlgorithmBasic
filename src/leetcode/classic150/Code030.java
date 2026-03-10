package leetcode.classic150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。
 * <p>
 * s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。
 * <p>
 * 例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。
 * "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
 *
 * 返回所有串联子串在 s 中的开始索引。你可以以 任意顺序 返回答案。
 *
 */
public class Code030 {

    /**
     * 使用前缀树  复杂度超高 因为使用了全排列
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        TrieTree trieTree = new TrieTree();
        process(0, words.length, words, trieTree);
        int len = s.length();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                String subStr = s.substring(i, j + 1);
                if (trieTree.search(subStr) > 0) {
                    ans.add(i);
                }
            }
        }
        return ans;
    }

    public void process(int index, int n, String[] words, TrieTree tree) {
        if (index == words.length) {
            StringBuilder sb = new StringBuilder();
            for (String word : words) {
                sb.append(word);
            }
            tree.insert(sb.toString());
        } else {
            // 任何位置都可以作为第一个数
            for (int i = index; i < n; i++) {
                swap(words, index, i);
                process(index + 1, n, words, tree);
                swap(words, index, i);
            }
        }
    }

    public void swap(String[] words, int i, int j) {
        String temp = words[i];
        words[i] = words[j];
        words[j] = temp;
    }

    class TrieTree {
        TrieNode root;

        TrieTree() {
            root = new TrieNode('\0');
        }

        void insert(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new TrieNode(c);
                }
                node.pass++;
                node = node.nexts[index];
            }
            node.end++;
        }

        int search(String prefix) {
            TrieNode node = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                int index = c - 'a';
                if (node.nexts[index] == null) {
                    return -1;
                }
                node = node.nexts[index];
            }
            return node.end;
        }

    }

    class TrieNode {
        char c;
        int pass = 0;
        int end = 0;
        TrieNode[] nexts = null;

        TrieNode(char c) {
            this.c = c;
            nexts = new TrieNode[26];
        }
    }

    /**
     * 时间复杂度更好的做法
     *
     * 每个单词的长度都相等 假设长度为 wordLen 那么串联子串的长度就是 wordLen * words.length
     *
     * 窗口
     *
     * 哈希表+滑动窗口
     *
     * 定长滑动窗口：
     *
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring2(String s, String[] words) {
        int wordLen = words[0].length(); // 一个单词的长度
        int windowLen = wordLen * words.length; // 所有单词的总长度，即窗口大小

        // 目标：窗口中的单词出现次数必须与 targetCnt 完全一致
        Map<String, Integer> targetCnt = new HashMap<>();
        for (String w : words) {
            targetCnt.merge(w, 1, Integer::sum); // targetCnt[w]++
        }

        List<Integer> ans = new ArrayList<>();
        // 枚举第一个窗口的左端点，做 wordLen 次起点不同的滑动窗口
        for (int start = 0; start < wordLen; start++) {
            Map<String, Integer> cnt = new HashMap<>();
            int overload = 0; // 统计过多的单词个数（包括不在 words 中的单词）
            // 枚举窗口最后一个单词的右开端点
            for (int right = start + wordLen; right <= s.length(); right += wordLen) {
                // 1. inWord 进入窗口
                String inWord = s.substring(right - wordLen, right);
                // 下面 cnt[inWord]++ 后，inWord 的出现次数过多
                if (cnt.getOrDefault(inWord, 0).equals(targetCnt.getOrDefault(inWord, 0))) {
                    overload++;
                }
                cnt.merge(inWord, 1, Integer::sum); // cnt[inWord]++

                int left = right - windowLen; // 窗口第一个单词的左端点
                if (left < 0) { // 窗口大小不足 windowLen
                    continue;
                }

                // 2. 更新答案
                // 如果没有超出 targetCnt 的单词，那么也不会有少于 targetCnt 的单词
                if (overload == 0) {
                    ans.add(left);
                }

                // 3. 窗口最左边的单词 outWord 离开窗口，为下一轮循环做准备
                String outWord = s.substring(left, left + wordLen);
                cnt.merge(outWord, -1, Integer::sum); // cnt[outWord]--
                if (cnt.get(outWord).equals(targetCnt.getOrDefault(outWord, 0))) {
                    overload--;
                }
            }
        }

        return ans;
    }

    public List<Integer> findSubstring3(String s, String[] words) {
        Map<String,Integer> targetCnt = new HashMap<>();
        // 统计words出现的次数
        for (String word:words) {
            targetCnt.merge(word, 1, Integer::sum);
        }
        // 统计出现次数之后 进行定长窗口的滑动
        int wordLen = words[0].length();
        int windowLen = wordLen * words.length;
        int sLen = s.length();
        int minus = sLen - windowLen;
        List<Integer> ans = new ArrayList<>();

        for (int start = 0;start < minus; start++) {
            Map<String, Integer> cnt = new HashMap<>();
            // 向后推送长度
            for (int curStart = start + wordLen;curStart < sLen;curStart += wordLen) {
                String inWord = s.substring(curStart - wordLen, curStart);
                cnt.merge(inWord, 1, Integer::sum);
                // 长度校验
                int left = curStart- start - windowLen;
                if (left < 0) {
                    continue;
                }
                // 校验是否满足条件
                boolean valid = true;
                for (Map.Entry<String, Integer> entry:cnt.entrySet()) {
                    for (Map.Entry<String, Integer> entry1:targetCnt.entrySet()) {
                        if (entry.getKey().equals(entry1.getKey())) {
                            if (!entry.getValue().equals(entry1.getValue())) {
                                valid = false;
                                break;
                            }
                        }
                    }
                }
                if (valid) {
                    ans.add(left);
                }
                String outWord = s.substring(left, left + wordLen);
                cnt.merge(outWord, -1, Integer::sum);
            }
        }

        return ans;
    }


    static void main() {
        Code030 code030 = new Code030();
        String s = "bcabbcaabbccacacbabccacaababcbb";
        String[] words = {"c","b","a","c","a","a","a","b","c"};
        List<Integer> res = code030.findSubstring(s, words);
        System.out.println(res);
    }
}
