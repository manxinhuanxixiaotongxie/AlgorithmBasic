package leetcode.classic150;

/**
 * 删除有序数组中的重复项I
 */
public class Code026 {
    public int removeDuplicates(int[] nums) {
        int[] help = new int[201];
        for (int i = 0; i < nums.length; i++) {
            help[nums[i] + 100]++;
        }
        int ans = 0;
        for (int i = 0; i< help.length; i++){
            if (help[i] >0) {
                nums[ans++] = i - 100;
            }
        }
        return ans;
    }

    /**
     * 不需要辅助数组的做法
     *
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        int ans = 1;
        for (int i = 1;i < nums.length;i++) {
            if (nums[i] != nums[i-1]) {
                nums[ans++] = nums[i];
            }
        }
        return ans;
    }
}
