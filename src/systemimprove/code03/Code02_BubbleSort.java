package systemimprove.code03;

import java.util.Arrays;

public class Code02_BubbleSort {

    /**
     * 冒泡排序
     * @param arr
     */
    public void sortForBubble(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }

            }
        }
    }

    /**
     * 选择排序
     * @param arr
     */
    public void sortForSelect(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }

    }

    /**
     * 插入排序
     * @param arr
     */
    public void sortForInsert(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            // 如果前面数比后面数大，就交换
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    /**
     * 插入排序2
     * @param arr
     */
    public void sortForInsert2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            // 如果前面数比后面数大，就交换
            while (i - 1 >= 0 && arr[i - 1] > arr[i]) {
                swap(arr, i - 1, i);
                i--;
            }
        }
    }

    /**
     * 生成的随机数组
     * @param maxLength
     * @param maxValue
     * @return
     */
    public int[] generate(int maxLength, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxLength)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;
    }

    /**
     * 交换数组中的两个数
     * @param arr
     * @param i
     * @param j
     */
    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 输出数组
     * @param arr
     */
    public void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 数组拷贝
     * @param arr
     * @return
     */
    public int[] copyArray(int[] arr) {
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }

    /**
     * 判断两个数组是否相等
     * @param arr1
     * @param arr2
     * @return
     */
    public boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1 == null && arr2 != null) {
            return false;
        }
        if (arr1 != null && arr2 == null) {
            return false;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int maxValue = 100;
        int testTimes = 1000000;
        int maxLength = 100;
        Code02_BubbleSort code02_bubbleSort = new Code02_BubbleSort();
        for (int i = 0; i < testTimes; i++) {
            int[] arr = code02_bubbleSort.generate(maxLength, maxValue);
            int[] arr1 = code02_bubbleSort.copyArray(arr);
            int[] arr2 = code02_bubbleSort.copyArray(arr);
            int[] arr3 = code02_bubbleSort.copyArray(arr);
            int[] arr4 = code02_bubbleSort.copyArray(arr);
            code02_bubbleSort.sortForBubble(arr1);
            Arrays.sort(arr2);
            code02_bubbleSort.sortForSelect(arr3);
            code02_bubbleSort.sortForInsert(arr4);
            if (!code02_bubbleSort.isEqual(arr1, arr2) || !code02_bubbleSort.isEqual(arr1, arr3) || !code02_bubbleSort.isEqual(arr1, arr4)) {
                code02_bubbleSort.print(arr);
                code02_bubbleSort.print(arr1);
                code02_bubbleSort.print(arr2);
                System.out.println("Oops!");
                break;
            }
        }
    }
}
