package systemlearning.code16;

import java.util.*;

/**
 * @Description 最小生成树K算法
 * @Author Scurry
 * @Date 2023-10-10 10:39
 */
public class Code05_MinSpanningTreeK {

    /**
     * 1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
     * 2）当前的边要么进入最小生成树的集合，要么丢弃
     * 3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
     * 4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
     * 5）考察完所有边之后，最小生成树的集合也得到了
     */
    public Set<Edge> kruskalMST(Graph graph) {

        /**
         * 思路：
         * 使用并查集 给所有的节点初始化
         * 解锁边的时候 如果边的两个节点不在一个集合中 就解锁
         *
         * 因为是 依次考察变的权重 使用小跟堆将所有的边放入
         *
         * 并查集是解决联通性的比较好的思路
         * k算法解决最小生成树的过程：
         * 1.创建并查集 初始状态每个点都是一个独立的集合
         * 2.创建一个小跟堆  K算法的的出发点就是边的权重最小到最大
         * 3.在小跟堆弹出的过程中 如果已经在一个集合中 就不要这个边
         */
        // 创建一个小根堆 根据边的权重
        PriorityQueue<Edge> edgePriorityQueue = new PriorityQueue<>(new ComparatorEdge());
        for (Edge edge : graph.edges) {
            edgePriorityQueue.add(edge);
        }
        UnionSet unionSet = new UnionSet();
        unionSet.makeSet(graph);
        Set<Edge> result = new HashSet<>();
        while (!edgePriorityQueue.isEmpty()) {
            Edge pop = edgePriorityQueue.poll();
            Node from = pop.from;
            Node to = pop.to;
            if (!unionSet.isSameSet(from, to)) {
                result.add(pop);
                unionSet.unionSet(from, to);
            }
        }
        return result;
    }


    class UnionSet {
        private Map<Node, Node> parentMap;
        private Map<Node, Integer> sizeMap;

        public UnionSet() {
            parentMap = new HashMap<>();
            sizeMap = new HashMap<>();
        }

        public void makeSet(Graph graph) {
            parentMap.clear();
            sizeMap.clear();
            for (Node node : graph.nodes.values()) {
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        public boolean isSameSet(Node a, Node b) {
            return findFather(a) == findFather(b);
        }

        public Node findFather(Node node) {
            Stack<Node> stack = new Stack<>();
            while (node != parentMap.get(node)) {
                stack.push(node);
                node = parentMap.get(node);
            }
            while (!stack.isEmpty()) {
                parentMap.put(stack.pop(), node);
            }
            return node;
        }

        public void unionSet(Node a, Node b) {
            Node aFather = findFather(a);
            Node bFather = findFather(b);
            if (aFather != bFather) {
                int aSize = sizeMap.get(aFather);
                int bSize = sizeMap.get(bFather);
                if (aSize > bSize) {
                    sizeMap.put(aFather, aSize + bSize);
                    parentMap.put(bFather, aFather);
                    sizeMap.remove(bFather);
                } else {
                    sizeMap.put(bFather, aSize + bSize);
                    parentMap.put(aFather, bFather);
                    sizeMap.remove(aFather);
                }
            }
        }
    }

    /**
     * 手写堆
     *
     * @param <Node>
     */
    class MyHeap<Node> {
        Node[] heap;
        int heapSize;
        Comparator<Edge> comparator;

        MyHeap(Comparator<Edge> comparator) {
            this.comparator = comparator;
        }


        public void add(Edge edge) {
            heapSize++;
            int fatherIndex = (heapSize - 1) / 2;
            while (comparator.compare(edge, (Edge) heap[fatherIndex]) < 0) {
                swap(fatherIndex, heapSize);
                fatherIndex = (fatherIndex - 1) / 2;
            }
        }

        public Edge poll() {
            Edge ans = (Edge) heap[0];
            heapify();
            return ans;
        }

        public void heapify() {
            swap(0, --heapSize);
//            int index = 0;
//            int leftIndex = index * 2 + 1;
//            int largeIndex = leftIndex + 1 < heapSize && comparator.compare((Edge) heap[leftIndex], (Edge) heap[leftIndex + 1]) > 0 ? leftIndex + 1 : leftIndex;
//            while (heapSize > largeIndex && comparator.compare((Edge) heap[index], (Edge) heap[largeIndex]) > 0) {
//                swap(index, largeIndex);
//                index = largeIndex;
//                leftIndex = index * 2 + 1;
//                largeIndex = leftIndex + 1 < heapSize && comparator.compare((Edge) heap[leftIndex], (Edge) heap[leftIndex + 1]) > 0 ? leftIndex + 1 : leftIndex;
//            }
            int index = 0;
            int leftIndex = index * 2 + 1;
            while (leftIndex < heapSize) {
                int largeIndex = leftIndex + 1 < heapSize && comparator.compare((Edge) heap[leftIndex], (Edge) heap[leftIndex + 1]) > 0 ? leftIndex + 1 : leftIndex;
                largeIndex = comparator.compare((Edge) heap[index], (Edge) heap[largeIndex]) > 0 ? largeIndex : index;
                if (largeIndex == index) {
                    break;
                }
                swap(index, largeIndex);
                index = largeIndex;
                leftIndex = index * 2 + 1;
            }
        }

        public void swap(int i, int j) {
            Edge temp = (Edge) heap[i];
            heap[i] = heap[j];
            heap[j] = (Node) temp;
        }

    }

    class ComparatorEdge implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }
}
