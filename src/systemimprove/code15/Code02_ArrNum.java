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
                int max = arr[i];
                int min = arr[i];
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

    // 下面注释的这几个方法有问题
/*
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
            // 外层的while循环是为了计算满足条件的数量
            //
            while (R < arr.length) {
                // 窗口最大值最小值的更新结构
                while (!maxWindow.isEmpty() && arr[maxWindow.pollLast()] <= arr[i]) {
                    maxWindow.pollLast();
                }
                maxWindow.addLast(i);
                // 最小值的更新结构
                // 怎么向右进行调整？
                // 如果窗口最大值减去窗口最小值是满足条件的话  说明 有一个子数组的范围是满足条件的
                //
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

    // 窗口最大值的更新结构
    // 窗口最小值的更新结构
    // 过程如下：

    *//**
     * @param arr
     * @return
     *//*
    public int getNUms3(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Deque<Integer> maxQueen = new LinkedList<>();
        Deque<Integer> minQueue = new LinkedList<>();
        int count = 0;
        int R = 0;
        for (int i = 0; i < arr.length; i++) {

            while (!maxQueen.isEmpty() && arr[maxQueen.peekLast()] <= arr[i]) {
                maxQueen.pollLast();
            }
            maxQueen.addLast(i);
            while (!minQueue.isEmpty() && arr[minQueue.peekLast()] >= arr[i]) {
                minQueue.pollLast();
            }
            minQueue.addLast(i);

            */

    /**
     * 窗口最大值最小值已经有了 接下来怎么处理
     * R会不会回退？
     * 怎么理解？
     * 给定一个例子：
     * [5,3,3,1,4,7]    num = 3
     * i等于0的时候
     * 窗口最大值跟最小值都是5   5小于等于3
     * i来到1的时候 窗口最大值的更新结构5,3
     * 窗口最小值变成3
     * 窗口最大值减去最小值 5-3 = 2  满足条件
     * i来到2位置的时候
     * 窗口最大值的更新结构变成了5,3 只是index发生了变化 值没变
     * 窗口最小值的更新结构依然是3 满足条件
     * 来到3位置的时候
     * 窗口最大值的更新结构变成了5,3,1
     * 窗口最小值的更新结构变成了1
     * 此时不满足条件
     * 来到4位置的时候
     * 窗口最大值的更新结构来到了5,4
     * 窗口最小值的更新结构变更了1.4最小值还是1 满足条件
     * <p>
     * 来到5位置的时候
     * 窗口最大值的更新结构来到了7
     * 窗口最小值的更新结构变成了1,4,7 不满足条件 不产生新的子数组
     * <p>
     * 分析以上过程：
     * R是不会回退的
     * 分析一下为什么？
     * 1.最大窗口的更新结构的值只会变大
     * 2.最小窗口的更新结构的值只会变小
     *//*
            while (R < arr.length) {
                if (arr[maxQueen.peekFirst()] - arr[minQueue.peekFirst()] <= num) {
                    R++;
                } else {
                    break;
                }
            }
//            if (maxQueen.peekFirst() == i) {
//                maxQueen.pollFirst();
//            }
//            if (minQueue.peekFirst() == i) {
//                minQueue.pollFirst();
//            }
            count += R - i;

        }
        return count;
    }*/
    public static int getNum4(int[] arr, int sum) {
        if (arr == null || arr.length == 0 || sum < 0) {
            return 0;
        }
        int N = arr.length;
        int count = 0;
        LinkedList<Integer> maxWindow = new LinkedList<>();
        LinkedList<Integer> minWindow = new LinkedList<>();
        int R = 0;
        for (int L = 0; L < N; L++) {
            while (R < N) {
                // 1 2 3 4
                while (!maxWindow.isEmpty() && arr[maxWindow.peekLast()] <= arr[R]) {
                    maxWindow.pollLast();
                }
                // 5 4 3 2 1
                // 0 1 2 3 4
                // L       R
                // L=0 R=4
                //
                maxWindow.addLast(R);
                while (!minWindow.isEmpty() && arr[minWindow.peekLast()] >= arr[R]) {
                    minWindow.pollLast();
                }
                minWindow.addLast(R);
                if (arr[maxWindow.peekFirst()] - arr[minWindow.peekFirst()] > sum) {
                    break;
                } else {
                    R++;
                }
            }
            // 以i开头的子数组数量
            count += R - L;
            if (maxWindow.peekFirst() == L) {
                maxWindow.pollFirst();
            }
            if (minWindow.peekFirst() == L) {
                minWindow.pollFirst();
            }
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
            int ans4 = getNum4(arr, num);
            if (ans1 != ans4) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
