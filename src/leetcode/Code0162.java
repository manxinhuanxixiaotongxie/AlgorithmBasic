package leetcode;

public class Code0162 {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return 0;
        }
        if (nums[0] > nums[1]) {
            return 0;
        }
        if (nums[nums.length - 1] > nums[nums.length - 2]) {
            return nums.length - 1;
        }
        int L = 1;
        int R = nums.length - 2;
        while (L < R) {
            int mid = (L + R) >> 1;
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid - 1] > nums[mid]) {
                R = mid - 1;
            } else if (nums[mid] < nums[mid + 1]) {
                L = mid + 1;
            }
        }
        return L;
    }

    public static void main(String[] args) {
        // [3,4,3,2,1]
        new Code0162().findPeakElement(new int[]{3, 4, 3, 2, 1});
    }
}
