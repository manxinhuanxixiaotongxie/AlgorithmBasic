package classic;

/**
 * @Description经典排序算法:选择排序
 * @Author Scurry
 * @Date 2023-05-09 15:52
 */
public class SelectionSort {

    public static void main(String[] args) {
//        int[] arr = {5, 3, 12, 2, 1, 6, 5};
        int[] arr = {5,3,12,2,1,6,5};
        printArr(arr);
        selectionSort(arr);
        printArr(arr);

    }

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                swap(arr, i, minIndex);
            }

        }
    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 注意：这里异或交换的坑，当i==j成立
     * 会将数组的值变成0
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {

        arr[i] = arr[i]^arr[j];
        arr[j] = arr[i]^arr[j];
        arr[i] = arr[i]^arr[j];


    }
}
