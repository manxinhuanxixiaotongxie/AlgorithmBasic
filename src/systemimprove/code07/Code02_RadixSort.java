package systemimprove.code07;

import java.util.Arrays;

/**
 * 计数排序：
 * 1. 找到数组中的最大值max
 * 2. 创建一个长度为max+1的数组count
 * 3. 遍历数组，将每个元素的值作为count的下标，count的值为该元素出现的次数
 * 4. 遍历count，将count中的值按照下标顺序输出
 * <p>
 *
 * 基数排序：
 *
 */
public class Code02_RadixSort {

    public void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int maxBit = getMaxBit(arr);

        // 经典的基数排序的过程
        // 准备10个桶编号0~9
        // 从左到右，第一位，第二位，第三位，... 按照每一位的值，放入到对应的桶里去
        // 从桶里依次倒出来
        // 一定是从低位到高位依次倒出来

        for (int i = 1;i <= maxBit;i++) {
            int[] sum = new int[10];

            // 从右到左处理
            // 假设我们真的准备了10个桶的话，那么index位置可以做一个转换
            // 处理成前缀和数组 那么
            for (int j = arr.length - 1;j >= 0;j--) {
                int num = getEndNum(arr[j],i);
                sum[num]++;
            }
            // 处理成前缀和数组
            for (int j = 1;j < sum.length;j++) {
                sum[j] = sum[j] + sum[j - 1];
            }
            // 从后往前遍历
            int[] help = new int[arr.length];
            for (int j = arr.length - 1;j >= 0;j--) {
                int num = getEndNum(arr[j],i);
                help[--sum[num]] = arr[j];
            }

            for (int j = 0;j < arr.length;j++) {
                arr[j] = help[j];
            }

        }

    }

    // 生成的正数数组
    public int[] generateArr(int size,int maxValue) {
        int[] arr = new int[size];
        for (int i = 0;i < size;i++) {
            arr[i] = (int) (Math.random() * maxValue);
        }
        return arr;

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

        for (int i = 0;i < arr1.length;i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;

    }

    public void printArr(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        for (int i = 0;i < arr.length;i++) {
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

    public int[] copyArr(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        int[] help = new int[arr.length];
        for (int i = 0;i < arr.length;i++) {
            help[i] = arr[i];
        }
        return help;
    }



    // 获取位数上的数值
    // 输入 15 1 返回的是个各位数   15 2 返回十位数
    public int getEndNum(int num,int radix) {
        // 给定一个数，求出这个数从个位数开始，第radix位上的数值
        return (num / ((int) Math.pow(10,radix - 1))) % 10;

    }



    public int getMaxBit(int[] arr) {
        int max = 0;
        for (int i = 0;i < arr.length;i++) {
            int num = arr[i];
            int count = 0;
            while (num != 0) {
                count++;
                num /= 10;
            }
            max = Math.max(max,count);

        }
        return max;
    }


    public static void main(String[] args) {
        Code02_RadixSort test = new Code02_RadixSort();
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 10000;
        for (int i = 0;i < testTime;i++) {
            int[] arr1 = test.generateArr(maxSize,maxValue);
            int[] arr2 = test.copyArr(arr1);
            test.radixSort(arr1);
            test.comparator(arr2);
            if (!test.isEqual(arr1,arr2)) {
                System.out.println("出错了");
                test.printArr(arr1);
                test.printArr(arr2);
                break;
            }
        }
    }
}
