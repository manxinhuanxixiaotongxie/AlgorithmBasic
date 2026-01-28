package leetcode.classic150;

/**
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * <p>
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * <p>
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * <p>
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 *
 */
public class Code162 {

    /**
     *
     * 为什么规定nums[-1] && nums[n] == -Infinity？
     * 这样保证数组一定有峰值 比如数组是严格递减的  那么的nums[0]就是唯一的峰值
     * 如果数组是递增的 那么nums[n-1]就是唯一的峰值
     *
     *
     * 性质分析：
     * 定理：如果i < n-1并且nums[i] <nums[i+1] 那么在下标[i+1,n-1]一定存在峰值
     *
     * 同理：
     * 如果i < n-1并且nums[i] > nums[i+1] 那么在下标[0,i]一定存在峰值
     *
     * 因此通过比较nums[i]与nums[i+1]的大小关系 不断地缩小存在峰值的范围 二分找到峰值
     *
     *
     * 二分定义：
     *
     * 1.开区间
     * 2.闭区间
     * 3.开闭区间
     *
     *
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        // 开区间
        int left = -1;
        int right = nums.length - 1; // 开区间 (-1, n-1)
        while (left + 1 < right) { // 开区间不为空
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) { // 下坡，峰顶位置 <= mid
                right = mid;
            } else { // 上坡，峰顶位置 > mid
                left = mid;
            }
        }
        return right;
    }

    public int findPeakElement2(int[] nums) {
        // 闭区间
        int left = 0;
        int right = nums.length - 1; // 开区间 (-1, n-1)
        while (left < right) { // 开区间不为空
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) { // 下坡，峰顶位置 <= mid
                right = mid;
            } else { // 上坡，峰顶位置 > mid
                left = mid + 1;
            }
        }
        return right;
    }
}
