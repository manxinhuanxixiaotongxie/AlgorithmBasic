package class01;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-06-14 14:37
 */

import java.util.Arrays;

/**
 * 在一个有序数组中 找<= 某个数最右侧的位置
 */
public class Code06_BSNearRight {

    public static void main(String[] args) {

        int maxSize = 10;
        int maxValue = 15;

        int testNum = 1000;


        for (int i = 0; i < testNum; i++) {
            int[] arr = generateArr(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) (((maxValue + 1)) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != findRightestIndex(arr, value)) {

                System.out.println("test fail");
                printArray(arr);

                System.out.println("test返回:" + test(arr, value));
                System.out.println("测试的值为:" + value);
                System.out.println("findRightestIndex返回:" + findRightestIndex(arr, value));
            }

            System.out.println("test pass");
        }

    }

    private static void printArray(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "   ");
        }

        System.out.println();

    }


    /**
     * 最右侧位置
     * 假设数组依然是 int [] testArr = new int[] {-12,-11,  -3  ,1  ,3,3,3,3,3,4,5  ,6  ,9};
     * 我们想找到3
     * 最简单粗暴
     * 遍历整个数组 只要数组的数第一次大于当前值 返回index
     * 时间复杂度高
     * <p>
     * 要求时间复杂度是O(log(N))
     * <p>
     * 二分
     *
     * @param arr
     * @param num
     * @return
     */
    private static int findRightestIndex(int[] arr, int num) {

        int L = 0;
        int R = arr.length - 1;

        int index = -1;

        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            //找<= 某个数最右侧的位置
            // 如果中间数比给定的数值大  那么最右侧的数在L-mid
            if (arr[mid] > num) {
                R = mid - 1;
            }
            //int [] testArr = new int[] {-12,-11,  -3  ,1  ,3,3,3,3,3,4,5  ,6  ,9};
            // 如果arr[mid]比给定的数小 说明最右侧的数在mid-R
            else if (arr[mid] < num) {
                L = mid + 1;
            } else if (arr[mid] == num) {
                index = mid;
                L = mid + 1;
            }
        }
        return index;

    }

    private static int test(int[] arr, int num) {

        int index = -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) {
                index = i;
                continue;
            }
        }

        return index;
    }

    private static int[] generateArr(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (((maxValue + 1)) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }
}
