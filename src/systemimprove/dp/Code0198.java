package systemimprove.dp;

/**
 * 打家劫舍
 * <p>
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 */
public class Code0198 {
    public int rob(int[] nums) {
        return process(nums, 0);
    }

    private int process(int[] nums, int index) {
        if (index >= nums.length) {
            return 0;
        }
        // 当前位置不打劫
        int p1 = process(nums, index + 1);
        // 当前位置打劫
        int p2 = nums[index] + process(nums, index + 2);
        return Math.max(p1, p2);
    }

    public int rob2(int[] nums) {
        int[] dp = new int[nums.length + 1];
        dp[nums.length] = 0;
        dp[nums.length - 1] = nums[nums.length - 1];
        for (int index = nums.length - 2; index >= 0; index--) {
            dp[index] = Math.max(dp[index + 1], nums[index] + dp[index + 2]);
        }
        return dp[0];
    }

    /**
     * 空间压缩
     *
     * @param nums
     * @return
     */
    public int rob3(int[] nums) {

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
