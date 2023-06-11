package classic;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-06-11 15:23
 */
public class HeapSortTest {

    public static void main(String[] args) {
        int[] arr = {5, 3, 12, 2, 1, 6, 5};
        printArr(arr);
        heapSort(arr);
        printArr(arr);

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
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        int heapSize = arr.length ;
        swap(arr, 0, --heapSize);
        while (heapSize > 0) {
            heapify(0, heapSize, arr);
            swap(arr, 0, --heapSize);
        }

    }

    /**
     * 建堆 大根堆
     */
    public static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index-1)/2;
        }
    }

    /**
     * 重新建堆
     *
     * @param arr
     * @param l
     * @param r
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
