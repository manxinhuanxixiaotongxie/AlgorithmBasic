package leetcode.classic150;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 字典 wordList 中从单词 beginWord 到 endWord 的 转换序列 是一个按下述规格形成的序列 beginWord -> s1 -> s2 -> ... -> sk：
 * <p>
 * 每一对相邻的单词只差一个字母。
 * 对于 1 <= i <= k 时，每个 si 都在 wordList 中。注意， beginWord 不需要在 wordList 中。
 * sk == endWord
 * 给你两个单词 beginWord 和 endWord 和一个字典 wordList ，返回 从 beginWord 到 endWord 的 最短转换序列 中的 单词数目 。
 * 如果不存在这样的转换序列，返回 0 。
 *
 */
public class Code127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 图的宽度优先遍历 但是有个难点  要是枚举所有的下一个单词 需要对每个单词进行一次遍历 以及每个单词的每个字符进行一次遍历 复杂度过高
        // 本题其实不用完整构建一张图
        // 从beginWord触出发 一直到endword结束
        if (beginWord.equals(endWord)) {
            return 1;
        }
        if (!wordList.contains(endWord)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.add(beginWord);
        visited.add(beginWord);
        for (int count = 0;!queue.isEmpty();count++) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                for (String next : wordList) {
                    if (visited.contains(next)) {
                        continue;
                    }
                    if (canTransfer(word, next)) {
                        if (next.equals(endWord)) {
                            return count + 2;
                        }
                        queue.add(next);
                        visited.add(next);
                    }
                }
            }
        }
        return 0;
    }

    public boolean canTransfer(String s1,String s2) {
        int diff = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                diff++;
            }
        }
        // 差异只能有一个 说明可以转换
        return diff == 1;
    }
}
