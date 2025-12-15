package leetcode.classic150;

import java.util.HashMap;
import java.util.Map;

/**
 * 旋转数组
 *
 * 给你一个数组，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 *
 * 进阶：
 *
 * 尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。
 * 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 *
 */
public class Code189 {
    /**
     * 借助了辅助结构
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        // 借助辅助结构
        // 存储这个数字将要去的位置是什么
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0;i < nums.length;i++) {
            int toIndex = (i + k) % nums.length;
            map.put(toIndex, nums[i]);
        }
        for (int i = 0;i < nums.length;i++) {
            nums[i] = map.get(i);
        }
    }

    /**
     * 时间复杂度 O(N) 空间复杂度 O(1)
     *
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k %= n; // 轮转 k 次等于轮转 k % n 次
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = temp;
        }
    }

}
