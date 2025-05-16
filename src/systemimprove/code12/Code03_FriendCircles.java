package systemimprove.code12;

/**
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * <p>
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * <p>
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * <p>
 * 返回矩阵中 省份 的数量。
 * <p>
 * <p>
 * lettcode 547
 */
public class Code03_FriendCircles {

    public int findCircleNum(int[][] isConnected) {
        if (isConnected == null || isConnected.length == 0) {
            return 0;
        }

        UnionSet unionSet = new UnionSet(isConnected.length);
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected[i].length; j++) {
                if (isConnected[i][j] == 1) {
                    unionSet.union(i, j);
                }
            }
        }
        return unionSet.sets();

    }

    class UnionSet {
        // parents[i] = k 代表 i 的父亲是 k
        int[] parent;
        // sizes[i] = k 代表 i 所在的集合大小是 k
        int[] size;
        // help数组是在寻找父节点的过程中将并查集变得扁平
        int[] help;
        int sets;

        UnionSet(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            sets = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int a, int b) {
            int aFather = findFather(a);
            int bFather = findFather(b);
            if (aFather != bFather) {
                if (size[aFather] >= size[bFather]) {
                    parent[bFather] = aFather;
                    size[aFather] += size[bFather];
                } else {
                    parent[aFather] = bFather;
                    size[bFather] += size[aFather];
                }
                sets--;
            }
        }


        public int sets() {
            return sets;
        }

        // a b 是否属于一个集合
        public boolean isSameSet(int a, int b) {
            return findFather(a) == findFather(b);
        }


        public int findFather(int a) {
//            int cur = a;
//            while (cur != parent[cur]) {
//                cur = parent[cur];
//            } return cur;

            int hi = 0;
            int cur = a;
            while (cur != parent[cur]) {
                help[hi++] = cur;
                cur = parent[cur];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = cur;
            }
            return cur;
        }
    }
}
