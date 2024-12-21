package leetcode.practice;

public class Code053 {

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        // 暴露递归的解法
        // 必须以i位置开头有
//        return process1(nums, 0);
//        return dp1(nums);
        return process2(nums, nums.length - 1);
    }

    private int process1(int[] nums, int index) {
        if (index == nums.length) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        for (int i = index; i < nums.length; i++) {
//            // 当前位置的值
//            int curValue = nums[i];
//            // 后序位置的最大值
//            int next = process1(nums,index +1);
//
//            // 这样求最大值倒是没错 但是 求得不是最大值 而是和的最大值
//            if (next > 0) {
//                ans = Math.max(ans,curValue + next);
//            } else {
//                ans = Math.max(ans,curValue);
//            }
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                ans = Math.max(ans, sum);
            }
        }
        ans = Math.max(ans, process1(nums, index + 1));
        return ans;
    }

    /**
     * 必须以i位置结尾
     *
     * @param nums
     * @param index
     * @return
     */
    private int process2(int[] nums, int index) {
        if (index < 0) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        // 当前位置的值
        int curValue = nums[index];
        // 前面能够获取的最大值
        int next = process2(nums, index - 1);
        // 决策过程
        // 前面能够提供的最大值加上当前位置的值与当前位置的值 二者求最大
        // 就是当前位置能够取得的最大的值
        return 0;

    }

    private int process(int[] nums, int index) {
        if (index == nums.length) {
            return Integer.MIN_VALUE;
        }
        int ans = Integer.MIN_VALUE;
        for (int i = index; i < nums.length; i++) {
            // 过程如下：
            // 1.必须以i位置开头
            // 2.必须以i位置结尾
            // 3.必须以i位置开头并且以i位置结尾
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                ans = Math.max(ans, sum);
            }
        }

        return Math.max(ans, process(nums, index + 1));
    }

    private int dp1(int[] nums) {

        int[] dp = new int[nums.length + 1];
        dp[nums.length] = Integer.MIN_VALUE;
        int ans = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                ans = Math.max(ans, sum);
            }
            dp[i] = ans;
            ans = Math.max(ans, dp[i + 1]);
        }
        return ans;
    }


    public static void main(String[] args) {
        Code053 code053 = new Code053();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        int[] nums = {-2, -1};
        System.out.println(code053.maxSubArray(nums));
    }
}
