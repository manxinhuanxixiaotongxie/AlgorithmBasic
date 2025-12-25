package leetcode.classic150;

public class Code055 {
    /**
     * 跳跃游戏
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {

        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (i > max) {
                return false;
            }
            max = Math.max(max,i+nums[i]);
        }
        return true;

    }

    static void main() {
        int[] nums = new int[] {3,2,1,0,4};
        boolean b = new Code055().canJump(nums);
        System.out.println(b);

    }
}
