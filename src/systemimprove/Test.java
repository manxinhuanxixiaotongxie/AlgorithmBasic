package systemimprove;

import java.util.Arrays;

public class Test {

    public void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr,0,arr.length-1);
    }

    public void process(int[] arr,int L,int R) {
        if (L==R) {
            return;
        }
        int mid = (L+R) >> 1;
        process(arr,L,mid);
        process(arr,mid+1,R);
        merge(arr,L,mid,R);
    }

    /**
     * 小和问题  在归并排序的过程中，将左边比他小的数转化成右边比他大的数
     * 逆序对问题
     * @param arr
     * @param L
     * @param mid
     * @param R
     */
    public void  merge(int[] arr,int L,int mid,int R) {
        int[] help = new int[R-L+1];
        int i = 0;
        int p1 = L;
        int p2 = mid+1;
        while (p1<=mid && p2<=R) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1<=mid) {
            help[i++] = arr[p1++];
        }
        while (p2<=R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L+i] = help[i];
        }
    }

    public int[] generateRandomArray(int maxSize,int maxValue) {
        int[] arr = new int[(int) ((maxSize+1)*Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue+1)*Math.random()) - (int) (maxValue*Math.random());
        }
        return arr;
    }

    public int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
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
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Test test = new Test();
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = test.generateRandomArray(maxSize, maxValue);
            int[] arr2 = test.copyArray(arr1);
            Arrays.sort(arr1);
            test.mergeSort(arr2);
            if (!test.isEqual(arr1, arr2)) {
                System.out.println("Oops!");
                test.printArray(arr1);
                test.printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
    }

    public int countOfRange(int[] arr) {
        if (arr == null || arr.length ==0) {
            return 0;
        }
        return process2(arr,0,arr.length-1);
    }

    public int process2(int[] arr,int L,int R) {
        if (L == R) {
            return 0;
        }
        int mid = (L+R) >> 1;
        return process2(arr,L,mid) + process2(arr,mid+1,R) + merge2(arr,L,mid,R);
    }

    public int merge2(int[] arr,int L,int mid,int R) {
        int[] help = new int[R-L+1];
        int i = 0;
        int p1 = L;
        int p2 = mid+1;
        int ans = 0;
        while (p1<=mid && p2<=R) {
            ans += arr[p1] < arr[p2] ? (R-p2+1)*arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1<=mid) {
            help[i++] = arr[p1++];
        }
        while (p2<=R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L+i] = help[i];
        }
        return ans;
    }
}
