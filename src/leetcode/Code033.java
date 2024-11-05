package leetcode;

public class Code033 {
    public int search(int[] nums, int target) {
        // 二分查找
        int ans = -1;

        int L = 0;
        int R = nums.length - 1;
        /**
         * 二分
         * 为什么可以二分？
         * 分情况讨论：
         * 只有两种情况（数组在转换之前 是有序并且没有重复的）：
         *
         *
         * 第一种情况：
         * 累死这种： 4 5 6 7 8 9 10  0 1 2
         * 前面部分是有序的  后面部分的数组是有序的 但是 前面有序的部分的长度比较长
         * 那么这时候 L到mid部分是有序的  如果target在这个范围内 那么就可以直接二分
         * 如果不在这个范围内 那么就在另外一部分
         *
         *
         * 第二种情况：
         * 9 10 0 1 2 3 4 5 6 7 8
         * 类似这种情况 前面部分有序 后面部分有序 但是 后面部分的长度比较长
         * 那么这时候 mid到R部分是有序的  如果target在这个范围内 那么就可以直接二分
         * 如果不在这个范围内 那么就在另外一部分
         *
         *
         */
        while (L <= R) {
            int mid = (L + R) >> 1;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[L] < nums[mid]) {
                if (nums[L] <= target && target < nums[mid]) {
                    R = mid - 1;
                } else {
                    L = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[R]) {
                    L = mid + 1;
                } else {
                    R = mid - 1;
                }
            }
        }

        return ans;

    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 3;
        Code033 solution = new Code033();
        int search = solution.search(nums, target);
        System.out.println(search);
    }
}
