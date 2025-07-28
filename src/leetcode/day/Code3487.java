package leetcode.day;

import java.util.Arrays;
import java.util.HashSet;

/**
 * 给你一个整数数组 nums 。
 * <p>
 * 你可以从数组 nums 中删除任意数量的元素，但不能将其变为 空 数组。执行删除操作后，选出 nums 中满足下述条件的一个子数组：
 * <p>
 * 子数组中的所有元素 互不相同 。
 * 最大化 子数组的元素和。
 * 返回子数组的 最大元素和 。
 * <p>
 * 子数组 是数组的一个连续、非空 的元素序列。
 */
public class Code3487 {
    public int maxSum(int[] nums) {
        int ans = 0;
        Arrays.sort(nums);
        // 从小到大进行排序
        HashSet<Integer> set = new HashSet<>();
        set.add(nums[nums.length - 1]);
        int index = nums.length - 1;
        while (index >= 0) {
            if (!set.contains(nums[index]) && nums[index] >= 0) {
                set.add(nums[index]);
            }
            index--;
        }
        for (Integer integer : set) {
            ans += integer;
        }
        return ans;
    }

    public static void main(String[] args) {
        Code3487 code3487 = new Code3487();
        int[] nums = {1,1,0,1,1};
        System.out.println(code3487.maxSum(nums)); // 输出 21
    }
}
