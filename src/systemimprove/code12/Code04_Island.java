package systemimprove.code12;

import java.io.FileOutputStream;
import java.util.*;

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
 */
public class Code04_Island {

    public int numIslands1(char[][] board) {
        if (board == null || board.length == 0) {
            return 0;
        }

        int N = board.length;
        int M = board[0].length;
        List<Character> dots = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == '1') {
                    dots.add(board[i][j]);
                }
            }
        }
        UnionSet unionSet = new UnionSet(dots);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == '1') {
                    if (i - 1 >= 0 && board[i - 1][j] == '1') {
                        unionSet.union(board[i][j], board[i - 1][j]);
                    }
                    if (i + 1 < N && board[i + 1][j] == '1') {
                        unionSet.union(board[i][j], board[i + 1][j]);
                    }
                    if (j - 1 >= 0 && board[i][j - 1] == '1') {
                        unionSet.union(board[i][j], board[i][j - 1]);
                    }
                    if (j + 1 < M && board[i][j + 1] == '1') {
                        unionSet.union(board[i][j], board[i][j + 1]);
                    }
                }
            }
        }

        return unionSet.sets();

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

}
