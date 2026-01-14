package leetcode.classic150;

import java.util.HashMap;
import java.util.Map;

/**
 * 001. 两数之和
 */
public class Code001 {
    public int[] twoSum(int[] nums, int target) {
        // 数据 下标
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
