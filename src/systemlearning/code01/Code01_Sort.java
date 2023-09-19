package systemlearning.code01;

import java.util.Arrays;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-09-14 18:51
 */
public class Code01_Sort {

    /**
     * 选择排序
     * 流程：
     * 1.在0-N-1选择一个数当做最小值
     * 2.在i+1-N-1选择一个数与最小值比较，如果比最小值小，就更新最小值
     * 3.进行交换
     *
     * @param arr
     * @return
     */
    public int[] selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
        return arr;
    }

    /**
     * 冒泡排序
     *
     * @param arr
     * @return
     */
    public int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j <= i - 1; j++) {
                if (arr[j] > arr[i]) {
                    swap(arr, j, i);
                }
            }
        }
        return arr;
    }

    /**
     * 插入排序
     *
     * @param arr
     * @return
     */
    public int[] insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }

        for (int i = 1; i < arr.length; i++) {
//            while (i-1>=0 && arr[i] < arr[i-1]) {
//                swap(arr,i,i-1);
//                i--;
//            }
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
        return arr;
    }

    // 对数器
    public int[] generateArr(int maxValue, int maxLength) {
        int[] arr = new int[(int) (Math.random() * maxLength) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    public boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 != null || arr1 != null && arr2 == null) {
            return false;
        }

        if (arr1 == null && arr2 == null) {
            return true;
        }

        if (arr1.length != arr2.length) {
            return false;
        }

        for (int i = 0; i < arr2.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }

        return true;

    }

    public static void main(String[] args) {
        int maxValue = 50;
        int maxLength = 10;
        int testTimes = 1000000;

        int[] testArr = new int[]{
                50, 10, 13, 20
        };
        new Code01_Sort().bubbleSort(testArr);

        for (int i = 0; i < testTimes; i++) {
            int[] arr = new Code01_Sort().generateArr(maxValue, maxLength);
            int[] arr1 = arr.clone();
            int[] arr2 = arr.clone();
            int[] arr3 = arr.clone();
            int[] arr4 = arr.clone();
            Arrays.sort(arr1);
            new Code01_Sort().selectSort(arr2);
            new Code01_Sort().bubbleSort(arr3);
            new Code01_Sort().insertSort(arr4);
            if (!new Code01_Sort().isEqual(arr1, arr2) || !new Code01_Sort().isEqual(arr1, arr3) || !new Code01_Sort().isEqual(arr1, arr4)) {
                System.out.println("Oops!");
                break;
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

    }
}
