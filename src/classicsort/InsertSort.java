package classicsort;

/**
 * @Description 经典排序算法插入排序
 *
 * @Author Scurry
 * @Date 2023-05-16 10:39
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {5, 3, 12, 2, 1, 6, 5};
        printArr(arr);
        insertSort2(arr);
        printArr(arr);

    }

    /**
     * 插入排序思路：
     * 0-0有序
     * 0-1有序
     * 0-2有序
     * 。。。
     * 0-N有序
     * <p>
     * 向前遍历，前一个大于当前值就交换
     *
     * @param arr
     */
    public static void insertSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int minNumIndex = i;
            while (minNumIndex >= 1 && arr[minNumIndex] < arr[minNumIndex - 1]) {
                swapNormal(arr, minNumIndex, --minNumIndex);
            }
        }
    }

    public static void insertSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        /**
         *
         * 插入排序的实质 假定前面部分都是有序的
         * 从当前位置向前推进 直到推不动为止
         */
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j >= 1 && arr[j] < arr[j - 1]; j--) {
                swapNormal(arr, j, j - 1);
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
     * 交换常规做法
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swapNormal(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
