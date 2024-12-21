package leetcode.practice;

import java.util.HashSet;
import java.util.Set;

public class Code0213 {

    public int rob2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        return process(nums, 0, new HashSet<>());
    }

    private int process(int[] nums, int index, Set<Integer> set) {
        if (index >= nums.length) {
            return 0;
        }
        int p1 = process(nums, index + 1, set);
        int p2 = Integer.MIN_VALUE;
        if (!set.contains(index)) {
            set.add(index);
            p2 = process(nums, index + 2, set) + nums[index];
            set.remove(index);
        }
        int p3 = nums[index];
        return Math.max(Math.max(p1, p2), p3);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 2};
        Code0213 code0213 = new Code0213();
        System.out.println(code0213.rob2(nums));
    }
}
