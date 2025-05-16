package leetcode.practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Code0210 {


    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Graph graph = new Graph();
        int[] ans = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            ans[i] = i;
        }
        if (prerequisites == null || prerequisites.length == 0) {
            return ans;
        }

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
        int index = 0;
        for (int i = 0; i < numCourses; i++) {
            if (!map.containsKey(i)) {
                ans[index++] = i;
            } else if (map.get(i).in == 0) {
                queue.add(map.get(i));
            }
        }
        Set<GraphNode> set = new HashSet<>();
        while (!queue.isEmpty()) {
            GraphNode node = queue.poll();
            set.add(node);
            ans[index++] = (int) node.val;
            for (int i = 0; i < node.nexts.size(); i++) {
                GraphNode to = (GraphNode) node.nexts.get(i);
                to.in--;
                if (to.in == 0 && !set.contains(to)) {
                    queue.add(to);
                }
            }
        }

        if (set.size() == graph.nodes.size()) {
            return ans;
        }

        return new int[0];
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

    public static void main(String[] args) {
        Code0210 solution = new Code0210();
        int numCourses = 4;
        int[][] prerequisites = new int[][]{{3, 0}, {0, 1}};
        int[] ans = solution.findOrder(numCourses, prerequisites);
        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}
