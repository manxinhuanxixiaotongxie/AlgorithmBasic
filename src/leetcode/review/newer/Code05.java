package leetcode.review.newer;

public class Code05 {
    public int xorOperation(int n, int start) {
        // 只需要计算数字 不需要构建数组
        int ans = 0;
        for (int i = 0; i < n; i++) {
            // 当前数字
            int tempCur = start + (i << 1);
            ans = ans ^ tempCur;
        }
        return ans;
    }
}
