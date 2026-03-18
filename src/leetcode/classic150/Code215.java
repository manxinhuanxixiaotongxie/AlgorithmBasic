package leetcode.classic150;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 */
public class Code215 {
    /**
     * 本题有很多做法  改写快拍  bfprt
     *
     *
     *
     * 本方法采用的最小堆
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        // 小根堆 维护K个最大元素 堆顶就是第K大
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        });
        for (int i = 0, index = 0; i < nums.length; i++, index++) {
            if (index < k) {
                pq.offer(nums[i]);
                continue;
            } else {
                // 新元素比堆顶大 才替换
                if (!pq.isEmpty() && nums[i] > pq.peek()) {
                    pq.poll();
                    pq.offer(nums[i]);
                }
            }
        }

        return pq.peek();
    }
}
