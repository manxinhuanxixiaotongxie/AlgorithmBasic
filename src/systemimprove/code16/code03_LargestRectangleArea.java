package systemimprove.code16;

import java.util.Stack;

public class code03_LargestRectangleArea {

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int ans = 0;
        for (int i = 0; i < heights.length;i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int pop = stack.pop();
                // 左边比他小的数是
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                // 右边比他小的数是
                int rightIndex = i;
                ans = Math.max(ans,(rightIndex - leftIndex - 1) * heights[pop]);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int pop = stack.pop();
            int leftIndex = stack.isEmpty() ? -1 : stack.peek();
            int rightIndex = heights.length;
            ans = Math.max(ans,(rightIndex - leftIndex - 1) * heights[pop]);
        }
        return ans;
    }

    public int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int[] stack = new int[heights.length];
        int stackSize = 0;
        int ans = 0;
        for (int i = 0; i < heights.length;i++) {
            while (stackSize > 0 && heights[stack[stackSize - 1]] >= heights[i]) {
                int pop = stack[--stackSize];
                // 左边比他小的数是
                int leftIndex = stackSize == 0 ? -1 : stack[stackSize - 1];
                // 右边比他小的数是
                int rightIndex = i;
                ans = Math.max(ans,(rightIndex - leftIndex - 1) * heights[pop]);
            }
            stack[stackSize++] = i;
        }
        while (stackSize > 0) {
            int pop = stack[--stackSize];
            int leftIndex = stackSize == 0 ? -1 : stack[stackSize - 1];
            int rightIndex = heights.length;
            ans = Math.max(ans,(rightIndex - leftIndex - 1) * heights[pop]);
        }
        return ans;
    }
}
