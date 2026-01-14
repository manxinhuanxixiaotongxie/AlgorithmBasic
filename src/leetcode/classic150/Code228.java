package leetcode.classic150;

import java.util.ArrayList;
import java.util.List;

public class Code228 {
    public List<String> summaryRanges(int[] nums) {
        // 数组已经有序，直接遍历
        List<String> ans = new ArrayList<>();
        int index = 0;
        int right = 1;
        int n = nums.length;
        while (index < n) {
            int min = nums[index];
            int max = nums[index];
            while (right< n && (nums[right]== nums[right-1] + 1 || nums[right] == nums[right-1])) {
                max = nums[right];
                right++;
            }
            // 结算
            if (max == nums[index]) {
                ans.add(String.valueOf(nums[index]));
            } else {
                ans.add(min + "->" + max);
            }
            index = right;
            right = index + 1;
        }
        return ans;
    }
}
