package systemimprove.code12;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 岛问题
 * <p>
 * 给定一个二维数组matrix，里面的值不是1就是0，
 * 上、下、左、右相邻的1认为是一片岛，
 * 返回matrix中岛的数量
 * <p>
 * <p>
 * 思路：使用并查集
 * matrix[i][j]为1的时候 上下左右为1的时候进行合并
 * <p>
 * <p>
 * <p>
 * leetcode 200题
 */
public class Code04_Island {

    public int numIslands1(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int N = grid.length;
        int M = grid[0].length;
        List<Character> dots = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == '1') {
                    dots.add(grid[i][j]);
                }
            }
        }
        UnionSet unionSet = new UnionSet(dots);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == '1') {
                    if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                        unionSet.union(grid[i][j], grid[i - 1][j]);
                    }
                    if (i + 1 < N && grid[i + 1][j] == '1') {
                        unionSet.union(grid[i][j], grid[i + 1][j]);
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                        unionSet.union(grid[i][j], grid[i][j - 1]);
                    }
                    if (j + 1 < M && grid[i][j + 1] == '1') {
                        unionSet.union(grid[i][j], grid[i][j + 1]);
                    }
                }
            }
        }

        return unionSet.sets();

    }

    public int numIslands2(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int M = grid.length;
        int N = grid[0].length;

        UnionSet2 unionSet2 = new UnionSet2(grid);

        // 算第0行的位置
        for (int i = 1; i < N; i++) {
            if (grid[0][i] == '1' && grid[0][i - 1] == '1') {
                unionSet2.union(0, i, 0, i - 1);
            }
        }


        // 算第0行的位置
        for (int j = 1; j < M; j++) {
            if (grid[j][0] == '1' && grid[j - 1][0] == '1') {
                unionSet2.union(j, 0, j - 1, 0);
            }
        }


        // 普遍位置
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                if (grid[i][j] == '1') {
                    if (grid[i - 1][j] == '1') {
                        unionSet2.union(i, j, i - 1, j);
                    }
                    if (grid[i][j - 1] == '1') {
                        unionSet2.union(i, j, i, j - 1);
                    }
                }
            }
        }

        return unionSet2.sets();

    }

    class UnionSet<V> {
        private Map<V, Dot<V>> node;
        private Map<Dot<V>, Integer> size;
        private Map<Dot<V>, Dot<V>> parent;
        private int sets;

        UnionSet(List<V> dots) {
            node = new HashMap<>();
            size = new HashMap<>();
            parent = new HashMap<>();
            sets = dots.size();
            for (V dot : dots) {
                Dot<V> d = new Dot<>();
                node.put(dot, d);
                size.put(d, 1);
                parent.put(d, d);
            }
        }

        public int sets() {
            return sets;
        }

        public Dot<V> findFather(V v) {
            Dot<V> dot = node.get(v);
            Stack<Dot<V>> stack = new Stack<>();
            while (dot != parent.get(dot)) {
                stack.push(dot);
                dot = parent.get(dot);
            }
            while (!stack.isEmpty()) {
                parent.put(stack.pop(), dot);
            }
            return dot;
        }

        public boolean isSameSet(V a, V b) {
            return findFather(a) == findFather(b);
        }

        public void union(V a, V b) {
            Dot<V> aFather = findFather(a);
            Dot<V> bFather = findFather(b);
            if (aFather != bFather) {
                int aSize = size.get(aFather);
                int bSize = size.get(bFather);
                if (aSize >= bSize) {
                    parent.put(bFather, aFather);
                    size.put(aFather, aSize + bSize);
                    size.remove(bFather);
                } else {
                    parent.put(aFather, bFather);
                    size.put(bFather, aSize + bSize);
                    size.remove(aFather);
                }
                sets--;
            }
        }

    }

    class Dot<V> {

    }


    /**
     * 使用数组实现
     */
    class UnionSet2 {
        private int[] father;
        private int[] size;
        private int[] help;
        private int sets;
        private int col;

        /**
         * 使用数组实现
         *
         * @param board
         */
        UnionSet2(char[][] board) {
            int M = board.length;
            int N = board[0].length;
            col = N;
            int len = N * M;
            father = new int[len];
            size = new int[len];
            help = new int[len];
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (board[i][j] == '1') {
                        int index = index(i, j);
                        father[index] = index;
                        size[index] = 1;
                        sets++;
                    }
                }
            }
        }

        public int sets() {
            return sets;
        }

//        public int findFather(int i) {
//            int hi = 0;
//            while (i != father[i]) {
//                help[hi++] = i;
//                i = father[i];
//            }
//            for (hi--; hi >= 0; hi--) {
//                father[help[hi]] = i;
//            }
//            return i;
//        }

        public int findFather(int c, int j) {
            int index = index(c, j);
            int hi = 0;
            while (index != father[index]) {
                help[hi++] = index;
                index = father[index];
            }
            for (hi--; hi >= 0; hi--) {
                father[help[hi]] = index;
            }
            return index;
        }

        public boolean isSameSet(int c1, int j1, int c2, int j2) {
            return findFather(c1, j1) == findFather(c2, j2);
        }

        public int index(int r, int c) {
            return r * col + c;
        }

        public void union(int c1, int j1, int c2, int j2) {
//            int aIndex = index(c1,j1);
//            int bIndex = index(c2,j2);
            int aFather = findFather(c1, j1);
            int bFather = findFather(c2, j2);
            if (aFather != bFather) {
                int aSize = size[aFather];
                int bSize = size[bFather];
                if (aSize >= bSize) {
                    father[bFather] = aFather;
                    size[aFather] = aSize + bSize;
                    size[bFather] = 0;
                } else {
                    father[aFather] = bFather;
                    size[bFather] = aSize + bSize;
                    size[aFather] = 0;
                }
                sets--;
            }
        }

    }

}
