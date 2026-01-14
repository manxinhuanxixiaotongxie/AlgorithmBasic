package leetcode.classic150;

import java.util.HashMap;
import java.util.Map;

public class Code219 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {

        // 数据 下标
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                int preIndex = map.get(nums[i]);
                if (i - preIndex <= k) {
                    return true;
                }
            }
            map.put(nums[i], i);
        }
        return false;

    }
}
