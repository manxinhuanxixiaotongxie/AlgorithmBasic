package systemimprove.code03;

import java.util.Arrays;

public class Code03_MergeSort {


    public void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int L = 0;
        int R = arr.length - 1;
        process(arr, L, R);
    }

    public void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    public void merge(int[] arr, int L, int mid, int R) {
        int leftIndex = L;
        int rightIndex = mid + 1;
        int[] help = new int[R - L + 1];
        int helpIndex = 0;
        while (leftIndex <= mid && rightIndex <= R) {
            if (arr[leftIndex] <= arr[rightIndex]) {
                help[helpIndex++] = arr[leftIndex++];
            } else {
                help[helpIndex++] = arr[rightIndex++];
            }
        }

        while (leftIndex <= mid) {
            help[helpIndex++] = arr[leftIndex++];
        }

        while (rightIndex <= R) {
            help[helpIndex++] = arr[rightIndex++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }

    }


    public int[] generateArray(int maxLength, int maxValue) {
        int[] arr = new int[(int) ((maxLength + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue) * Math.random());
        }
        return arr;
    }

    public int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] copyArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copyArr[i] = arr[i];
        }
        return copyArr;
    }

    public boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
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

    public void printArray(int[] arr) {
        if (arr == null) {
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Code03_MergeSort mergeSort = new Code03_MergeSort();
        int testTime = 500000;
        int maxLength = 100;
        int maxValue = 100;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = mergeSort.generateArray(maxLength, maxValue);
            int[] arr2 = mergeSort.copyArray(arr1);
            mergeSort.mergeSort(arr1);
            mergeSort.comparator(arr2);
            if (!mergeSort.isEqual(arr1, arr2)) {
                System.out.println("出错了");
                mergeSort.printArray(arr1);
                mergeSort.printArray(arr2);
                break;
            }
        }
    }

    private void comparator(int[] arr2) {
        Arrays.sort(arr2);
    }


}
