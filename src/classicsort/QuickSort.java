package classicsort;

import static classicsort.InsertSort.insertSort1;

/**
 * @Description经典排序算法：快速排序
 * @Author Scurry
 * @Date 2023-05-09 15:54
 */
public class QuickSort {

    public static void quidlSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);

    }

    public static void process1(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int i = netherlandFlag(arr, L, R);
        process1(arr, L, i - 1);
        process1(arr, i + 1, R);

    }

    public static void quidlSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);

    }

    public static void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] leftRight = netherlandFlag2(arr, L, R);
        process2(arr, L, leftRight[0]);
        process2(arr, leftRight[1] + 1, R);

    }

    public static void quidlSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);

    }

    public static void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] leftRight = netherlandFlag3(arr, L, R);
        process3(arr, L, leftRight[0]);
        process3(arr, leftRight[1] + 1, R);

    }

    /**
     * 荷兰国旗问题
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int netherlandFlag(int[] arr, int L, int R) {
        int left = L - 1;
        int right = R;
        int num = arr[right];
        int index = L;
        while (index < right) {
            if (arr[index] == num) {
                index++;
            } else if (arr[index] < num) {
                swap(arr, index++, ++left);
            } else {
                swap(arr, index, --right);
            }
        }

        swap(arr, R, right);

        return right;

    }

    /**
     * 荷兰国旗问题2
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int[] netherlandFlag2(int[] arr, int L, int R) {
        int left = L - 1;
        int right = R;
        int num = arr[right];
        int index = L;
        while (index < right) {
            if (arr[index] == num) {
                index++;
            } else if (arr[index] < num) {
                swap(arr, index++, ++left);
            } else {
                swap(arr, index, --right);
            }
        }

        swap(arr, R, right);

        return new int[]{left, right};

    }

    /**
     * 荷兰国旗问题3
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static int[] netherlandFlag3(int[] arr, int L, int R) {
        int randomIndex = (int) (Math.random() * (R - L + 1));
        swap(arr, L + randomIndex, R);
        int left = L - 1;
        int right = R;
        int num = arr[right];
        int index = L;
        while (index < right) {
            if (arr[index] == num) {
                index++;
            } else if (arr[index] < num) {
                swap(arr, index++, ++left);
            } else {
                swap(arr, index, --right);
            }
        }

        swap(arr, R, right);

        return new int[]{left, right};

    }

    public static void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
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

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            int[] arr3 = copyArray(arr1);
            int[] arr4 = copyArray(arr1);
            quidlSort1(arr1);
            insertSort1(arr2);
            quidlSort2(arr3);
            quidlSort3(arr4);
            if (!isEqual(arr1, arr2) || !isEqual(arr2, arr3) || !isEqual(arr3, arr4)) {
                System.out.println("出错了！");
                printArray(arr1);
                printArray(arr2);
                printArray(arr3);
                printArray(arr4);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
