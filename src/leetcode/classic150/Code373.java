package leetcode.classic150;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Code373 {
    /**
     * 全量入堆
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> (o[0] + o[1])));
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                queue.offer(new int[]{nums1[i], nums2[j]});
            }
        }
        List<List<Integer>> res = new java.util.ArrayList<>();
        while (k > 0 && !queue.isEmpty()) {
            int[] pair = queue.poll();
            res.add(java.util.Arrays.asList(pair[0], pair[1]));
            k--;
        }
        return res;
    }

    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new java.util.ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return res;
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        // 初始化
        for (int i = 0; i < nums2.length; i++) {
            queue.offer(new int[]{nums1[0] + nums2[i], 0, i});
        }
        while (k > 0 && !queue.isEmpty()) {
            // 弹出
            int[] poll = queue.poll();
            int i = poll[1];
            int j = poll[2];
            res.add(java.util.Arrays.asList(nums1[i],nums2[j]));
            if (i < nums1.length - 1) {
                queue.offer(new int[]{nums1[i + 1] + nums2[j], i + 1, j});
            }
            k--;
        }
        return res;
    }
}
