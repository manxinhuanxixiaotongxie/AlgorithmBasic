package systemreview.code04;

/**
 * 在一个数组中，一个数左边比它小的数的总和，叫数的小和，所有数的小和累加起来，叫数组小和。求数组小和。
 * 例子： [1,3,4,2,5]
 * 1左边比1小的数：没有
 * 3左边比3小的数：1
 * 4左边比4小的数：1、3
 * 2左边比2小的数：1
 * 5左边比5小的数：1、3、4、 2
 * 所以数组的小和为1+1+3+1+1+3+4+2=16
 */
public class Code01_SmallSum {
    /**
     * 使用归并排序的思想
     * 既然要求的是左边有多少数比他大 转化一下 其实就是是在i位置的后面 有多少数比他大
     */

    public int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    sum += arr[i];
                }
            }
        }
        return sum;
    }

    public int smallSum2(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        return process(arr, 0, arr.length - 1);
    }

    private int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid) + process(arr, mid + 1, R) + merge(arr, mid, L, R);
    }

    private int merge(int[] arr, int mid, int L, int R) {
        if (L == R) {
            return 0;
        }
        int[] help = new int[R - L + 1];
        int leftIndex = L;
        int rightIndex = mid + 1;
        int index = 0;
        int ans = 0;
        while (leftIndex <= mid && rightIndex <= R) {
            // 如果arr[leftIndex] < arr[rightIndex] 那么右边以leftIndex为底的产生的小数和是arr[leftIndex]*(R-rightIndex+1)
            // 相等拷贝左右都可以
            if (arr[leftIndex] < arr[rightIndex]) {
                ans += arr[leftIndex] * (R - rightIndex + 1);
                help[index++] = arr[leftIndex++];
            } else {
                help[index++] = arr[rightIndex++];
            }
        }
        while (leftIndex <= mid) {
            help[index++] = arr[leftIndex++];
        }
        while (rightIndex <= R) {
            help[index++] = arr[rightIndex++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }

    public int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
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

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        Code01_SmallSum smallSum = new Code01_SmallSum();
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = smallSum.generateRandomArray(maxSize, maxValue);
            int[] arr2 = smallSum.copyArray(arr1);
            if (smallSum.smallSum(arr1) != smallSum.smallSum2(arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Oops!");
    }


}
