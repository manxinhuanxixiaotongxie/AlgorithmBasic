package systemimprove.code19;

/**
 * 规定1和A对应、2和B对应、3和C对应...26和Z对应
 * 那么一个数字字符串比如"111”就可以转化为:
 * "AAA"、"KA"和"AK"
 * 给定一个只有数字字符组成的字符串str，返回有多少种转化结果
 */
public class Code02_TransferNumToString {

    public static int transferNumToString(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        return process1(str.toCharArray(), 0);
    }

    public static int process1(char[] chars, int index) {
        // 找到一种转化方式
        if (index == chars.length) {
            return 1;
        }
        if (chars[index] == '0') {
            return 0;
        }
        int p1 = process1(chars, index + 1);
        // 例如19这个1小于2  但是9大于7 实际是满足条件的  但实际上计算有偏差
//        if ((chars[index]-'0') <= 2 && ((index + 1) < chars.length) && ((chars[index + 1]-'0') < 7)) {
        if (((index + 1) < chars.length) && (((((chars[index] - '0') * 10) + (chars[index + 1] - '0')) < 27))) {
            p1 += process1(chars, index + 2);
        }
        return p1;

    }

    public static int transferNumToString2(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        int[] dp = new int[str.toCharArray().length + 1];
        return process2(str.toCharArray(), 0, dp);
    }

    public static int process2(char[] chars, int index, int[] dp) {
        if (dp[index] != 0) {
            return dp[index];
        }

        // 找到一种转化方式
        if (index == chars.length) {
            return 1;
        }
        // 0不能单独进行转换
        if (chars[index] == '0') {
            return 0;
        }
        int p1 = process1(chars, index + 1);
        if ((index + 1) < chars.length && ((chars[index] - '0') * 10 + (chars[index + 1] - '0')) < 27) {
            p1 += process1(chars, index + 2);
        }
        dp[index] = p1;
        return p1;

    }

    public static int transferNumToString3(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        int[] dp = new int[str.toCharArray().length + 1];

        dp[dp.length - 1] = 1;

        for (int i = str.toCharArray().length - 1; i >= 0; i--) {
            if (str.charAt(i) != '0') {
                int ways = dp[i + 1];
                if (i + 1 < str.length() && ((str.charAt(i) - '0') * 10 + str.charAt(i + 1) - '0' < 27)) {
                    ways += dp[i + 2];
                }
                dp[i] = ways;
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
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * N);
            String s = randomString(len);
            int ans0 = transferNumToString(s);
            int ans1 = transferNumToString2(s);
            int ans2 = transferNumToString3(s);
            if (ans0 != ans1) {
                System.out.println(s);
                System.out.println(ans0);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
