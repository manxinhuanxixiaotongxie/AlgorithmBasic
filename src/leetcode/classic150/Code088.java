package leetcode.classic150;

/**
 * 合并两个有序数组
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 *
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 *
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，
 * 应忽略。nums2 的长度为 n 。
 *
 * 进阶：你可以设计实现一个时间复杂度为 O(m + n) 的算法解决此问题吗？
 *
 */
public class Code088 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int r1 = m - 1;
        int r2 = n - 1;
        int r = nums1.length - 1;
        while (r1 >= 0 && r2 >= 0) {
            if (nums1[r1] > nums2[r2]) {
                nums1[r--] = nums1[r1--];
            }else {
                nums1[r--] = nums2[r2--];
            }
        }
        while (r1 >= 0) {
            nums1[r--] = nums1[r1--];
        }
        while (r2 >= 0) {
            nums1[r--] = nums2[r2--];
        }
    }
}
