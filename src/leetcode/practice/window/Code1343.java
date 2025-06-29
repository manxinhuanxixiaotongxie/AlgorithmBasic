package leetcode.practice.window;

/**
 * 给你一个整数数组 arr 和两个整数 k 和 threshold 。
 * <p>
 * 请你返回长度为 k 且平均值大于等于 threshold 的子数组数目。
 */
public class Code1343 {
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (i < k - 1) {
                continue;
            }
            // 结算
            if (sum / k >= threshold) {
                ans++;
            }
            sum -= arr[i - k + 1];
        }
        return ans;
    }
}
