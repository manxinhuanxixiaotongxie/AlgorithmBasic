package systemimprove.code04;

/**
 * 给你一个整数数组 nums 以及两个整数 lower 和 upper 。
 * 求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
 */
public class Code04_CountOfRange {

    /**
     * 暴力解法
     *
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                long sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                if (sum >= lower && sum <= upper) {
                    ans++;
                }
            }
        }
        return ans;

    }

    /**
     * 前缀和进行优化
     *
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public int countRangeSum2(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                // 1-2 sum[2]-sum[0]
                long sumIJ = i == 0 ? sum[j] : sum[j] - sum[i - 1];
                if (sumIJ >= lower && sumIJ <= upper) {
                    ans++;
                }
            }
        }

        return ans;

    }

    /**
     * 归并排序进行优化
     * 归并排序的过程实际就是在左边有序、右边有序的过程中搜集答案
     * <p>
     * 如果能在归并排序的过程中，将答案搜集出来，那么就不用再进行暴力解法了
     * <p>
     * 子数组在 Lower-Upper之间
     * <p>
     * 将数组处理成前缀和数组
     * <p>
     * 联想一下小和问题以及逆序对、两倍问题
     * 小和问题以及逆序对的问题 都是转化成能在左边有序右边有序能获取到结论
     * <p>
     * 这个范围的问题，可以尝试转化成能在左边有序右边有序能获取到结论
     * <p>
     * 子数组的求和在[Lower,Upper]之间
     * <p>
     * Lower <= sum[j] - sum[i-1] <= Upper
     * <p>
     * sum[i-1] >= sum[j] - Upper
     * sum[i-1] <= sum[j] - Lower
     * <p>
     * 假设原数组是：
     * 5 2 1 3 4 6
     * 0 1 2 3 4 5
     * 前缀和数组是：
     * 5 7 8 11 15 21
     * 0 1 2  3  4  5
     * <p>
     * 以i未知的结尾的子数组如果要满足条件的话：
     * Lower <= sum[j] - sum[i-1] <= Upper
     * 得到：
     * sum[i-1] >= sum[j] - Upper
     * sum[i-1] <= sum[j] - Lower
     */
    public int countRangeSum3(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        return process(sum, 0, sum.length - 1, lower, upper);

    }

    public int process(long[] sum, int L, int R, int lower, int upper) {
        if (L == R) {
            return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
        }
        int mid = L + ((R - L) >> 1);
        return process(sum, L, mid, lower, upper) + process(sum, mid + 1, R, lower, upper) + merge(sum, L, R, mid, lower, upper);
    }

    /**
     * 思想怎么转化：
     * 比如说有一个数组
     * 3 1 2 4 5 1 1 0
     * 0 1 2 3 4 5 6 7
     * <p>
     * 给定给一个范围：[3,6]
     * 那么就会产生以下的子数组：
     * [0,0]
     * [0,1]
     * [0,2]
     * [1,2]
     * [2,3]
     * [3,3]
     * [4,4]
     * [4,5]
     * <p>
     * 将这个数组转化成前缀和数组：
     * 3 4 6 10 15 16 17 17
     * 0 1 2  3  4  5  6 7
     * <p>
     * lower-upper  -> [3,6]
     * <p>
     * 如果是在归并排序的过程中进行操作的话，那么就会产生以下的子数组：
     * 我们现在要得出以下答案：
     * * [0,0]  sum[0]
     * * [0,1]  sum[1]
     * * [0,2]  sum[2]
     * * [1,2]  sum[2] -sum[0] 转换一下  --> -3,3 之间的数
     * * [2,3]  sum[3] -sum[1] 4,7 之间的数
     * * [3,3]  sum[3] -sum[2] 4,7 之间的数
     * * [4,4]  sum[4] -sum[3] 9，12 之间的数
     * * [4,5]  sum[5] -sum[3] 10 ，13 之间的数
     * <p>
     * [0,1]的遍历如何判断呢：
     * 如果以1位置结尾：
     * <p>
     * -2 5 -1
     * -2 2
     * <p>
     * -2 3 2
     * <p>
     * 0 1 2
     *
     * @param sum
     * @param L
     * @param R
     * @param mid
     * @param lower
     * @param upper
     * @return
     */
    public int merge(long[] sum, int L, int R, int mid, int lower, int upper) {
        long[] help = new long[R - L + 1];
        int leftIndex = L;
        int rightIndex = mid + 1;
        int ans = 0;
        int helpIndex = 0;
//        while (leftIndex <= mid && rightIndex <= R) {
//            // 如果要求以i位置结尾数组在[lower,upper]之间
//            // 那么就要求以i位置结尾的数组的前缀和在[sum[j]-upper,sum[j]-lower]之间
//            long leftLower = sum[rightIndex] - upper;
//            long leftUpper = sum[rightIndex] - lower;
//
//            for (int i = leftIndex; i <= mid; i++) {
//                if (sum[i] >= leftLower && sum[i] <= leftUpper) {
//                    ans++;
//                }
//            }
//            rightIndex++;
//
//        }

        // 上面得方法是O（N^2）的 每一个右边界都要遍历一遍左边界
        // 其实左边界已经是有序的了，Windows是不回退的O（N）

        /**
         * 回顾一下这个题目。归根到底是怎么的使用归并排序的思想来解决这个问题的呢？
         * 归并排序的思想：与逆序对 小和问题 两倍问题的思想是一样的
         * 都是以某一个位置结尾的时候，能够获取到答案
         * 比如小和问题：以某一个位置结尾的时候，左边比他小的数的和
         * 逆序对问题：以I位置结尾 有多少个数比他大
         * 两倍问题：以I位置结尾 有多少个数比他的两倍大
         *
         */

        int windowL = L;
        int windowR = L;
        for (int i = mid + 1; i <= R; i++) {
            long min = sum[i] - upper;
            long max = sum[i] - lower;
            while (windowR <= mid && sum[windowR] <= max) {
                windowR++;
            }
            while (windowL <= mid && sum[windowL] < min) {
                windowL++;
            }
            ans += windowR - windowL;
        }

        leftIndex = L;
        rightIndex = mid + 1;


        while (leftIndex <= mid && rightIndex <= R) {
            if (sum[leftIndex] <= sum[rightIndex]) {
                help[helpIndex++] = sum[leftIndex++];
            } else {
                help[helpIndex++] = sum[rightIndex++];
            }
        }

        while (leftIndex <= mid) {
            help[helpIndex++] = sum[leftIndex++];
        }

        while (rightIndex <= R) {
            help[helpIndex++] = sum[rightIndex++];
        }

        for (int i = 0; i < help.length; i++) {
            sum[L + i] = help[i];
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4, 5, 1, 1, 0};
        int lower = 3;
        int upper = 6;
        System.out.println(new Code04_CountOfRange().countRangeSum(arr, lower, upper));
    }


}
