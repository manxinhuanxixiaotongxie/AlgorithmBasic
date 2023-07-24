package code13;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-07-24 16:37
 */
public class Code04_ConvertToLetterString {

    public int numDecodings(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return dp(str.toCharArray(), 0);
    }


    public int process(char[] chars, int i) {
        if (i == chars.length) {
            return 1;
        }

        if (chars[i] == '0') {
            return 0;
        }
        int process = process(chars, i + 1);
        if (i + 1 < chars.length && (chars[i] - '0') * 10 + (chars[i + 1] - '0') < 27) {
            process += process(chars, i + 2);
        }
        return process;
    }

    public int dp(char[] chars, int i) {
        int N = chars.length + 1;
        int[] dp = new int[N];

        dp[chars.length] = 1;


        for (int index = chars.length - 1; index >= 0; index--) {
            if (chars[index] == '0') {
                dp[index] = 0;
            } else {
                dp[index] = dp[index + 1];
                if (index + 1 < chars.length && (chars[index] - '0') * 10 + (chars[index + 1] - '0') < 27) {
                    dp[index] += dp[index + 2];
                }
            }
        }
        return dp[0];
    }

}
