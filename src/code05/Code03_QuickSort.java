package code05;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-06-30 19:14
 */
public class Code03_QuickSort {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 11, 2, 1, 2, 1, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        process1(arr, 0, arr.length - 1);

        for (int i = 0; i < arr.length; i++) {
            System.out.printf(arr[i] + "    ");
        }

    }

    public static int[] process1(int[] arr, int L, int R) {
        if (L == R) {
            return new int[]{L, R};
        }

        if (L > R) {
            return new int[]{-1, -1};
        }

        int index = L;
        int left = L - 1;
        int right = R;
        while (index < right) {
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] > arr[R]) {
                swap(arr, index, --right);
//                right++;
            } else {
                swap(arr, ++left, index++);
//                left ++;
//                index++;
            }
        }

        swap(arr, right, R);

        process1(arr, L, right - 1);
        process1(arr, right + 1, R);

        return new int[]{left, right};

    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
