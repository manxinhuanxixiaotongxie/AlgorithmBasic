package systemimprove.code13;

public class Code01_Graph {
    /**
     * 图的表示方式
     * <p>
     * <p>
     * 邻接表法：
     * <p>
     * 邻接矩阵法：
     *
     * <p>
     * <p>
     * 1.点集
     * 2.边集
     */
    public Graph createGraph(int[][] matrix) {
        Graph graph = new Graph();
        int N = matrix.length;
        for (int i = 0; i < N; i++) {
            int from = matrix[i][0];
            int to = matrix[i][1];
            int weight = matrix[i][2];
            // from to 在点集没有就新建一个
            // 新创建的点的入度 出度 相邻的点集 编辑都是默认值
            if (graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            if (graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }
            // 获取from to点
            Node fromNode = graph.nodes.get(from);
            Node toNode = graph.nodes.get(to);

            fromNode.nexts.add(toNode);
            fromNode.setOut(fromNode.getOut() + 1);
            toNode.setIn(toNode.getIn() + 1);
            Edge newEdge = new Edge(weight, fromNode, toNode);
            fromNode.edges.add(newEdge);
            graph.edges.add(newEdge);
        }
        return graph;

    }
}
