package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Code010 {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int ans = 0;
        int sum = 0;
        Map<Integer, Integer> numMap = new HashMap<>();
        numMap.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            // 这个题目的求解思路 使用map记录前面出现过的前缀和等于num[i]-k的次数
            // 题目求的是有多少个子数组的和为K 意味着前面出现的前缀和为sum[i]-k的次数
            sum += nums[i];
            if (numMap.containsKey(sum - k)) {
                ans += numMap.get(sum - k);
            }
            numMap.put(sum, numMap.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }
}
