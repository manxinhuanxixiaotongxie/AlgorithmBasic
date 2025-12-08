package systemimprove.dp.division;

/**
 * 分割回文串
 * 给你一个字符串S 请你将S分割成一些子串 使每个子串都是回文串
 * 返回符合要求的最少分割次数
 */
public class Code132 {
    /**
     * 超时
     *
     * @param s
     * @return
     */
    public int minCut(String s) {
        char[] str = s.toCharArray();
        return process(str, str.length - 1);
    }

    /**
     * 将字符串切分成回文最少切割次数
     *
     * @param str
     * @param index
     * @return
     */
    public int process(char[] str, int index) {

        if (isParm(str, 0, index)) {
            // 0--index已经是回文 不需要切割
            return 0;
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= index; i++) {
            // 判断i到index是否是回文串
            if (isParm(str, i, index)) {
                res = Math.min(res, process(str, i - 1) + 1);
            }
        }
        return res;
    }

    /**
     * 判断str在区间范围是否为回文串
     *
     * @param str
     * @param l
     * @param r
     * @return
     */
    public boolean isParm(char[] str, int l, int r) {
        boolean ans = true;
        while (l <= r) {
            if (str[l++] != str[r--]) {
                ans = false;
                break;
            }
        }
        return ans;
    }

    /**
     * 改成动态规划
     *
     * @param s
     * @return
     */
    public int minCut2(String s) {
        char[] str = s.toCharArray();
        int[] dp = new int[str.length];
        for (int index = 1; index < str.length; index++) {
            if (isParm(str, 0, index)) {
                // 0--index已经是回文 不需要切割
                dp[index] = 0;
                continue;
            }
            int res = Integer.MAX_VALUE;
            for (int i = 0; i <= index; i++) {
                // 判断i到index是否是回文串
                if (isParm(str, i, index)) {
                    res = Math.min(res, dp[i - 1] + 1);
                }
            }
            dp[index] = res;
        }
        return dp[dp.length - 1];
    }
}
