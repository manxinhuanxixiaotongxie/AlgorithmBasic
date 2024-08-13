package leetcode;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Code994 {

    int[] dr = new int[]{-1, 0, 1, 0};
    int[] dc = new int[]{0, -1, 0, 1};

    /**
     * 这个解法是有问题的
     *
     * @param grid
     * @return
     */
    public synchronized int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int res = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 2 && (checkNeedAffect(grid, r + 1, c) || checkNeedAffect(grid, r - 1, c)
                        || checkNeedAffect(grid, r, c + 1) || checkNeedAffect(grid, r, c - 1)))
                {
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


    /**
     * 官方解答
     *
     * @param grid
     * @return
     */
    public int orangesRotting2(int[][] grid) {
        int R = grid.length, C = grid[0].length;
        Queue<Integer> queue = new ArrayDeque<Integer>();
        Map<Integer, Integer> depth = new HashMap<Integer, Integer>();
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                if (grid[r][c] == 2) {
                    int code = r * C + c;
                    queue.add(code);
                    depth.put(code, 0);
                }
            }
        }
        int ans = 0;
        while (!queue.isEmpty()) {
            int code = queue.remove();
            int r = code / C, c = code % C;
            for (int k = 0; k < 4; ++k) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (0 <= nr && nr < R && 0 <= nc && nc < C && grid[nr][nc] == 1) {
                    grid[nr][nc] = 2;
                    int ncode = nr * C + nc;
                    queue.add(ncode);
                    depth.put(ncode, depth.get(code) + 1);
                    ans = depth.get(ncode);
                }
            }
        }
        for (int[] row: grid) {
            for (int v: row) {
                if (v == 1) {
                    return -1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
//        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
//        int[][] grid = {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
//        int[][] grid = {{1},{ 2}};
//        int[][] grid = {{0, 2}};
        //      [[2,0,1,1,1,1,1,1,1,1],[1,0,1,0,0,0,0,0,0,1],[1,0,1,0,1,1,1,1,0,1],[1,0,1,0,1,0,0,1,0,1],[1,0,1,0,1,0,0,1,0,1],[1,0,1,0,1,1,0,1,0,1],[1,0,1,0,0,0,0,1,0,1],[1,0,1,1,1,1,1,1,0,1],[1,0,0,0,0,0,0,0,0,1],[1,1,1,1,1,1,1,1,1,1]]
        int[][] grid =
                       {{2,0,1,1,1,1,1,1,1,1},
                        {1,0,1,0,0,0,0,0,0,1},
                        {1,0,1,0,1,1,1,1,0,1},
                        {1,0,1,0,1,0,0,1,0,1},
                        {1,0,1,0,1,0,0,1,0,1},
                        {1,0,1,0,1,1,0,1,0,1},
                        {1,0,1,0,0,0,0,1,0,1},
                        {1,0,1,1,1,1,1,1,0,1},
                        {1,0,0,0,0,0,0,0,0,1},
                        {1,1,1,1,1,1,1,1,1,1}};
        Code994 code994 = new Code994();
        System.out.println(code994.orangesRotting(grid));
        System.out.println(code994.orangesRotting2(grid));
    }
}
