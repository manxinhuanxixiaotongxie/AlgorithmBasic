package systemreview.code01;

public class Code03_SelectSort {

    /**
     * 选择排序 从前到后进行比对
     * 实现从小到大的排序
     * 当发现前面一个后面的一个数小的时候 就进行交换
     */
    public void selectSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
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

    public static void main(String[] args) {
        Code03_SelectSort selectSort = new Code03_SelectSort();
        int testTime = 500000;
        int maxValue = 100;
        int maxLength = 100;
        for (int i = 0; i < testTime; i++) {
            int[] arr = selectSort.generateArr(maxValue, maxLength);
            int[] arr1 = selectSort.copyArr(arr);
            int[] arr2 = selectSort.copyArr(arr);
            selectSort.selectSort(arr1);
            selectSort.selectSort(arr2);
            if (!selectSort.isEuqal(arr1, arr2)) {
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("finish!");
    }
}
