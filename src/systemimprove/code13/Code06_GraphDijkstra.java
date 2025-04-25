package systemimprove.code13;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * dijkstra算法
 * 就是为了计算从一个节点出发到其他所有节点的最短路径的问题
 *
 * 过程：
 * 1.给定出发点 建一张距离表 表中没有的数据表示无穷大
 * 2.从给定点出发
 */
public class Code06_GraphDijkstra {

    /**
     *
     * 本实现方法没有负边
     *
     * 1.新建一张距离表 一开始的时候所有的点都不在距离表中 任务距离正无穷大
     * 2.开始点自己到自己的距离认为是0
     * 3.考察最小距离的边
     * 4.如果不在距离表 说明没到到达过这个点  更新距离起始点的距离到最近点的距离加上当前边的权重
     *
     * @param from
     * @return
     */
    public Map<Node, Integer> dijkstra1(Node from) {
        Map<Node, Integer> distanceMap = new HashMap<>();
        Set<Node> selectNode = new HashSet<>();
        distanceMap.put(from, 0);
        // 找到距离表中的最小值
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectNode);

        while (minNode != null) {

            int distance = distanceMap.get(minNode);
            // 边到点
            for (Edge edge : minNode.edges) {
                Node to = edge.to;

                if (!distanceMap.containsKey(to)) {
                    // 原始点 minNode 到 to 的距离
                    distanceMap.put(to, distance + edge.weight);
                } else {
                    distanceMap.put(to, Math.min(distanceMap.get(to), distance + edge.weight));
                }
            }
            selectNode.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectNode);
        }

        return distanceMap;
    }

    public Node getMinDistanceAndUnselectedNode(Map<Node, Integer> distanceMap, Set<Node> selectNode) {
        Node ans = null;
        int min = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!selectNode.contains(node) && distance < min) {
                ans = node;
                min = distance;
            }
        }
        return ans;
    }

    public Map<Node,Integer> dijkstra12(Node from,int size) {
        Map<Node,Integer> result = new HashMap<>();
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(from,0);
        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to,edge.weight + distance);
            }
            result.put(cur,distance);
        }

        return result;
    }

    public Node minNode() {
        return null;
    }

    class NodeHeap {
        private Map<Node,Integer> distanceMap;
        private int size;
        // 在这个堆结构中，如果一个数已经进入过堆并且已经结算过距离的话 将index的位置置为-1
        private Map<Node,Integer> indexMap;
        private Node[] heap;

        NodeHeap(int limit) {
            distanceMap = new HashMap<>();
            size = 0;
            indexMap = new HashMap<>();
            heap = new Node[limit];
        }

        public boolean isEmpty() {
            return size == 0;
        }
        public NodeRecord pop() {
            NodeRecord record = new NodeRecord(heap[0],distanceMap.get(heap[0]));
            swap(0, size - 1);
            indexMap.put(heap[size - 1], -1);
            distanceMap.remove(heap[size - 1]);
            heap[size - 1] = null;
            heapify(0, --size);
            return record;
        }

        private void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallest = left + 1 < size && distanceMap.get(heap[left + 1]) < distanceMap.get(heap[left])
                        ? left + 1
                        : left;
                smallest = distanceMap.get(heap[smallest]) < distanceMap.get(heap[index]) ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        public void addOrUpdateOrIgnore(Node node,int distance) {
            // 进过小跟堆 但是还没有进行结算
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                heapInsert(indexMap.get(node));

            }
            // 之前完全没有进过小根堆 加入小跟堆
            if (!isEntered(node)) {
                distanceMap.put(node, distance);
                indexMap.put(node, size);
                heap[size] = node;
                heapInsert(size++);
            }
        }

        public void heapInsert(int index) {
            while (distanceMap.get(heap[index]) < distanceMap.get(heap[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void swap(int index1, int index2) {
            indexMap.put(heap[index1], index2);
            indexMap.put(heap[index2], index1);
            Node temp = heap[index1];
            heap[index1] = heap[index2];
            heap[index2] = temp;
        }

        public boolean inHeap(Node node) {
            return isEntered(node) && indexMap.get(node) != -1;
        }

        public boolean isEntered(Node node) {
         return indexMap.containsKey(node);
        }
    }

    class NodeRecord {
        public Node node;
        public int distance;

        NodeRecord(Node node,int distance) {
            this.node = node;
            this.distance = distance;
        }

    }
}
