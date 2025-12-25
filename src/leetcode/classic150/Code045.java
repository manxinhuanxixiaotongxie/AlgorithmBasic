package leetcode.classic150;

public class Code045 {
    /**
     * 最小跳跃次数
     *
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int ans = 0;
        // 搭桥 什么情况下需要搭桥
        int max = nums[0];
        int lastMax = 0;
        for (int i = 0;i < nums.length;i++) {
            max = Math.max(max,i+nums[i]);
            if (i >=lastMax && lastMax < nums.length -1) {
                ans++;
                lastMax = max;
            }

        }
        return ans;
    }

    static void main() {
        int[] nums = new int[] {1,2,3};
        int jump = new Code045().jump(nums);
        System.out.println(jump);
    }
}
