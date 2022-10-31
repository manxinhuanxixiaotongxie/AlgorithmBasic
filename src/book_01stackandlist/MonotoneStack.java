package book_01stackandlist;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-10-30 16:43
 */

import java.util.LinkedList;
import java.util.Stack;

/**
 * 给定一不含重复值的数组arr，找到每一个i位置左边和右边离i位置最近且值比arr[i]小的位置
 *
 * 进阶：如果数组中的值可以重复，找到每一个i位置左边和右边离i位置最近且值比arr[i]小的位置
 *
 * 要求：
 * 时间复杂度O(N)
 */
public class MonotoneStack {

    /**
     * N^2的方式很容易实现 左右两个for循环
     * @param arr
     * @return
     */
    public int[][] getNearLessNoRepeat1(int[] arr) {
        int[][] res = new int[arr.length][2];
       for (int i =0;i< arr.length;i++) {
           int leftLessIndex = -1;
           int rightLessIndex = -1;
           int cur = i - 1;
           while (cur >= 0 && arr[cur] > arr[i]) {
               leftLessIndex = cur;
               cur--;
           }
           cur = i + 1;
           while (cur < arr.length && arr[cur] > arr[i]) {
               rightLessIndex = cur;
               cur++;
           }
           res[i][0] = leftLessIndex;
           res[i][1] = rightLessIndex;
       }
        return res;
    }

    /**
     * 使用单调栈的方式
     * 首先思考没有重复值的情况如何使用单调栈解决这个问题
     *
     * 有没有可能使用单调栈解决有重复值的问题
     * @param arr
     * @return
     */
    public int[][] getNearLessNoRepeat2(int[] arr) {
        int[][] res = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        /**
         * 如何生成一个单调的栈？
         * 【5 4 3 2 1】
         */
        for (int i = 0; i < arr.length; i++) {
            // 如果栈不为空，且栈顶元素大于当前元素，就弹出栈顶元素
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int popIndex = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                res[popIndex][0] = leftLessIndex;
                res[popIndex][1] = i;
            }
            stack.push(i);
        }
        // 如果栈不为空，就弹出栈顶元素
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            res[popIndex][0] = leftLessIndex;
            res[popIndex][1] = -1;
        }
        return res;
    }
}
