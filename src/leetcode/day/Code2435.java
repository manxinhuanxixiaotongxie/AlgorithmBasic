package leetcode.day;

/**
 * 给你一个下标从 0 开始的 m x n 整数矩阵 grid 和一个整数 k 。你从起点 (0, 0) 出发，每一步只能往 下 或者往 右
 * ，你想要到达终点 (m - 1, n - 1) 。
 * <p>
 * 请你返回路径和能被 k 整除的路径数目，由于答案可能很大，返回答案对 109 + 7 取余 的结果。
 */
public class Code2435 {
    /**
     * 超时
     *
     * @param grid
     * @param k
     * @return
     */
    public int numberOfPaths(int[][] grid, int k) {
        int rowLimit = grid.length - 1;
        int colLimit = grid[0].length - 1;
        int pathSum = 0;
        return process(grid, k, 0, 0, rowLimit, colLimit, pathSum);
    }

    public int process(int[][] grid, int k, int curRow, int curCol, int rowLimit, int colLimit, int pathSum) {
        if (curRow > rowLimit || curRow < 0 || curCol > colLimit || curCol < 0) {
            return 0;
        }
        // 定义边界条件
        if (curRow == rowLimit && curCol == colLimit) {
            if ((pathSum + grid[curRow][curCol]) % k == 0) {
                return 1;
            } else {
                return 0;
            }
        }
        int ans = 0;
        if (curRow == rowLimit) {
            // 已经到了最后一行 但是没有到最后一列 只能向右走
            ans += process(grid, k, curRow, curCol + 1, rowLimit, colLimit, pathSum + grid[curRow][curCol]);
        } else if (curCol == colLimit) {
            // 已经来到了最后一列 但是没有到最后一行 只能向下走
            ans += process(grid, k, curRow + 1, curCol, rowLimit, colLimit, pathSum + grid[curRow][curCol]);
        } else {
            // 普遍位置 可以向下走 也可以向右走
            ans += process(grid, k, curRow + 1, curCol, rowLimit, colLimit, pathSum + grid[curRow][curCol]);
            ans += process(grid, k, curRow, curCol + 1, rowLimit, colLimit, pathSum + grid[curRow][curCol]);
        }
        return ans % (1000000007);
    }


    public int numberOfPaths2(int[][] grid, int k) {
        int rowLimit = grid.length - 1;
        int colLimit = grid[0].length - 1;
        int[][][] memo = new int[k][rowLimit + 1][colLimit + 1];
        return process2(grid, k, 0, 0, 0, rowLimit, colLimit, memo);

    }

    // 尝试凑k
    public int process2(int[][] grid, int k, int rest, int curRow, int curCol, int rowLimit, int colLimit, int[][][] memo) {
        // 怎么凑k
        if (curRow > rowLimit || curRow < 0 || curCol > colLimit || curCol < 0) {
            return 0;
        }

        if (memo[rest][curRow][curCol] != 0) {
            return memo[rest][curRow][curCol];
        }

        // 定义边界条件
        if (curRow == rowLimit && curCol == colLimit) {
            if ((rest + grid[curRow][curCol]) % k == 0) {
                return 1;
            } else {
                return 0;
            }
        }

        int ans = 0;
        if (curRow == rowLimit) {
            // 已经到了最后一行 但是没有到最后一列 只能向右走
            ans += process2(grid, k, (rest + grid[curRow][curCol]) % k, curRow, curCol + 1, rowLimit, colLimit, memo);
        } else if (curCol == colLimit) {
            // 已经来到了最后一列 但是没有到最后一行 只能向下走
            ans += process2(grid, k, (rest + grid[curRow][curCol]) % k, curRow + 1, curCol, rowLimit, colLimit, memo);

        } else {
            // 普遍位置 可以向下走 也可以向右走
            ans += process2(grid, k, (rest + grid[curRow][curCol]) % k, curRow + 1, curCol, rowLimit, colLimit, memo);
            ans += process2(grid, k, (rest + grid[curRow][curCol]) % k, curRow, curCol + 1, rowLimit, colLimit, memo);

        }
        memo[rest][curRow][curCol] = ans % (1000000007);
        return ans % (1000000007);

    }


    public static void main(String[] args) {
        Code2435 code2435 = new Code2435();
        int[][] grid = {{5, 2, 4}, {3, 0, 5}, {0, 7, 2}};
        int k = 3;
        int ans = code2435.numberOfPaths(grid, k);
        System.out.println(ans);

        int ans2 = code2435.numberOfPaths2(grid, k);
        System.out.println(ans2);
    }
}
