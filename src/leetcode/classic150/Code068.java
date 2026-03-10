package leetcode.classic150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 给定一个单词数组 words 和一个长度 maxWidth ，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 *
 * 你应该使用 “贪心算法” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 *
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 *
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 *
 * 注意:
 *
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 *
 */
public class Code068 {

    /**
     * 分组循环：适用场景 按照题目要求 数组会被分割成若干组 每一组的判断 处理逻辑的是相同的
     * 核心思想：
     * （1）外层循环负遍历组之前的准备工作（记录开始位置） 和遍历组之后的统计工作（更新答案）
     * （2）内层循环负责遍历数组 找出这一组最远在哪里结束
     *
     * 内层循环开始前：记录当前位置start=i 这也是这一行第一个单词的下标 初始化这一行的最小长度sumLen为word[i]的长度
     *
     * 内层循环：从这一行的第二个单词i+1开始循环  从何第二个单词开始 每个单词之前必须有一个空格 所以每个单词占用的长度是单词长度+1 加给sumLen
     * 如果sumLen+word[i]的长度+1（空格）<=maxWidth 说明这个单词可以放在这一行 继续循环 否则结束循环
     *
     * 内层循环结束后：
     * 这一行还剩下extraSpaces=maxWidth-sumLen个空格没有分配 这一行单词之间的空隙个数gaps=i-start-1（单词个数减一）
     *
     * @param words
     * @param maxWidth
     * @return
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int n = words.length;
        for (int i = 0; i < n; ) {
            int start = i; // 这一行第一个单词的下标
            int sumLen = words[i].length(); // 第一个单词的长度
            for (i++; i < n && sumLen + words[i].length() + 1 <= maxWidth; i++) {
                sumLen += words[i].length() + 1; // 单词之间至少要有一个空格
            }

            int extraSpaces = maxWidth - sumLen; // 这一行剩余未分配的空格个数
            int gaps = i - start - 1; // 这一行单词之间的空隙个数（单词个数减一）

            // 特殊情况：如果只有一个单词，或者是最后一行，那么左对齐，末尾补空格
            if (gaps == 0 || i == n) {
                // 末尾补空格
                String row = join(words, start, i, " ") + " ".repeat(extraSpaces);
                ans.add(row);
                continue;
            }

            // 一般情况：把 extraSpaces 个空格均匀分配到 gaps 个空隙中（靠左的空格更多）
            int avg = extraSpaces / gaps;
            int rem = extraSpaces % gaps;
            // +1 表示加上单词之间已有的一个空格
            String spaces = " ".repeat(avg + 1);
            // 前 rem 个空隙多一个空格
            String row = join(words, start, start + rem + 1, spaces + ' ') +
                    spaces + join(words, start + rem + 1, i, spaces);
            ans.add(row);
        }
        return ans;
    }

    // 用 StringBuilder 的写法见【Java 写法二】
    private String join(String[] words, int start, int end, String sep) {
        return String.join(sep, Arrays.copyOfRange(words, start, end));
    }


    static void main() {
        Code068 code068 = new Code068();
        String[] words = new String[] {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;
        List<String> res = code068.fullJustify(words, maxWidth);
        for (String line : res) {
            System.out.println("[" + line + "]");
        }
    }
}
