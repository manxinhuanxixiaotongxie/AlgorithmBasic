package systemimprove.code40;

/**
 * 给定一个整数组成的无序数组arr，值可能正、可能负、可能0
 * 给定一个整数值K
 * 找到arr的所有子数组里，哪个子数组的累加和<=K，并且是长度最大的
 * 返回其长度
 */
public class Code03_MaxLength {

    public int maxLength1(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        /**
         * 暴力解法
         */
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum <= k) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }

    /**
     * O(N)解法
     *
     * @param arr
     * @param k
     * @return
     */
    public int maxLength2(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        /**
         * 流程：
         * 1.两个数组
         * minSum：含义是以i开头的子数组 能够取到的最小的累加和是多少
         * minSumEnd 含义是以i开头的子数组能够取到最小累加和的最右侧位置
         *
         * 有了这两个数组之后 我们接下来的流程是：
         */
        int[] minSum = new int[arr.length];
        int[] minSumEnd = new int[arr.length];
        minSum[arr.length - 1] = arr[arr.length - 1];
        minSumEnd[arr.length - 1] = arr.length - 1;
        for (int i = arr.length - 2; i >= 0; i--) {
            if (minSum[i + 1] > 0) {
                minSum[i] = arr[i] + minSum[i + 1];
                minSumEnd[i] = minSumEnd[i + 1];
            } else {
                minSum[i] = arr[i];
                minSumEnd[i] = i;
            }
        }
        int ans = 0;
        int end = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            // 如果当前位置最小的累加和比k要小的话 那么end进行右扩
            while (end < arr.length && sum + minSum[i] <= k) {
                sum += minSum[end];
                end = minSumEnd[end] + 1;
            }
            ans = Math.max(ans, end - i);
            if (end > i) {
                sum -= arr[i];
            } else {
                end = i + 1;
            }
        }
        return ans;
    }
}
