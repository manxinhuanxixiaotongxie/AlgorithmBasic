package book_01stackandlist;

import java.util.Stack;

/**
 * 给定一个整型矩阵map，其中的值只有0和1两种 求其中全是1的所有矩形区域中最大矩形区域为1的数量
 * <p>
 * <p>
 * https://leetcode.com/problems/maximal-rectangle/
 */
public class MaxRecSize {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        // 列
        int[] heights = new int[matrix[0].length];
        int ans = 0;
        // 行
        for (int row = 0; row < matrix.length; row++) {
            // 列
            for (int j = 0; j < matrix[0].length; j++) {
                // 如果当前行的值为0，则高度为0，否则高度加1
                heights[j] = matrix[row][j] == '0' ? 0 : heights[j] + 1;
            }
            ans = Math.max(ans, maxSize(heights));
        }

        return ans;
    }


    public int maxSize(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        // 在height数组中，求最大的矩形数量
        // 找到左边理我最近比我小的 右边离我最近比我大的
        // 单调栈 从栈顶到栈底从大到小
        for (int i = 0; i < heights.length; i++) {
            // 相等要不要弹出呢
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                Integer pop = stack.pop();
                // 结算当前
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                max = Math.max(max, heights[pop] * (i - leftIndex - 1));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int pop = stack.pop();
            int leftIndex = stack.isEmpty() ? -1 : stack.peek();
            max = Math.max(max, heights[pop] * (heights.length - leftIndex - 1));
        }
        return max;
    }

    public int maximalRectangle2(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        // 列
        int[] heights = new int[matrix[0].length];
        int ans = 0;
        // 行
        for (int row = 0; row < matrix.length; row++) {
            // 列
            for (int j = 0; j < heights.length; j++) {
                // 如果当前行的值为0，则高度为0，否则高度加1
                heights[j] = matrix[row][j] == '0' ? 0 : heights[j] + 1;
            }
            Stack<Integer> stack = new Stack<>();
            // 在height数组中，求最大的矩形数量
            // 找到左边理我最近比我小的 右边离我最近比我大的
            // 单调栈 从栈顶到栈底从大到小
            for (int i = 0; i < heights.length; i++) {
                // 相等要不要弹出呢
                while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                    Integer pop = stack.pop();
                    // 结算当前
                    int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                    ans = Math.max(ans, heights[pop] * (i - leftIndex - 1));
                }
                stack.push(i);
            }
            while (!stack.isEmpty()) {
                int pop = stack.pop();
                int rightIndex = heights.length;
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                ans = Math.max(ans, heights[pop] * (rightIndex - leftIndex - 1));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        MaxRecSize maxRecSize = new MaxRecSize();
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println(maxRecSize.maximalRectangle(matrix)); // 输出 6
    }

}
