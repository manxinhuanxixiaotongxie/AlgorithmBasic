package leetcode.day;

/**
 * 给你一个顾客访问商店的日志，用一个下标从 0 开始且只包含字符 'N' 和 'Y' 的字符串 customers 表示：
 * <p>
 * 如果第 i 个字符是 'Y' ，它表示第 i 小时有顾客到达。
 * 如果第 i 个字符是 'N' ，它表示第 i 小时没有顾客到达。
 * 如果商店在第 j 小时关门（0 <= j <= n），代价按如下方式计算：
 * <p>
 * 在开门期间，如果某一个小时没有顾客到达，代价增加 1 。
 * 在关门期间，如果某一个小时有顾客到达，代价增加 1 。
 * 请你返回在确保代价 最小 的前提下，商店的 最早 关门时间。
 * <p>
 * 注意，商店在第 j 小时关门表示在第 j 小时以及之后商店处于关门状态。
 *
 */
public class Code2483 {

    public int bestClosingTime(String customers) {
        char[] str = customers.toCharArray();
        int n = str.length;
        int ans = n;
        int max;
        int nCount = 0;
        for (int i = 0; i < n; i++) {
            if (str[i] == 'Y') {
            }
            if (str[i] == 'N') {
                nCount++;
            }
        }
        // 全部关门的代价
        max = nCount;
        int preYCount = 0;
        // 从i位置进行的关门
        for (int i = n - 1; i >= 0; i--) {
            // 当前i位置关门代价是多少
            if (str[i] == 'Y') {
                preYCount++;
            }
            if (str[i] == 'N') {
                nCount--;
            }
            int cost = nCount + preYCount;
            if (cost <= max) {
                max = cost;
                ans = i;
            }
        }
        return ans;
    }

    static void main() {
        Code2483 code2483 = new Code2483();
        String customers = "YYNY";
        int res = code2483.bestClosingTime(customers);
        System.out.println(res);
    }
}
