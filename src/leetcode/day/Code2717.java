package leetcode.day;

public class Code2717 {
    public int semiOrderedPermutation(int[] nums) {
        if (nums == null || nums.length <= 1 || nums[0] == 1 && nums[nums.length - 1] == nums.length) {
            return 0;
        }
        int firstOneIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                firstOneIndex = i;
                break;
            }
        }
        // 需要交换的次数
        int ans = 0;
        ans += firstOneIndex;
        // 执行交换
        while (firstOneIndex > 0) {
            swap(nums, firstOneIndex, --firstOneIndex);
        }
        int lastFourIndex = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] == nums.length) {
                lastFourIndex = i;
                break;
            }
        }
        ans += nums.length - 1 - lastFourIndex;
        return ans;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
