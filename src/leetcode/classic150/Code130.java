package leetcode.classic150;

/**
 * 给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' 组成，捕获 所有 被围绕的区域：
 *
 * 连接：一个单元格与水平或垂直方向上相邻的单元格连接。
 * 区域：连接所有 'O' 的单元格来形成一个区域。
 * 围绕：如果您可以用 'X' 单元格 连接这个区域，并且区域中没有任何单元格位于 board 边缘，则该区域被 'X' 单元格围绕。
 * 通过 原地 将输入矩阵中的所有 'O' 替换为 'X' 来 捕获被围绕的区域。你不需要返回任何值。
 */
public class Code130 {
    /**
     * 并查集
     *
     * @param board
     */
    public void solve(char[][] board) {
        UnionSet unionSet = new UnionSet(board);
        int n = board.length;
        int col = board[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    if (i > 0 && board[i - 1][j] == 'O') {
                        unionSet.union(i, j, i - 1, j);
                    }
                    if (j > 0 && board[i][j - 1] == 'O') {
                        unionSet.union(i, j, i, j - 1);
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    int father = unionSet.findFather(i, j);
                    if (!unionSet.isOnEdge[father]) {
                        board[i][j] = 'X';
                    }
                }
            }
        }
    }

    class UnionSet {
        int[] parent;
        int[] sizeMap;
        boolean[] isOnEdge;
        int size;
        int col;
        int n;
        int[] help ;

        UnionSet(char[][] grid) {
            int n = grid.length;
            this.n = n;
            this.col = grid[0].length;
            parent = new int[n * col];
            sizeMap = new int[n * col];
            this.size = 0;
            this.help = new int[n * col];
            isOnEdge = new boolean[n * col];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == 'O') {
                        size++;
                        parent[i * col + j] = i * col + j;
                        sizeMap[i * col + j] = 1;
                    }
                    if ((i == 0 || j == 0 || i == n - 1 || j == col - 1) && grid[i][j] == 'O') {
                        isOnEdge[i * col + j] = true;
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
            int hi = 0;
            boolean onEdge = false;
            while (parent[index] != index) {
                help[hi++] = index;
                if (isOnEdge[index]) {
                    onEdge = true;
                }
                index = parent[index];
            }
            for (int k = 0; k < hi; k++) {
                if (onEdge) {
                    isOnEdge[help[k]] = true;
                }
                parent[help[k]] = index;
            }
            if (onEdge) {
                isOnEdge[index] = true;
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
                    // 这里要使用或 只要有一个在边上 那么合并后的也在边上
                    isOnEdge[father] |= isOnEdge[father2];
                } else {
                    parent[father] = father2;
                    sizeMap[father2] = size1 + size2;
                    isOnEdge[father2] |= isOnEdge[father];
                }
                this.size--;

            }
        }
    }
}
