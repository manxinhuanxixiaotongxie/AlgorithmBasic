package systemimprove.code15;

import java.util.Deque;
import java.util.LinkedList;

public class Code01_MaxWindow {

    public int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || arr.length == 0 || arr.length < w) {
            return null;
        }
        int[] ans = new int[arr.length - w + 1];
        for (int i = 0; i < arr.length - w + 1; i++) {
            int max = arr[i];
            for (int j = i + 1; j < i + w; j++) {
                max = Math.max(max, arr[j]);
            }
            ans[i] = max;
        }
        return ans;
    }

    public int[] generateArr(int maxValue, int maxLength) {
        int[] arr = new int[(int) (Math.random() * maxLength) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    public static void main(String[] args) {
        Code01_MaxWindow test = new Code01_MaxWindow();
        int maxValue = 100;
        int maxLength = 30;
        int testTimes = 500000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = test.generateArr(maxValue, maxLength);
            int w = (int) (Math.random() * arr.length) + 1;
            int[] ans1 = test.getMaxWindow(arr, w);
            int[] ans2 = test.maxSlidingWindow(arr, w);
            if (ans1 == null && ans2 == null) {
                continue;
            }
            if (ans1.length != ans2.length) {
                System.out.println("Oops!");
            }
            for (int j = 0; j < ans1.length; j++) {
                if (ans1[j] != ans2[j]) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("finish!");
    }

    /**
     * 维护一个窗口最大值的更新结构
     * <p>
     * 队列里面的大小是的从头到位一次减小
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] arr, int k) {
        // 无效参数过滤
        if (arr == null || k < 1 || arr.length < k) {
            return null;
        }
        Deque<Integer> queue = new LinkedList<>();
        int N = arr.length;
        int[] ans = new int[N - k + 1];
        int index = 0;
        for (int i = 0; i < N; i++) {
            // 这是一个头-》尾单调递减的双端队列
            // 等于为什么要弹出？更新索引
            while (!queue.isEmpty() && arr[queue.peekLast()] <= arr[i]) {
                queue.pollLast();
            }
            queue.addLast(i);
            // 如果队列头部的下标已经过期，弹出
            if (queue.peekFirst() == i - k) {
                queue.pollFirst();
            }
            // 如果窗口形成了，就开始收集答案
            if (i >= k - 1) {
                ans[index++] = arr[queue.peekFirst()];
            }
        }
        return ans;
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        return null;
    }
}
