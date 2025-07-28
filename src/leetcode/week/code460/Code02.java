package leetcode.week.code460;

import java.util.PriorityQueue;

/**
 * 给你一个由大写英文字母组成的字符串 s。
 * <p>
 * 你可以在字符串的 任意 位置（包括字符串的开头或结尾）最多插入一个 大写英文字母。
 * <p>
 * 返回在 最多插入一个字母 后，字符串中可以形成的 "LCT" 子序列的 最大 数量。
 * <p>
 * 子序列 是从另一个字符串中删除某些字符（可以不删除）且不改变剩余字符顺序后得到的一个 非空 字符串。©leetcode
 */
public class Code02 {
    /**
     * 此贪心有问题 待确认
     *
     *
     * @param s
     * @return
     */
    public long numOfSubsequences(String s) {
        if (s == null || s.length() < 1) return 0;
        char[] str = s.toCharArray();

        char[] help = new char[26];
        for (int i = 0; i < str.length; i++) {
            help[str[i] - 'A']++;
        }
        int count = 0;
        if (help['L' - 'A'] > 0) {
            count++;
        }
        if (help['C' - 'A'] > 0) {
            count++;
        }
        if (help['T' - 'A'] > 0) {
            count++;
        }
        if (count < 2) {
            return 0;
        }
        int countL = help['L' - 'A'];
        int countC = help['C' - 'A'];
        int countT = help['T' - 'A'];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(countL);
        pq.offer(countC);
        pq.offer(countT);
        pq.offer(pq.poll() + 1);
        long ans = 1;
        while (!pq.isEmpty()) {
            ans *= pq.poll();
        }
        return ans;
    }

    public static void main(String[] args) {
        Code02 code02 = new Code02();
        System.out.println(code02.numOfSubsequences("LCTKLCLT"));
    }
}
