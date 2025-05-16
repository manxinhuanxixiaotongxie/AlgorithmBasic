package classicsort;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-06-11 16:16
 */
public class MergeTest {

    public static void main(String[] args) {
        int[] arr = {5, 3, 12, 2, 1, 6, 5};
        printArr(arr);
        mergesort(arr);
        printArr(arr);

    }

    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void mergesort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);


    }

    public static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        // 0 6
        // 0 3
        // 0 1
        // 0 0
        // 0 1
        // 1,1
        int mid = l + (((r - l)) >> 1);
        System.out.println(mid);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, r, mid);
    }

    public static void merge(int[] arr, int l, int r, int mid) {

        int[] help = new int[r - l + 1];
        int left = l;
        int right = mid + 1;
        int index = 0;

        while (left <= mid && right <= r) {
            int max = arr[left] < arr[right] ? left++ : right++;
            help[index++] = arr[max];
        }
        while (left <= mid) {
            help[index++] = arr[left++];
        }
        while (right <= r) {
            help[index++] = arr[right++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }

    }
}
