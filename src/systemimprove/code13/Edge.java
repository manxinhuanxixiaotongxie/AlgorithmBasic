package systemimprove.code13;

public class Edge {
    // 权重
    public int weight;
    public Node from;
    public Node to;

    Edge(int weight, Node from, Node to) {
        this.weight = weight;
        this.from = from;
        this.to = to;

    }

}
