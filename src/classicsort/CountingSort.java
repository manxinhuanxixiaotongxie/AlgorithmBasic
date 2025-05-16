package classicsort;

/**
 * @Description 经典排序算法：计数排序
 * @Author Scurry
 * @Date 2023-05-09 16:17
 */
public class CountingSort {
    /**
     * 计数排序
     *
     * @param arr 待排序数组
     * @return 排序后的数组
     */
    public static int[] countingSort(int[] arr) {

        /**
         * @See radixSort
         */
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] help = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            help[arr[i]]++;
        }
        int index = 0;
        for (int i = 1; i < help.length; i++) {
            if (help[i] > 0) {
                arr[index++] = i;
            }
        }
        return arr;
    }
}
