package leetcode.day;

import java.util.Arrays;

/**
 * 给你一个字符串 s ，它由某个字符串 t 和若干 t  的 同位字符串 连接而成。
 * <p>
 * 请你返回字符串 t 的 最小 可能长度。
 * <p>
 * 同位字符串 指的是重新排列一个单词得到的另外一个字符串，原来字符串中的每个字符在新字符串中都恰好只使用一次。
 */
public class Code31338 {
    // acb bca
    public int minAnagramLength(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        next:
        for (int k = 1; k <= n / 2; k++) {
            if (n % k > 0) {
                continue;
            }
            int[] help = new int[26];
            for (int j = 0; j < k; j++) {
                help[s[j] - 'a']++;
            }
            // i从k 或者k*2开始都是对的
            // k*2会少算一个刚开始已经算过的help的对比 是个小优化
            for (int i = k * 2; i <= n; i += k) {
                int[] temp = new int[26];
                for (int j = i - k; j < i; j++) {
                    temp[s[j] - 'a']++;
                }
                if (!Arrays.equals(temp, help)) {
                    continue next;
                }
            }
            return k;
        }
        return n;
    }

    /**
     * 思路说明：
     * 暴力解法
     * 最小值的长度依次遍历 看看能否找到一个长度是满足条件的
     * <p>
     * <p>
     * 如果一直试到K/2的长度 说明长度一定是n
     *
     * @param S
     * @return
     */
    public int minAnagramLength2(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        // 标记循环开始的位置
        next:
        for (int k = 1; k <= n / 2; k++) {
            // 从[1-k/2]进行长度的尝试
            // 同位字符串的长度与t的长度是一样的
            // 如果s的长度为k 那么数组的长度一定是k的整数倍
            if (n % k != 0) {
                continue;
            }
            // 每次组组装长度为k的数组
            int[] help = new int[26];
            for (int i = 0; i < k; i++) {
                help[s[i] - 'a']++;
            }
            // 从k位置开始一路想下加
            for (int i = k; i < n; i += k) {
                int[] temp = new int[26];
                for (int j = i; j < i + k; j++) {
                    temp[s[j] - 'a']++;
                }
                if (!Arrays.equals(help, temp)) {
                    continue next;
                }
            }

            return k;
        }
        // 以上任何一个长度都不满足 长度就是N
        return n;
    }


    public static void main(String[] args) {
        Code31338 code31338 = new Code31338();
        System.out.println(code31338.minAnagramLength("pqqppqpqpq"));
    }
}
