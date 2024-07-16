package systemimprove.code06;

import java.util.PriorityQueue;

/**
 * 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的。
 *
 * 请选择一个合适的排序策略，对这个数组进行排序。
 *
 *
 */
public class Code03_LessK {

    public void lessK(int[] arr,int k) {
        if (arr == null || arr.length < 2) {
            return;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(k);
        int i= 0;
        for (; i < Math.min(arr.length-1,k-1); i++) {
            heap.add(arr[i]);
        }

        // 以上实现有问题
        int index = 0;
        for (;i< arr.length;i++,index++) {
            heap.add(arr[i]);
            arr[index] = heap.poll();
        }

        while (!heap.isEmpty()) {
            arr[index++] = heap.poll();
        }
    }





}
