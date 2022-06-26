package class03;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-06-26 15:14
 */

/**
 * 求数组arr最大值
 */
public class Code08_GetMax {

    /**
     * 1、循环遍历数组arr找到最大值 时间复杂度高
     * 2、二分
     */

    public static int getMax(int[] arr, int L , int R){


        if (L == R) {
            return arr[L];
        }
        int mid = L +( L + (R- L) >> 1);
        int l = getMax(arr, L, mid);

        int r = getMax(arr, mid + 1, R);

        return Math.max(l,r);

        /**
         *
         * 这个递归执行过程分析：
         * 1.在L-mid找到最大值
         * 2.在mid+1- R找到最大值
         * 3.左边最大值 右边最大值比较大小
         *
         */


    }
}
