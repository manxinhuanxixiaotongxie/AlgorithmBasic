package leetcode.day;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Code3108 {
    /**
     * 这个方法求的是一个点到另外一个点最小的距离
     * 与题意不符合 无法得出有效解
     *
     * @param n
     * @param edges
     * @param query
     * @return
     */
    public int[] minimumCost(int n, int[][] edges, int[][] query) {
        int[] answer = new int[query.length];
        Code3108Graph graph = new Code3108Graph(edges);

        for (int i = 0; i < query.length; i++) {
            answer[i] = djGraph(graph, query[i]);
        }
        return answer;
    }

    private int djGraph(Code3108Graph graph, int[] query) {
        int from = query[0];
        int to = query[1];
        Code3108Node fromNode = graph.nodes.get(from);
        Code3108Node toNode = graph.nodes.get(to);
        // 记录从from到图中任何节点的最小路径
        Map<Code3108Node, Integer> map = new HashMap<>();
        // 初始化一条记录 表示从fromNode到toNode的距离路径是0
        map.put(fromNode, 0);
        Set<Code3108Node> set = new HashSet<>();
        Code3108Node minNode = fromNode;
        while (minNode != null) {
            for (Code3108Edge edge : minNode.edges) {
                Code3108Node toNode1 = edge.to;
                if (map.get(toNode1) == null) {
                    map.put(toNode1, map.get(minNode) + edge.weight);
                } else {
                    map.put(toNode1, Math.min(map.get(minNode) + edge.weight, map.get(toNode1)));
                }
            }
            set.add(minNode);
            minNode = getMinNode(map, set);
        }
        return map.get(toNode) == null ? -1 : map.get(toNode);
    }

    /**
     * 将错就错  回顾一下加强版的dj
     * 使用加强堆
     *
     * @param graph
     * @param query
     * @return
     */
    private int djGraph2(Code3108Graph graph, int[] query) {
        int from = query[0];
        int to = query[1];
        Code3108Node fromNode = graph.nodes.get(from);
        Code3108Node toNode = graph.nodes.get(to);
        // 记录从from到图中任何节点的最小路径
        Map<Code3108Node, Integer> map = new HashMap<>();
        // 初始化一条记录 表示从fromNode到toNode的距离路径是0
        map.put(fromNode, 0);
        Set<Code3108Node> set = new HashSet<>();
        Code3108Node minNode = fromNode;
        while (minNode != null) {
            for (Code3108Edge edge : minNode.edges) {
                Code3108Node toNode1 = edge.to;
                if (map.get(toNode1) == null) {
                    map.put(toNode1, map.get(minNode) + edge.weight);
                } else {
                    map.put(toNode1, Math.min(map.get(minNode) + edge.weight, map.get(toNode1)));
                }
            }
            set.add(minNode);
            minNode = getMinNode(map, set);
        }
        return map.get(toNode) == null ? -1 : map.get(toNode);
    }

    private Code3108Node getMinNode(Map<Code3108Node, Integer> map, Set<Code3108Node> set) {
        int min = Integer.MAX_VALUE;
        Code3108Node minNode = null;
        for (Map.Entry<Code3108Node, Integer> entry : map.entrySet()) {
            if (set.contains(entry.getKey())) {
                continue;
            }
            if (entry.getValue() < min) {
                min = entry.getValue();
                minNode = entry.getKey();
            }
        }
        return minNode;
    }

    // 构建一张图

    class Code3108Graph {
        Map<Integer, Code3108Node> nodes;

        Code3108Graph(int[][] edges) {
            nodes = new HashMap<>();
            for (int i = 0; i < edges.length; i++) {
                int[] info = edges[i];
                int from = info[0];
                if (nodes.get(from) == null) {
                    nodes.put(from, new Code3108Node(from));
                }
                int to = info[1];
                if (nodes.get(to) == null) {
                    nodes.put(to, new Code3108Node(to));
                }
                Code3108Node fromNode = nodes.get(from);
                Code3108Node toNode = nodes.get(to);
                fromNode.next.add(toNode);
                toNode.next.add(fromNode);
                int weight = info[2];
                // 构造边
                Code3108Edge edge = new Code3108Edge(fromNode, toNode, weight);
                fromNode.edges.add(edge);
                toNode.edges.add(edge);
            }
        }
    }

    class Code3108Node {
        int val;
        List<Code3108Node> next;
        List<Code3108Edge> edges;

        Code3108Node(int val) {
            this.val = val;
            next = new ArrayList<>();
            edges = new ArrayList<>();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj instanceof Code3108Node) {
                return ((Code3108Node) obj).val == this.val;
            }
            return false;
        }

    }

    class Code3108Edge {
        // 边的值怎么的求出来
        Code3108Node from;
        Code3108Node to;
        int weight;

        Code3108Edge(Code3108Node from, Code3108Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
