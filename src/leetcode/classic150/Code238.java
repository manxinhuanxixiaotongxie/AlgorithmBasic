package leetcode.classic150;

/**
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 * <p>
 * 进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组 不被视为 额外空间。）
 *
 *
 */
public class Code238 {
    /**
     * 枚举右维护左
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        // 判断是否有0
        // 一个0 或者多个0 如果有多个0结果全部是0
        int zeroCount = 0;
        int n = nums.length;
        int[] result = new int[n];
        long sum = 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            } else {
                sum *= nums[i];
            }
        }
        if (zeroCount == 0) {
            // 一个0也没有
            int leftSum = 1;
            int rightSum = (int) sum;
            for (int i = 0; i < n; i++) {
                // 除自身以外
                result[i] = leftSum * (rightSum / nums[i]);
                leftSum *= nums[i];
                rightSum /= nums[i];
            }
            return result;
        } else if (zeroCount == 1) {
            for (int i = 0; i < n; i++) {
                if (nums[i] == 0) {
                    result[i] = (int) sum;
                }
            }
            return result;
        } else {
            return result;
        }
    }


    /**
     *
     * 一次遍历维护前缀和后缀
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        // 先将result维护成后缀数组 不包含自己
        result[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            result[i] = nums[i+1] * result[i + 1];
        }
        int pre = nums[0];
        for (int i = 1; i < n; i++) {
            result[i] = pre * result[i];
            pre *= nums[i];
        }
        return result;
    }
}
