package leetcode.practice;

public class Code0189 {

    public void rotate(int[] nums, int k) {
        // 1 2 3 4 5 6 7    k = 3
        // 0 1 2 3 4 5 6
        // 4 3 2 1 7 6 5
        // 5 3 2 1 7 6 4
        int L = 0;
        int N = nums.length;
        if (k == 0 || k == N) {
            return;
        }
        if (k > N) {
            k = k % N;
        }
        int R = N - k - 1;
        while (L < R) {
            // 交换
            int temp = nums[L];
            nums[L] = nums[R];
            nums[R] = temp;
            L++;
            R--;
        }
        L = N - k;
        R = N - 1;
        while (L < R) {
            // 交换
            int temp = nums[L];
            nums[L] = nums[R];
            nums[R] = temp;
            L++;
            R--;
        }
        L = 0;
        R = N - 1;
        while (L < R) {
            // 交换
            int temp = nums[L];
            nums[L] = nums[R];
            nums[R] = temp;
            L++;
            R--;
            k--;
        }
    }
}
