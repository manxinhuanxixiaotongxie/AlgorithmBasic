package systemimprove.code18;

public class GraphEdge {
    GraphNode from;
    GraphNode to;
    int weight;
    GraphEdge(GraphNode f, GraphNode t, int w) {
        from = f;
        to = t;
        weight = w;
    }
}
