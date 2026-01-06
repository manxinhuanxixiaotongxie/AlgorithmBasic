package leetcode.week.code483;

import java.util.Map;

public class Code03 {
    /**
     * 你可以对字符串 s 和 t 应用以下操作任意次（顺序不限）：
     * <p>
     * 选择任意下标 i，翻转 s[i] 或 t[i]（将 '0' 变为 '1' 或将 '1' 变为 '0'）。此操作的成本为 flipCost。
     * 选择两个 不同 下标 i 和 j，交换 s[i] 和 s[j] 或 t[i] 和 t[j]。此操作的成本为 swapCost。
     * 选择一个下标 i，交换 s[i] 和 t[i]。此操作的成本为 crossCost。©leetcode
     *
     * @param s
     * @param t
     * @param flipCost
     * @param swapCost
     * @param crossCost
     * @return
     */
    public long minimumCost(String s, String t, int flipCost, int swapCost, int crossCost) {
        return 0;
    }

    public long process(char[] str1, char[] str2, int index, int flipCost, int swapCost, int crossCost) {
        if (index == 0) {
            // 后面的位置已经做好了选择并且是相等的
            return str1[index] == str2[index] ? 0 : flipCost;
        }
        // 已经相等返回0
        if (String.valueOf(str1).equals(String.valueOf(str2))) return 0;
        long res = Long.MAX_VALUE;
        if (str1[index] == str2[index]) {
            // 已经相等了就得在之前的位置进行选择
            res = Math.min(res, process(str1, str2, index - 1, flipCost, swapCost, crossCost));
        }else {
            // 不相等
            // 第一种情况 翻转位置 最小代价是
            long p1 = flipCost + process(str1,str2,index - 1,flipCost,swapCost,crossCost);
            // 第二种情况
        }

        return res;
    }
}
