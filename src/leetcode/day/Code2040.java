package leetcode.day;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给你两个 从小到大排好序 且下标从 0 开始的整数数组 nums1 和 nums2 以及一个整数 k ，请你返回第 k （从 1 开始编号）小的 nums1[i] * nums2[j]
 * 的乘积，其中 0 <= i < nums1.length 且 0 <= j < nums2.length 。
 * <p>
 * 提示：
 * <p>
 * 1 <= nums1.length, nums2.length <= 5 * 10^4
 * -10^5 <= nums1[i], nums2[j] <= 10^5
 * 1 <= k <= nums1.length * nums2.length
 * nums1 和 nums2 都是从小到大排好序的。
 */
public class Code2040 {
    /**
     * O(N*M * logK) 的时间复杂度
     *
     * 超时
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        if (nums1.length == 0 || nums2.length == 0 || k <= 0) {
            return 0;
        }
        // 大根堆
        PriorityQueue<Long> pq = new PriorityQueue<>(Comparator.reverseOrder());
        // 维护一个大小为k的堆
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (pq.size() < k) {
                    pq.add(nums1[i] * (long) nums2[j]);
                } else {
                    if ((long) nums1[i] * nums2[j] < pq.peek()) {
                        pq.poll();
                        pq.add(nums1[i] * (long) nums2[j]);
                    }
                }
            }
        }
        return pq.poll();
    }
}
