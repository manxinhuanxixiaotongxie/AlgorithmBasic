package leetcode.day;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个下标从 0 开始的整数数组 nums 和两个整数 key 和 k 。K
 * 近邻下标 是 nums 中的一个下标 i ，并满足至少存在一个下标 j 使得 |i - j| <= k 且 nums[j] == key 。
 * <p>
 * 以列表形式返回按 递增顺序 排序的所有 K 近邻下标。
 */
public class Code2200 {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j <= k; j++) {
                if ((i - j >= 0) && (nums[i - j] == key)) {
                    ans.add(i);
                    break;
                } else if (i + j < nums.length && nums[i + j] == key) {
                    ans.add(i);
                    break;
                }
            }
        }
        return ans;
    }
}
