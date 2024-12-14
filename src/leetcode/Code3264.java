package leetcode;

import java.util.Arrays;

public class Code3264 {
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        while (k > 0) {
            int minIndex = findMinIndex(nums);
            nums[minIndex] *= multiplier;
            k--;
        }
        return nums;
    }

    public int[] getFinalState2(int[] nums, int k, int multiplier) {
        while (k > 0) {
            int minIndex = findMinIndex(nums);
            nums[minIndex] *= multiplier;
            k--;
        }
        return nums;
    }

    private int findMinIndex(int[] nums) {
        int minIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }
}
