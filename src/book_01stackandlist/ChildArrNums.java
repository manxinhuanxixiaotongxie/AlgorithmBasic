package book_01stackandlist;

import java.util.LinkedList;

/**
 * 最大值减去最小值小于或者等于num的子数组的数量
 * 给定数组arr[N] 和整数num
 * max(arr[i...j])-min(arr[i...j]) <= num
 * 要求时间复杂度O（N）
 * <p>
 * arr[1 3 21 2  2 1 ]  3
 */
public class ChildArrNums {

    public int getChildNum(int[] arr, int num) {

        if (arr == null || arr.length == 0 || num < 0) {
            return 0;
        }
        /**
         * 范围扩大 最大值只会变大或者不变
         * 范围扩大 最小值只会变小 或者不变
         *
         * max -min
         *
         * 范围缩小 最大值只会变小或者不变
         * 范围缩小 最小值只会变大或者不变
         *
         * 如果某一个范围满足最大值减去最小值小于等于num 那么这么范围的所有子数组一定是满足条件
         * 如果某一个范围不满足最大值减去最小值小于等于num 那么这个范围的所有子数组一定是不满足条件
         *
         * 使用双端队列来维护最大值和最小值
         */
        LinkedList<Integer> qMax = new LinkedList<>();
        LinkedList<Integer> qMin = new LinkedList<>();
        // 使用两个变量i j维护子数组的范围
        // 含义 以i开通的子数组 j结尾
        int i = 0;
        // 另J不断扩大范围 同时维护最大值和最小值的更新结构
        // 一旦不满足条件就停止j的扩大
        // 完成这个步骤之后 所有必须以i开头的子数组都满足条件 总共有j - i个子数组
        // 此时i位置已经完成了清算，将i位置进行清算 i++，继续下一个i位置的清算 同时维护最大值最小值的更新结构
        int j = 0;
        int res = 0;
        while (i < arr.length) {
            while (j < arr.length) {
                while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[j]) {
                    qMax.pollLast();
                }
                qMax.addLast(j);
                while (!qMin.isEmpty() && arr[qMin.peekLast()] >= arr[j]) {
                    qMin.pollLast();
                }
                qMin.addLast(j);
                if (arr[qMax.peekFirst()] - arr[qMin.peekFirst()] > num) {
                    break;
                } else {
                    j++;
                }
            }
            res += j - i;
            if (qMin.peekFirst() == i) {
                qMin.pollFirst();
            }
            if (qMax.peekFirst() == i) {
                qMax.pollFirst();
            }
            i++;
        }
        return res;
    }


    public int right(int[] arr, int num) {
        if (arr == null || arr.length == 0 || num < 0) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    max = Math.max(max, arr[k]);
                    min = Math.min(min, arr[k]);
                }
                if (max - min <= num) {
                    res++;
                }
            }
        }
        return res;
    }



    public int[] generateArr(int maxLength, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxLength)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxValue + 1) - (int) (Math.random() * maxValue + 1);
        }
        return arr;
    }

    public void printArr(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("[]");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLength = 10;
        int maxValue = 10;
        int testTimes = 100000;
        ChildArrNums childArrNums = new ChildArrNums();
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = childArrNums.generateArr(maxLength, maxValue);
            int num = (int) (Math.random() * maxValue);
            int res1 = childArrNums.getChildNum(arr, num);
            int res2 = childArrNums.right(arr, num);
            if (res1 != res2) {
                System.out.println("num: " + num);
                childArrNums.printArr(arr);
                System.out.println("Oops!");
                System.out.println("res1: " + res1);
                System.out.println("res2: " + res2);
                break;
            }
        }
        System.out.println("test end");
    }

}
