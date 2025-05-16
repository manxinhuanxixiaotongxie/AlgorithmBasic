package systemlearning.code15;

/**
 * @Description 省份数量
 * 有n个城市，其中一些彼此相连，另一些没有相连。
 * 如果城市a与城市b直接相连，且城市b与城市c直接相连，
 * 那么城市a与城市c间接相连。
 * 省份 是一组直接或间接相连的城市，
 * <p>
 * https://leetcode.cn/problems/number-of-provinces/description/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 * @Author Scurry
 * @Date 2023-10-01 14:22
 */
public class Code01_FriendCircles {

//    public int findCircleNum(int[][] isConnected) {
//        if (isConnected == null || isConnected.length == 0) {
//            return 0;
//        }
//        return process(isConnected);
//    }
//
//    /**
//     * 递归函数
//     *
//     * @param isConnected
//     * @return
//     */
//    public int process(int[][] isConnected) {
//        int ans = 0;
//        for (int row = 0; row < isConnected.length; row++) {
//            for (int col = row; col < isConnected[0].length; col++) {
//                if (isConnected[row][col] == 1) {
//                    affect(isConnected, row, col);
//                    ans ++;
//                }
//            }
//        }
//        return ans;
//    }
//
//    public void affect(int[][] isConnected, int row, int col) {
//        if (isConnected == null || row < 0 || row >= isConnected.length || col < 0 || col >= isConnected[0].length) {
//            return;
//        }
//        if (isConnected[row][col] == 1) {
//            isConnected[row][col] = 2;
//            //上下左右
//            affect(isConnected, row - 1, col);
//            affect(isConnected, row + 1, col);
//            affect(isConnected, row, col - 1);
//            affect(isConnected, row, col + 1);
//        }
//
//    }
//
//    public static void main(String[] args) {
//        int[][] isConnected = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
//
//
//        Code01_FriendCircles code01_friendCircles = new Code01_FriendCircles();
//        int circleNum = code01_friendCircles.findCircleNum(isConnected);
//        System.out.println(circleNum);
//    }


    /*
     *   深度优先遍历
     * [[1,0,0,1],
     * [0,1,1,0],
     * [0,1,1,1],
     * [1,0,1,1]]
     * [0][0] [3,0][0,3] [2,1][1,2] [3,2][0,1]
     *遍历所有的城市
     * 对于每个城市 如果该城市没有被访问过，则从该城市开始深度优先遍历
     * 深度优先遍历的过程中，将该城市的所有连接城市都标记为已访问
     * 深度优先遍历结束后，进行下一个城市的遍历
     * @param isConnected
     * @return
     */

    public int findCircleNum(int[][] isConnected) {
        int cities = isConnected.length;
        boolean[] visited = new boolean[cities];
        int provinces = 0;
        for (int i = 0; i < cities; i++) {
            if (!visited[i]) {
                process(isConnected, visited, cities, i);
                provinces++;
            }
        }
        return provinces;
    }

    public void process(int[][] isConnected, boolean[] visited, int cities, int i) {
        for (int j = 0; j < cities; j++) {
            if (isConnected[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                process(isConnected, visited, cities, j);
            }
        }
    }

    public int findCircleNum2(int[][] isConnected) {
        int cities = isConnected.length;
        UnionFind unionFind = new UnionFind(cities);
        for (int i = 0; i < cities; i++) {
            for (int j = 0; j < cities; j++) {
                if (isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.getSets();
    }


    // 并查集 使用数组实现
    class UnionFind {
        // 新建一个用于定义父节点的数组
        private int[] partent;
        private int[] size;
        private int[] help;

        private int sets;

        UnionFind(int N) {
            sets = N;
            size = new int[N];
            partent = new int[N];
            help = new int[N];
            for (int i = 0; i < N; i++) {
                partent[i] = i;
                size[i] = 1;
            }
        }

        // findFather 是否是一个祖先
        public int findFather(int i) {
            int index = 0;
            while (i != partent[i]) {
                help[index++] = i;
                i = partent[i];
            }
            for (int j = 0; j < index; j++) {
                partent[help[j]] = i;
            }
            return i;
        }

        /**
         * 合并两个集合
         *
         * @param i
         * @param j
         */
        public void union(int i, int j) {
            int iFather = findFather(i);
            int jFather = findFather(j);
            if (iFather != jFather) {
                if (size[iFather] > size[jFather]) {
                    partent[jFather] = iFather;
                    size[iFather] += size[jFather];
                } else {
                    partent[iFather] = jFather;
                    size[jFather] += size[iFather];
                }
                sets--;
            }
        }

        public int getSets() {
            return sets;
        }
    }
}





