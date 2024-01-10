package systemimprove.code04;

/**
 * 数组的小和问题
 */
public class Code01_SmallSum {

    /**
     * 以下假设 归并排序都是从小到大进行排序
     */
    /**
     * 将这个问题转化一下
     * 左边比他小的转化成右边比他大的
     */
    /**
     * 在归并排序的过程中，将左边比他小的数转化成右边比他大的数
     * 总共产生多少个这样的数呢？R-rightIndex +1个
     * 如果相等的话，先拷贝右边的数，为什么要先拷贝右边的数呢？
     * 如果先拷贝左边的数，在拷贝的过程中，会将当前的leftIndex位置数丢失
     * 举个例子：
     * 2 2 | 2 3
     * 先拷贝左边的2 那么第一个位置2 与最后一个位置的3组成的小和就丢失了
     */

    public int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    public int smallSum2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int smallSum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                smallSum += arr[i] < arr[j] ? arr[i] : 0;
            }
        }
        return smallSum;
    }


    public int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        /**
         * 解释一下这里的递归
         * 1.先求左边的小和
         * 2.再求右边的小和
         * 3.再求左右merge的小和
         * 4.将左边的小和 + 右边的小和 + merge的小和
         */
        return process(arr, L, mid) + process(arr, mid + 1, R) + merge(arr, L, mid, R);

    }

    public int merge(int[] arr, int L, int mid, int R) {
        int[] help = new int[R - L + 1];
        int helpIndex = 0;
        int leftIndex = L;
        int rightIndex = mid + 1;
        int smallSum = 0;
        while (leftIndex <= mid && rightIndex <= R) {
            smallSum += arr[leftIndex] < arr[rightIndex] ? (R - rightIndex + 1) * arr[leftIndex] : 0;
            help[helpIndex++] = arr[leftIndex] < arr[rightIndex] ? arr[leftIndex++] : arr[rightIndex++];
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
        return smallSum;

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

    public static void main(String[] args) {
        Code01_SmallSum smallSum = new Code01_SmallSum();
        int testTime = 500000;
        int maxLength = 100;
        int maxValue = 100;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = smallSum.generateArray(maxLength, maxValue);
            int[] arr2 = smallSum.copyArray(arr1);
            if (smallSum.smallSum(arr1) != smallSum.smallSum2(arr2)) {
                System.out.printf("Oops!");
            }
        }

    }
}
