package leetcode.day;

/**
 * 给你一个整数数组 prices ，表示一支股票的历史每日股价，其中 prices[i] 是这支股票第 i 天的价格。
 * <p>
 * 一个 平滑下降的阶段 定义为：对于 连续一天或者多天 ，每日股价都比 前一日股价恰好少 1 ，这个阶段第一天的股价没有限制。
 * <p>
 * 请你返回 平滑下降阶段 的数目。
 * <p>
 * 提示：
 * <p>
 * 1 <= prices.length <= 10^5
 * 1 <= prices[i] <= 10^5
 *
 */
public class Code2110 {

    /**
     * 滑动窗口
     *
     * @param prices
     * @return
     */
    public long getDescentPeriods(int[] prices) {
        long ans = 0;
        int right = 1;
        int n = prices.length;
        for (int i = 0; i < n; i++) {
            while (right < n && prices[right] == prices[right - 1] - 1) {
                right++;
            }
            // 结算
            int length = right - i;
            ans += (long) length * (length - 1) / 2;
            i = right - 1;
            right++;
        }
        return ans + n;
    }

    static void main() {
        Code2110 code2110 = new Code2110();
        int[] prices = {12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 4, 3, 10, 9, 8, 7};
        long res = code2110.getDescentPeriods(prices);
        System.out.println(res);
    }
}
