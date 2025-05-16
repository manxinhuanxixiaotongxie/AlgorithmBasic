package leetcode.practice;

public class Code0200 {

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    affect(grid, i, j);
                }
            }
        }
        return res;

    }

    private void affect(char[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length)
            return;
        if (grid[x][y] == '1') {
            grid[x][y] = 2;
            affect(grid, x - 1, y);
            affect(grid, x + 1, y);
            affect(grid, x, y - 1);
            affect(grid, x, y + 1);
        }
    }

    /**
     * 使用并查集
     *
     * @param grid
     * @return
     */
    public int numIslands2(char[][] grid) {

        UnionSet200 unionSet = new UnionSet200(grid);
        // 第一行
        for (int i = 1; i < grid[0].length; i++) {
            if (grid[0][i] == '1' && grid[0][i - 1] == '1') {
                unionSet.union(0, i, 0, i - 1);
            }
        }
        // 第一列
        for (int i = 1; i < grid.length; i++) {
            if (grid[i][0] == '1' && grid[i - 1][0] == '1') {
                unionSet.union(i, 0, i - 1, 0);
            }
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    if (grid[i - 1][j] == '1') {
                        unionSet.union(i, j, i - 1, j);
                    }
                    if (grid[i][j - 1] == '1') {
                        unionSet.union(i, j, i, j - 1);
                    }
                }
            }
        }
        return unionSet.getSize();
    }

    class UnionSet200 {
        private int size;

        private int col;

        private int[] parent;

        private int[] sizeArr;

        UnionSet200(char[][] grid) {
            this.col = grid[0].length;
            this.size = 0;
            this.parent = new int[grid.length * grid[0].length];
            this.sizeArr = new int[grid.length * grid[0].length];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '1') {
                        size++;
                    }
                    parent[getIndex(i, j)] = getIndex(i, j);
                    sizeArr[getIndex(i, j)] = 1;
                }
            }
        }

        public void union(int x1, int y1, int x2, int y2) {
            int father1 = findFather(x1, y1);
            int father2 = findFather(x2, y2);
            if (father1 != father2) {
                if (sizeArr[father1] > sizeArr[father2]) {
                    parent[father2] = father1;
                    sizeArr[father1] += sizeArr[father2];
                } else {
                    parent[father1] = father2;
                    sizeArr[father2] += sizeArr[father1];
                }
                size--;
            }
        }

        public int findFather(int x, int y) {
            int index = getIndex(x, y);
            int[] help = new int[parent.length];
            int helpIndex = 0;
            while (parent[index] != index) {
                help[helpIndex++] = index;
                index = parent[index];
            }
            while (helpIndex > 0) {
                parent[help[--helpIndex]] = index;
            }
            return index;
        }

        public int getIndex(int x, int y) {
            return x * col + y;
        }

        public int getSize() {
            return size;
        }
    }

    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        Code0200 code0200 = new Code0200();
        System.out.println(code0200.numIslands2(grid));
    }
}
