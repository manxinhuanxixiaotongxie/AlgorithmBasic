package systemimprove.dp;

/**
 * 给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3], target = 4
 * 输出：7
 * 解释：
 * 所有可能的组合为：
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 请注意，顺序不同的序列被视作不同的组合。
 * <p>
 * <p>
 * 注意：题目已经说了数组的值都是不重复的
 * 并且 每个数组都可以多次选择
 *
 * <p>
 * 题目数据保证答案符合 32 位整数范围。
 */
public class Code0377 {
    /**
     * 先从暴力递归开始
     * 这个题目的尝试思路：
     * 定义一个方法 含义是：在数组中搞定taeget的方法数是多少
     *
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum(int[] nums, int target) {
        return process(nums, target);
    }

    public int combinationSum2(int[] nums, int target) {
        return process2(nums, 0, target);
    }

    /**
     * 这个递归实际上是不行的
     * 题目要求：顺序不同认为是不同的集合
     * 但是这个递归实际上是认为是相同的集合
     *
     * @param nums
     * @param index
     * @param rest
     * @return
     */
    private int process2(int[] nums, int index, int rest) {
        if (rest == 0) {
            return 1;
        }
        if (index == nums.length) {
            return 0;
        }
        int ans = 0;
        for (int zhang = 0; zhang * nums[index] <= rest; zhang++) {
            ans += process2(nums, index + 1, rest - zhang * nums[index]);
        }
        return ans;
    }

    /**
     * 递归过程：
     * 这个题目可以看做是爬楼梯 每次从nums选出一个数 作为向上爬的台阶数
     *
     * @param nums
     * @param rest
     * @return
     */
    private int process(int[] nums, int rest) {
        // 只有一个base case
        if (rest == 0) {
            return 1;
        }
        if (rest < 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans += process(nums, rest - nums[i]);
        }
        return ans;

    }

    public int combinationSum42(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }

        return dp[target];
    }

}
