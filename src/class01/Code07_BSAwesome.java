package class01;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-06-14 15:24
 */

/**
 * 局部最小值问题
 *
 */
public class Code07_BSAwesome {


    /**
     *
     * 如果第一个数小于第二个数  那么直接返回第一个数
     *
     * 如果最后一个数小于倒数第二个数 直接返回最后一个数
     *
     * 如果第一个数大于第二个数   最后一个数大于倒数第二个数  那么在第一个数到最后一个数  必然存在局部最小值
     *
     * 二分过程
     *
     * 如果mid-1小于mid的话    那么  在L -mid中间必然存在局部最小值
     *
     * 注意一个问题
     *
     * 我们只能在这样的区间找到局部最小值
     *
     * 下降之后上升
     *
     * 如果mid+1 小于mid的值话话  那么在mid+1到R会有局部最小值 返回就行
     *
     */


    private  static  int findLocalMinimum(int[] arr){

        if (arr == null || arr.length <2) {
            return -1;
        }

        int L = 0;
        int R = arr.length -1;

        if (arr[0] < arr[1]) {
            return arr[0];
        }

        if (arr[arr.length -2] < arr[arr.length - 1]) {
            return arr[arr.length - 1];
        }

        // 0 1 2
        while (L < R) {
            int mid = L + ((R-L) >> 1);
            if (arr[mid] > arr[mid -1 ]) {
                R = mid - 1;

            } else if (arr[mid] > arr[mid +1 ]) {
                L = mid -1;
            } else {
                return arr[mid];
            }

        }

        return arr[L];

    }
}
