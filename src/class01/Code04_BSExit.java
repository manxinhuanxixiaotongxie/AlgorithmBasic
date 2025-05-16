package class01;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-06-09 20:31
 */

import java.util.Arrays;

/**
 * 时间复杂度
 * <p>
 * 使用二分法在一个有序数组中，找某个数是否存在
 * 要求时间复杂度 O(logN)
 */
public class Code04_BSExit {

    public static void main(String[] args) {


        int maxSize = 15;
        int maxValue = 15;

        for (int i = 0; i < 10; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (testExit(arr, value) != findNumberExit(arr, value)) {
                System.out.println("test fail");
            }
            System.out.println("test pass");

        }


    }

    private static boolean testExit(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return true;
            }
        }
        return false;
    }


    /**
     * 对数组进行拆分  拆分方式  R-L >> 1
     * <p>
     * 比如   现在有一个数组
     * int[] arr = new int[] {1,2,3,4,5,6,7,8,9}
     * 假设现在要找出来4是否在数组中
     * 因为数组有序 我们直接判断中位数与给定数值的大小
     * <p>
     * 1.先从整个数组判断
     * 如果mid的数组的值比给定的值小的话 我们将L = mid 即从中位数向更大的方向查找是否有满足条件的数据
     * 2.如果mid的数组的值比给定的要大的话，说明满足条件的在mid较小的数值方向R = mid
     * <p>
     * 3.必须要考虑到数组的极端情况 只有一个元素的话  L  == R == mid
     * 4.只有两个元素的话  L！= R == mid
     *
     * @param arr
     * @param num
     * @return
     */
    private static boolean findNumberExit(int[] arr, int num) {

        if (arr == null || arr.length == 0) {
            return false;
        }
        int L = 0;
        int R = arr.length - 1;
//        int mid = L + (R-L) >> 1;
        // 只有两个个元素不满足
//        while (mid != L) {
        while (L < R) {
            // 必须要确保R比L大
            int mid = L + ((R - L) >> 1);
            if (arr[mid] > num) {
                // 这样写会死循环
//                R= mid;
                R = mid - 1;
            } else if (arr[mid] < num) {
//                L= mid;
                L = mid + 1;
            } else if (arr[mid] == num) {
                return true;
            }
        }
        // 只有一个元素不满足
//        return false;

        return arr[L] == num;
    }

    private static int[] generateRandomArray(int maxSize, int maxValue) {

//        Math.random()   [0.1)
        // Math.random()
        //
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) Math.random();
        }

        return arr;
    }


}
