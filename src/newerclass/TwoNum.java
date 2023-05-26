package newerclass;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description https://leetcode.cn/problems/two-sum/description/
 * @Author Scurry
 * @Date 2023-05-26 9:47
 */
public class TwoNum {

    /**
     * 时间复杂度O（n^2）
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum1(int[] nums, int target) {

        /**

         时间复杂度O（n^2）
         */
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1 ; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    ans[0] = i;
                    ans[1] = j;
                    break;
                }
            }
        }
        return ans;

    }

    /**
     * 进阶  能不能想一个时间复杂度更好的
     * 使用hashmap
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {

        /**

         时间复杂度O（N）
         */
        int[] ans = new int[2];
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target-nums[i])) {
                ans[0] = i;
                ans[1] = map.get(target-nums[i]);
            }
            map.put(nums[i],i );
        }
        return ans;

    }
}
