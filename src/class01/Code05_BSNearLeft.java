package class01;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-06-13 20:12
 */

/**
 * 在一个有序数组中 找>= 某个数最左侧的位置
 */

import java.util.Arrays;

/**
 * int[] arr = new int[]{ 1 2 3 4 5 6 6 6 7 7 7 7 88 89 89 89  99 99 99 }
 * 找到6 最左侧的位置   index为5
 * <p>
 * 最简单的方式就是遍历数组 查找元素第一次出现的位置  return  时间复杂度比较高  0(N)
 * <p>
 * <p>
 * 假设这时要求时间复杂度为O(logN)?
 * 使用二分
 */
public class Code05_BSNearLeft {

    public static void main(String[] args) {

//        int [] testArr = new int[] {-12,-11,  -3  ,1  ,3  ,6  ,9};
//
//        findLeftestIndex(testArr,3);

        int maxSize = 10;
        int maxValue = 15;
        int testNum = 1000;

        for (int i = 0; i < testNum; i++) {
            int[] arr = generateArr(maxSize, maxValue);

            // 有序
            Arrays.sort(arr);

            int value = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue) * Math.random());

            if (test(arr, value) != findLeftestIndex(arr, value)) {
                System.out.println("test fail");
                printArr(arr);
                System.out.println("test返回" + test(arr, value));
                System.out.println("find返回" + findLeftestIndex(arr, value));
                System.out.println(value);
                break;
            }

            System.out.println("test pass");

        }
    }

    // 打印数组
    private static void printArr(int[] arr) {

        if (arr == null) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "  ");
        }
        System.out.println();

    }

    private static int[] generateArr(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue) * Math.random());
        }

        return arr;
    }

    private static int test(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                return i;
            }
        }
        return -1;
    }


    private static int findLeftestIndex(int[] arr, int num) {

        if (arr == null || arr.length == 0) {
            return -1;
        }

        int L = 0;
        int R = arr.length - 1;

        // 最小位置下标
        // 有可能压根就不存在
//        int index = 0;
        int index = -1;
//        while ( L < R) {
        // 有可能会存在L R指向同一个位置
        while (L <= R) {

            int mid = L + ((R - L) >> 1);

            /**
             * 假设中间数值比num大  意味着 最左侧的数在L ---> mid中
             *
             */
            if (arr[mid] > num) {
//                index = mid;
                R = mid - 1;
            } else if (arr[mid] == num) {
                index = mid;
                R = mid - 1;
            } else {
//                index = mid;
                L = mid + 1;
            }

//            return index;

        }

        return index;

    }
}
