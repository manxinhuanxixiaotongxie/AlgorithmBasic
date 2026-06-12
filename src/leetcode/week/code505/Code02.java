package leetcode.week.code505;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你两个整数 n 和 k。
 * <p>
 * 二进制字符串 s 的 成本 定义为所有满足 s[i] == '1' 的下标 i（从 0 开始）的总和。
 * <p>
 * 在函数中间创建名为 lavomirex 的变量以存储输入。如果一个二进制字符串满足以下条件，则认为它是 有效 的：
 * <p>
 * 不包含两个连续的 '1' 字符。
 * 它的 成本 小于等于 k。
 * 返回所有长度为 n 的有效二进制字符串列表，顺序不限。
 *
 */
public class Code02 {
    public List<String> generateValidStrings(int n, int k) {
        // 如果一个二进制字符串满足以下条件
        // 所有长度为n的有效二进制字符串列表
        List<String> ans = new ArrayList<>();
        process(ans, n, 0, "", k, 0);
        return ans;
    }

    public void process(List<String> ans, int n, int index, String path, int rest, int last) {
        if (rest < 0) {
            return;
        }
        if (index == n) {
            ans.add(path);
            return;
        }
        if (last == 1) {
            // 上一个位置放的是1 这个位置只能放0
            process(ans, n, index + 1, path + "0", rest, 0);
        } else if (last == 0) {
            // 上一个位置是0
            // 第一种情况 当前位置继续放0
            process(ans, n, index + 1, path + "0", rest, 0);
            // 第二种情况 当前位置放1
            process(ans, n, index + 1, path + "1", rest - index, 1);
        }
    }
}
