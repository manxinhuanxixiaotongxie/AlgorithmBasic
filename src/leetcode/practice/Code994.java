package leetcode.practice;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Code994 {

    int[] dr = new int[]{-1, 0, 1, 0};
    int[] dc = new int[]{0, -1, 0, 1};

    /**
     * 这个解法是有问题的
     *
     * 感染是同时发生的，所以不能在一次循环中就把所有的感染都处理掉，否则会导致感染的时间计算错误。
     *
     * @param grid
     * @return
     */
    public  int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int res = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 2 && (checkNeedAffect(grid, r + 1, c) || checkNeedAffect(grid, r - 1, c)
                        || checkNeedAffect(grid, r, c + 1) || checkNeedAffect(grid, r, c - 1))) {
                    affect(grid, r + 1, c);
                    affect(grid, r - 1, c);
                    affect(grid, r, c + 1);
                    affect(grid, r, c - 1);
                    res++;
                }
            }
        }

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    return -1;
                }
            }
        }
        return res;
    }

    private boolean checkNeedAffect(int[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == 1;
    }

    private void affect(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != 1) {
            return;
        }

        if (grid[r][c] == 1) {
            grid[r][c] = 2;
        }
    }


    public int orangesRotting2(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<int[]> queue = new LinkedList<int[]>();
        // 定义上下左右
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true; // 标记腐烂的橘子已访问
                }
                if (grid[i][j] == 0) {
                    visited[i][j] = true;
                }
            }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            ans++;
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int k = 0; k < 4; k++) {
                    int x = cur[0] + dx[k];
                    int y = cur[1] + dy[k];
                    if (x < 0 || x >= row || y < 0 || y >= col || visited[x][y] || grid[x][y] != 1) {
                        continue;
                    }
                    visited[x][y] = true;
                    if (grid[x][y] == 1) {
                        queue.offer(new int[]{x, y});
                        grid[x][y] = 2;
                    }
                }
            }
        }
        // 检查是否还有新鲜橘子
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    return -1; // 如果还有新鲜橘子，返回 -1
                }
            }
        }
        return ans == 0 ? 0 : ans - 1; // 如果没有新鲜橘子，返回 ans - 1
    }

    public static void main(String[] args) {
//        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
//        int[][] grid = {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
//        int[][] grid = {{1},{ 2}};
//        int[][] grid = {{0, 2}};
        //      [[2,0,1,1,1,1,1,1,1,1],[1,0,1,0,0,0,0,0,0,1],[1,0,1,0,1,1,1,1,0,1],[1,0,1,0,1,0,0,1,0,1],[1,0,1,0,1,0,0,1,0,1],[1,0,1,0,1,1,0,1,0,1],[1,0,1,0,0,0,0,1,0,1],[1,0,1,1,1,1,1,1,0,1],[1,0,0,0,0,0,0,0,0,1],[1,1,1,1,1,1,1,1,1,1]]
        int[][] grid =
                {{2, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                        {1, 0, 1, 0, 1, 1, 1, 1, 0, 1},
                        {1, 0, 1, 0, 1, 0, 0, 1, 0, 1},
                        {1, 0, 1, 0, 1, 0, 0, 1, 0, 1},
                        {1, 0, 1, 0, 1, 1, 0, 1, 0, 1},
                        {1, 0, 1, 0, 0, 0, 0, 1, 0, 1},
                        {1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
        Code994 code994 = new Code994();
        System.out.println(code994.orangesRotting(grid));
        System.out.println(code994.orangesRotting2(grid));
    }
}
