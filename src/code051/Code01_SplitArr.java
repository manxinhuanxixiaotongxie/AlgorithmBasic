/**
 * @desc:
 * @author: Scurry
 * @date: 2022/6/28 22:16
 */

package code051;

/**
 * @desc:
 * @author: Scurry
 * @date: 2022/6/28 22:16
 */

/**
 * 需求：给定一个数组，给定一个数
 * 将数组中小于等于给定的数放在数组左边 大于等于数组的数放在数组的右边
 */
public class Code01_SplitArr {

    public static void main(String[] args) {
        int[] a = new int[] {5,4,3,2,1};
        int num = 3;
        spilitArr(a,num);
        for (int i = 0;i<a.length;i++) {
            System.out.print(a[i]+"    ");
        }
    }

    /**
     * 思路：
     * 1.假设给定一个范围
     * 1）小于num范围右扩 index++
     * 2）大于num 范围不动 index++
     * 3）下一个数小于数组的数  交换 范围右扩
     * @param arr
     * @param num
     */
    public static void spilitArr(int[] arr,int num){

        int left = -1;
        int index = 0;

        for (int i =0;i<arr.length;i++) {

            if (arr[i] <= num) {
                swap(arr,left+1,i);
                // 范围右扩
                left++;
            }

//            if (arr[i] <=num) {
//                left++;
//                ++i;
//
//            }else {
//
//                if (arr[++i] <=num) {
//                    swap(arr,left+1,i);
//                    left ++;
//                    i++;
//                }
//
//            }
        }
    }

    public static void swap(int[] arr,int a,int b){
        int temp = arr[b];
        arr[b] = arr[a];
        arr[a] = temp;

    }
}
