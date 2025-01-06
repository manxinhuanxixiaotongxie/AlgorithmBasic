package systemimprove.dp;

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
 * <p>
 * <p>
 * <p>
 * <p>
 * 2 abc
 * 3 def
 * 4 ghi
 * 5 jkl
 * 6 mno
 * 7 pqrs
 * 8 tuv
 * 9 wxyz
 * <p>
 * <p>
 * <p>
 * 尝试：
 * 给定一个字符串 返回总共能够产生的输入的可能性
 */
public class Code2266 {
    public int countTexts(String pressedKeys) {
        char[] chars = pressedKeys.toCharArray();
        return process2(chars, 0);
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
     * 暴力递归怎么尝试
     * 从左到右进行尝试
     *
     * @param chars
     * @return
     */
    public int process2(char[] chars, int index) {
        if (index == chars.length) {
            return 1;
        }
        int cur = index;
        while (cur < chars.length - 1 && chars[cur] == chars[cur + 1]) {
            cur++;
        }
        // 从index -cur都是相同的字符
        int ans = 0;
        // 尝试每一种方式
        for (int i = index; i <= cur; i++) {
            // 当前数作为一个
            // 当前2个作为一个
            // 当前三个作为一个
            ans = ans % MOD + process2(chars, i + 1) % MOD;
        }
        return ans;
    }

    public static void main(String[] args) {
        Code2266 code2266 = new Code2266();
        System.out.println(code2266.countTexts("222222222222222222222222222222222222"));
        System.out.println(code2266.countTextsRight("222222222222222222222222222222222222"));
    }
}
