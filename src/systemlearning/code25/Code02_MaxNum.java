package systemlearning.code25;

import java.util.Stack;

/**
 * @Description 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 * 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 * 那么所有子数组中，这个值最大是多少？
 * <p>
 * arr[]正数 子数组必须连续
 * @Author Scurry
 * @Date 2023-10-23 17:25
 */
public class Code02_MaxNum {

    /**
     * 所有子数组
     * 以i作为结束位置 总共有多少个数组
     *
     * @param arr
     * @return
     */

    public int getMax1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            int min = Integer.MAX_VALUE;
            for (int j = i; j >= 0; j--) {
                sum += arr[j];
                min = Math.min(min, arr[j]);
                ans = Math.max(ans, sum * min);
            }
        }
        return ans;

    }

    /**
     * 单调栈
     * 使用单调栈求解：
     * <p>
     * 左边离我最近比我小
     *
     * @param arr
     * @return
     */
    public int getMax2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int size = arr.length;
        int[] sums = new int[size];
        sums[0] = arr[0];
        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }

        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int j = stack.pop();
                max = Math.max(max, (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * arr[j]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? sums[size - 1] : (sums[size - 1] - sums[stack.peek()])) * arr[j]);
        }
        return max;
    }
}
