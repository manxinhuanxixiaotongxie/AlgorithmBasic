package leetcode.classic150;

public class Code011 {
    /**
     * 盛水最多的容器
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {

        int left = 0;
        int right = height.length - 1;
        int leftMax = height[left];
        int rightMax = height[right];
        int max = 0;
        while (left < right) {
            // 结算
            max = Math.max(max,Math.min(leftMax,rightMax) * (right - left));
            if (height[left] < height[right]) {
                left++;
                leftMax = Math.max(leftMax, height[left]);
            } else {
                right--;
                rightMax = Math.max(rightMax, height[right]);
            }
        }

        return max;

    }
}
