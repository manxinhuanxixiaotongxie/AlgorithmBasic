package systemimprove.code39;

import java.util.HashSet;

/**
 * 给定一个非负数组arr，和一个正数m。
 * 返回arr的所有子序列中累加和%m之后的最大值。
 */
public class Code02_SubsquenceMaxModM {

    /**
     * 数组 arr
     * 非负整数
     * 子序列累加和%M之后的最大值
     *
     * @param arr
     * @return
     */
    public int maxValue(int[] arr, int m) {
        HashSet<Integer> set = new HashSet<>();
        process1(arr, 0, 0, set);
        int ans = 0;
        for (Integer sum : set) {
            ans = Math.max(ans, sum % m);
        }
        return ans;
    }

    public static void process1(int[] arr, int index, int sum, HashSet<Integer> set) {
        if (index == arr.length) {
            set.add(sum);
        } else {
            process1(arr, index + 1, sum, set);
            process1(arr, index + 1, sum + arr[index], set);
        }
    }
}
