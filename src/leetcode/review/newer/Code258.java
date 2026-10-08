package leetcode.review.newer;

public class Code258 {
    public int addDigits(int num) {
        // 进阶：你可以不使用循环或者递归，在 O(1) 时间复杂度内解决这个问题吗？
        int ans = num;
        while (ans >= 10) {
            int temp = 0;
            while (ans != 0) {
                temp += (ans % 10);
                ans = ans / 10;
            }
            ans = temp;
        }
        return ans;
    }

}
