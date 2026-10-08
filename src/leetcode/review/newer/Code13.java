package leetcode.review.newer;

public class Code13 {
    public int[] shuffle(int[] nums, int n) {
        int len = n << 1;
        int[] res = new int[len];
        for (int i = 0; i < len - 1; i += 2) {
            res[i] = nums[i >> 1];
            res[i + 1] = nums[(i >> 1) + n];
        }
        return res;
    }

    static void main() {
        int[] nums = {2, 5, 1, 3, 4, 7};
        int n = nums.length / 2;
        Code13 code = new Code13();
        int[] res = code.shuffle(nums, n);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }

    }
}
