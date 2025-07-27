package leetcode.day;

/**
 * 给你一个下标从 0 开始的整数数组 nums 。如果两侧距 i 最近的不相等邻居的值均小于 nums[i] ，
 * 则下标 i 是 nums 中，某个峰的一部分。类似地，如果两侧距 i 最近的不相等邻居的值均大于 nums[i] ，
 * 则下标 i 是 nums 中某个谷的一部分。对于相邻下标 i 和 j ，如果 nums[i] == nums[j] ， 则认为这两下标属于 同一个 峰或谷。
 * <p>
 * 注意，要使某个下标所做峰或谷的一部分，那么它左右两侧必须 都 存在不相等邻居。
 * <p>
 * 返回 nums 中峰和谷的数量。
 */
public class Code2210 {

    /**
     * 暴力解法
     *
     * @param nums
     * @return
     */
    public int countHillValley(int[] nums) {
        int ans = 0;
        if (nums == null || nums.length <= 2) {
            return ans;
        }
        int index = 1;
        int N = nums.length;
        while (index < N - 1) {
            // 最大值是N-2
            int temp = index - 1;
            while (temp >= 0 && nums[temp] == nums[index]) {
                temp--;
            }
            // 此时temp来到了与当前index不相等的位置
            if (temp == 0 && nums[temp] == nums[index] || temp < 0) {
                index++;
                continue;
            }
            int left = nums[temp];
            temp = index + 1;
            while (temp < N - 1 && nums[temp] == nums[index]) {
                temp++;
            }
            // 此时temp来到了右边与index不相等的位置
            int right = nums[temp];
            if (left < nums[index] && right < nums[index] || left > nums[index] && right > nums[index]) {
                ans++;
            }
            index++;
            while (index < N - 1 && nums[index] == nums[index - 1]) {
                index++;
            }

        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {6, 6, 5, 5, 4, 1};
        System.out.println(new Code2210().countHillValley(nums));
    }
}
