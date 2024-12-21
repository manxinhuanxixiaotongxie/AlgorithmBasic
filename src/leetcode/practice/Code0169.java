package leetcode.practice;

import java.util.HashMap;
import java.util.Map;

public class Code0169 {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> times = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (times.get(nums[i]) == null) {
                times.put(nums[i], 1);
            } else {
                times.put(nums[i], times.get(nums[i]) + 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : times.entrySet()) {
            if (entry.getValue() > nums.length / 2) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public int majorityElement2(int[] nums) {
        int times = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (times == 0) {
                ans = nums[i];
                times++;
            } else if (nums[i] == ans) {
                times++;
            } else if (nums[i] != ans) {
                times--;
            }
        }
        return ans;
    }
}
