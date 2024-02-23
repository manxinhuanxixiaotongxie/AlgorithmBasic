package systemimprove.code15;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给定一个数组，如果子数组满足 最大值减去最小值<=num的情况下，求有多少个子数组
 */
public class Code02_ArrNum {


    // 暴力方法
    public int getNums1(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        // 找到所有的子数组
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                int max = Integer.MIN_VALUE;
                int min = Integer.MIN_VALUE;
                for (int k = i; k <= j; k++) {
                    max = Math.max(max, arr[k]);
                    min = Math.min(min, arr[k]);
                }
                if (max - min <= num) {
                    count++;
                }
            }
        }
        return count;


    }

    // 使用窗口最大值的更新结构进行优化
    public int getNums2(int[] arr, int num) {

        // 使用两个窗口结构  一个窗口最大值  一个窗口最小值
        // 两个结论：
        // 如果 i-j满足条件的话  那么在（i-j）范围里面的都是满足条件的子数组
        // 如果i-j满足不满足条件 那么范围在i-j一定是不满足条件的  这个怎么理解？
        // [i,,,j] 不满足条件   那么在i向左扩大或者j向右扩大  那么在新的范围的最大值  只可能变大  最小值有可能变小或者不变  最大值减去最小值更是不可能满足条件
        int count = 0;
        int ans = 0;
        Deque<Integer> maxWindow = new LinkedList<Integer>();
        Deque<Integer> minWindow = new LinkedList<Integer>();
        int R = 0;
        for (int i = 0; i < arr.length; i++) {
            while (R < arr.length) {
                // 窗口最大值最小值的更新结构
                while (!maxWindow.isEmpty() && arr[maxWindow.pollLast()] <= arr[i]) {
                    maxWindow.pollLast();
                }
                maxWindow.addLast(i);
                // 最小值的更新结构
                while (!minWindow.isEmpty() && arr[minWindow.peekLast()] >= arr[i]) {
                    minWindow.pollLast();
                }
                minWindow.addLast(i);


                // 窗口最大值最小值已经有了 接下来怎么处理
                if (arr[maxWindow.peekFirst()] - arr[minWindow.peekFirst()] > num) {
                    break;
                } else {
                    R++;
                }
                // 窗口已经过期
                if (maxWindow.peekFirst() == i) {
                    maxWindow.pollFirst();
                }
                if (minWindow.peekFirst() == i) {
                    minWindow.pollFirst();
                }
            }
            count += (R - i);


        }

        return count;

    }

    public int[] generateArr(int maxValue, int maxLength) {
        int[] arr = new int[(int) (Math.random() * maxLength) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        Code02_ArrNum test = new Code02_ArrNum();
        int maxValue = 100;
        int maxLength = 30;
        int testTimes = 500000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = test.generateArr(maxValue, maxLength);
            int num = (int) (Math.random() * maxValue) + 1;
            int ans1 = test.getNums1(arr, num);
            int ans2 = test.getNums2(arr, num);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
