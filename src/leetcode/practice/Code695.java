package leetcode.practice;

import java.util.HashMap;
import java.util.Map;

/**
 * 以下两个方法都在leetcode上面测试通过
 */
public class Code695 {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        Uniset2 uniset = new Uniset2(grid);
        int max = 0;
        // 先填第一行
        for (int i = 0; i < grid[0].length; i++) {
            if (grid[0][i] == 1) {
                if (i > 0 && grid[0][i - 1] == 1) {
                    uniset.union(0, i, 0, i - 1);
                }
                if (i < grid[0].length - 1 && grid[0][i + 1] == 1) {
                    uniset.union(0, i, 0, i + 1);
                }

                max = Math.max(max, uniset.getSize(0, i));
            }
        }

        // 再填第一列
        for (int i = 1; i < grid.length; i++) {
            if (grid[i][0] == 1) {
                if (grid[i - 1][0] == 1) {
                    uniset.union(i, 0, i - 1, 0);
                }
                max = Math.max(max, uniset.getSize(i, 0));
            }
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    if (grid[i - 1][j] == 1) {
                        uniset.union(i, j, i - 1, j);
                    }
                    if (grid[i][j - 1] == 1) {
                        uniset.union(i, j, i, j - 1);
                    }
                    max = Math.max(max, uniset.getSize(i, j));
                }
            }
        }
        return max;

    }

    public static void main(String[] args) {
        int[][] grid = new int[][]
                        {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                        {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                        {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        Code695 code695 = new Code695();
        System.out.println(code695.maxAreaOfIsland(grid));

    }

}

class Uniset {
    // 记录当前节点的父节点index
    private Map<Integer, Integer> parentMap;
    // 当前节点拥有的节点数是多少
    private Map<Integer, Integer> sizeMap;

    private Integer col;
    private int max;

    Uniset(int[][] grid) {
        this.col = grid[0].length;
        parentMap = new HashMap<>();
        sizeMap = new HashMap<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int index = getIndex(i, j);
                    parentMap.put(index, index);
                    sizeMap.put(index, 1);
                }
            }
        }

    }

    public int getSize(int i, int j) {

        return sizeMap.get(find(i, j));
    }



    public void union(int i1, int j1, int i2, int j2) {
        int father1 = find(i1, j1);
        int father2 = find(i2, j2);
        if (father1 != father2) {
            int size1 = sizeMap.get(father1);
            int size2 = sizeMap.get(father2);
            if (size1 > size2) {
                parentMap.put(father2, father1);
                sizeMap.put(father1, size1 + size2);
                max = Math.max(max, size1 + size2);
                sizeMap.remove(father2);
            } else {
                parentMap.put(father1, father2);
                sizeMap.put(father2, size1 + size2);
                max = Math.max(max, size1 + size2);
                sizeMap.remove(father1);
            }
        }
    }

    private int find(int i, int j) {
        // 当前节点的位置
        int index = getIndex(i, j);
        // 父节点的位置
        Integer father = parentMap.get(index);
        int[] stack = new int[col];
        int stackSize = 0;
        while (father != index) {
            stack[stackSize++] = index;
            index = father;
            father = parentMap.get(index);
        }
        // 路径压缩
        for (int k = 0; k < stackSize; k++) {
            parentMap.put(stack[k], father);
        }

        return father;
    }

    // 计算出index位置
    private int getIndex(int i, int j) {
        return i * col + j;
    }
}

/**
 * 使用数组实现
 */
class Uniset2 {
    // 记录当前节点的父节点index
//    private Map<Integer, Integer> parentMap;
    // int[index] = k 标识index位置的父节点是K
    private int[] parentMap;
    // 当前节点拥有的节点数是多少
//    private Map<Integer, Integer> sizeMap;
    // sizeMap[index]=k 标识index位置的节点拥有k个节点
    private int[] sizeMap;

    private Integer col;

    Uniset2(int[][] grid) {
        this.col = grid[0].length;
        parentMap = new int[grid.length * grid[0].length];
        sizeMap = new int[grid.length * grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    int index = getIndex(i, j);
                    parentMap[index] = index;
                    sizeMap[index] = 1;
                }
            }
        }

    }

    public int getSize(int i, int j) {

        return sizeMap[find(i, j)];
    }


    public void union(int i1, int j1, int i2, int j2) {
        int father1 = find(i1, j1);
        int father2 = find(i2, j2);
        if (father1 != father2) {
            int size1 = sizeMap[father1];
            int size2 = sizeMap[father2];
            if (size1 > size2) {
                parentMap[father2] = father1;
                sizeMap[father1] = size1 + size2;
                sizeMap[father2] = 0;
            } else {
                parentMap[father1] = father2;
                sizeMap[father2] = size1 + size2;
                sizeMap[father1] = 0;
            }
        }
    }

    private int find(int i, int j) {
        // 当前节点的位置
        int index = getIndex(i, j);
        // 父节点的位置
        Integer father = parentMap[index];
        int[] stack = new int[col];
        int stackSize = 0;
        while (father != index) {
            stack[stackSize++] = index;
            index = father;
            father = parentMap[index];
        }
        // 路径压缩
        for (int k = 0; k < stackSize; k++) {
            parentMap[stack[k]] = father;
        }

        return father;
    }

    // 计算出index位置
    private int getIndex(int i, int j) {
        return i * col + j;
    }
}
