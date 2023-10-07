package systemlearning.code15;

/**
 * @Description 并查集的应用-岛问题
 * @Author Scurry
 * @Date 2023-10-07 15:25
 */
public class Code02_IsLand {

    /**
     * 暴力递归
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int ans = 0;
        if (grid == null || grid.length == 0) {
            return ans;
        }

        int M = grid.length;
        int N = grid[0].length;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (grid[i][j] == '1') {
                    infect(grid, i, j, M, N);
                    ans++;
                }
            }
        }

        return ans;
    }

    public void infect(char[][] isLand, int i, int j, int M, int N) {
        if (i < 0 || i >= M || j < 0 || j >= N || isLand[i][j] != '1') {
            return;
        }
        isLand[i][j] = '2';
        infect(isLand, i - 1, j, M, N);
        infect(isLand, i + 1, j, M, N);
        infect(isLand, i, j - 1, M, N);
        infect(isLand, i, j + 1, M, N);
    }

    /**
     * 使用并查集解决问题
     * 以下代码有问题
     * 问题出在什么地方？
     *
     [["1","1","1","1","1","0","1","1","1","1"],["1","0","1","0","1","1","1","1","1","1"],["0","1","1","1","0","1","1","1","1","1"],["1","1","0","1","1","0","0","0","0","1"],["1","0","1","0","1","0","0","1","0","1"],["1","0","0","1","1","1","0","1","0","0"],["0","0","1","0","0","1","1","1","1","0"],["1","0","1","1","1","0","0","1","1","1"],["1","1","1","1","1","1","1","1","0","1"],["1","0","1","1","1","1","1","1","1","0"]]
     *
     * @param isLand
     * @return
     */
    public int numIslands2(char[][] isLand) {
        if (isLand == null || isLand.length == 0) {
            return 0;
        }

        int row = isLand.length;
        int col = isLand[0].length;
        UnionFind unionFind = new UnionFind(isLand);

        // 第0行
        for (int i = 1; i < col; i++) {
            if (isLand[0][i] == '1' && isLand[0][i - 1] == '1') {
                unionFind.union(0, i - 1, 0, i);
            }
        }

        // 第0列
        for (int j = 1; j < row; j++) {
            if (isLand[j][0] == '1' && isLand[j - 1][0] == '1') {
//                unionFind.union(0, j - 1, 0, j);
                unionFind.union(j - 1, 0, j, 0);
            }
        }

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (isLand[i][j] == '1') {
                    if (isLand[i][j - 1] == '1') {
                        unionFind.union(i, j - 1, i, j);
                        System.out.println("i = " + i + ", j = " + j + ", i - 1 = " + (i - 1) + ", j = " + j+"sets:"+unionFind.getSets());
                    }

                    if (isLand[i - 1][j] == '1') {
                        unionFind.union(i - 1, j, i, j);

                        System.out.println("i = " + i + ", j = " + j + ", i - 1 = " + (i - 1) + ", j = " + j+"sets:"+unionFind.getSets());

                    }


                }
            }
        }

        return unionFind.getSets();

    }

    class UnionFind {
        // 定义父节点信息
        private int[] parent;
        // 定义size
        private int[] size;
        // 定义辅助数组
        private int[] help;

        private int sets;

        private int col;


        UnionFind(char[][] isLand) {
            int row = isLand.length;
            col = isLand[0].length;
            int len = row * col;
            parent = new int[len];
            help = new int[len];
            size = new int[len];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (isLand[i][j] == '1') {
                        parent[index(i, j)] = index(i, j);
                        size[index(i, j)] = 1;
                        sets++;
                    }
                }
            }

        }


        public int index(int r, int c) {
            return r * col + c;
        }

        public int find(int i) {
            // 如果当前节点不等于父节点，就一直往上找
            int index = 0;
            if (i != parent[i]) {
                // 沿途记录遍历的节点信息
                help[index++] = i;
                i = parent[i];
            }

            for (int j = 0; j < index; j++) {
                // 将沿途的节点信息指向父节点
                parent[help[j]] = i;
            }

            return i;
        }

        public boolean isSameSet(int i, int j) {
            return find(i) == find(j);
        }

        public void union(int i1, int j1, int i2, int j2) {

            int i = index(i1, j1);
            int j = index(i2, j2);

            int aParent = find(i);
            int bParent = find(j);
            // 当a b两个节点不属于一个集合 将两个节点进行合并
            if (aParent != bParent) {
                int aSize = size[aParent];
                int bSize = size[bParent];
                if (aSize > bSize) {
                    size[aParent] += size[bParent];
                    parent[bParent] = aParent;
                } else {
                    size[bParent] += size[aParent];
                    parent[aParent] = bParent;
                }

                sets--;
            }
        }


        public int getSets() {
            return sets;
        }

    }

    public static void main(String[] args) {
        char[][] isLand = {
                {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1'},
                {'1', '0', '1', '0', '1', '1', '1', '1', '1', '1'},
                {'0', '1', '1', '1', '0', '1', '1', '1', '1', '1'},
                {'1', '1', '0', '1', '1', '0', '0', '0', '0', '1'},
                {'1', '0', '1', '0', '1', '0', '0', '1', '0', '1'},
                {'1', '0', '0', '1', '1', '1', '0', '1', '0', '0'},
                {'0', '0', '1', '0', '0', '1', '1', '1', '1', '0'},
                {'1', '0', '1', '1', '1', '0', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '0', '1'},
                {'1', '0', '1', '1', '1', '1', '1', '1', '1', '0'}
        };

        Code02_IsLand code02_isLand = new Code02_IsLand();
        System.out.println(code02_isLand.numIslands2(isLand));
    }
}



