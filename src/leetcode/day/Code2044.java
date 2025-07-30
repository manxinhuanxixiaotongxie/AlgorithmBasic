package leetcode.day;

/**
 * 给你一个整数数组 nums ，请你找出 nums 子集 按位或 可能得到的 最大值 ，并返回按位或能得到最大值的 不同非空子集的数目 。
 * <p>
 * 如果数组 a 可以由数组 b 删除一些元素（或不删除）得到，则认为数组 a 是数组 b 的一个 子集 。如果选中的元素下标位置不一样，则认为两个子集 不同 。
 * <p>
 * 对数组 a 执行 按位或 ，结果等于 a[0] OR a[1] OR ... OR a[a.length - 1]（下标从 0 开始）。
 */
public class Code2044 {
    public int countMaxOrSubsets(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max |= nums[i];
        }
        // 这个值就是所有的数异或出来的最大值
        return process(nums, 0, max, 0);
    }


    public int process(int[] nums, int index, int max, int rest) {
        if (index == nums.length) {
            return rest == max ? 1 : 0;
        } else {
            // 当前位置不要
            int ans = 0;
            ans += process(nums, index + 1, max, rest);
            // 当前位置要但是是有条件的
            ans += process(nums, index + 1, max, rest | nums[index]);
            return ans;
        }
    }

    /**
     * 改成动态规划
     *
     * @param nums
     * @return
     */
    public int countMaxOrSubsets2(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            max |= nums[i];
        }
        // 这个值就是所有的数异或出来的最大值
        return process(nums, 0, max, 0);
    }
}
