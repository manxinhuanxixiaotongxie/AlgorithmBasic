package leetcode.foundation;

import java.util.HashMap;
import java.util.Map;

public class Code1512 {
    public int numIdenticalPairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            map.merge(nums[i], 1, Integer::sum);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ans += getNums(entry.getValue());
        }
        return ans;
    }


    private int getNums(int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        int ans = 0;
        while (n > 0) {
            ans += n - 1;
        }
        return ans;
    }
}
