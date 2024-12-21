package leetcode.dp;

/**
 * 为了 打出 一个字母，Alice 需要 按 对应字母 i 次，i 是该字母在这个按键上所处的位置。
 * <p>
 * 比方说，为了按出字母 's' ，Alice 需要按 '7' 四次。类似的， Alice 需要按 '5' 两次得到字母  'k' 。
 * 注意，数字 '0' 和 '1' 不映射到任何字母，所以 Alice 不 使用它们。
 * 但是，由于传输的错误，Bob 没有收到 Alice 打字的字母信息，反而收到了 按键的字符串信息 。
 * <p>
 * 比方说，Alice 发出的信息为 "bob" ，Bob 将收到字符串 "2266622" 。
 * 给你一个字符串 pressedKeys ，表示 Bob 收到的字符串，请你返回 Alice 总共可能发出多少种文字信息 。
 * <p>
 * 由于答案可能很大，将它对 109 + 7 取余 后返回
 */
public class Code2266 {
    public int countTexts(String pressedKeys) {
        char[] chars = pressedKeys.toCharArray();
        int n = chars.length;
        return process(chars, n, 0);

    }

    private static final int MOD = 1_000_000_007;
    private static final int MX = 100_001;
    private static final long[] f = new long[MX];
    private static final long[] g = new long[MX];

    static {
        f[0] = g[0] = 1;
        f[1] = g[1] = 1;
        f[2] = g[2] = 2;
        f[3] = g[3] = 4;
        for (int i = 4; i < MX; i++) {
            f[i] = (f[i - 1] + f[i - 2] + f[i - 3]) % MOD;
            g[i] = (g[i - 1] + g[i - 2] + g[i - 3] + g[i - 4]) % MOD;
        }
    }

    public int countTextsRight(String s) {
        long ans = 1;
        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            cnt++;
            if (i == s.length() - 1 || c != s.charAt(i + 1)) { // 找到一个完整的组
                ans = ans * (c != '7' && c != '9' ? f[cnt] : g[cnt]) % MOD;
                cnt = 0;
            }
        }
        return (int) ans;
    }


    /**
     * 以下这个递归会算多
     *
     *
     * @param chars
     * @param n
     * @param index
     * @return
     */
    private int process(char[] chars, int n, int index) {
        if (index == n) {
            return 1;
        }

        if (index > n) {
            return 0;
        }
        // 从当前位置开始选
        // 找到与当前位置不相等的一个数
        int right = index + 1;
        while (right < n && chars[right - 1] == chars[right]) {
            right++;
        }
        // right是到不了的位置
        // 从当前位置选一个  选两个  选right-index个
        int ans = 0;
        int count;
        if (chars[index] == '7' || chars[index] == '9') {
            // 如果right是7 index是3   3 4 5 6 7
            count = (right - index) ;
            if (count > 4) {
                return 0;
            }
        } else {
            count = (right - index) ;
            if (count > 3) {
                return 0;
            }
        }
        for (int i = 0; i < count; i++) {
            System.out.printf("%c", chars[index], index + i + 1);
            ans = ans % 1000000007 + process(chars, n, index + i + 1) % 1000000007;
        }
        return ans % 1000000007;
    }

    public static void main(String[] args) {
        Code2266 code2266 = new Code2266();
        System.out.println(code2266.countTexts("22"));
        System.out.println(code2266.countTextsRight("22"));
    }
}
