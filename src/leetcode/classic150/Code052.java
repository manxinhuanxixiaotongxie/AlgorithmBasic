package leetcode.classic150;

public class Code052 {
    public int totalNQueens(int n) {
        return process(n, 0, new int[n]);
    }

    /**
     * int r 当前行
     * int[] cols  cols[i] n  i行占据了col[i]列位置
     * 返回总共有多少中n皇后的放置方法
     *
     * @return
     */
    public int process(int n, int r, int[] cols) {
        if (r == n) {
            // 找到一种有效的放置方式
            return 1;
        } else {
            // 在当前行进行选择 选择出能能够放置的列
            int ans = 0;
            for (int col = 0; col < n; col++) {
                // 检查当前列是否可以放置皇后
                if (isValid(r, col, cols)) {
                    cols[r] = col;
                    // 放置下一行
                    ans += process(n, r + 1, cols);

                }
            }
            return ans;
        }
    }

    /**
     * r 当前要放置的行
     * c当前要放置的列
     * 检查有效性：不能同行 不能同列 不能共斜线
     *
     * @param r
     * @param c
     * @param cols
     * @return
     */
    private boolean isValid(int r, int c, int[] cols) {
        for (int curRow = 0; curRow < r; curRow++) {
            // 不能共列
            if (cols[curRow] == c) return false;
            // 不能共斜线
            if (Math.abs(curRow - r) == Math.abs(cols[curRow] - c)) return false;
        }
        return true;
    }

    /**
     * n皇后 利用位运算进行优化的尝试
     *
     * @param n
     * @return
     */
    public int totalNQueens2(int n) {
        return process2(n, 0, 0, 0, 0);
    }

    public int process2(int n,int r,int colLimit,int colLeftLimit,int colRightLimit) {
        if (r == n) {
            // 找到一种有效的放置方式
            return 1;
        }
        // 普遍位置
        // 求出当前行能够放置列位置
        int curColLimit = colLimit | colLeftLimit | colRightLimit;
        // 求最后一位
        curColLimit = ~curColLimit & ((1 << n) -1);
        // 再求最后一位的
        int ans = 0;
        while (curColLimit > 0) {
            int curLimit = curColLimit & (-curColLimit);
            curColLimit -= curLimit;
            ans += process2(n, r + 1, colLimit | curLimit,
                    (colLeftLimit | curLimit) << 1,
                    (colRightLimit | curLimit) >> 1);
        }
        return ans;
    }
}
