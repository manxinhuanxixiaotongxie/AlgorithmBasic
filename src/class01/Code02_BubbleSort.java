package class01;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-05-31 18:07
 */
public class Code02_BubbleSort {

    public static void main(String[] args) {

        int[] arr = new int[]{6, 3, 1, 5, 7, 9, 2};
        bubbleSort(arr);
        System.out.print(arr);

    }

    // [ 6 5 4 3 2 1]
    private static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] > arr[i]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
