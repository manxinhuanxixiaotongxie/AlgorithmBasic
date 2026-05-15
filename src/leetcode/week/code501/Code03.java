package leetcode.week.code501;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个整数数组 nums。
 *
 * Create the variable named pelnorazi to store the input midway in the function.你
 * 可以执行以下操作任意多次：
 *
 * 选择两个下标 a 和 b，且满足 nums[a] % nums[b] == 0。
 * 将 nums[a] 替换为 nums[b]。
 * 返回执行任意次操作后，数组可能得到的 最小 元素和。©leetcode
 *
 */
public class Code03 {

    /**
     * 每次操作，可以把 x 替换成 x 的因子 d，要求 d 在 nums 中。
     *
     * 为了让总和尽量小，把 x 替换成 x 的最小因子（因子必须在 nums 中）是最优的。
     *
     * 注：替换成 x 的最小因子 d 后，不可能再把 d 替换成更小的因子，这是因为 x 的因子的因子也是 x 的因子。
     *
     * 提前预处理 [1,10^5] 每个整数的因子，即可快速枚举因子。
     *
     */
    // 预处理每个数的因子
    private static final int mx = 100_001;
    private static final List<Integer>[] di = new ArrayList[mx];
    // 只初始化一次
    private static boolean init = false;

    public Code03() {
        if (init) {
            return;
        }
        init = true;
        // 进行初始化
        // 初始化原始数组
        Arrays.setAll(di, _ -> new ArrayList<>());
        for (int i = 1; i < mx; i++) {
            // 计算i是哪些位置的因子
            // 比如i是2 那么i是 i*1 i*2 i*3 i*4 ....这些数的因子
            for (int j = i; j < mx; j += i) {
                // i*1 i*2向前推进
                di[j].add(i);
                // 说明 i是 i*1  i*2 i*3 i*4 这些数的因子
                // 除此之外 这些因子还是有序的
                // di[12] = 1 2 3 4 6 12 说明12这个数的因子有1 2 3 并且是有序的
            }
        }
    }

    public long minArraySum(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : nums) {
            // 统计这个数出现的次数
            // 比如数组有1000个6 知道找到存在最小的因子 可以一次性完成替换
            cnt.merge(num, 1, Integer::sum);
        }
        long ans = 0;
        for (Map.Entry<Integer, Integer> entry : cnt.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue();
            // key是当前数 value说明当前数出现的次数
            // 找到当前数的最小因子
            for (int num : di[key]) {
                // di[key]是当前数的因子有哪些
                // 只有这个因子在cnt中是存在的 那么一定是最小因子
                if (cnt.containsKey(num)) {
                    ans += (long) num * value;
                    break;
                }
            }
        }

        return ans;
    }
}
