package leetcode;

import java.util.Stack;

public class Code042 {
    public int trap(int[] height) {
        // 使用单调栈
        // 栈的结构是从的小到大
        // 这样就能找到左边比我大的最近的位置
        // 右边比我大的最近的位置
        if (height == null || height.length == 0) {
            return 0;
        }
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            // 从上到小是依次增加的栈
            // 相等的时候要不要弹出来
            while (!stack.isEmpty() && height[stack.peek()] <= height[i]) {
                int cur = stack.pop();
                // 当前位置左边比我大的数
                if (stack.isEmpty()) {
                    continue;
                }
                int left = stack.peek();
                int right = i;
                // 以当前位置可以接到雨水数
                int h = Math.min(height[left], height[right]) - height[cur];
                ans += (right - left - 1) * h;
            }
            stack.push(i);
        }
        return ans;
    }
}
