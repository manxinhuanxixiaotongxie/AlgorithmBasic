package systemlearning.code04;

/**
 * @Description 给你一个整数数组 nums 以及两个整数 lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
 * <p>
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 * @Author Scurry
 * @Date 2023-09-21 10:18
 */
public class Code02_MergeSortLowerUpper {

    public int countRangeSum1(int[] arr, int lower, int upper) {
        if (arr == null) {
            return 0;
        }

        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }

        int ans = 0;
        int between = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                while (i <= j) {
                    between = sum[j] - sum[i];
                }
            }
            if (between >= lower && between <= upper) {
                ans++;
            }
        }
        return ans;
    }


    public int countRangeSum2(int[] arr, int lower, int upper) {
        if (arr == null) {
            return 0;
        }

        return process(arr, 0, arr.length - 1, lower, upper);
    }

    public int process(int[] arr, int L, int R, int lower, int upper) {
        if (L == R) {
            return arr[L] >= lower && arr[L] <= upper ? 1 : 0;
        }

        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }

        int mid = L + ((R - L) >> 1);
        return process(sum, L, mid, lower, upper) + process(sum, mid + 1, R, lower, upper) + merge(sum, L, R, mid, lower, upper);
    }

    /**
     * sum数组是前缀和的形式
     * L左边界
     * R右边界
     * mid中点
     * <p>
     * <p>
     * 求区间和的个数：
     * sum[i]表达的是0-i的和
     * sum[j]-sum[i]表达的是i-j的和
     * <p>
     * <p>
     * <p>
     * 【2 3 3】【1 2 5】
     *
     * @param sum
     * @param L
     * @param R
     * @param mid
     * @param lower
     * @param upper
     * @return
     */
    public int merge(int[] sum, int L, int R, int mid, int lower, int upper) {
        int[] help = new int[R - L + 1];
        int left = L;
        int right = mid + 1;
        int index = 0;
        int ans = 0;

        while (left <= mid && right <= R) {
            help[index++] = sum[left] < sum[right] ? sum[left++] : sum[right++];
        }

        while (left <= mid) {
            help[index++] = sum[left++];
        }

        while (right <= R) {
            help[index++] = sum[right++];
        }

        for (int i = 0; i < help.length; i++) {
            sum[L + i] = help[i];
        }

        return 0;
    }
}
