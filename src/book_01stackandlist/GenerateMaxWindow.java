package book_01stackandlist;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-10-25 20:15
 */

import java.util.LinkedList;

/**
 * 长度为N的数组   长度为W的窗口
 * 输出一个新的数组 包含最大值
 * <p>
 * 要求时间复杂度O(N)
 */
public class GenerateMaxWindow {

    public int[] getMaxWindow(int[] arr, int w) {

        if (arr == null || arr.length == 0 || w < 1 || arr.length < w) {
            return null;
        }

        /**
         * 。。。。。。。。。。。。。。
         * 使用双端队列 窗口的最大值的更新问题
         *
         * 时间复杂度O（N）
         *
         * 思路:
         * 第一个数放进去队列里
         * 第二个数和第一个数比较，如果比第一个数大，就把第一个数弹出，把第二个数放进去
         * 第w个数和第w-1个数比较，如果比第w-1个数大，就把第w-1个数弹出，把第w个数放进去
         * 在这个过程中 窗口始终维护的是最大值或者最小值
         *如果第w个数比w-1小的话加到队列的尾巴
         *
         */
        // 最大值的更新结构
        LinkedList<Integer> qmax = new LinkedList<>();
        // 窗口的大小是w 数组的长度是N 那么总共产生n-w+1个窗口
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            // 队列的最后一位数比当前数小 从尾部弹出队列
            // 经过这步之后，队列一定是单调的
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.pollLast();
            }
            // 加入当前值
            qmax.addLast(i);
            // 什么时候从头弹出？相等说明队头的下标已过期
            // 如果被移出窗口的元素是当前窗口最大元素，则移出队头元素
            // 先前放入的数如果还存在于结构中，那么该数一定比后放入的数都大
            // 最大数的下标与窗口的下一个数相等，意味着最大数已经不在窗口中了
            // 最大值是这样的，如果一个窗口的最大值被确定 意味着这个最大值只能作为这个窗口的最大值   当最大值的下标加上一个窗口大小等于当前的下标时，说明这个最大值已经不在这个窗口里了

            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }
            // 5 4 3 2 1
            // 4 5 3 2 1
            // 0 1 2 3 4
            // w 3

            // 4 3 5 4 3 3 6 7
            // 数组的长度一定是i-w+1
            if (i >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }

        }
        return res;
    }

    public int[] right(int[] arr, int w) {

        if (arr == null || arr.length == 0 || w < 1 || arr.length < w) {
            return null;
        }
        int[] res = new int[arr.length - w + 1];
        int l = 0;
        int r = w - 1;
        while (r < arr.length) {
            int max = arr[l];
            for (int i = l + 1; i <= r; i++) {
                max = Math.max(max, arr[i]);
            }
            res[l] = max;
            l++;
            r++;
        }
        return res;
    }

    public int[] generateArr(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1 == null || arr2 == null) {
            return false;
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
        int testTime = 100000;
        int maxLength = 10;
        int maxValue = 100;
        System.out.println("test begin");
        GenerateMaxWindow generateMaxWindow = new GenerateMaxWindow();
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateMaxWindow.generateArr(maxLength, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] res1 = generateMaxWindow.getMaxWindow(arr, w);
            int[] res2 = generateMaxWindow.right(arr, w);
            if (!generateMaxWindow.isEqual(res1, res2)) {
                System.out.println("Oops!");
                System.out.println("arr:");
                for (int num : arr) {
                    System.out.print(num + " ");
                }
                System.out.println();
                System.out.println("w:" + w);
                System.out.println("res1:");
                for (int num : res1) {
                    System.out.print(num + " ");
                }
                System.out.println();
                System.out.println("res2:");
                for (int num : res2) {
                    System.out.print(num + " ");
                }
                System.out.println();
                break;
            }
        }
        System.out.println("test end");
    }

}
