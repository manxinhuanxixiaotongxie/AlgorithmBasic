package systemreview.code06;

import java.util.PriorityQueue;

/**
 * 存在一个数组 是无序 但是每个元素可以移动小于等于K的位置 可以使得这个数组变的有序
 * <p>
 * 选择一个合适的排序策略 对这个数组记性排序
 */
public class Code03_Lessk {

    /**
     * 使用小跟堆 维护一个limit为K的的堆
     * 每次加一个出一个
     */

    public void lessK(int[] arr, int k) {
        if (arr == null || arr.length < 2) {
            return;
        }

        PriorityQueue<Integer> heap = new PriorityQueue<>(k);
        int i = 0;
        for (; i < Math.min(arr.length, k - 1); i++) {
            heap.add(arr[i]);
        }
        int index = 0;
        for (; i < arr.length; i++, index++) {
            heap.add(arr[i]);
            arr[index] = heap.poll();
        }
        while (!heap.isEmpty()) {
            arr[index++] = heap.poll();
        }

    }
}
