package leetcode.week.code501;

public class Code01 {
    public int[] concatWithReverse(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        int n = nums.length;
        int[] ans = new int[n << 1];
        for (int i = 0; i < n;i++) {
            ans[i] = nums[i];
            ans[i + n] = nums[n - i - 1];
        }
        return ans;
    }
}
