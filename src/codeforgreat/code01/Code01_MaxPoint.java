package codeforgreat.code01;

import java.util.Arrays;

/**
 * 给定一个有序数组arr，代表坐落在X轴上的点，给定一个正数K，代表绳子的长度，返回绳子最多压中几个点？
 * 即使绳子边缘处盖住点也算盖住
 * <p>
 * 1.第一种思路：
 * <p>
 * 二分：因为数组是有序的因此我们可以采取二分的策略进行处理
 * 过程如下：
 * 对于当前位置找到第一个大于等于arr[i] + K的位置，那么这个位置的前一个位置就是绳子最右边的位置
 */
public class Code01_MaxPoint {


    public static int maxPoint1(int[] arr, int k) {
        // 给定一个有序数组
        int ans = 1;
        for (int i = 0; i < arr.length; i++) {
            int mostLeftBiggerThan = findMostLeftBiggerThan(arr, i, arr.length - 1, arr[i] + k);
            ans = Math.max(ans, mostLeftBiggerThan - i + 1);
        }
        return ans;
    }

    /**
     * 滑动窗口
     * @param arr
     * @param k
     * @return
     */
    public static int maxPoint2(int[] arr, int k) {
        // 给定一个有序数组
        int ans = 1;
        int windowL = 0;
        for (int i = 0; i < arr.length; i++) {
            while (windowL <arr.length && arr[windowL]-arr[i] <=k) {
                windowL++;
            }
            ans = Math.max(ans, windowL - i);
        }
        return ans;
    }

    // 给定一个范围 找到这个范围上小于等于num的最右边的位置
    private static int findMostLeftBiggerThan(int[] nums, int l, int r, int num) {
        int ans = l;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            // 找到中点位置
            if (nums[mid] <= num) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

    // for test
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }

    public static int maxPointRight(int[] arr, int L) {
        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            int nearest = nearestIndex(arr, i, arr[i] - L);
            res = Math.max(res, i - nearest + 1);
        }
        return res;
    }

    public static int nearestIndex(int[] arr, int R, int value) {
        int L = 0;
        int index = R;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= value) {
                index = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return index;
    }

    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }

    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int L = (int) (Math.random() * max);
            int[] arr = generateArray(len, max);
            int ans1 = maxPoint1(arr, L);
            int ans2 = maxPointRight(arr, L);
            int ans3 = test(arr, L);
            int ans4 = maxPoint2(arr, L);
            if (ans1 != ans2 || ans2 != ans3 || ans3 != ans4) {
                System.out.println("oops!");
                break;
            }
        }

    }
}
