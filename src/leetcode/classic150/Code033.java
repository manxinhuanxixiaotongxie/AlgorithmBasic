package leetcode.classic150;

/**
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 向左旋转，使数组变为 [nums[k],
 * nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 下标 3 上向左旋转后可能变为 [4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 *
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 */
public class Code033 {
    /**
     *
     * 讨论情况：
     *
     * 假设x = nums[mid]是中点所在的值
     *
     *
     *
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int last = nums[nums.length - 1];
        int left = -1;
        // 开区间 (-1, n-1)
        int right = nums.length - 1;
        // 开区间不为空
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            int x = nums[mid];
            // target 在第一段，x 在第二段
            if (target > last && x <= last) {
                // 下轮循环去左边找
                right = mid;
                // x 在第一段，target 在第二段
            } else if (x > last && target <= last) {
                // 下轮循环去右边找
                left = mid;
                // 否则，x 和 target 在同一段，这就和方法一的 lowerBound 一样了
            } else if (x >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return nums[right] == target ? right : -1;
    }
}
