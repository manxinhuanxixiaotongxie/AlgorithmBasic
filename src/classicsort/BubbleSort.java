package classicsort;

/**
 * @Description经典排序算法冒泡
 *
 * @Author Scurry
 * @Date 2023-05-09 15:53
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {5, 3, 12, 2, 1, 6, 5};
        printArr(arr);
        bubbleSort(arr);
        printArr(arr);
    }

    /**
     * 冒泡排序核心思路：两两交换
     * 参考实现方式第二种以及第三种方式
     * 0-1比较  交换
     * 2-1比较  交换
     * 3-2比较  交换
     * N-N-1比较  交换
     * 经过一次循环之后能找到最大值或者最小值
     * 假设此时最大值在最右侧
     * 那么再从0-N-1找到次最大值
     * 周而复始
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        /**
         * 由大到小(第一种方式)
         */
//        for (int i = 0; i < arr.length-1; i++) {
//            for (int j = i+1;j <arr.length;j++) {
//                if (arr[i] < arr[j]) {
//                    swapNormal(arr, i, j);
//                }
//            }
//        }
        /**
         * 由大到小(第二种方式)
         */
//        int N = arr.length - 1;
//
//        for (int i = N; i >0; i--) {
//            // 0-N-1 范围开始找 找到一个最大值或者最小值
//            // 0-N-2 范围开始找 找到一个最大值或者最小值
//            // 0-N-3 范围开始找 找到一个最大值或者最小值
//            //...
//            // 0-1 范围开始找 找到一个最大值或者最小值
//            for (int j = 1; j <= i; j++) {
//                if (arr[j - 1] > arr[j]) {
//                    swapNormal(arr, j - 1, j);
//                }
//            }
//        }

        /**
         * 由大到小(第三种方式)
         */
        int N = arr.length - 1;

        for (int i = 0; i < N; i++) {
            // 0-N-1 范围开始找 找到一个最大值或者最小值
            // 0-N-2 范围开始找 找到一个最大值或者最小值
            // 0-N-3 范围开始找 找到一个最大值或者最小值
            //...
            // 0-1 范围开始找 找到一个最大值或者最小值
            for (int j = 0; j < N - i; j++) {
                if (arr[j + 1] > arr[j]) {
                    swapNormal(arr, j + 1, j);
                }
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
