package leetcode.week.code427;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Code003 {
    /**
     * 这个写法无法AC
     *
     * @param edges1
     * @param edges2
     * @param k
     * @return
     */
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        // 构建图
        Code003Graph graph1 = new Code003Graph();


        for (int i = 0; i < edges1.length; i++) {
            int from = edges1[i][0];
            int to = edges1[i][1];
            if (graph1.nodes.get(from) == null) {
                graph1.nodes.put(from, new Code003Node(from, "1"));
            }

            if (graph1.nodes.get(to) == null) {
                graph1.nodes.put(to, new Code003Node(to, "1"));
            }

            Code003Node fromNode = graph1.nodes.get(from);
            Code003Node toNode = graph1.nodes.get(to);
            fromNode.in++;
            toNode.in++;
            fromNode.next.add(toNode);
            toNode.next.add(fromNode);
        }


        Code003Graph graph2 = new Code003Graph();

        // 构建第二张图
        for (int i = 0; i < edges2.length; i++) {
            int from = edges2[i][0];
            int to = edges2[i][1];
            if (graph2.nodes.get(from) == null) {
                graph2.nodes.put(from, new Code003Node(from, "2"));
            }
            if (graph2.nodes.get(to) == null) {
                graph2.nodes.put(to, new Code003Node(to, "2"));
            }
            Code003Node fromNode = graph2.nodes.get(from);
            Code003Node toNode = graph2.nodes.get(to);
            fromNode.in++;
            toNode.in++;
            fromNode.next.add(toNode);
            toNode.next.add(fromNode);
        }
        int[] answer = new int[edges1.length + 1];
        int count = 0;
        for (int i = 0; i < edges1.length + 1; i++) {
            Code003Node node = graph1.nodes.get(i);
            // 让图一跟图二的最大入度的节点相连
            // 获取图二最大入度的点
            Code003Node maxInNode = graph2.getMaxInNode();
            node.in++;
            maxInNode.in++;
            node.next.add(maxInNode);
            maxInNode.next.add(node);
            Queue<Code003Node> queue = new LinkedList<>();
            queue.add(node);
            HashSet<Code003Node> set = new HashSet<>();
            int temp = k;
            while (temp >= 0) {
                int size = queue.size();
                answer[i] += size;
                // 打印答案
                while (size > 0) {
                    Code003Node poll = queue.poll();
                    System.out.println("第" + ++count + "个数 :" + "属于第几个图" + poll.flag + "查来的节点：" + poll.val);
                    set.add(poll);
                    for (Code003Node next : poll.next) {
                        if (!set.contains(next)) {
                            queue.add(next);
                        }
                    }
                    size--;
                }
                temp--;
            }
            // 断开图一与图二相连的边
            node.in--;
            maxInNode.in--;
            node.next.remove(maxInNode);
            maxInNode.next.remove(node);
        }
        return answer;
    }

    class Code003Graph {
        Map<Integer, Code003Node> nodes;


        Code003Graph() {
            nodes = new HashMap<>();
        }

        public Code003Node getMaxInNode() {
            Code003Node maxNode = null;
            for (Code003Node node : nodes.values()) {
                if (maxNode == null || node.in > maxNode.in || (node.in == maxNode.in && node.val < maxNode.val)) {
                    maxNode = node;
                }
            }
            return maxNode;
        }
    }

    class Code003Node {
        int val;
        List<Code003Node> next;
        String flag;
        int in;

        Code003Node(int val, String flag) {
            this.val = val;
            this.in = 0;
            this.flag = flag;
            next = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        // edges1 = [[0,1],[0,2],[2,3],[2,4]], edges2 = [[0,1],[0,2],[0,3],[2,7],[1,4],[4,5],[4,6]], k = 2
//        int[][] edges1 = new int[][]{{0, 1}, {0, 2}, {2, 3}, {2, 4}};
//        int[][] edges2 = new int[][]{{0, 1}, {0, 2}, {0, 3}, {2, 7}, {1, 4}, {4, 5}, {4, 6}};
        // [[3,0],[2,1],[5,2],[6,3],[5,4],[5,6]]
        // [[5,0],[1,5],[6,1],[3,6],[2,3],[4,2],[7,4],[7,8]]
        int[][] edges1 = new int[][]{{3, 0}, {2, 1}, {5, 2}, {6, 3}, {5, 4}, {5, 6}};
        int[][] edges2 = new int[][]{{5, 0}, {1, 5}, {6, 1}, {3, 6}, {2, 3}, {4, 2}, {7, 4}, {7, 8}};
        int k = 4;
        Code003 code003 = new Code003();
        int[] result = code003.maxTargetNodes(edges1, edges2, k);
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}
