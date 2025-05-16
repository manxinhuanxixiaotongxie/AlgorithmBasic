package systemlearning.code16;

/**
 * @Description 图：
 * 由点和边组成
 * <p>
 * 点：
 * 存在入度、出度
 * 存在边集合
 * 存在nexts集合（与当前节点进行连接）
 * <p>
 * 边：
 * 存在权重
 * 存在from点 当前边从from指向to
 * 存在to点
 * <p>
 * 创建图：
 * 1.创建点集合 2.创建边集合 3.遍历二维数组，创建点和边
 * <p>
 * 1.创建点集合：
 * 点集合使用HashMap存储，key为点的值，value为点
 * 当点集不存在时创建点，存在时不创建
 * <p>
 * 2.创建边集合：
 * 边存在from与to位置以及对应的权重
 * 边集合使用HashSet存储，存储边
 * 设置点集的入度出度信息
 * <p>
 * 3.遍历二维数组，创建点和边
 * 点集以及边集创建完成后，
 * 将点集以及边集合并到图中
 * @Author Scurry
 * @Date 2023-10-08 10:11
 */
public class Code01_Graph {

    // 给定一个数组[0,1,2]形如这种格式的二维数组0位置代表from，1位置代表to，2位置代表weight
    public Graph createGraph(int[][] matrix) {
        Graph graph = new Graph();
        for (int i = 0; i < matrix.length; i++) {
            // 每一行的第一个数
            int from = matrix[i][0];
            // 每一行的第二个数
            int to = matrix[i][1];
            // 每一行的第三个数
            int weight = matrix[i][2];

            // 如果图中没有from点，就创建一个
            if (!graph.nodes.containsKey(from)) {
                graph.nodes.put(from, new Node(from));
            }
            // 如果图中没有to点，就创建一个
            if (!graph.nodes.containsKey(to)) {
                graph.nodes.put(to, new Node(to));
            }

            // 获取from点
            Node fromNode = graph.nodes.get(from);
            // 获取to点
            Node toNode = graph.nodes.get(to);

            // 创建一条边
            Edge newEdge = new Edge(weight, fromNode, toNode);

            // from点的出度+1
            fromNode.setOut(fromNode.getOut() + 1);
            // to点的入度+1
            toNode.setIn(toNode.getIn() + 1);

            // from点的nexts集合中添加to点
            fromNode.nexts.add(toNode);
            // from点的边集合中添加这条边
            fromNode.edges.add(newEdge);

            // 图中的边集合中添加这条边
            graph.edges.add(newEdge);
        }
        return graph;
    }

}
