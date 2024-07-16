package systemreview.code07;

import java.util.Arrays;

/**
 * 计数排序：
 * 准备十个桶 分别是下标0-9
 * 先根据个位数将数组放在不同的桶中
 * 然后从桶中依次取出
 * 再根据十位数将数组放在不同的桶中
 * 然后从桶中依次取出
 * 。。。
 * 进行这个过程之后  整个数组就是有序
 * <p>
 * 经典的计数排序只用了一个桶进行实现
 */
public class Code02_RadixSort {

    public void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int maxBit = getNumLength(max);
        for (int i = 0; i < maxBit; i++) {
            int[] count = new int[10];
            for (int j = arr.length - 1; j >= 0; j--) {
                int num = getEndNum(arr[j], i);
                count[num]++;
            }

            // 处理成前缀和数组
            for (int j = 1; j < count.length; j++) {
                count[j] = count[j] + count[j - 1];
            }

            int[] help = new int[arr.length];
            for (int j = arr.length - 1; j >= 0; j--) {
                int num = getEndNum(arr[j], i);
                help[--count[num]] = arr[j];
            }

            for (int j = 0; j < arr.length; j++) {
                arr[j] = help[j];
            }
        }
    }

    public int getEndNum(int num, int i) {
        return (num / (int) Math.pow(10, i)) % 10;
    }

    private int getNumLength(int num) {
        int ans = 0;
        while (num > 0) {
            ans++;
            num /= 10;
        }
        return ans;
    }

    public int[] generateArr(int maxValue, int maxLength) {
        int[] ans = new int[(int) (Math.random() * maxLength) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * maxValue);
        }
        return ans;
    }

    public void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public void comparator(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
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
        Code02_RadixSort radixSort = new Code02_RadixSort();
        int maxValue = 100;
        int maxLength = 3000;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = radixSort.generateArr(maxValue, maxLength);
            int[] arr1 = new int[arr.length];
            System.arraycopy(arr, 0, arr1, 0, arr.length);
            int[] arr2 = new int[arr.length];
            System.arraycopy(arr, 0, arr2, 0, arr.length);
            radixSort.radixSort(arr1);
            radixSort.comparator(arr2);
            if (!radixSort.isEqual(arr1, arr2)) {
                System.out.println("Oops!");
                radixSort.printArr(arr);
                radixSort.printArr(arr1);
                radixSort.printArr(arr2);
                break;
            }
        }
        System.out.println("Nice!");
    }
}
