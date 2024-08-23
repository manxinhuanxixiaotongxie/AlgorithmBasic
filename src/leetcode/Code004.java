package leetcode;

public class Code004 {
    public static void main(String[] args) {
        Code004 code004 = new Code004();
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(code004.findMedianSortedArrays(nums1, nums2));
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int length = m + n;
        // 奇数
        if (length % 2 != 0) {
            int midIndex = length / 2;
            return getKthElement(nums1, nums2, midIndex);
        } else {
            // 偶数
            int midIndex1 = length / 2 - 1, midIndex2 = length / 2;
            return (getKthElement(nums1, nums2, midIndex1 ) + getKthElement(nums1, nums2, midIndex2)) / 2.0;
        }
    }

    private int getKthElement(int[] nums1, int[] nums2, int index) {
        int i = 0;
        int lIndex = 0;
        int rIndex = 0;
        int ans = 0;
        while (lIndex < nums1.length || rIndex < nums2.length) {
            if (lIndex == nums1.length) {
                ans = nums2[rIndex];
                rIndex++;
            } else if (rIndex == nums2.length) {
                ans = nums1[lIndex];
                lIndex++;
            } else {
                if (nums1[lIndex] < nums2[rIndex]) {
                    ans = nums1[lIndex];
                    lIndex++;
                } else {
                    ans = nums2[rIndex];
                    rIndex++;
                }
            }
            if (i == index) {
                return ans;
            }
            i++;
        }
        return ans;
    }
}
