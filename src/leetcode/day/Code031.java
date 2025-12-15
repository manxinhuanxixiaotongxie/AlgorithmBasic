package leetcode.day;

/**
 * 下一个排列
 * 要求原地修改 只允许使用额外常数空间
 */
public class Code031 {
    public void nextPermutation(int[] nums) {

        //  从右边向左开始查找 找到第一个数 使得nums[i] <nums[i+1]
        // 那么从当前位置i的下一个位置开始一直到数组的最后一个元素 都是递减的
        int n = nums.length;
        int i = n - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i == -1) {
            // 数组是倒序排列
            int l = 0;
            int r = n - 1;
            while (l < r) {
                swap(nums, l++, r--);
            }
        } else {
            // i位置后面一直到数组最后一位递减的
            int l = i;
            int r = n - 1;
            // 从r位置开始查找 找到第一个数比nums[i]要大的数 交换位置
            while (nums[r] <= nums[i]) {
                r--;
            }
            swap(nums, l, r);
            l = i + 1;
            r = n - 1;
            while (l < r) {
                swap(nums, l++, r--);
            }
        }

    }

    private void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }

    static void main() {
        Code031 code031 = new Code031();
        int[] nums = {1, 3, 2};
        code031.nextPermutation(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
