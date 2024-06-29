package systemreview.code02;

public class Code02_EvenTimesOddTimes {

    /**
     * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这种数
     *
     * @return
     */
    public int findNum1(int[] arr) {
        // 相同的两个数进行异或 得到的值是0
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            ans ^= arr[i];
        }

        return ans;
    }

    public int findRightOne(int n) {
        return n & (~n + 1);
    }

    /**
     * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到并打印这两种数
     *
     * @param arr
     * @return
     */
    public int[] findTwoNums(int[] arr) {
        if (arr == null || arr.length <= 2) {
            return null;
        }
        int temp = 0;
        for (int i = 0; i < arr.length; i++) {
            temp ^= arr[i];
        }
        // 此时temp的值就是出现了奇数次两个数异或的结果
        // 找到这个数最后一个1
        int tempRight = temp & (~temp + 1);

        int another = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((tempRight & arr[i]) == 0) {
                another ^= arr[i];
            }
        }
        int[] ans = new int[2];
        ans[0] = another;
        ans[1] = another ^ tempRight;
        return ans;
    }

    /**
     * 一个数组中有一种数出现K次，其他数都出现了M次，
     * M > 1,  K < M
     * 找到，出现了K次的数，
     * 要求，额外空间复杂度O(1)，时间复杂度O(N)
     *
     * @param arr
     * @return
     */
    public int fineKNums(int[] arr, int k, int m) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 辅助数组 记录0-31位的位置变化
        int[] help = new int[32];
        for (int i = 0; i < arr.length; i++) {
            // 32位
            for (int j = 0; j < 32; j++) {
                help[j] += (arr[i] >> j) & 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (help[i] % m != 0) {
                ans |= 1 << i;
            }
        }
        return ans;
    }

}
