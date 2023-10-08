package systemlearning.code14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @Description
 * 并查集
 * 1.使用map实现
 * 2.使用数组实现
 * @Author Scurry
 * @Date 2023-10-01 10:14
 */
public class Code05_Union {

    /**
     * 1.初始化的时候，将所有的元素都维护成一个单独的集合，并且将自己的父节点指向自己
     * 2.在合并的时候，将两个集合的父节点指向同一个节点
     */


    public static class UnionFind<V> {
        private Map<V, V> parent;
        private Map<V, Integer> size;

        UnionFind(List<V> values) {
            parent = new HashMap<>();
            size = new HashMap<>();
            for (V v : values) {
                parent.put(v, v);
                size.put(v, 1);
            }
        }

        public V findFather(V v) {
            V father = parent.get(v);
            if (father != v) {
                father = findFather(father);
            }
            parent.put(v, father);
            return father;
        }

        public V findFather2(V v) {
            V father = parent.get(v);
            Stack<V> stack = new Stack<>();
            while (father != v) {
                stack.push(v);
                v = father;
                father = parent.get(v);
            }
            while (!stack.isEmpty()) {
                parent.put(stack.pop(), father);
            }
            return father;
        }

        public void union(V a, V b) {
            V aFanther = findFather(a);
            V bFather = findFather(b);
            if (aFanther != bFather) {
                // 小挂大
                int aSize = size.get(a);
                int bSize = size.get(b);
                // b挂a
                if (aSize > bSize) {
                    parent.put(b, a);
                    size.put(a, aSize + bSize);
                    size.remove(b);
                } else {
                    parent.put(a, b);
                    size.put(b, aSize + bSize);
                    size.remove(a);
                }
            }
        }

        public boolean isSameSet(V a, V b) {
            return findFather(a) == findFather(b);
        }

    }

    /**
     * 使用数组的方式实现并查集
     */
    public static class UnionFind2 {

        private int[] parent;
        private int[] size;
        private int[] help;
        UnionFind2(int N) {
            parent = new int[N];
            help = new int[N];
            for (int i = 0;i<N;i++) {
                // i位置的父节点是谁 初始化是自己
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int findfather(int a) {
            // 找到父节点
//            int father = parent[a];
//
//            while (father != a) {
//                // 记录沿途父节点信息
//                help[a] = father;
//                a = father;
//                father = parent[a];
//            }

            int hi = 0;
            while (a != parent[a]) {
                help[hi++] = a;
                a = parent[a];
            }
            // help[i]代表的从当前节点找到代表节点过程中沿途遍历节点
            // 将沿途遍历的节点均指向代表节点
            for (int i = hi-1;i>=0;i--) {
                parent[help[i]] = a;
            }

            return a;
        }

        public void union(int a,int b) {
            int aFather = findfather(a);
            int bFather = findfather(b);
            if (aFather != bFather) {
                int aSize = size[aFather];
                int bSize = size[bFather];
                if (aSize > bSize) {
                    parent[bFather] = aFather;
                    size[aFather] = aSize + bSize;
                } else {
                    parent[aFather] = bFather;
                    size[bFather] = aSize + bSize;
                }
            }
        }

    }

}
