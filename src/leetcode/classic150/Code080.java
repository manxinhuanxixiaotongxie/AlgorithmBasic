package leetcode.classic150;

/**
 * 删除有序数组中的重复项II
 *
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使得出现次数超过两次的元素只出现两次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 */
public class Code080 {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        int ans = 1;
        int cnt = 1;
        int index = 1;
        while (index < nums.length) {
            while (index < nums.length && nums[index] == nums[index - 1]) {
                if (cnt < 2) {
                    nums[ans++] = nums[index];
                }
                cnt ++;
                index++;
            }
            cnt = 1;
            if (index < nums.length) {
                nums[ans++] = nums[index++];
            }
        }

        return ans;

    }
}
