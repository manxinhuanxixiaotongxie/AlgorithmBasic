package leetcode.practice;

import java.util.HashSet;

public class Code026 {

    /**
     * 快慢指针
     * 过程：
     * 1.住哟
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return nums.length;
        }
        int slowIndex = 0;
        int fastIndex = 0;
        HashSet<Integer> set = new HashSet<>();
        while (fastIndex < nums.length) {
            if (set.contains(nums[fastIndex])) {
                fastIndex++;
            } else {
                set.add(nums[fastIndex]);
                swap(nums, slowIndex++, fastIndex++);
            }
        }
        return slowIndex;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Code026 code026 = new Code026();
        int[] nums = {1, 1, 2};
        System.out.println(code026.removeDuplicates(nums));
    }
}
