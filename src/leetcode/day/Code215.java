package leetcode.day;

/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 */
public class Code215 {
    /**
     * 改写快排
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        return process(nums, 0, nums.length - 1, k - 1);
    }

    public int process(int[] nums, int l, int r, int k) {
        if (l >= r) {
            return nums[l];
        }
        int index = l + (int) (Math.random() * (r - l + 1));
        int value = nums[index];
        int[] partition = partition(nums, l, r, value);
        if (partition[0] <= k && partition[1] >= k) {
            return nums[partition[0]];
        } else if (partition[0] > k) {
            // 在左边找
            return process(nums, l, partition[0] - 1, k);
        } else {
            return process(nums, partition[1] + 1, r, k);
        }
    }

    /**
     * 将数组进行划分
     * 划分成左边大 中间相等 右边小
     *
     * @param nums
     * @param value
     * @return
     */
    public int[] partition(int[] nums, int l, int r, int value) {
        int left = l - 1;
        int right = r + 1;
        int index = 0;
        // 左边大 中间等 右边小
        while (index < right) {
            if (nums[index] == value) {
                index++;
            } else if (nums[index] > value) {
                swap(nums, ++left, index++);
            } else {
                swap(nums, --right, index);
            }
        }
        return new int[]{left + 1, right - 1};
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
