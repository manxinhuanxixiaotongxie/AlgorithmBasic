package systemimprove.code05;

import java.util.Arrays;

public class Code03_FastSort {

    public void sort01(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public void sort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    public void process(int[] arr,int L,int R) {
        if (L >= R) {
            return;
        }
        // 随机快排
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process(arr, L, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, R);
    }

    public void process2(int[] arr,int L,int R) {
        if (L >= R) {
            return;
        }
        int[] equalArea = netherlandsFlag2(arr, L, R);
        process(arr, L, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, R);
    }

    /**
     * 3 6 4 3 5
     * 0 1 2 3 4
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public int[] netherlandsFlag(int[] arr,int L,int R) {

        int leftIndex = L-1;
        int rightIndex = R;
        int index = L;
        while (index < rightIndex) {
            if (arr[index] < arr[R]) {
                swap(arr, ++leftIndex, index++);
            } else if (arr[index] > arr[R]) {
                swap(arr, --rightIndex, index);
            } else {
                index++;
            }
        }
        swap(arr, rightIndex, R);
        return new int[]{leftIndex + 1, rightIndex};
    }

    /**
     * 3 6 4 3 5
     * 0 1 2 3 4
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public int[] netherlandsFlag2(int[] arr,int L,int R) {

        int leftIndex = L-1;
        int rightIndex = R;
        int index = L;
        while (index < rightIndex) {
            if (arr[index] < arr[R]) {
                swap(arr, ++leftIndex, index++);
            } else if (arr[index] > arr[R]) {
                swap(arr, --rightIndex, index);
            } else {
                index++;
            }
        }
        swap(arr, rightIndex, R);
        return new int[]{leftIndex + 1, rightIndex};
    }

    public void swap(int[] arr,int l,int r) {
        int swap = arr[l];
        arr[l] = arr[r];
        arr[r] = swap;
    }

    public int[] generateRandomArray(int maxSize,int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0;i < arr.length;i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue) * Math.random());
        }
        return arr;
    }

    public int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] ans = new int[arr.length];
        for (int i = 0;i < arr.length;i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0;i < arr.length;i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    public boolean isEqual(int[] arr1,int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }

        if (arr1 == null && arr2 == null) {
            return true;
        }

        if (arr1.length != arr2.length) {
            return false;
        }

        for (int i = 0;i < arr1.length;i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        Code03_FastSort fastSort = new Code03_FastSort();
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        for (int i = 0;i < testTime;i++) {
            int[] arr1 = fastSort.generateRandomArray(maxSize, maxValue);
            int[] arr2 = fastSort.copyArray(arr1);
            int[] arr3 = fastSort.copyArray(arr1);
            fastSort.sort01(arr1);
            fastSort.comparator(arr2);
            fastSort.sort2(arr3);
            if (!fastSort.isEqual(arr1, arr2) || !fastSort.isEqual(arr2, arr3)) {
                System.out.println("Oops!");
                fastSort.printArray(arr1);
                fastSort.printArray(arr2);
                break;
            }
        }
    }
}
