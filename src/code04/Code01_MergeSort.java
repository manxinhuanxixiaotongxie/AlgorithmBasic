package code04;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-06-26 16:06
 */
public class Code01_MergeSort {

    public static void main(String[] args) {

    }

    public static void mergeSort(int[] arr,int L, int R){

        if (L == R) {
            return;
        }
        int mid = L + ((R- L) >> 1);

        /**
         * 执行顺序
         * 1、二分一直将数组拆分
         * 2、左边有序  右边有序
         * 3.创建一个help数组覆盖到原有数组
         *
         * 假设现在有一个数组 int[] arr = new int[] {2 6 5 2 1 3}
         *
         * 第一次执行：
         * L = 0 R = 5 mid = 2
         *
         *
         * mergerSort(arr,0 ,2)    ---> mergeSort(arr.0,1)  mergeSort(arr,1,2)
         * * --> mergeSort(arr,0,0)  mergetSort(arr,1,1) mergeSort(2,2)  第一次执行结束
         *
         *
         * 第二次执行：
         *
         * mergerSort(arr,3,5)    ---> mergeSort(arr.3,4)  mergeSort(arr,4,5)
         * --> mergeSort(arr,3,3)  mergetSort(arr,4,4) mergeSort(5,5)  第二次执行结束
         *
         * 之后执行一次压栈的结果
         * 执行merge
         * merge(arr,3,4,5)
         * 左边压栈的结果弹出来
         * merge(arr,0,1,2)
         *
         *
         *
         */
        mergeSort(arr,L,mid);
        mergeSort(arr,mid+1,R);
        merge(arr,L,mid,R);

    }

    /**
     * 这个方法的作用是：新建一个辅助数组
     * 左边右边都有序的情况下  将两边数组的数值大小一次进行比对
     * 假设现在是有小到大排序
     * 那么现在谁小将谁放入新数组
     * @param arr
     * @param L
     * @param R
     */
    public static void merge(int[] arr,int L,int mid,int R) {
        int lIndex = 0;
        int rIndex = mid+1;
        int index = 0;

        int[] help = new int[R-L];

        /**
         * 构建一个辅助数组  谁小放谁
         * 可能出现左边没结束  或者右边没结束  直接放进去
         */
        while (lIndex <=mid && rIndex<= R ) {
            help[index++] = arr[lIndex] > arr[rIndex]?arr[lIndex++]:arr[rIndex++];
        }

        while (lIndex <= mid) {
            help[index++] = arr[lIndex];
        }


        while (rIndex<= R) {
            help[index++] = arr[rIndex];

        }

        /**
         * 将help数组的值覆盖到原有数组
         * 使得原有数组部分有序或者全部有序
         */

        for (int i = 0 ; i< help.length;i++) {
            arr[L+i] = help[i];
        }


    }
}
