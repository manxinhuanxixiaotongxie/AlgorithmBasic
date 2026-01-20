package leetcode.classic150;

public class Code198 {
    public int rob(int[] nums) {

        int prePre = 0;
        int pre = nums[nums.length - 1];
        for (int index = nums.length - 2; index >= 0; index--) {
            int temp = Math.max(pre, prePre + nums[index]);
            prePre = pre;
            pre = temp;
        }
        return pre;
    }
}
