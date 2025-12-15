package leetcode.classic150;

/**
 * 找到多数元素
 *
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 */
public class Code169 {
    /**
     * 擂台打擂
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int count = 0;
        int ans = nums[0];
        for (int i = 0;i < nums.length;i++) {
            if (count == 0) {
                ans = nums[i];
                count++;
            }else  if (nums[i] == ans) {
                count++;
            }
            else {
                count--;
            }
        }
        return ans;

    }
}
