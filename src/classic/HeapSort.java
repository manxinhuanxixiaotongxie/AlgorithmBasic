package classic;

import static classic.InsertSort.insertSort1;

/**
 * @Description:经典排序算法堆排序
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

        // O(N)
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        int heapSize = arr.length;

        swap(arr, 0, --heapSize);

        while (heapSize > 0) {
            heapify(arr, 0, heapSize--);

            swap(arr, 0, heapSize);
        }

    }

    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int maxValueIndex = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;

            maxValueIndex = arr[maxValueIndex] > arr[index] ? maxValueIndex : index;

            if (maxValueIndex == index) {
                break;
            }
            swap(arr, index, maxValueIndex);
            index = maxValueIndex;
            left = index * 2 + 1;
        }
    }

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
            insertSort1(arr2);
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
