package leetcode.hot100;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * <p>
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 */
public class Code0283 {

    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }
        int n = nums.length - 1;
        while (n >= 0) {
            if (n == nums.length - 1 && nums[n] == 0 || nums[n] != 0) {
                n--;
                continue;
            }
            // 当前位置是0
            int m = n;
            while (m < nums.length - 1) {
                swap(nums, m, ++m);
            }
            n--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
