package leetcode.classic150;

/**
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 */
public class Code153 {
    /**
     * 假设x=nums[mid]是现在二分取到的数
     *
     * 我们需要判断x和数组最小值的位置关系 谁在左边 谁在右边？
     * 把x与最后一个数nums[n-1]比大小：
     * 1.如果x>nums[n-1] 那么可以推出以下结论：
     *   （1）nums一定被划分成左右两个递增段
     *   （2）第一段所有的元素均大于第二段的所有元素
     *   （3）x在第一段
     *   （4）最小值在第二段
     *   （5）所以x一定在最小值的左边
     * 2.如果x <=nums[n-1]那么x一定在第二段（或者nums就是递增数组 此时只有一段）
     *   （1）x要么是最小值 要么在最小值的右边
     *
     * 所以只需要比较x和nums[n-1]的大小关系 就间接地知道了x和数组最小值的位置关系 从而不断地缩小数组最小值所在位置的范围
     * 二分找到数组最小值
     *
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int n = nums.length;
        int left = -1;
        int right = n - 1; // 开区间 (-1, n-1)
        while (left + 1 < right) { // 开区间不为空
            int mid = (left + right) >>> 1;
            if (nums[mid] < nums[n - 1]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return nums[right];
    }
}
