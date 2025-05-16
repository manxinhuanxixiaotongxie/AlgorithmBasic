package leetcode.day;

/**
 * 给你一个字符串 num ，表示一个大整数。如果一个整数满足下述所有条件，则认为该整数是一个 优质整数 ：
 * <p>
 * 该整数是 num 的一个长度为 3 的 子字符串 。
 * 该整数由唯一一个数字重复 3 次组成。
 * 以字符串形式返回 最大的优质整数 。如果不存在满足要求的整数，则返回一个空字符串 "" 。
 * <p>
 * 注意：
 * <p>
 * 子字符串 是字符串中的一个连续字符序列。
 * num 或优质整数中可能存在 前导零 。
 * <p>
 * 示例 1：
 * <p>
 * 输入：num = "6777133339"
 * 输出："777"
 * 解释：num 中存在两个优质整数："777" 和 "333" 。
 * "777" 是最大的那个，所以返回 "777" 。
 * 示例 2：
 * <p>
 * 输入：num = "2300019"
 * 输出："000"
 * 解释："000" 是唯一一个优质整数。
 * 示例 3：
 * <p>
 * 输入：num = "42352338"
 * 输出：""
 * 解释：不存在长度为 3 且仅由一个唯一数字组成的整数。因此，不存在优质整数。
 */
public class Code2264 {
    /**
     * 对字符串的操作拖垮了常数时间
     *
     * @param num
     * @return
     */
    public String largestGoodInteger(String num) {
        String ans = null;
        int max = Integer.MIN_VALUE;
        char[] chars = num.toCharArray();
        // 从第一个未出出发
        for (int i = 1; i < chars.length - 1; i++) {
            int pre = chars[i - 1] - '0';
            int after = chars[i + 1] - '0';
            int cur = chars[i] - '0';
            // 三者相等 并且当前值比最大值要大更新答案
            if (pre == after && pre == cur && cur > max) {
                max = cur;
                ans = cur + String.valueOf(cur) + cur;
            }
        }
        return ans == null ? "" : ans;
    }

//    public String largestGoodInteger2(String num) {
//        char mx = 0;
//        for (int i = 0; i < num.length() - 2; i++) {
//            char d = num.charAt(i);
//            if (d > mx && d == num.charAt(i + 1) && d == num.charAt(i + 2)) {
//                mx = d;
//            }
//        }
//        return mx > 0 ? String.valueOf(mx).repeat(3) : "";
//    }
}
