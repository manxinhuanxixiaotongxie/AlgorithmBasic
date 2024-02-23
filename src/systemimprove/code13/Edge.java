package systemimprove.code13;

public class Edge {
    // 权重
    private int weight;
    private Node from;
    private Node to;
    Edge(int weight,Node from ,Node to){
        this.weight = weight;
        this.from = from;
        this.to = to;

    }

}
