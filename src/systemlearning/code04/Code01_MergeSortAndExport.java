package systemlearning.code04;

import java.util.Arrays;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-09-18 11:13
 */
public class Code01_MergeSortAndExport {

    public int[] mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }

        process(arr, 0, arr.length - 1);
        return arr;
    }

    /**
     * @param arr
     * @param L
     * @param R
     */
    public void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);

        process(arr, L, mid);
        process(arr, mid + 1, R);

        merge(arr, L, R, mid);

    }

    public void merge(int[] arr, int L, int R, int mid) {


        int[] help = new int[R - L + 1];

        int p1 = L;
        int p2 = mid + 1;
        int index = 0;

        // 谁小放谁
        while (p1 <= mid && p2 <= R) {
            help[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= mid) {
            help[index++] = arr[p1++];
        }

        while (p2 <= R) {
            help[index++] = arr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }

    }

    public int[] generateArr(int maxValue, int maxLength) {
        int[] arr = new int[(int) ((maxLength + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue) * Math.random());
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

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }

        return true;
    }


    /**
     * 在一个数组中，一个数的左边比他小的数的总和，叫做这个数的小和，所有数的小和累加起来，叫做数组的小和，求数组的小和
     *
     * @param arr
     * @return
     */
    public int getMinSum1(int[] arr) {

        if (arr == null || arr.length < 2) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int j = i;
            while (j >= 0) {
                if (arr[j] < arr[i]) {
                    ans += arr[j];
                }
                j--;
            }
        }
        return ans;
    }

    /**
     * @param arr
     * @return
     */
    public int getMinSum2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return process2(arr, 0, arr.length - 1);
    }

    public int process2(int[] arr, int L, int R) {

        if (L == R) {
            return 0;
        }

        int mid = L + ((R - L) >> 1);
        return process2(arr, L, mid) + process2(arr, mid + 1, R) + merge2(arr, L, R, mid);
    }


    public int merge2(int[] arr, int L, int R, int mid) {
        if (L == R) {
            return 0;
        }
        int[] help = new int[R - L + 1];
        int index = 0;
        int p1 = L;
        int p2 = mid + 1;
        int ans = 0;

        while (p1 <= mid && p2 <= R) {
            // 总共产生了多少个小和
            // 归并排序的过程中，如果左边的数小于右边的数，这个数产生了多少小和？右边的数还剩下多少个就产生了多少个小和
            // 为什么可以使用归并排序的过程计算小数和？
            // 找到一个数前面有多少比他小   --》与找到改数后面有多少数比他大是一样的
            ans += arr[p1] < arr[p2] ? (R - p2 + 1) * arr[p1] : 0;
            help[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[index++] = arr[p1++];
        }
        while (p2 <= R) {
            help[index++] = arr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }


    public int getMinSum3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return process3(arr, 0, arr.length - 1);
    }

    public int process3(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return process3(arr, L, mid) + process3(arr, mid + 1, R) + merge3(arr, L, R, mid);
    }

    /**
     * 有多少逆序对
     *
     *
     * @param arr
     * @param L
     * @param R
     * @param mid
     * @return
     */
    public int merge3(int[] arr, int L, int R, int mid) {
        int[] help = new int[R - L + 1];

        int index = 0;
        int p1 = L;
        int p2 = mid +1;
        int ans = 0;
        // 逆序对 后面有多少数比当前数小
        while (p1 <= mid && p2 <= R) {
            // 由大到小进行排序
            ans += arr[p1] > arr[p2] ? (R-p2+1) : 0;
            help[index++] = arr[p1] < arr[p2] ? arr[p2++] : arr[p1++];
        }
        while (p1 <= mid) {
            help[index++] = arr[p1++];
        }
        while (p2 <= R) {
            help[index++] = arr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }

    public  int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    ans++;
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Code01_MergeSortAndExport code01_mergeSortAndExport = new Code01_MergeSortAndExport();
        int maxVlaue = 100;
        int maxLength = 6;
        int testTimes = 100000;
        int[] test = new int[] {45,68,20,25};
        System.out.println(code01_mergeSortAndExport.comparator(test));
        System.out.println(code01_mergeSortAndExport.getMinSum3(test));
        for (int i = 0; i < testTimes; i++) {
            int[] arr = code01_mergeSortAndExport.generateArr(maxVlaue, maxLength);
            int[] arr1 = Arrays.copyOf(arr, arr.length);
            int[] arr2 = Arrays.copyOf(arr, arr.length);

            code01_mergeSortAndExport.mergeSort(arr1);
            Arrays.sort(arr2);

            if (!code01_mergeSortAndExport.isEqual(arr1, arr2)) {
                System.out.println("merge sort oops!");
            }

            int[] arr3 = Arrays.copyOf(arr, arr.length);
            int[] arr4 = Arrays.copyOf(arr, arr.length);

            if (code01_mergeSortAndExport.getMinSum1(arr3) != code01_mergeSortAndExport.getMinSum2(arr4)) {
                System.out.println("min sum oops!");
            }

            int[] arr5 = Arrays.copyOf(arr, arr.length);
            int[] arr6 = Arrays.copyOf(arr, arr.length);
            if (code01_mergeSortAndExport.getMinSum3(arr5) != code01_mergeSortAndExport.comparator(arr6)) {
                System.out.println("nixu oops!");
            }
        }


    }


}
