package leetcode.practice;

import java.util.HashSet;
import java.util.Set;

/**
 * 测试通过
 */
public class Code1254 {

    public int closedIsland(int[][] grid) {
        if (grid == null || grid.length <= 2 || grid[0].length <= 2) return 0;

        MyUnionSet myUnionSet = new MyUnionSet(grid);

        int row = grid.length;
        int col = grid[0].length;
        // 怎么进行合并
        // 先填第一行和第一列
        // 第一行
        for (int c = 1; c < col; c++) {
            if (grid[0][c - 1] == 0 && grid[0][c] == 0) {
                myUnionSet.union(0, c - 1, 0, c);
            }
        }

        // 第一列
        for (int r = 1; r < row; r++) {
            if (grid[r - 1][0] == 0 && grid[r][0] == 0) {
                myUnionSet.union(r - 1, 0, r, 0);
            }
        }

        // 填充其他的
        for (int r = 1; r < row; r++) {
            for (int c = 1; c < col; c++) {
                if (grid[r][c] == 0) {
                    if (grid[r - 1][c] == 0) {
                        myUnionSet.union(r - 1, c, r, c);
                    }
                    if (grid[r][c - 1] == 0) {
                        myUnionSet.union(r, c - 1, r, c);
                    }
                }
            }
        }

        // 校验
        // 边边上为0的集合要剔除
        int res = myUnionSet.getSets();
        // 对周边进行遍历
        // 第一行
        Set<Integer> set = new HashSet<>();
        for (int c = 0; c < col; c++) {
            if (grid[0][c] == 0 && !set.contains(myUnionSet.findFather(0, c))) {
                res--;
                set.add(myUnionSet.findFather(0, c));
            }
        }
        // 第一列
        for (int r = 1; r < row; r++) {
            if (grid[r][0] == 0 && !set.contains(myUnionSet.findFather(r, 0))) {
                res--;
                set.add(myUnionSet.findFather(r, 0));
            }
        }

        // 最后一列
        for (int r = 1; r < row; r++) {
            if (grid[r][col - 1] == 0 && !set.contains(myUnionSet.findFather(r, col - 1))) {
                res--;
                set.add(myUnionSet.findFather(r, col - 1));
            }
        }
        // 最后一行
        for (int c = 0; c < col - 1; c++) {
            if (grid[row - 1][c] == 0 && !set.contains(myUnionSet.findFather(row - 1, c))) {
                res--;
                set.add(myUnionSet.findFather(row - 1, c));
            }
        }

        return res;
    }

    public static void main(String[] args) {
        // [[1,1,0,1,1,1,1,1,1,1],[0,0,1,0,0,1,0,1,1,1],[1,0,1,0,0,0,1,0,1,0],[1,1,1,1,1,0,0,1,0,0],[1,0,1,0,1,1,1,1,1,0],[0,0,0,0,1,1,0,0,0,0],[1,0,1,0,0,0,0,1,1,0],[1,1,0,0,1,1,0,0,0,0],[0,0,0,1,1,0,1,1,1,0],[1,1,0,1,0,1,0,0,1,0]]
        int[][] grid = {{1, 1, 0, 1, 1, 1, 1, 1, 1, 1},
                {0, 0, 1, 0, 0, 1, 0, 1, 1, 1},
                {1, 0, 1, 0, 0, 0, 1, 0, 1, 0},
                {1, 1, 1, 1, 1, 0, 0, 1, 0, 0},
                {1, 0, 1, 0, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                {1, 0, 1, 0, 0, 0, 0, 1, 1, 0},
                {1, 1, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1, 1, 1, 0},
                {1, 1, 0, 1, 0, 1, 0, 0, 1, 0}};
        Code1254 code1254 = new Code1254();
        System.out.println(code1254.closedIsland(grid));
    }

}


class MyUnionSet {
    // parent[i] = k 标识i位置的父的index是k
    private int[] parent;
    private int col;
    private int sets;

    MyUnionSet(int[][] grid) {
        col = grid[0].length;
        parent = new int[grid.length * col];
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] == 0) {
                    int index = r * col + c;
                    parent[index] = index;
                    sets++;
                }
            }
        }
    }

    // 合并两个集合
    public void union(int r1, int c1, int r2, int c2) {
        int father1 = findFather(r1, c1);
        int father2 = findFather(r2, c2);
        if (father1 != father2) {
            parent[father1] = parent[father2];
            sets--;
        }
    }

    public int findFather(int r, int c) {
        int index = r * col + c;
        int father = parent[index];
        int[] help = new int[parent.length];
        int stackSize = 0;
        while (father != parent[father]) {
            help[stackSize++] = index;
            index = father;
            father = parent[index];
        }
        for (int i = 0; i < stackSize; i++) {
            parent[help[i]] = father;
        }
        return father;
    }

    public int getSets() {
        return sets;
    }
}
