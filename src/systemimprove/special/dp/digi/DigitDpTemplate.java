package systemimprove.special.dp.digi;

import javax.print.DocFlavor;
import java.util.Arrays;

/**
 *
 * 统计合法元素的数目
 *
 * 数位dp的模板
 *
 */
public class DigitDpTemplate {

    /**
     * 不带记忆化搜索版本
     *
     *
     * 数位dp的模板
     *
     * 注意：只有上界约束的题目 low ==0 或者 low ==1
     *
     * <p>
     * 这个递归函数的含义是：在low到high范围内 枚举所有的数字
     *
     * @param i
     * @param cnt0
     * @param limitLow
     * @param limitHigh
     * @param lows
     * @param highs
     * @param target
     * @return
     */
    public int digitProcess(int i, int cnt0, boolean limitLow, boolean limitHigh, char[] lows, char[] highs, int target) {
        if (cnt0 > target) {
            return 0;
        }
        // 从0位置开始 一直填满高位所有的数字
        if (i == highs.length) {
            return cnt0 == target ? 1 : 0;
        }
        // 计算两位的位数差异 为什么要计算这个差异？
        /**
         * 其实是需要在低位进行补0
         * 比如低位数字是    13500
         *    高位数字是 15816116
         *
         *    那么这个递归其实就是在枚举从13500-15816116之间的所有数字
         *
         * 那么低位在与高位进行对齐的时候  这些位数是可以拿0进行补偿的
         */
        int diff = highs.length - lows.length;
        // 计算当前位的数字上限  以及下限范围
        // 低位  如果受到低位限制 i来到了diff之后 其实就是前面的位置都填0了 那么从当前位置开始的数 必须要>=低位的数才能保证范围是正确的
        int low = limitLow && (i >= diff) ? (lows[i - diff] - '0') : 0;
        // 高位 如果受到高位的限制的话 那么当前位置最高位只能选到当前数
        int high = limitHigh ? highs[i] : 0;

        // 如果前导0影响计算结果的话 那么需要加这一段
        int ans = 0;
        int d = low;
        if (limitLow && i < diff) {
            // 受到低位的限制 并且i还在低位 那么意味着低位与高位的差异部分填充的是0
            ans = digitProcess(i+1,0,true,true,lows, highs, target);
            d = 1;
        }
        // 当前位枚举从低位到高位的所有数字
        for (; d <= high; d++) {
            ans += digitProcess(i + 1, cnt0 + (d == 0 ? 1 : 0),
                    limitLow && (d == low), limitHigh && (d == high), lows, highs, target);
        }
        return ans;

    }

    /**
     * 带记忆化搜索版本
     * <p>
     * 这个递归函数的含义是：在low到high范围内 枚举所有的数字
     *
     * @param i
     * @param cnt0
     * @param limitLow
     * @param limitHigh
     * @param lows
     * @param highs
     * @param target
     * @return
     */
    public int digitProcess2(int i, int cnt0, boolean limitLow, boolean limitHigh, char[] lows, char[] highs, int target,long[][] memo) {
        if (cnt0 > target) {
            return 0;
        }
        // 从0位置开始 一直填满高位所有的数字
        if (i == highs.length) {
            return cnt0 == target ? 1 : 0;
        }
        // !limitLow && !limitHigh 这个条件十分重要 意味着 从低位到高位都已经做完了选择 与低位是否受到限制 高位是否受到限制都无关紧要了
        if (!limitLow && !limitHigh && memo[i][cnt0] != -1) {
            return (int)memo[i][cnt0];
        }
        // 计算两位的位数差异 为什么要计算这个差异？
        /**
         * 其实是需要在低位进行补0
         * 比如低位数字是    13500
         *    高位数字是 15816116
         *
         *    那么这个递归其实就是在枚举从13500-15816116之间的所有数字
         *
         * 那么低位在与高位进行对齐的时候  这些位数是可以拿0进行补偿的
         */
        int diff = highs.length - lows.length;
        // 计算当前位的数字上限  以及下限范围
        // 低位  如果受到低位限制 i来到了diff之后 其实就是前面的位置都填0了 那么从当前位置开始的数 必须要>=低位的数才能保证范围是正确的
        int low = limitLow && (i >= diff) ? (lows[i - diff] - '0') : 0;
        // 高位 如果受到高位的限制的话 那么当前位置最高位只能选到当前数
        int high = limitHigh ? highs[i] : 0;

        // 如果前导0影响计算结果的话 那么需要加这一段
        int ans = 0;
        int d = low;
        if (limitLow && i < diff) {
            // 受到低位的限制 并且i还在低位 那么意味着低位与高位的差异部分填充的是0
            ans = digitProcess2(i+1,0,true,true,lows, highs, target,memo);
            d = 1;
        }
        // 当前位枚举从低位到高位的所有数字
        for (; d <= high; d++) {
            ans += digitProcess2(i + 1, cnt0 + (d == 0 ? 1 : 0),
                    limitLow && (d == low), limitHigh && (d == high), lows, highs, target,memo);
        }
        if (!limitLow && !limitHigh) {
            memo[i][cnt0] = ans;
        }
        return ans;
    }

    /**
     * 统计有多少个数字是代表low到high范围内 且数字中0的个数为target的数字
     *
     * @param low
     * @param high
     * @param target
     * @return
     */
    public long digitDp(long low,long high,int target) {
        char[] lows = Long.toBinaryString(low).toCharArray();
        char[] highs = Long.toBinaryString(high).toCharArray();
        int n = highs.length;
        long[][] memo = new long[n][target+1];
        for (long[] row : memo) {
            Arrays.fill(row, -1);
        }
        return digitProcess2(0, 0, true, true, lows, highs, target,memo);

    }
}
