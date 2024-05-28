package systemreview.code01;

import java.util.Arrays;

public class Code01_FindNums {

    /**
     * 在一个有序数组中
     * 找到>=某个数最左侧的位置
     */
    public int findIndex(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int N = arr.length;
        // 采用二分的方式进行查找
        // 当中点的位置的值比k大的时候 r位置向左进行偏移
        int L = 0;
        int R = N - 1;
        int ans = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] >= k) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ans;
    }

    // for test
    public int right(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= k) {
                return i;
            }
        }
        return -1;
    }

    public int[] gengerateArr(int maxValue,int maxLength) {
        int[] ans = new int[(int) (Math.random()*maxLength+1)];
        for (int i = 0 ; i < ans.length; i++) {
            ans[i] = (int) (Math.random()*maxValue+1) - (int) (Math.random()*maxValue+1);
        }
        Arrays.sort(ans);
        return ans;
    }

    public static void main(String[] args) {
        int maxValue = 20;
        int maxLength = 20;
        int testTimes = 100000;
        int k = (int) (Math.random()*maxValue+1 - Math.random()*maxValue+1);
        Code01_FindNums code01 = new Code01_FindNums();
        for (int i = 0; i < testTimes; i++) {
            int[] arr = code01.gengerateArr(maxValue, maxLength);
            int ans1 = code01.findIndex(arr, k);
            int ans2 = code01.right(arr, k);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finished!");
    }

}
