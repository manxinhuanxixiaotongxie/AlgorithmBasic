package code05;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-06-29 19:28
 */

import javax.print.DocFlavor;
import java.sql.Ref;

/**
 * 荷兰国旗问题
 *
 * 将一个数组拆分成三部分
 * 左边的数比给定的数小
 * 中间的数与给定的数相等
 * 右边的数比给定的数大
 *
 */
public class Code02_NeighlandFlag {

    /**
     *
     * 5 4 3  2 2 2 6 1 3 2    2
     * 实现思路：
     * 1.给定两个范围  一个两个范围  left right
     * 2.当前数比给定的数小  与左侧看的下一个数交换 左范围右扩 index++
     * 3.当前数比给定的数大  与右侧上一个数交换 右侧范围左扩  index停在原地
     * 注意：一开始我也没想明白为啥Index要停在原地
     * index不动的原因是  不清楚转换过来的数究竟是比原来的数大还是小
     * 由于右侧范围左阔  实际下次与index作比对的已经是right右侧的上上一个数（当前右侧范围的上一个数）
     * 假如在这个地方进行index++的话
     * 可能会出现一个问题
     * 举个例子：假设现在数组是 5 4 4  给定3   4最后一个 就会少被右侧范围统计进去
     * 4.相等  index++
     *
     */

    public static void netherlandFlag(int[] arr,int num){
        // 左边界
        int left = -1;
        // 右边界
        int right = arr.length;

        for (int i =0;i<arr.length;) {
            if (arr[i] == num) {
                i++;
            }

            if (arr[i] < num) {

                swap(arr,++left,i++);
//                left++;
//                i++;
            }

            if (arr[i] > num) {
                swap(arr,i,--right);
            }
        }

    }

    public static void netherlandFlag2(int[] arr, int L,int R){
        // 左边界
        int left = L-1;
        // 右边界
        int right = R;

        int index = L;
//        int num = arr[R]
        while (index < right) {
            if (arr[index] == arr[R]) {
                index++;
            }

            if (arr[index] < arr[R]) {
                swap(arr,++left,index++);
            }

            if (arr[index] > arr[R]) {
                swap(arr,--right,index);
            }
        }
        /**
         * 这一步做个说明  这个方法是将最后一个数作为比较数
         * 在右侧范围左扩的过程中，最后一个数是没有做处理的
         * 这里可以有两个选择
         * 讲最左侧最后一个数交换
         * 将最右侧前一个数交换
         */
        swap(arr,right,R);

        /**
         * 假设给定的数组是：
         * 5 4 4 4 3 --> 4445 4   -->
         */
    }




    public static void swap(int[] arr,int a,int b){

        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;

    }


}
