package leetcode;

public class Code034 {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;
        if (nums == null || nums.length == 0) {
            return res;
        }
        // 原数组是的非递减的
        // 那么可以进行二分
        int L = 0;
        int R = nums.length - 1;
        // 找到res[0]的答案
        while (L <= R) {
            int mid = (R + L) >> 1;
            if (nums[mid] == target) {
                res[0] = mid;
                R = mid - 1;
            } else if (nums[mid] < target) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        // 找到res[1]的答案
        L = 0;
        R = nums.length - 1;
        while (L <= R) {
            int mid = (R + L) >> 1;
            if (nums[mid] == target) {
                res[1] = mid;
                L = mid + 1;
            } else if (nums[mid] < target) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return res;
    }
}
