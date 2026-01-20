package leetcode.classic150;

public class Code200 {
    /**
     * 并查集
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        UnionSet unionSet = new UnionSet(grid);
        // 第一行
        for (int col = 1; col < grid[0].length; col++) {
            if (grid[0][col - 1] == '1' && grid[0][col] == '1') {
                unionSet.union(0, col - 1, 0, col);
            }
        }
        // 第一列
        for (int row = 1; row < grid.length; row++) {
            if (grid[row][0] == '1' && grid[row - 1][0] == '1') {
                unionSet.union(row - 1, 0, row, 0);
            }
        }
        // 普遍位置
        for (int row = 1; row < grid.length; row++) {
            for (int col = 1; col < grid[0].length; col++) {
                if (grid[row][col] == '1') {
                    if (grid[row - 1][col] == '1') {
                        unionSet.union(row - 1, col, row, col);
                    }
                    if (grid[row][col - 1] == '1') {
                        unionSet.union(row, col - 1, row, col);
                    }
                }
            }
        }
        return unionSet.getSize();
    }

    class UnionSet {
        int[] parent;
        int[] sizeMap;
        int size;
        int col;
        int n;

        UnionSet(char[][] grid) {
            int n = grid.length;
            this.n = n;
            this.col = grid[0].length;
            parent = new int[n * col];
            sizeMap = new int[n * col];
            this.size = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == '1') {
                        size++;
                        parent[i * col + j] = i * col + j;
                        sizeMap[i * col + j] = 1;
                    }
                }
            }
        }

        private int getIndex(int i, int j) {
            return i * col + j;
        }

        public int getSize() {
            return size;
        }

        private boolean isSameFather(int i1, int j1, int i2, int j2) {
            int index1 = getIndex(i1, j1);
            int index2 = getIndex(i2, j2);
            return findFather(i1, j1) == findFather(i2, j2);
        }

        private int findFather(int i, int j) {
            int index = getIndex(i, j);
            int[] help = new int[n * col];
            int hi = 0;
            while (parent[index] != index) {
                help[hi++] = index;
                index = parent[index];
            }
            for (int k = 0; k < hi; k++) {
                parent[help[k]] = index;
            }
            return index;
        }

        public void union(int i1, int j1, int i2, int j2) {

            // 不是一个父亲进行合并
            int father = findFather(i1, j1);
            int father2 = findFather(i2, j2);
            int size1 = sizeMap[father];
            int size2 = sizeMap[father2];
            if (father2 != father) {
                if (size1 >= size2) {
                    parent[father2] = father;
                    sizeMap[father] = size1 + size2;
                } else {
                    parent[father] = father2;
                    sizeMap[father2] = size1 + size2;
                }
                this.size--;
            }
        }
    }
}
