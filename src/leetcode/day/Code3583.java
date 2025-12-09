package leetcode.day;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一个整数数组 nums。
 *
 * 特殊三元组 定义为满足以下条件的下标三元组 (i, j, k)：
 *
 * 0 <= i < j < k < n，其中 n = nums.length
 * nums[i] == nums[j] * 2
 * nums[k] == nums[j] * 2
 * 返回数组中 特殊三元组 的总数。
 *
 * 由于答案可能非常大，请返回结果对 10^9 + 7 取余数后的值。
 */
public class Code3583 {

    private int MOD = 1000000007;
    public int specialTriplets(int[] nums) {
        // help1[i]含义当前位置前面的位置多少个数是当前数的两倍
        int[] help1 = new int[nums.length];
        Map<Integer,Integer> map1 = new HashMap<>();
        // help2[i]含义当前位置后面有多少个数是当前数的两倍
        int[] help2 = new int[nums.length];
        // map2记录后面出现的数的个数
        Map<Integer,Integer> map2 = new HashMap<>();
        // 正序遍历
        map1.putIfAbsent(nums[0],1);
        for (int i = 1; i < nums.length; i++) {
            if (map1.containsKey(nums[i] << 1)) {
                help1[i] = map1.get(nums[i] << 1);
            }
            map1.merge(nums[i], 1, Integer::sum);
        }
        // 逆序遍历
        map2.putIfAbsent(nums[nums.length-1],1);
        for (int i = nums.length-2; i >= 0; i--) {
            if (map2.containsKey(nums[i] << 1)) {
                help2[i] = map2.get(nums[i] << 1);
            }
            map2.merge(nums[i], 1, Integer::sum);
        }
        // 继续正序遍历
        int ans = 0;
        for (int i = 1; i < help1.length; i++) {
            // 这里需要考虑溢出问题
            // help1[i] * help2[i]可能会溢出int

            ans = (int)((ans + ((long)help1[i] * help2[i]) % MOD) % MOD);
        }
        return ans;
    }

    static void main() {
        Code3583 code3583 = new Code3583();
        int[] nums = {1,2,2,4,8};
        int res = code3583.specialTriplets(nums);
        System.out.println(res);
    }
}
