package leetcode;

public class Code088 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] ans = new int[m + n];
        int L1 = 0;
        int L2 = 0;
        int index = 0;
        while (L1 < m && L2 < n) {
            if (nums1[L1] < nums2[L2]) {
                ans[index++] = nums1[L1++];
            } else {
                ans[index++] = nums2[L2++];
            }
        }
        while (L1 < m) {
            ans[index++] = nums1[L1++];
        }
        while (L2 < n) {
            ans[index++] = nums2[L2++];
        }
        for (int i = 0; i < m + n; i++) {
            nums1[i] = ans[i];
        }
    }
}
