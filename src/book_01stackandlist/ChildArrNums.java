package book_01stackandlist;

/**
 * 最大值减去最小值小于或者等于num的子数组的数量
 * 给定数组arr[N] 和整数num
 * max(arr[i...j])-min(arr[i...j]) <= num
 * 要求时间复杂度O（N）
 * <p>
 * arr[1 3 21 2  2 1 ]  3
 */
public class ChildArrNums {

    public int getChildNum(int[] arr, int num) {

        if (arr == null || arr.length == 0) {
            return 0;
        }
        /**
         * 范围扩大 最大值只会变大或者不变
         * 范围扩大 最小值只会变小 或者不变
         *
         * max -min
         *
         * 范围缩小 最大值只会变小或者不变
         * 范围缩小 最小值只会变大或者不变
         *
         * 如果某一个范围满足最大值减去最小值小于等于num 那么这么范围的子数组一定是满足条件的
         */
        int l = 0;
        int r = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        int res = 0;
        while (r < arr.length) {
            while (max - min <= num && r < arr.length) {
                min = Math.min(min, arr[r]);
                max = Math.max(max, arr[r]);
                r++;
            }
            // 结算
            res += r - l;
            l++;
        }
        return res;
    }

}
