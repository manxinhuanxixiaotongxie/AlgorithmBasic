package classicsort;

import java.util.Arrays;

/**
 * @Description 经典排序算法堆排序
 * @Author Scurry
 * @Date 2023-05-09 15:53
 */
public class HeapSort {

    /**
     * @param arr
     */
    public static void heapsort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }
        // 建堆 大跟堆
//        for (int i = 0; i < arr.length; i++) {
//            heapInsert(arr, i);
//        }
        int heapSize = arr.length;

        // O(N)
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, heapSize);
        }

        // 堆的弹出过程 将堆顶的位置与堆的最后一个位置进行交换
        // 堆的大小进行减小
//        swap(arr, 0, --heapSize);
//
//        while (heapSize > 0) {
//            heapify(arr, 0, heapSize);
//            swap(arr, 0, --heapSize);
//        }

        while (heapSize > 0) {
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }

    }

    /**
     * 向下调整
     *
     * @param arr
     * @param index
     * @param heapSize
     */
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            // 找到子节点的最大值
            int maxValueIndex = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            // 子节点的最大值与当前位置进行比较
            maxValueIndex = arr[maxValueIndex] > arr[index] ? maxValueIndex : index;
            // 如果当前位置已经是最大值了  不需要进行处理
            if (maxValueIndex == index) {
                break;
            }
            swap(arr, index, maxValueIndex);
            index = maxValueIndex;
            left = index * 2 + 1;
        }
    }

    /**
     * 在index位置 插入arr[index]的数组
     *
     * @param arr
     * @param index
     */
    public static void heapInsert(int[] arr, int index) {

        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
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

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            int[] arr4 = copyArray(arr1);
            heapsort(arr1);
            Arrays.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                printArray(arr3);
                printArray(arr4);
                break;
            }
        }
//        int[] arr = new int[]{19, -14, 32};
//        heapsort(arr);
        System.out.println("测试结束");
    }


}
