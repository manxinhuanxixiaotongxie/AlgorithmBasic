package systemimprove.code06;

import java.util.Arrays;

/**
 * 堆结构
 * 完全二叉树
 * 空树可以认为是完全二叉树
 * <p>
 * 完全二叉树：
 * 1.叶子节点只能出现在最下层和次下层
 * 2.最下层的叶子节点集中在左部连续位置
 * 3.倒数第二层，若有叶子节点，一定在右部连续位置
 * 4.如果节点度为1，那么只有左孩子，没有右孩子
 * 5.同样节点数的二叉树，完全二叉树的深度最小
 * <p>
 */
public class Code02_HeapSort {

    private int heapSize;
    private int limit;
    private int[] heap;

    Code02_HeapSort(int limit) {
        this.limit = limit;
        heap = new int[limit];
        heapSize = 0;
    }

    /**
     * （N-1）/2 就是父节点的位置
     * <p>
     * 假设调整成大根堆的话
     *
     * @param num
     */
    public void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }

    }

    /**
     * 重新调整成大跟堆
     *
     * @param index
     */
    public void heapify(int[] arr, int index, int heapSize) {
        int child = (index * 2) + 1;
        // 假设heapsize是10，那么最后一个叶子节点的位置是9
        // index为4的时候，child为9，那么child就是最后一个叶子节点的位置
        while (child < heapSize) {
            int largest = child + 1 < heapSize && arr[child + 1] > arr[child] ? child + 1 : child;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            child = (index * 2) + 1;
        }
    }


    // 弹出堆顶元素
    public int poll() {
        swap(heap, 0, --heapSize);
        heapify(heap, 0, heapSize);
        return heap[heapSize];

    }

    public void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

//        for (int i = 0; i < arr.length; i++) {
//            heapInsert(arr, i);
//        }

        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        // 最大值放在的数组最后一个位置
        int heapSize = arr.length;
        // 最后一位是最大值
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            // 重新调整成大根堆
            // heapSize刚好为4 那么index就是0 1 2 3
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }

    }

    public int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue) * Math.random());
        }
        return arr;
    }

    public void printArray(int[] arr) {
        if (arr == null) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }

        if (arr1 == null && arr2 == null) {
            return true;
        }

        if (arr1.length != arr2.length) {
            return false;
        }

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }

        return true;
    }

    public int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }

        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public void comparator(int[] arr) {

        Arrays.sort(arr);
    }


    public static void main(String[] args) {
        Code02_HeapSort heap = new Code02_HeapSort(10);
        int testTime = 100000;
        int maxSize = 5;
        int maxValue = 100;

        for (int i = 0; i < testTime; i++) {
            int[] arr1 = heap.generateRandomArray(maxSize, maxValue);
            int[] arr2 = heap.copyArray(arr1);
            int[] arr3 = heap.copyArray(arr1);
            heap.heapSort(arr1);
            heap.comparator(arr2);
            if (!heap.isEqual(arr1, arr2)) {
                heap.printArray(arr3);
                heap.printArray(arr1);
                heap.printArray(arr2);
                break;
            }
        }
    }


    public void swap(int[] arr, int l, int r) {
        int swap = arr[l];
        arr[l] = arr[r];
        arr[r] = swap;
    }
}
