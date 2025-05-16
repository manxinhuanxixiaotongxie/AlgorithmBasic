package systemimprove.code16;

public class Code04_MaximalRectangle {

    /**
     * 给定一个二维数组matrix，其中的值不是0就是1，
     * 返回全部由1组成的最大子矩形，内部有多少个1
     * <p>
     * 转化一下：
     * 1. 以每一行为底的情况下，求最大的矩形面积
     * 2. 求最大的矩形面积
     * 3.以某一行为底的情况下，求最大的矩形面积
     * 4. 求最大的矩形面积
     *
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        int ans = 0;
        int[] height = new int[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
            }
            ans = Math.max(ans, largestRectangleArea2(height));

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
        for (int i = 0; i < heights.length; i++) {
            while (stackSize > 0 && heights[stack[stackSize - 1]] >= heights[i]) {
                int pop = stack[--stackSize];
                // 左边比他小的数是
                int leftIndex = stackSize == 0 ? -1 : stack[stackSize - 1];
                // 右边比他小的数是
                int rightIndex = i;
                ans = Math.max(ans, (rightIndex - leftIndex - 1) * heights[pop]);
            }
            stack[stackSize++] = i;
        }
        while (stackSize > 0) {
            int pop = stack[--stackSize];
            int leftIndex = stackSize == 0 ? -1 : stack[stackSize - 1];
            int rightIndex = heights.length;
            ans = Math.max(ans, (rightIndex - leftIndex - 1) * heights[pop]);
        }
        return ans;
    }
}
