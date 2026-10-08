package leetcode.review.newer;

public class Code07 {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        // 好三元组的数量
        // 暴力算
        // 第一个数
        int n = arr.length;
        int ans = 0;
        for (int i = 0; i < n - 2; i++) {
            // 第一个数i
            // 第二个数j
            for (int j = i + 1; j < n - 1; j++) {
                // j是第二个数
                for (int k = j + 1; k < n; k++) {
                    // 计算是否符合条件
                    if ((Math.abs(arr[i] - arr[j]) < a) && (Math.abs(arr[j] - arr[k]) < b) && (Math.abs(arr[i] - arr[k]) < c)) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
}
