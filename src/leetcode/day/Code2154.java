package leetcode.day;

import java.util.HashSet;

/**
 * 给你一个整数数组 nums ，另给你一个整数 original ，这是需要在 nums 中搜索的第一个数字。
 *
 * 接下来，你需要按下述步骤操作：
 *
 * 如果在 nums 中找到 original ，将 original 乘以 2 ，得到新 original（即，令 original = 2 * original）。
 * 否则，停止这一过程。
 * 只要能在数组中找到新 original ，就对新 original 继续 重复 这一过程。
 * 返回 original 的 最终 值。
 *
 */
public class Code2154 {

    public int findFinalValue(int[] nums, int original) {
        int ans = original;
        if (ans  <=0) {
            return original;
        }
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        while (set.contains(ans)) {
            ans = ans * 2;
        }
        return ans;
    }

    /**
     * 使用位图
     *
     * @param nums
     * @param original
     * @return
     */
    public int findFinalValue2(int[] nums, int original) {
        // 题目已经给定了nums中每个数的大小 每个整数作为一个bit位就可以存放32位整数
        // 使用固定长度的辅助数组 空间复杂度是O（1）的
        int[] help =new int[100];
        for (int num : nums) {
            // 既然每个整数都可以存放32位 那么当前的数应该来到多少位
            // 当前数左移5位 就是他应该来到的下标 那么找到下标之后 就应该计算他应该来到的位置
            // 怎么计算当前位置呢  num & 31 就是当前位置 为什么 因为31的二进制是00000000000000000000000000011111
            // 与运算就相当于取模32的意思
                help[num >> 5] |= ( 1 << (num & 31));
        }
        int ans = original;
        // 计算下标 看是否存在
        // 下标 help[ans >> 5]  &&  1 << (ans >> 5)
        while ((help[ans >> 5] & ( 1 << ( ans & 31))) != 0 ) {
            ans <<= 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Code2154 code2154 = new Code2154();
        int[] nums = {2,7,9};
        int original = 4;
        int finalValue = code2154.findFinalValue(nums, original);
        System.out.println(finalValue);

        int finalValue2 = code2154.findFinalValue2(nums, original);
        System.out.println(finalValue2);
    }
}
