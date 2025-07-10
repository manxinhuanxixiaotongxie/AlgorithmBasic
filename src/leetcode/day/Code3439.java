package leetcode.day;

/**
 * 给你一个整数 eventTime 表示一个活动的总时长，这个活动开始于 t = 0 ，结束于 t = eventTime 。
 * <p>
 * 同时给你两个长度为 n 的整数数组 startTime 和 endTime 。它们表示这次活动中 n 个时间 没有重叠 的会议，其中第 i 个会议的时间为 [startTime[i], endTime[i]] 。
 * <p>
 * 你可以重新安排 至多 k 个会议，安排的规则是将会议时间平移，且保持原来的 会议时长 ，你的目的是移动会议后 最大化 相邻两个会议之间的 最长 连续空余时间。
 * <p>
 * 移动前后所有会议之间的 相对 顺序需要保持不变，而且会议时间也需要保持互不重叠。
 * <p>
 * 请你返回重新安排会议以后，可以得到的 最大 空余时间。
 * <p>
 * 注意，会议 不能 安排到整个活动的时间以外。
 * <p>
 * 1 <= eventTime <= 10^9
 * n == startTime.length == endTime.length
 * 2 <= n <= 10^5
 * 1 <= k <= n
 * 0 <= startTime[i] < endTime[i] <= eventTime
 * endTime[i] <= startTime[i + 1] 其中 i 在范围 [0, n - 2] 之间。
 */
public class Code3439 {
    /**
     * 定长滑动窗口
     * <p>
     * 看示例 1，把会议区间 [1,2] 移动到 [0,1] 或者 [2,3]，会产生空余时间段 [1,3] 或者 [0,2]，相当于把两个相邻的长为 1 空余时间段 [0,1] 和 [2,3] 合并成一个更大的长为 1+1=2 的空余时间段。
     * <p>
     * 如果 k=1，那么我们可以合并 2 个相邻的空余时间段。
     * <p>
     * 如果 k=2，为了让答案尽量大，合并连续 3 个空余时间段，相比其他方案是最优的。（注意题目要求会议之间的相对顺序必须保持不变）
     * <p>
     * 一般地，最优做法是合并连续 k+1 个空余时间段。
     * <p>
     * 现在问题变成：
     * <p>
     * 给你 n+1 个空余时间段，合并其中连续 k+1 个空余时间段，得到的最大长度是多少？
     * 注 1：最左边和最右边各有一个空余时间段，中间有 n−1 个空余时间段夹在两个相邻会议之间，所以有 n+1 个空余时间段。
     * <p>
     * 注 2：空余时间段的长度可以是 0。
     * <p>
     * 作者：灵茶山艾府
     * 链接：https://leetcode.cn/problems/reschedule-meetings-for-maximum-free-time-i/solutions/3061619/zhuan-huan-cheng-ding-chang-hua-dong-chu-1kg1/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * 假设我们已经找到了最优的移动方式，并且获得了最大的空闲区间I。如果存在某次移动与剩余k - 1次移动的会议并不连续，那么这次移动并不会对I有任何贡献；
     * 此时，我们可以将这次移动更换为移动与剩余k - 1次移动相邻的会议，那么更换后的移动一定会让I增大，这与我们一开始的最优假设矛盾。所以一定是连续移动k个会议最优。
     *
     * @param eventTime
     * @param k
     * @param startTime
     * @param endTime
     * @return
     */
    public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
        int n = startTime.length;
        int[] free = new int[n + 1];
        free[0] = startTime[0]; // 最左边的空余时间段
        for (int i = 1; i < n; i++) {
            free[i] = startTime[i] - endTime[i - 1]; // 中间的空余时间段
        }
        free[n] = eventTime - endTime[n - 1]; // 最右边的空余时间段

        // 套定长滑窗模板（窗口长为 k+1）
        int ans = 0;
        int s = 0;
        for (int i = 0; i <= n ; i++) {
            s += free[i];
            if (i < k) {
                continue;
            }
            ans = Math.max(ans, s);
            s -= free[i - k];
        }
        return ans;
    }
}
