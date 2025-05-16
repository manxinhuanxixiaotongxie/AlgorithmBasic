package code04;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-06-26 21:38
 */

/**
 * 小和问题 O(N*logN)
 * 每个位置的数左边比自己小的数累加起来，整体计算一个累加和
 * 左组和右组相等的时候，优先拷贝右组的数组，防止重复数据左组计算右组比他大的个数出现错误
 */
public class Code02_SmallSum {

    /**
     * 假设有一个数组  int[] arr = new int[]{1 3 4 5 6};
     * 第二个数的小和 1
     * 第三个数的小和 1 3
     * 第三个数的小和 1 3 4
     * 第四个数的小和 1 3 4 5
     * 小和汇总 1+ 1 + 3 + 1 + 3 + 4 + 1 + 3 + 4 + 5
     */

    /**
     * 第一种解法：简单粗暴  定义一个小和变量 遍历数组 满足条件累加
     * 第二种解法：二分
     *
     * @param arr
     * @return
     */
    public static int smallSum(int[] arr) {

        return process(arr, 0, arr.length - 1);

    }

    public static int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }

        int mid = L + ((R - L) >> 1);

        return process(arr, L, mid) + process(arr, mid + 1, R) + merge(arr, L, mid, R);


    }


    public static int merge(int[] arr, int L, int mid, int R) {

        int[] help = new int[R - L + 1];

        int lIndex = L;
        int rIndex = mid + 1;
        int index = 0;

//        while (L < R) {
//            if ()
//        }

        // 小和累加
        int res = 0;
        while (lIndex <= mid && rIndex <= R) {

            /**
             * 核心思想：左右两边进行拆分
             * 右边的数假设已经进行过对比，并且比lIndex大的话
             * 那么rIndex-R位置上的数都是小和
             */
            res += arr[lIndex] < arr[rIndex] ? (R - rIndex + 1) * arr[lIndex] : 0;
            help[index++] = arr[lIndex] <= arr[rIndex] ? arr[lIndex++] : arr[rIndex];
        }

        while (lIndex <= mid) {
            help[index++] = arr[lIndex++];
        }

        while (rIndex <= R) {
            help[index++] = arr[rIndex++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }

        return res;
    }


}
