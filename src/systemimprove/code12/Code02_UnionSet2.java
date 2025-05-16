package systemimprove.code12;

/**
 * 并查集提供了两种方式实现
 * 1.第一种 使用辅助数据结构
 * 1.1 使用Map 指定两个map  一个是节点到父节点的映射 一个是节点到节点所在集合的大小
 * <p>
 * 2.第二种 使用数组实现
 * 2.1 使用数组实现 两个数组 一个是节点到父节点的映射 一个是节点所在集合的大小
 * 2.2 使用辅助数组 干什么用呢？ 在向上找父亲的过程中，把沿途的点都指向父亲 模拟栈操作
 * <p>
 * 在笔试的过程中，如果遇到并查集的问题，优先使用第二种方式
 */
public class Code02_UnionSet2 {
    // 使用数组实现
    public static class UnionSet {
        // parents[i] = k 代表 i 的父亲是 k
        private int[] parents;
        // sizes[i] = k 代表 i 所在的集合大小是 k
        private int[] sizes;
        // help 是辅助数组 干什么用呢？
        // 在向上找父亲的过程中，把沿途的点都指向父亲
        private int[] help;

        public UnionSet(int size) {
            parents = new int[size];
            sizes = new int[size];
            help = new int[size];
            for (int i = 0; i < size; i++) {
                parents[i] = i;
                sizes[i] = 1;
            }
        }

        public int findFather(int i) {
            int hi = 0;
            while (i != parents[i]) {
                // 沿途的点都记录下来
                help[hi++] = i;
                i = parents[i];
            }
            // 沿途的点都指向父亲
            for (hi--; hi >= 0; hi--) {
                parents[help[hi]] = i;
            }
            return i;
        }

        public boolean isSameSet(int i, int j) {
            return findFather(i) == findFather(j);
        }

        public void union(int i, int j) {
            int f1 = findFather(i);
            int f2 = findFather(j);
            if (f1 != f2) {
                int big = sizes[f1] >= sizes[f2] ? f1 : f2;
                int small = big == f1 ? f2 : f1;
                parents[small] = big;
                sizes[big] = sizes[f1] + sizes[f2];
            }
        }

        public int size() {
            return sizes.length;
        }
    }
}
