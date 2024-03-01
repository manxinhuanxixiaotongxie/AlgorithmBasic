package systemimprove.code13;

import java.util.*;

/**
 * 给定一张图 请返回图的拓扑排序
 * 算法的过程如下：
 * 1.遍历整张图 找到所有入度为0的节点都找到
 *    1.1 使用map存储节点的入度 遍历一张图初始化
 *    2.2 再次遍历整张图 找到图的子节点
 *    3.3 遍历map 找到入度为0的点
 *    4.4 依次弹出队列 在弹出的过程中找到节点对应的子节点的入度-1
 *    5.5 如果子节点的入度为0 加入队列  直到所有的节点都删除干净
 */
public class Code04_GraphTop {

    public List<Node> topGraph(List<Node> nodes) {
        if (nodes == null || nodes.size() == 0) {
            return null;
        }
        List<Node> ans = new ArrayList<>();
        Map<Node,Integer> inMap = new HashMap<>();
        // 初始化 所有的节点的入度都为0
        for (Node node : nodes) {
            inMap.put(node,0);
        }

        for (Node node : nodes) {
            for (Node next : node.nexts) {
                inMap.put(next,inMap.get(next) + 1);
            }
        }
        Queue<Node> zeroInQueue = new LinkedList<>();
        // 遍历图 找到入度为0的节点
        for (Node node : inMap.keySet()) {
            if (inMap.get(node) == 0) {
                zeroInQueue.add(node);
            }
        }
        while (!zeroInQueue.isEmpty()) {
            Node cur = zeroInQueue.poll();
            ans.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next,inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return ans;
    }

    public  List<Node> sortedTopology(Graph graph) {
        Queue<Node> queue = new LinkedList<>();
        Map<Node,Integer> inMap = new HashMap<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node,node.getIn());
            if (node.getIn() == 0) {
                queue.add(node);
            }
        }
        List<Node> ans = new ArrayList<>();
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            ans.add(cur);
            for (Node next : cur.nexts) {
                inMap.put(next,inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    queue.add(next);
                }
            }
        }
        return ans;
    }
}
