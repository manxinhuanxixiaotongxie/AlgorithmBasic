package leetcode.classic150;

/**
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 *
 */
public class Code034 {
    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[]{-1, -1};
        int n = nums.length;
        // 查找左边界
        int left = -1, right = n;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        // 检查 right 是否越界且是否等于 target
        if (right == n || nums[right] != target) {
            return ans;
        }
        ans[0] = right;

        // 查找右边界
        left = -1; right = n;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        ans[1] = left;

        return ans;
    }

    static void main() {
        int[] nums = {5,7,7,8,8,10};
        int target = 8;
        Code034 code034 = new Code034();
        int[] res = code034.searchRange(nums, target);
        System.out.println(res[0] + " " + res[1]);
    }
}
