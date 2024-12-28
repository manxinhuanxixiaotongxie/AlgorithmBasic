package systemimprove.code05;

import java.util.Arrays;
import java.util.Stack;

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

    // 快排非递归版本需要的辅助类
    // 要处理的是什么范围上的排序
    public static class Op {
        public int l;
        public int r;

        public Op(int left, int right) {
            l = left;
            r = right;
        }
    }

    // 快排3.0 非递归版本 用栈来执行
    public void sort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        swap(arr, (int) (Math.random() * N), N - 1);
        int[] equalArea = netherlandsFlag(arr, 0, N - 1);
        int el = equalArea[0];
        int er = equalArea[1];
        Stack<Op> stack = new Stack<>();
        stack.push(new Op(0, el - 1));
        stack.push(new Op(er + 1, N - 1));
        while (!stack.isEmpty()) {
            Op op = stack.pop(); // op.l ... op.r
            if (op.l < op.r) {
                swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
                equalArea = netherlandsFlag(arr, op.l, op.r);
                el = equalArea[0];
                er = equalArea[1];
                stack.push(new Op(op.l, el - 1));
                stack.push(new Op(er + 1, op.r));
            }
        }
    }

    /**
     * 随机快排  递归版本
     * 经典快速排序的实现
     * 在L-R范围上随机选择一个数作为基准值
     *
     * @param arr
     * @param L
     * @param R
     */
    public void process(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // 随机快排
        int[] equalArea = netherlandsFlag(arr, L, R);
        process(arr, L, equalArea[0] - 1);
        process(arr, equalArea[1] + 1, R);
    }

    /**
     * 使用最右侧的数作为基准值进行比较
     * 存在最差情况
     * 当数组是有序的时候 会导致时间复杂度为O(N^2)
     *
     * @param arr
     * @param L
     * @param R
     */
    public void process2(int[] arr, int L, int R) {
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
    public int[] netherlandsFlag(int[] arr, int L, int R) {
        swap(arr, L + (int) (Math.random() * (R - L + 1)), R);

        int leftIndex = L - 1;
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
    public int[] netherlandsFlag2(int[] arr, int L, int R) {

        int leftIndex = L - 1;
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

    public void swap(int[] arr, int l, int r) {
        int swap = arr[l];
        arr[l] = arr[r];
        arr[r] = swap;
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
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
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

    public void comparator(int[] arr) {
        Arrays.sort(arr);
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
        Code03_FastSort fastSort = new Code03_FastSort();
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.printf("测试开始\n");
        for (int i = 0; i < testTime; i++) {
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
        System.out.printf("测试结束\n");
    }
}
