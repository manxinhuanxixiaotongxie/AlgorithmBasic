/**
 * @desc:
 * @author: Scurry
 * @date: 2022/6/29 23:05
 */

package code05;

/**
 * @desc:
 * @author: Scurry
 * @date: 2022/6/29 23:05
 */

/**
 * 快速排序
 *
 * 回顾一下荷兰国旗的问题
 * 小数在左边  等于在中间  大于在右边
 *
 * 中间等于已经有序
 * 左边有序  右边有序
 */
public class Code03_FastSort {

    public static void main(String[] args) {
        int[] arr = new int[] {9,8,7,6,5,4,3,2,1};
//        neigherlandFlag(arr,0,arr.length-1);
        process1(arr,0,arr.length-1);
        for (int i = 0;i < arr.length;i++) {
            System.out.printf(arr[i] + "   ");
        }
    }


    public static int[] neigherlandFlag(int[] arr,int L,int R) {

        int index = L;
        int left = L-1;
        int right = R;

        while (index < right) {
            if (arr[index] == arr[R]) {
                index ++;

            }
            if (arr[index] > arr[R]) {
                swap(arr,index,--right);
            }
            if (arr[index] < arr[R]) {
//                swap(arr,);
                swap(arr,++left,index++);
//                left++;
//                index++;
            }

        }

        swap(arr,right,R);


        return new int[] {left,right};
    }

    /**
     * 相等的数已经做过排序  不进行处理
     *
     * 荷兰国旗问题已经处理过小于的边界 大于的边界
     *
     * 相等已经有序  不进行处理
     * 从L-indexleft   indexRight-R 进行递归
     *
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int[] process1(int[] arr,int L,int R) {
        if (L == R) {
            return new int[] {L,R};
        }

        if (L > R) {
            return new int[] {-1,-1};
        }

        int[] ints = neigherlandFlag(arr, L, R);
        process1(arr,L,ints[0]);
        process1(arr,ints[1],R);

        return ints;
    }

    public static void swap(int[] arr,int a,int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
