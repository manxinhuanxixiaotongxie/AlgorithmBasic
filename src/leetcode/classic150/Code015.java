package leetcode.classic150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code015 {

    public List<List<Integer>> threeSum(int[] nums) {
        // 排序
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        // 先固定第一个数 再找另外两个数 保证三数之和为0
        int n = nums.length;
        for (int i = 0; i < n- 2;i++) {
            int l = i + 1, r = n - 1;
            int sum = 0 - nums[i];
            if (i > 0 && nums[i] == nums[i-1]) continue;
            while (l < r) {
                if (nums[l] + nums[r] == sum) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i], nums[l], nums[r])));
                    // 去重
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                }else if (nums[l] + nums[r] > sum) {
                    r--;
                }else {
                    l++;
                }
            }
        }

        return res;
    }

    static void main() {
        int[] nums = new int[] {-1,0,1,2,-1,-4};
//        int[] nums = new int[] {0,0,0,0};
        List<List<Integer>> lists = new Code015().threeSum(nums);
        System.out.println(lists);
    }
}
