package leetcode.practice;

import java.util.Stack;

/**
 * 接雨水问题一维数组
 *
 */
public class Code042 {

    /**
     * 使用单调栈
     * <p>
     * <p>
     * 维持一个单调栈 单调栈里面的内容是从小到大的
     * 那么这个单调栈能够帮助我们获取到某一个位置的左边离我最近比我大  以及右边位置离我最近比我大的位置
     *
     * @param height
     * @return
     */
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

    /**
     * 使用双指针
     * <p>
     * 当前i位置能够接到的雨水数量数是左边最大值与当前位置右边最大值 两者取最小值之后与当前位置作比较
     * <p>
     * Math.max(Math.min(leftMax,RightMax)-height[i],0)
     * <p>
     * 能够得到上面的这个规律
     * <p>
     * 那么在进行双指针进行判断的过程中：
     * 根据上面的公式：进行求解当前i能够获取的最大雨水的问题时，受限制的是左边最大值与右边最大值  两者的最小值
     * <p>
     * 假设：leftMax是小于rightMax的话  因为此时右边的最大值并不一定是最大值 有可能扩大 但是 起码不会比当前的rightMaX小
     * <p>
     * <p>
     * 因此此时其实是可以获取当前i位置的接雨水的数量的，当前i位置能够接到的雨水的数量受限于左边位置leftMax
     * <p>
     * 如果leftMax比rightMax要大的话  那么此时受到限制就是右边rightMax 左边的leftMax可能会被扩大  此时是可以结算rightIndex位置的数
     *
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int ans = 0;
        // 使用左右指针的方式进行求解
        // 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
        int length = height.length;
        int leftMax = height[0];
        int rightMax = height[length - 1];
        int left = 1;
        int right = height.length - 2;
        while (left <= right) {

            if (leftMax < rightMax) {
                ans += Math.max(Math.min(leftMax, rightMax) - height[left], 0);
                leftMax = Math.max(leftMax, height[left]);
                left++;
            } else {
                ans += Math.max(Math.min(leftMax, rightMax) - height[right], 0);
                rightMax = Math.max(rightMax, height[right]);
                right--;
            }
        }
        return ans;
    }
}
