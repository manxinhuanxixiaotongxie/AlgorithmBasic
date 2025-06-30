package leetcode.day;

/**
 * 给你一个二进制字符串 s 和一个正整数 k 。
 * <p>
 * 请你返回 s 的 最长 子序列的长度，且该子序列对应的 二进制 数字小于等于 k 。
 * <p>
 * 注意：
 * <p>
 * 子序列可以有 前导 0 。
 * 空字符串视为 0 。
 * 子序列 是指从一个字符串中删除零个或者多个字符后，不改变顺序得到的剩余字符序列。
 */
public class Code2311 {
    /**
     * 暴力解法
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubsequence(String s, int k) {
        if (s == null || s.length() == 0 || k < 0) {
            return 0;
        }
        // 空字符串
        String[] split = s.split("\\s+");
        if (split.length == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return process(str, str.length - 1, k, 1);
    }

    /**
     * 从0-index范围找到满足条件的最长子序列的长度
     *
     * @param str
     * @param index
     * @param rest
     * @return
     */
    public int process(char[] str, int index, int rest, int pre) {
        if (index == 0) {
            return str[index] == '0' ? 1 : rest - pre >= 0 ? 1 : 0;
        }
        int ans = 0;
        // 当前位置不要
        ans = process(str, index - 1, rest, pre << 1);
        // 当前位置要
        if (str[index] == '0') {
            ans = Math.max(ans, process(str, index - 1, rest, pre << 1)) + 1;
        } else {
            if (rest >= 0 && rest >= pre) {
                // 判断当前位置数字
                ans = Math.max(ans, process(str, index - 1, rest - pre, pre << 1)) + 1;
            }
        }
        return ans;
    }

    /**
     * 贪心
     *
     * @param s
     * @param k
     * @return
     */
    public int longestSubsequence2(String s, int k) {
        int n = s.length();
        int m = 32 - Integer.numberOfLeadingZeros(k); // k 的二进制长度
        if (n < m) {
            return n; // 全选
        }

        int sufVal = Integer.parseInt(s.substring(n - m), 2);
        int ans = sufVal <= k ? m : m - 1; // 后缀长度

        for (int i = 0; i < n - m; i++) {
            ans += '1' - s.charAt(i); // 添加前导零
        }
        return ans;
    }


    public String generateStr(int maxLength) {
        int length = (int) (Math.random() * maxLength) + 1; // 随机长度
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            double probability = Math.random();
            if (probability < 0.5) {
                sb.append('1');
            }else {
                sb.append('0');
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        Code2311 code2311 = new Code2311();
        int testTimes = 100000;
        int maxLength = 1000;
        System.out.println("test start!");
        for (int i = 0; i < testTimes; i++) {
            // 随机一个K
            int k = (int) (Math.random() * Math.pow(10, 9));
            String s = code2311.generateStr(maxLength);
            int ans1 = code2311.longestSubsequence(s, k);
            int ans2 = code2311.longestSubsequence2(s, k);
            if (ans1 != ans2) {
                System.out.println("Error!");
                System.out.println("s = " + s);
                System.out.println("k = " + k);
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                break;
            } else {
                System.out.println("Test " + i + " passed.");
            }
        }
        System.out.println("test end!");
    }
}
