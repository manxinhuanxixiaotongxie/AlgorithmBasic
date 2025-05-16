package systemimprove.code16;

import java.util.Stack;

/**
 * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 * 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 * 那么所有子数组中，这个值最大是多少？
 */
public class Code02_MInSum {
    /**
     * 纯暴力
     *
     * @param arr
     * @return
     */
    public int minSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                int min = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    min = Math.min(min, arr[k]);
                    sum += arr[k];
                }
                ans = Math.max(ans, min * sum);
            }
        }
        return ans;
    }

    /**
     * 使用前缀和优化
     */
    public int minSum2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int min = Integer.MAX_VALUE;
                int areaSum = sum[j] - (i > 0 ? sum[i - 1] : 0);
                for (int k = i; k <= j; k++) {
                    min = Math.min(min, arr[k]);
                }
                ans = Math.max(ans, min * areaSum);
            }
        }
        return ans;
    }

    /**
     * 使用单调栈优化
     * 思路转化：
     * 以i位置作为最小值的情况下 算出来的最大值  答案肯定在其中
     */
    public int minSum3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < N; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && sum[stack.peek()] - (stack.peek() == 0 ? 0 : sum[stack.peek() - 1]) >= sum[i] - (i == 0 ? 0 : sum[i - 1])) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int areaSum = sum[i - 1] - (k == -1 ? 0 : sum[k]);
                int min = arr[j];
                ans = Math.max(ans, min * areaSum);

            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int areaSum = sum[N - 1] - (k == -1 ? 0 : sum[k]);
            int min = arr[j];
            ans = Math.max(ans, min * areaSum);
        }
        return ans;
    }

    public int[] generateArr(int maxvalue, int maxLength) {
        int[] ans = new int[(int) ((maxLength + 1) * Math.random())];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) ((maxvalue + 1) * Math.random());
        }
        return ans;
    }

    public void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Code02_MInSum minSum = new Code02_MInSum();
        int maxvalue = 10;
        int maxLength = 10;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = minSum.generateArr(maxvalue, maxLength);
            int ans1 = minSum.minSum(arr);
            int ans2 = minSum.minSum2(arr);
            int ans3 = minSum.minSum3(arr);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println(ans1 + " " + ans2 + " " + ans3);
                System.out.println("=====");
                minSum.printArr(arr);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("finish!");
    }
}
