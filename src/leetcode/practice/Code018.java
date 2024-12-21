package leetcode.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code018 {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 参考三数之和
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return ans;
        }
        // 对数组进行排序
        Arrays.sort(nums);
        // 对数组进行遍历
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i - 1] != nums[i]) {
                // 转化为三数之和
                long target1 = target - nums[i];
                if (target1 > Integer.MAX_VALUE  || target1 < Integer.MIN_VALUE ) {
                    continue;
                }
                List<List<Integer>> nexts = threeSum(nums, i + 1, target1);
                for (List<Integer> cur : nexts) {
                    cur.add(0, nums[i]);
                    ans.add(cur);
                }
            }
        }
        return ans;
    }

    private List<List<Integer>> threeSum(int[] sum, int begin, long target) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = begin; i < sum.length; i++) {
            if (i == begin || sum[i] != sum[i - 1]) {
                long target1 = target - sum[i];
                if (target1 > Integer.MAX_VALUE  || target1 < Integer.MIN_VALUE ) {
                    continue;
                }
                List<List<Integer>> nexts = twoSum(sum, i + 1, target1);
                for (List<Integer> cur : nexts) {
                    cur.add(0, sum[i]);
                    ans.add(cur);
                }
            }
        }
        return ans;
    }

    private List<List<Integer>> twoSum(int[] sum, int begin, long target) {
        int L = begin;
        int R = sum.length - 1;
        List<List<Integer>> ans = new ArrayList<>();
        while (L < R) {
            if (sum[L] + sum[R] > target) {
                R--;
            } else if (sum[L] + sum[R] < target) {
                L++;
            } else {
                if (L == begin || sum[L - 1] != sum[L]) {

                    List<Integer> cur = new ArrayList<>();
                    cur.add(sum[L]);
                    cur.add(sum[R]);
                    ans.add(cur);
                }
                L++;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Code018 code018 = new Code018();
        int[] nums = {1000000000,1000000000,1000000000,1000000000};
        List<List<Integer>> lists = code018.fourSum(nums, -294967296);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }


}
