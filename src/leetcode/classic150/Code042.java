package leetcode.classic150;

import java.util.Stack;

public class Code042 {
    /**
     * 先用单调栈处理
     * 左边离我最近比我大 右边离我最近比我大
     * 栈顶到栈底单调递减
     *
     * @param height
     * @return
     */
    public int trap1(int[] height) {
        // 相等也要弹出 结算的结果不影响最终的结果
        int ans = 0;
        Stack<Integer> minStack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!minStack.isEmpty() && height[minStack.peek()] <= height[i]) {
                int top = minStack.pop();
                // left
                int left = minStack.isEmpty() ? -1 : minStack.peek();
                int right = i;
                // 结算
                if (left != -1) {
                    int h = Math.max(0, Math.min(height[left], height[right]) - height[top]);
                    int w = right - left - 1;
                    ans += h * w;
                }
            }
            minStack.push(i);
        }

        return ans;

    }

    /**
     * 双指针
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        // 相等也要弹出 结算的结果不影响最终的结果
        int ans = 0;
        int n = height.length;
        int leftMax = height[0];
        int rightMax = height[n - 1];
        int left = 1;
        int right = n - 1;
        // 双指针
        while (left <= right) {
            //if (height[left] < height[right]) {
            if (leftMax < rightMax) {
                ans += Math.max(0, leftMax - height[left]);
                leftMax = Math.max(leftMax, height[left]);
                left++;
            } else {
                ans += Math.max(0, rightMax - height[right]);
                rightMax = Math.max(rightMax, height[right]);
                right--;
            }

        }

        return ans;

    }
}
