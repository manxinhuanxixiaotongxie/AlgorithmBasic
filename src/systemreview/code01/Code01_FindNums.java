package systemreview.code01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Code01_FindNums {

    /**
     * 在一个有序数组中
     * 找到>=某个数最左侧的位置
     */
    public int findIndex(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int N = arr.length;
        // 采用二分的方式进行查找
        // 当中点的位置的值比k大的时候 r位置向左进行偏移
        int L = 0;
        int R = N - 1;
        int ans = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= k) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 在一个有序数组中 查找某个数是否存在
     * 举个例子：
     * 数组：1 2 3 4
     * 在这个数组中是否存在3
     *
     * @return
     */
    public boolean findExists(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int L = 0;
        int R = arr.length - 1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] == k) {
                return true;
            } else if (arr[mid] > k) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return false;
    }

    // for test
    public int right(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= k) {
                return i;
            }
        }
        return -1;
    }

    public int[] gengerateArr(int maxValue, int maxLength) {
        int[] ans = new int[(int) (Math.random() * maxLength + 1)];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * maxValue + 1) - (int) (Math.random() * maxValue + 1);
        }
        Arrays.sort(ans);
        return ans;
    }

    /**
     * 使用二分进行局部最小值问题的查找
     * <p>
     * 局部最小值 左边的数当前的数大 右边的数比当前的数大 那么就可以认为当前数就是这个数组的某一个局部最小值
     * <p>
     * 使用二分的方式进行查找
     * 为什么可以使用二分的方式进行查找呢？
     * 1.如果0位置的数比1位置的数小 那么可以认为0位置的数就是局部最小值
     * 2.如果arr.length-1的数比arr.length-2的数小 那么可以认为arr.length-1的数就是局部最小值
     * 3，上述两种情况都不满足的情况下 因为数组是无序的  那么必然L-R范围之间一定存在某个局部最小值
     *
     * @param arr
     * @return
     */
    public int findPartNum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr.length < 2) {
            return arr[0];
        }
        if (arr[0] < arr[1]) {
            return arr[0];
        }
        if (arr[arr.length - 1] < arr[arr.length - 2]) {
            return arr[arr.length - 1];
        }
        int L = 0;
        int R = arr.length - 1;

        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid - 1] < arr[mid]) {
                R = mid - 1;
            } else if (arr[mid + 1] < arr[mid]) {
                L = mid + 1;
            } else {
                return arr[mid];
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        int maxValue = 20;
        int maxLength = 20;
        int testTimes = 100000;
        int k = (int) (Math.random() * maxValue + 1 - Math.random() * maxValue + 1);
        Code01_FindNums code01 = new Code01_FindNums();
        for (int i = 0; i < testTimes; i++) {
            int[] arr = code01.gengerateArr(maxValue, maxLength);
            int ans1 = code01.findIndex(arr, k);
            int ans2 = code01.right(arr, k);
            if (ans1 != ans2) {
                System.out.println(" find index Oops!");
            }

            boolean exists = code01.findExists(arr, k);
            List<Integer> collect = Arrays.stream(arr).boxed().collect(Collectors.toList());
            if (collect.contains(k) != exists) {
                System.out.println(" find exist Oops!");
            }

        }
        System.out.println("finished!");
    }

}
