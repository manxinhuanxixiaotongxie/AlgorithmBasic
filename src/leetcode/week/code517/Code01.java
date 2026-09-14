package leetcode.week.code517;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个整数数组 nums。
 * <p>
 * 如果整数 x 在 nums 中的所有出现位置都位于同一个 连续 区间内，则称 x 为 特殊整数。
 * <p>
 * 返回 nums 中 不同 特殊整数的数量。
 *
 */
public class Code01 {
    public int countSpecialIntegers(int[] nums) {
        // 一个整数数组nums
        // x在nums中所有出现的位置都位于一个连续的区间
        // 返回nums中 不同特殊整数的数量
        int ans = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.get(nums[i]).add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(nums[i], list);
            }
        }
        // 遍历
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == 1) {
                ans++;
                continue;
            }
            boolean find = true;
            for (int i = 1; i < entry.getValue().size(); i++) {
                if ((entry.getValue().get(i - 1) + 1) != entry.getValue().get(i)) {
                    find = false;
                }
            }
            if (find) ans++;
        }
        return ans;
    }
}
