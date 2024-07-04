package systemreview.code04;

/**
 * 在一个数组中，
 * 对于每个数num，求有多少个后面的数 * 2 依然<num，求总个数
 * 比如：[3,1,7,0,2]
 * 3的后面有：1，0
 * 1的后面有：0
 * 7的后面有：0，2
 * 0的后面没有
 * 2的后面没有
 * 所以总共有5个
 */
public class Code02_LessThanTwice {

    public int lessTahnTwice(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j] * 2) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public int lessTahnTwice2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        // 使用归并排序 使用从大到小排序
        return process(arr, 0, arr.length - 1);

    }

    private int process(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
//        return process(arr, L, mid) + process(arr, mid + 1, R) + merge1(arr, L, mid, R);
        return process(arr, L, mid) + process(arr, mid + 1, R) + merge2(arr, L, mid, R);
    }

    /**
     * 此方法提供的是从小到大的实现
     *
     * @param arr
     * @param L
     * @param mid
     * @param R
     * @return
     */
    private int merge1(int[] arr, int L, int mid, int R) {
        if (L == R) {
            return 0;
        }
        int[] help = new int[R - L + 1];
        int leftIndex = L;
        int rightIndex = mid + 1;
        int ans = 0;

        /**
         * 进行转化 现在是从小大
         * 如果发现一个位置是比右边的的位置大两倍的话 那么从leftIndexda-mid位置就产生了mid - leftIndex + 1个数符合条件
         * 此时 因为rightIndex已经参与了结算
         */
        while (leftIndex <= mid && rightIndex <= R) {
            // 从小到大 如果左边的位置比右边的大两倍的话 总共产生了rightIndex - mid个数
            if (arr[leftIndex] > arr[rightIndex] * 2) {
                ans += mid - leftIndex + 1;
                rightIndex++;
            } else {
                leftIndex++;
            }
        }


        // 现在要计算的问题
        int index = 0;
        leftIndex = L;
        rightIndex = mid + 1;
        while (leftIndex <= mid && rightIndex <= R) {
            if (arr[leftIndex] < arr[rightIndex]) {
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

    /**
     * 此方法提供从大到小的实现
     *
     * @param arr
     * @param L
     * @param mid
     * @param R
     * @return
     */
    private int merge2(int[] arr, int L, int mid, int R) {
        if (L == R) {
            return 0;
        }
        int[] help = new int[R - L + 1];
        int leftIndex = L;
        int rightIndex = mid + 1;
        int ans = 0;

        /**
         * 进行转化 现在是大到小
         * 如果发现了一个leftIndex比rightIndex大两倍的话
         * 那么用leftIndex作为基准比较的话 在righIndex到R之间会有R - rightIndex + 1个数符合条件
         */
        while (leftIndex <= mid && rightIndex <= R) {
            // 从大到小
            if (arr[leftIndex] > arr[rightIndex] * 2) {
                ans += R - rightIndex + 1;
                leftIndex++;
            } else {
                rightIndex++;
            }
        }


        // 现在要计算的问题
        int index = 0;
        leftIndex = L;
        rightIndex = mid + 1;
        while (leftIndex <= mid && rightIndex <= R) {
            if (arr[leftIndex] > arr[rightIndex]) {
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
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue) * Math.random());
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
        Code02_LessThanTwice code02_lessThanTwice = new Code02_LessThanTwice();
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        for (int i = 0; i < testTime; i++) {
            int[] arr = code02_lessThanTwice.generateRandomArray(maxSize, maxValue);
            int[] arr1 = code02_lessThanTwice.copyArray(arr);
            int[] arr2 = code02_lessThanTwice.copyArray(arr);
            int ans1 = code02_lessThanTwice.lessTahnTwice(arr1);
            int ans2 = code02_lessThanTwice.lessTahnTwice2(arr2);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }

    }
}
