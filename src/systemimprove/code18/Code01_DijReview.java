package systemimprove.code18;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 经典的dij算法
 */
public class Code01_DijReview {
    // dijsktra算法
    // 求的是最小距离的问题
    public Map<GraphNode, Integer> diJ1(GraphNode head) {
        // 给点一个出发点 找到这个点到图中其他点的最短距离
        Map<GraphNode, Integer> distanceMap = new HashMap<>();
        distanceMap.put(head, 0);
        // 已经结算过的点   怎么理解这个结算过的点 防止重复结算
        Set<GraphNode> selectNode = new HashSet<>();
        GraphNode minNode = getMinNode(distanceMap, selectNode);
        // 如果还能查出来的minNode 说明图中能参与结算的点还有 继续进行图的最小距离的结算
        while (minNode != null) {
            for (GraphEdge nextEdge : minNode.edges) {
                GraphNode toNode = nextEdge.to;
                // 如果toNode没有结算过
                if (distanceMap.get(toNode) != null) {
                    distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distanceMap.get(minNode) + nextEdge.weight));
                } else {
                    // 如果distanceMap没有的话 标识从head出发经过minNode到TONode的距离还没有计算过 直接加进去 距离就是minNode的距离加上minNode到toNode的距离
                    distanceMap.put(toNode, distanceMap.get(minNode) + nextEdge.weight);
                }
            }
            selectNode.add(minNode);
            minNode = getMinNode(distanceMap, selectNode);
        }
        return distanceMap;

    }

    /**
     * 找到没有结算过的最小距离的点，并返回
     *
     * @return
     */
    public GraphNode getMinNode(Map<GraphNode, Integer> distanceMap, Set<GraphNode> selectedNode) {
        GraphNode ans = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<GraphNode, Integer> entry : distanceMap.entrySet()) {
            // 在distanceMap中找到最小距离的点  并且没有结算过
            if (selectedNode.contains(entry.getKey()) && entry.getValue() < minDistance) {
                ans = entry.getKey();
                minDistance = entry.getValue();
            }
        }
        return ans;
    }

    /**
     * DIJ改进版本  使用加强堆
     *
     * @param head
     * @return
     */
    public Map<GraphNode, Integer> diJ2(GraphNode head) {
        if (head == null) {
            return null;
        }
        Map<GraphNode, Integer> ans = new HashMap<>();
        HeapGreater heapGreater = new HeapGreater();
        heapGreater.addOrUpdateOrIgnore(head, 0);
        while (heapGreater.size() > 0) {
            ReCord reCord = heapGreater.pop();
            for (GraphEdge edge : reCord.node.edges) {
                heapGreater.addOrUpdateOrIgnore(edge.to, reCord.distance + edge.weight);

            }
            ans.put(reCord.node, reCord.distance);
        }
        return ans;
    }
}

class HeapGreater {
    // 经典distanceMap放在堆中进行维护
    private Map<GraphNode, Integer> distanceMap;
    // 小跟堆的维护
    private GraphNode[] nodes;

    // 维护小跟堆中节点所处的index位置 方便快速找到节点
    private Map<GraphNode, Integer> indexMap;

    private int size;

    public int size() {
        return size;
    }

    public void addOrUpdateOrIgnore(GraphNode node, int diatance) {
        // add
        if (!isEntered(node)) {
            // 没有进过堆表示是第一次进入堆中 直接将节点加入堆中
            distanceMap.put(node, diatance);
            indexMap.put(node, size);
            nodes[size] = node;
            heapInsert(size++);
        }
        // update
        if (inHeap(node)) {
            distanceMap.put(node, Math.min(distanceMap.get(node), diatance));
            heapInsert(indexMap.get(node));
        }
    }

    public ReCord pop() {
        ReCord reCord = new ReCord(nodes[0], distanceMap.get(nodes[0]));
        swap(0, size - 1);
        nodes[size - 1] = null;
        distanceMap.remove(nodes[size - 1]);
        indexMap.put(nodes[size - 1], -1);
        size--;
        heapify(0);
        return reCord;
    }

    private void heapify(int index) {
        int leftIndex = 2 * index + 1;
        while (leftIndex < size) {
            int smallest = leftIndex + 1 < size && distanceMap.get(nodes[leftIndex + 1]) < distanceMap.get(nodes[leftIndex]) ? leftIndex + 1 : leftIndex;
            smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
            if (smallest == index) {
                break;
            }
            swap(smallest, index);
            index = smallest;
            leftIndex = 2 * index + 1;
        }
    }

    private void heapInsert(int index) {
        while (distanceMap.get(index) < distanceMap.get((index - 1) / 2)) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void swap(int i, int j) {
        indexMap.put(nodes[i], j);
        indexMap.put(nodes[j], i);
        GraphNode tmp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = tmp;
    }

    /**
     * 节点是否已经结算过 与经典selectedNode结构相同
     *
     * @param node
     * @return
     */
    public boolean isEntered(GraphNode node) {
        return indexMap.containsKey(node) && indexMap.get(node) != -1;
    }

    // 判断是不是在堆中
    public boolean inHeap(GraphNode node) {
        return distanceMap.get(node) != null;
    }

}

class ReCord {
    public GraphNode node;
    public int distance;

    public ReCord(GraphNode node, int distance) {
        this.node = node;
        this.distance = distance;
    }
}
