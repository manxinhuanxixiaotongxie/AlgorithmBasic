package leetcode.practice;

import java.util.ArrayList;
import java.util.List;

public class Code0016 {
    public int threeSumClosest(int[] nums, int target) {
        List<Integer> list = new ArrayList<>();
        process(nums, 0, 3, list);
        int ans = list.get(0);
        int gap = Math.abs(ans - target);
        for (int i = 1; i < list.size(); i++) {
            if (Math.abs(list.get(i) - target) < gap) {
                ans = list.get(i);
                gap = Math.abs(ans - target);
            }
        }
        return ans;
    }

    // 在nums上面选出chooseNum个数，使得这些数的和最接近target的和是多少
    private void process(int[] nums, int target, int chooseNum, List<Integer> list) {
        if (chooseNum == 0) {
            list.add(target);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            int[] newNums = except(nums, i);
            process(newNums, target + nums[i], chooseNum - 1, list);
        }
    }

    private int[] except(int[] nums, int index) {
        int[] newNums = new int[nums.length - 1];
        for (int i = 0; i < nums.length; i++) {
            if (i < index) {
                newNums[i] = nums[i];
            } else if (i > index) {
                newNums[i - 1] = nums[i];
            }
        }
        return newNums;
    }
}
