package systemreview.code01;

import java.util.Arrays;

public class Code02_InsertSort {
    /**
     * 插入排序
     * 假设当前来到了i位置 认定前面0-1位置是有序的
     * <p>
     * 插入排序的过程：
     * 从0位置开始一直到arr.length-1位置
     * 本次排序实现的结果是从小到大的排序
     */

    public void insertSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int index = 0;
        while (index < arr.length) {
            int temp = index;
            while (temp > 0 && arr[temp] < arr[temp - 1]) {
                swap(arr, temp, temp - 1);
                temp--;
            }
            index++;
        }
    }

    public int[] generateArr(int maxValue, int maxLength) {
        int[] res = new int[(int) (Math.random() * maxLength + 1)];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * maxValue + 1) - (int) (Math.random() * maxValue + 1);
        }
        return res;
    }

    public boolean isEuqal(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1 == null || arr2 == null) {
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

    public int[] copyArr(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        Code02_InsertSort code02_insertSort = new Code02_InsertSort();
        int testTime = 500000;
        int maxValue = 100;
        int maxLength = 100;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = code02_insertSort.generateArr(maxValue, maxLength);
            int[] arr2 = code02_insertSort.copyArr(arr1);
            code02_insertSort.insertSort(arr1);
            Arrays.sort(arr2);
            if (!code02_insertSort.isEuqal(arr1, arr2)) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("finish!");
    }
}
