package classicsort;

import java.util.Arrays;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-06-11 15:23
 */
public class HeapSortTest {

    public static void main(String[] args) {
        int maxLength = 20;
        int maxValue = 20;
        int testTimes = 10000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            // 测试这么多次
            int[] arr = generateArr(maxLength, maxValue);
            int[] copy = copyRandomArr(arr);
            Arrays.sort(copy);
            heapSort(arr);
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] != copy[j]) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("test end");

    }

    public static int[] copyRandomArr(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public static int[] generateArr(int maxLength, int maxValue) {
        int[] ans = new int[(int) (Math.random() * (maxLength + 1))];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return ans;
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 建堆
//        for (int i = 0; i < arr.length; i++) {
//            heapInsert(arr, i);
//        }

        int heapSize = arr.length;
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(i, heapSize, arr);
        }
        while (heapSize > 0) {
            swap(arr, 0, --heapSize);
            heapify(0, heapSize, arr);
        }
    }

    /**
     * 建堆 大根堆
     */
    public static void heapInsert(int[] arr, int index) {

        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 重新建堆
     * <p>
     * param index
     * param heapSize
     * param arr
     */
    public static void heapify(int index, int heapSize, int[] arr) {
        int left = 2 * index + 1;
        while (left < heapSize) {
            int maxIndex = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            maxIndex = arr[index] > arr[maxIndex] ? index : maxIndex;
            if (maxIndex == index) {
                break;
            }
            swap(arr, index, maxIndex);
            index = maxIndex;
            left = 2 * index + 1;
        }
    }

    public static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;

    }
}
