package traingcamp.camp003.code04;

/**
 * 给定一个数组arr，返回子数组的最大累加和。
 */
public class Code06_SubArrayMaxSum {

    public static int subArraMaxSum1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int minValue = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            // 以i位置结尾
            for (int j = 0; j <= i; j++) {
                int sum = 0;
                for (int k = j; k <= i; k++) {
                    sum += arr[k];
                }
                minValue = Math.max(minValue, sum);
            }
        }
        return minValue;
    }

    public static int subArraMaxSum2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 利用前缀和数组
        int[] preSum = new int[arr.length];
        preSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }
        int minValue = preSum[0];
        for (int i = 1; i < arr.length; i++) {
            // 利用前缀和数组进行优化
            for (int j = 0; j < i; j++) {
                minValue = Math.max(minValue, preSum[i] - preSum[j]);
            }
        }
        return minValue;
    }

    /**
     * 最优解
     * <p>
     * 假设答案法
     * <p>
     * <p>
     * 如果i-j的区间能够获取到最大累加和
     * <p>
     * x    i   k   j
     * <p>
     * 其中i-j位置是我们假设出来的能够获取最大累加和的位置
     * <p>
     * 那么i-k这个区间的累加和一定是大于0的
     * 如果i-k这个区间的累加和小于0，那么i-j这个区间的累加和一定不是最大的
     * 所以我们可以假设i-k这个区间的累加和是大于0的
     * x-i这个区间的累加和一定是小于0的
     * 如果x-i这个区间的累加和大于等于0，那么i-j这个区间的累加和一定不是最大的
     * <p>
     * O(N)的解法
     * 流程：
     * 如果累加和小于等于0，那么累加和清零
     * 如果累加和大于0，那么累加和继续累加
     * 每次累加和都和最大值进行比较
     *
     * @param arr
     * @return
     */
    public static int subArraMaxSum3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = 0;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            maxValue = Math.max(maxValue, sum);
            sum = Math.max(sum, 0);
        }
        return maxValue;
    }

    public static void main(String[] args) {
        int[] arr1 = {-2, -3, -5, 40, -10, -10, 100, 1};

        System.out.print(subArraMaxSum1(arr1) + " ");
        System.out.print(subArraMaxSum2(arr1) + " ");
        System.out.print(subArraMaxSum3(arr1) + " ");

        System.out.print("\n");
        int[] arr2 = {-2, -3, -5, 0, 1, 2, -1};
        System.out.print(subArraMaxSum1(arr2) + " ");
        System.out.print(subArraMaxSum2(arr2) + " ");
        System.out.print(subArraMaxSum3(arr2) + " ");

        System.out.print("\n");

        int[] arr3 = {-2, -3, -5, -1};
        System.out.print(subArraMaxSum1(arr3) + " ");
        System.out.print(subArraMaxSum2(arr3) + " ");
        System.out.print(subArraMaxSum3(arr3) + " ");
    }
}
