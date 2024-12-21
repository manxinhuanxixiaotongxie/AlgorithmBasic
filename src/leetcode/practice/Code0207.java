package leetcode.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Code0207 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Graph graph = new Graph();
        Map<Integer, GraphNode> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int from = prerequisites[i][1];
            int to = prerequisites[i][0];
            if (!map.containsKey(from)) {
                map.put(from, new GraphNode(from));
                graph.nodes.add(map.get(from));
            }
            if (!map.containsKey(to)) {
                map.put(to, new GraphNode(to));
                graph.nodes.add(map.get(to));
            }
            GraphNode fromNode = map.get(from);
            GraphNode toNode = map.get(to);
            fromNode.nexts.add(toNode);
            toNode.in++;
        }

        Queue<GraphNode> queue = new LinkedList<>();
        for (GraphNode node : map.values()) {
            if (node.in == 0) {
                queue.add(node);
            }
        }
        Set<GraphNode> set = new HashSet<>();
        while (!queue.isEmpty()) {
            GraphNode node = queue.poll();
            set.add(node);
            for (int i = 0; i < node.nexts.size(); i++) {
                GraphNode to = (GraphNode) node.nexts.get(i);
                to.in--;
                if (to.in == 0 && !set.contains(to)) {
                    queue.add(to);
                }
            }
        }


        return set.size() == graph.nodes.size();
    }

    class Graph {
        List<GraphNode> nodes;

        Graph() {
            this.nodes = new ArrayList<>();
        }
    }

    class GraphNode<Integer> {
        Integer val;
        GraphNode from;
        List<GraphNode> nexts;
        int in;

        GraphNode(Integer val) {
            this.nexts = new ArrayList<GraphNode>();
            this.val = val;
            this.in = 0;
        }

        // 重写equals方法
        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            if (obj instanceof GraphNode) {
                GraphNode node = (GraphNode) obj;
                return this.val.equals(node.val);
            }
            return false;
        }
    }
}
