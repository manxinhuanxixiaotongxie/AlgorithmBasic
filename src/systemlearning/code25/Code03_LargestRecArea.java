package systemlearning.code25;

import java.util.Stack;

/**
 * @Description 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * @Author Scurry
 * @Date 2023-10-25 19:35
 */
public class Code03_LargestRecArea {

    public int largestRectangleArea(int[] heights) {

        if (heights == null || heights.length == 0) {
            return 0;
        }

        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && stack.peek() <= heights[i]) {
                int j = stack.pop();
                int k = stack.isEmpty() ? -1 : stack.peek();
                int curArea = k == -1 ? heights[j] : (i - k - 1) * heights[j];
                maxArea = Math.max(maxArea, curArea);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int j = stack.pop();
            int k = stack.isEmpty() ? -1 : stack.peek();
            int curArea = k == -1 ? heights[j] : (heights.length - k - 1) * heights[j];
            maxArea = Math.max(maxArea, curArea);
        }
        return maxArea;
    }
}
