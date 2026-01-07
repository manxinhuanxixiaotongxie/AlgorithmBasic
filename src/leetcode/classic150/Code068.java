package leetcode.classic150;

import java.util.ArrayList;
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
    public List<String> fullJustify(String[] words, int maxWidth) {

        // 每行的的长度要小于等于 maxWidth
        List<String> ans = new ArrayList<>();

        // 滑动窗口
        int n = words.length;
        int index = 0;
        int right = 0;
        while (index < n) {
            // 下一个位置从right+1开始
            // 开始扩大窗口
            int curLen = 0;
            while (curLen <= maxWidth) {
                // 暴力扩大窗口
                curLen += words[index].length();
                right++;
            }
            // 结算index 到right -1 这一行
            if (right == index + 1) {
                // 说明只有这一个数
                ans.add(words[index++]);
            } else {
                StringBuilder sb = new StringBuilder();
                for (; index < Math.min(n,right); index++) {
                    sb.append(words[index]);
                }
                ans.add(sb.toString());
            }
        }

        return ans;
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
