package leetcode.classic150;

import java.util.List;

/**
 *
 * 进阶：
 * <p>
 * 你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题吗？
 *
 */
public class Code120 {
    /**
     * 超时
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        return process(triangle, 1, 0) + triangle.get(0).get(0);
    }

    /**
     * 最小路径和
     * index 来到了当前行
     * col上一行选择的列
     * <p>
     * 第0行只有一个数 选择的列只有0 从第1行开始进行选择
     *
     * @return
     */
    public int process(List<List<Integer>> triangle, int index, int col) {
        if (index == triangle.size()) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        // 下一行选择的列
        for (int i = 0; i < 2; i++) {
            ans = Math.min(ans, process(triangle, index + 1, col + i) +
                    triangle.get(index).get(col + i));
        }
        return ans;
    }

    public int minimumTotal2(List<List<Integer>> triangle) {
        int row = triangle.size();
        int colLimit = triangle.get(row - 1).size();
        int[][] dp = new int[row + 1][colLimit];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < colLimit; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int index = row - 1; index >= 0; index--) {
            int curCol = triangle.get(index).size();
            for (int col = curCol - 2; col >= 0; col--) {
                for (int i = 0; i < 2; i++) {
                    dp[index][col] = Math.min(dp[index][col],
                            dp[index + 1][col + i] + triangle.get(index).get(col + i));
                }
            }
        }
        return dp[1][0] + triangle.get(0).get(0);
    }
}
