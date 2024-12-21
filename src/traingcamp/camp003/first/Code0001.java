package traingcamp.camp003.first;

import java.util.Arrays;

/**
 * 给定一个有序数组arr，从左到右依次表示X轴上从左往右点的位置
 * 给定一个正整数K，返回如果有一根长度为K的绳子，最多能盖住几个点
 * 绳子的边缘点碰到X轴上的点，也算盖住
 */
public class Code0001 {

    /**
     * 滑动窗口
     *
     * @param arr
     * @param L
     * @return
     */
    public static int maxPoint1(int[] arr, int L) {
        // 有序数组
        int windowL = 0;
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            while (windowL < arr.length && arr[windowL] - arr[i] <= L) {
                windowL++;
            }
            ans = Math.max(ans, windowL - i);
        }
        return ans;
    }

    /**
     * 二分
     *
     * @param arr
     * @param L
     * @return
     */
    public static int maxPoint4(int[] arr, int L) {
        // 有序数组
        int ans = 1;
        for (int i = 0; i < arr.length; i++) {
            // 在i到R之间找到离i最近但是比i+k大的数
            int nearestBigger = findNearestBigger(arr, i, arr.length - 1, arr[i] + L);
            ans = Math.max(ans, nearestBigger - i + 1);
        }
        return ans;
    }

    private static int findNearestBigger(int[] arr, int left, int right, int num) {
        int ans = left;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= num) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    public static int maxPoint2(int[] arr, int L) {
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

    // for test
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


    // for test
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
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
            int ans2 = maxPoint2(arr, L);
            int ans3 = test(arr, L);
            int ans4 = maxPoint4(arr, L);
            if (ans1 != ans3 || ans2 != ans3 || ans4 != ans3) {
                System.out.println("oops!");
                break;
            }
        }
        System.out.println("测试结束");

    }


}
