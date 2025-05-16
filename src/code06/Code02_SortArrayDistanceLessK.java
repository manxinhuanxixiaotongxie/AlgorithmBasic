package code06;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-07-11 11:17
 */

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 不完全无序数组，移动距离不超过K，排序
 * 准备一个K大小的小根堆，可以使用系统实现的优先队列
 */
public class Code02_SortArrayDistanceLessK {


    public static void sortedArrDistanceLessK(int[] arr, int k) {
        // 小根堆
        Queue<Integer> heap = new PriorityQueue<>();

        // 先找出前K个最小值
        int index = 0;
        for (; index <= Math.min(arr.length, k); index++) {
            heap.add(arr[index]);
        }

        /**
         * 在0-arr.length-k依次放入堆顶的值
         */
        int i = 0;
        for (; index < arr.length; index++, i++) {
            arr[i] = heap.poll();
            heap.add(arr[index]);
        }

        /**
         * 剩下的K个数依次放入
         */
        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }


    }


}
