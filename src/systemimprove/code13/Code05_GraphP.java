package systemimprove.code13;


import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class Code05_GraphP {

    /**
     * 1）可以从任意节点出发来寻找最小生成树
     * 2）某个点加入到被选取的点中后，解锁这个点出发的所有新的边
     * 3）在所有解锁的边中选最小的边，然后看看这个边会不会形成环
     * 4）如果会，不要当前边，继续考察剩下解锁的边中最小的边，重复3）
     * 5）如果不会，要当前边，将该边的指向点加入到被选取的点中，重复2）
     * 6）当所有点都被选取，最小生成树就得到了
     */
    public Set<Edge> minSpanningTreeP(Graph graph) {
        Set<Edge> ans = new HashSet<>();
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new ComparatorEdge());
        Set<Node> set = new HashSet<>();
        for (Node node : graph.nodes.values()) {
            if (!set.contains(node)) {
                set.add(node);
                for (Edge edge : node.edges) {
                    priorityQueue.add(edge);
                }
                while (!priorityQueue.isEmpty()) {
                    Edge edge = priorityQueue.poll();
                    Node toNode = edge.to;
                    if (!set.contains(toNode)) {
                        set.add(toNode);
                        ans.add(edge);
                        for (Edge nextEdge : toNode.edges) {
                            priorityQueue.add(nextEdge);
                        }
                    }
                }
            }
            // 防森林
            break;
        }

        return ans;
    }

    class ComparatorEdge implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }
}
