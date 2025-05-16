package code06;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-07-05 9:12
 */

/**
 * 堆排序
 */
public class Code01_HeapSort {

    /**
     * 堆结构：
     * 二叉树
     *
     * 小跟堆：父节点比左右两个子节点的数据都要小
     * 大根堆：父节点比左右两个子节点的数据都要大
     *
     * 子节点：
     *    左子节点下标:2*i+1
     *    右子节点下标:2*i+2
     */

    /**
     * 小跟堆
     * 比较父节点的数据与左右两个子节点的数据
     * 如果父节点比子节点大的话，与子节点交换
     */
    public static class MyHeap {
        private int[] heap;
        private int limit;
        private int heapSize;

        MyHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }

        public void push(int value) {

            if (heapSize == limit) {
                System.out.println("heap is full");
            }

            heap[heapSize] = value;

            heapInsert(heap, heapSize++);

        }

        /**
         * 小跟堆 弹出最小值 剩下的值依然保持小跟堆
         *
         * @return
         */
        public int pop() {
            if (heapSize == 0) {
                System.out.println("heap is null");
            }
            int temp = heap[0];
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return temp;

        }

        public static void heapify(int[] arr, int index, int heapSize) {
            int leftIndex = index >> 1 | 1;
            // 选出左右节点较小的那个下标
//            int minIndex = arr[leftIndex + 1] < arr[leftIndex] ? leftIndex + 1 : leftIndex;
//            while (arr[index] > arr[minIndex]) {
//                swap(arr,index,minIndex);
//                index = minIndex;
//            }

            while (leftIndex < heapSize) {
                // 选出左右节点最小的下标
                int minIndex = leftIndex + 1 < heapSize && arr[leftIndex + 1] < arr[leftIndex] ? leftIndex + 1 : leftIndex;
                minIndex = arr[minIndex] > arr[index] ? index : minIndex;
                if (minIndex == index) {
                    break;
                }
                swap(arr, minIndex, index);
                leftIndex = index * 2 + 1;
            }

        }

        /**
         * 小跟堆
         *
         * @param arr
         * @param index
         */
        public void heapInsert(int[] arr, int index) {

            while (arr[index] < arr[(index - 1) / 2]) {
//                heapSize++;
                swap(arr, index, (index - 1) / 2);
                // index向上查找
                index = (index - 1) / 2;
            }

        }

        /**
         * 堆排序的过程  先维护一个最大堆或者最小堆
         * 那么堆顶要么是最大值 要么是最小值
         *
         * @param arr
         */
        public static void heapSort(int[] arr) {
            int heapSize = arr.length;
            swap(arr, 0, --heapSize);
            while (heapSize > 0) {
                heapify(arr, 0, heapSize);
                swap(arr, 0, --heapSize);
            }

        }
    }

    public static void swap(int[] arr, int a, int b) {

        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;


    }


}
