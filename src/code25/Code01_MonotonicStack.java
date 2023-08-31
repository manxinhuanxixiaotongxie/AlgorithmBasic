package code25;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * 数组不重复 时间复杂度O（N）
     * 思路：
     * 准备一个栈  保证栈底到栈顶是从小到大的
     * 在遍历的过程中，如果当前数小于栈顶，弹出栈顶，否则压入栈
     * 在这个过程中 如果弹出栈顶，那么当前数就是栈顶的右边最小值
     * 左边最小值就是栈顶的下一个数
     *
     * @param arr
     * @return
     */
    public int[][] getNearestNum2(int[] arr) {
        if (arr == null || arr.length < 0) {
            return null;
        }
        int[][] ans = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
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

    /**
     * 生成不重复的数组
     *
     * @param max
     * @param maxLengh
     * @return
     */
    public int[] generateArrNoRepeat(int max, int maxLengh) {
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

    /**
     * 数组中有重复的数
     * 处理思路：
     * 栈中存放的是List数组，List中存放的是相同的数
     * 在遍历的过程中，如果当前数小于栈顶，弹出栈顶，否则压入栈
     * 在这个过程中 如果弹出栈顶，那么当前数就是栈顶的右边最小值
     * 左边最小值就是栈顶的下一个数
     * 如果不比当前数大，可能也可能相等 如果相等的话，就把当前数放到栈顶的List中
     * 如果大的话 就把当前数放到栈中
     *
     * @param arr
     * @return
     */
    public int[][] getNearestNum(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return null;
        }
        Stack<List<Integer>> stack = new Stack<>();
        int[][] ans = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> list = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                int rightLessIndex = i;
                for (Integer integer : list) {
                    ans[integer][0] = leftLessIndex;
                    ans[integer][1] = rightLessIndex;
                }

            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }

        while (!stack.isEmpty()) {
            List<Integer> list = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
            int rightLessIndex = -1;
            for (Integer integer : list) {
                ans[integer][0] = leftLessIndex;
                ans[integer][1] = rightLessIndex;
            }
        }
        return ans;
    }

    public int[] generateArr(int max, int maxLengh) {
        int[] arr = new int[(int) (Math.random() * maxLengh) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) Math.random() * max;
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
            int[][] ans2 = code01_monotonicStack.getNearestNum(arr);
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
