package systemimprove.dp.fromrecursiontpdp;

/**
 * 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 * <p>
 * <p>
 * 从左往右的尝试模型
 */
public class Code06_ConvertToLetterString {

    public int number(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        return process1(str.toCharArray(), 0);

    }

    public int process1(char[] strs, int index) {
        if (index == strs.length) {
            // 找到一种转化方法
            return 1;
        }
        // 当前位置单独转
        if (strs[index] == '0') {
            // 0不可以单独转化
            return 0;
        }
        // 当前位置单独转化
        int p1 = process1(strs, index + 1);
        int p2 = 0;
        // 可以一起转化的情形
        // 两者的组合可以一起转化
        if (index + 1 < strs.length && (strs[index] - '0') * 10 + (strs[index + 1] - '0') < 27) {
            p2 = process1(strs, index + 2);
        }
        return p1 + p2;
    }

    // 改成动态规划
    public int number2(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        char[] strs = str.toCharArray();
        int[] dp = new int[strs.length + 1];
        dp[strs.length] = 1;
        for (int i = strs.length - 1; i >= 0; i--) {
            if (strs[i] == '0') {
                dp[i] = 0;
            } else {
                dp[i] = dp[i + 1];
                if (i + 1 < strs.length && (strs[i] - '0') * 10 + (strs[i + 1] - '0') < 27) {
                    dp[i] += dp[i + 2];
                }
            }
        }
        return dp[0];
    }

    public static String randomString(int len) {
        char[] str = new char[len];
        for (int i = 0; i < len; i++) {
            str[i] = (char) ((int) (Math.random() * 10) + '0');
        }
        return String.valueOf(str);
    }

    public static void main(String[] args) {
        int N = 30;
        int testTime = 1000000;
        System.out.println("测试开始");
        Code06_ConvertToLetterString convert = new Code06_ConvertToLetterString();
        for (int i = 0; i < testTime; i++) {
            String str = randomString(N);
            int ans1 = convert.number(str);
            int ans2 = convert.number2(str);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("测试结束");
    }
}
