package leetcode.review.newer;

public class Code17 {
    public int peakIndexInMountainArray(int[] arr) {
        //你必须设计并实现时间复杂度为 O(log(n)) 的解决方案。
        // 二分
        // 返回峰值下标
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return left;

    }
}
