package leetcode.review.newer;

import java.util.HashMap;
import java.util.Map;

public class Code06 {
    // 好数对
    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        int n = nums.length;
        for (int i = n - 1; i >= 0; i--) {
            res += map.getOrDefault(nums[i], 0);
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        return res;
    }
}
