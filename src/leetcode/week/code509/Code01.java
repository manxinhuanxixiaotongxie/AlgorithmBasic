package leetcode.week.code509;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个整数数组 nums。
 * <p>
 * 一个整数的 数字范围 定义为其 最大 数字与 最小 数字之间的差。
 * <p>
 * 例如，5724 的数字范围为 7 - 2 = 5。
 * <p>
 * 返回 nums 中所有 数字范围 等于数组中 最大数字范围 的整数之和。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 10 <= nums[i] <= 10^5
 */
public class Code01 {
    public int maxDigitRange(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            int curMax = Integer.MIN_VALUE;
            int curMin = Integer.MAX_VALUE;
            while (num != 0) {
                int cur = num % 10;
                curMax = Math.max(curMax, cur);
                curMin = Math.min(curMin, cur);
                num /= 10;
            }
            int mivus = curMax - curMin;
            max = Math.max(max, mivus);
        }
        // 计算出最大值最小值是多少
        Map<Integer, Integer> map = new HashMap<>();
        map.put(max, 0);
        for (int num : nums) {
            int curMax = Integer.MIN_VALUE;
            int curMin = Integer.MAX_VALUE;
            int curNum = num;
            while (num != 0) {
                int cur = num % 10;
                curMax = Math.max(curMax, cur);
                curMin = Math.min(curMin, cur);
                num /= 10;
            }
            int mivus = curMax - curMin;
            // 计算答案
            if (map.containsKey(mivus)) {
                map.put(mivus, map.get(mivus) + curNum);
            }
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ans = Math.max(ans, entry.getValue());
        }
        return ans;
    }

    static void main() {
        int[] nums = {5724,111,350};
        Code01 c = new Code01();
        System.out.println(c.maxDigitRange(nums));
    }
}
