package code25;

import java.util.Stack;

/**
 * @Description 在一个数组中选出左边比自己小的最近的数，右边比自己小的最近的数
 * @Author Scurry
 * @Date 2023-08-29 10:48
 */
public class Code01_MonotonicStack {

    // int[][] 意思是i位置的最左边最小值、右边最小值
    public int[][] getNearstNum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        int[][] ans = new int[arr.length][2];

        for (int i = 0; i < arr.length; i++) {
            int leftLessIndex = -1;
            int rightLessIndex = -1;

            int cur = i - 1;
            while (cur >= 0) {
                if (arr[cur] < arr[i]) {
                    leftLessIndex = cur;
                    break;
                }
                cur--;
            }

            cur = i + 1;
            while (cur < arr.length) {
                if (arr[cur] < arr[i]) {
                    rightLessIndex = cur;
                    break;
                }
                cur++;
            }
            ans[i][0] = leftLessIndex;
            ans[i][1] = rightLessIndex;
        }
        return ans;
    }

    public int[][] getNearestNum2(int[] arr) {
        if (arr == null || arr.length < 0) {
            return null;
        }
        int[][] ans = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && stack.peek() > arr[i]) {
                int popIndex = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                int rightLessIndex = i;
                ans[popIndex][0] = leftLessIndex;
                ans[popIndex][1] = rightLessIndex;
            }
            stack.push(i);

        }

        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            int righLessIndex = -1;
            ans[popIndex][0] = leftLessIndex;
            ans[popIndex][1] = righLessIndex;
        }

        return ans;
    }

    public int[] generateArr(int max, int maxLengh) {
        int[] arr = new int[(int) (Math.random() * maxLengh) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < arr.length; i++) {
            int swapIndex = (int) (Math.random() * arr.length);
            int tmp = arr[swapIndex];
            arr[swapIndex] = arr[i];
            arr[i] = tmp;
        }
        return arr;
    }

    public static void main(String[] args) {
        Code01_MonotonicStack code01_monotonicStack = new Code01_MonotonicStack();
        int max = 100;
        int maxLength = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = code01_monotonicStack.generateArr(max, maxLength);
            int[][] ans1 = code01_monotonicStack.getNearstNum(arr);
            int[][] ans2 = code01_monotonicStack.getNearestNum2(arr);
            if (!isEqual(ans1, ans2)) {
                System.out.println("Oops!");
            }
        }
    }

    private static boolean isEqual(int[][] ans1, int[][] ans2) {
        for (int i = 0; i < ans1.length; i++) {
            if (ans1[i][0] != ans2[i][0] || ans1[i][1] != ans2[i][1]) {
                return false;
            }
        }
        return true;
    }
}
