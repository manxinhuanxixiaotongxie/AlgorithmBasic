package systemimprove.dp;

/**
 * 给你整数 zero ，one ，low 和 high ，我们从空字符串开始构造一个字符串，每一步执行下面操作中的一种：
 * <p>
 * 将 '0' 在字符串末尾添加 zero  次。
 * 将 '1' 在字符串末尾添加 one 次。
 * 以上操作可以执行任意次。
 * <p>
 * 如果通过以上过程得到一个 长度 在 low 和 high 之间（包含上下边界）的字符串，那么这个字符串我们称为 好 字符串。
 * <p>
 * 请你返回满足以上要求的 不同 好字符串数目。由于答案可能很大，请将结果对 10 ^ 9 + 7 取余 后返回。
 */
public class Code2466 {
    public int countGoodStrings(int low, int high, int zero, int one) {
        return process(low, high, zero, one, 0);
    }

    /**
     * 最后一位添加0或者1的字符串
     *
     * @param low
     * @param high
     * @param zero
     * @param one
     * @param length
     * @return
     */
    private int process(int low, int high, int zero, int one, int length) {

        if (length <= high && length >= low) {
            return 1 + process(low, high, zero, one, length + zero) + process(low, high, zero, one, length + one);
        }
        if (length > high) {
            return 0;
        }
        // 000  011 110
        int ans = 0;
        ans += process(low, high, zero, one, length + zero);
        ans += process(low, high, zero, one, length + one);

        return ans;
    }

    public int countGoodStrings2(int low, int high, int zero, int one) {
        int[] dp = new int[high + 1];
        for (int i = high; i >= 0; i--) {
            if (i <= high && i >= low) {
                dp[i] = 1 + ((i + zero) > high ? 0 : dp[i + zero]) + ((i + one > high) ? 0 : dp[i + one]);
            } else {
                dp[i] = ((i + zero) > high ? 0 : dp[i + zero]) + ((i + one > high) ? 0 : dp[i + one]);
            }
            dp[i] %= 1000000007;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        Code2466 code2466 = new Code2466();

        System.out.println(code2466.countGoodStrings2(2, 3, 1, 2));
    }
}
