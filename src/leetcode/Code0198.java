package leetcode;

public class Code0198 {
    public int rob(int[] nums) {
        return process(nums, 0);
    }

    private int process(int[] nums,int index) {
        if (index >= nums.length) {
            return 0;
        }
        // 当前位置不偷的话 能偷到的最大值
        int p1 = process(nums, index + 1);
        // 当前位置偷
        int p2 = nums[index] + process(nums, index + 2);
        return Math.max(p1, p2);
    }

    /**
     * 改成动态规划
     *
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
       int N = nums.length ;
       int[] dp = new int[N+2];
       for (int i = N-1; i >= 0; i--) {
           dp[i] = Math.max(nums[i] + dp[i+2], dp[i+1]);
       }
         return dp[0];
    }
}
