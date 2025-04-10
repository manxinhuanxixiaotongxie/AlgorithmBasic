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

    public int numIdenticalPairs2(int[] nums) {
        // key是数组的数值 value是数值出现的次数
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            indexMap.put(nums[i], indexMap.getOrDefault(nums[i], 0) + 1);
        }
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans += indexMap.getOrDefault(nums[i], 1) - 1;
            // 结算之后 需要将当前数量减去1
            indexMap.put(nums[i], indexMap.getOrDefault(nums[i], 1) - 1);
        }
        return ans;
    }
}
