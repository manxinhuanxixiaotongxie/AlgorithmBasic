package systemimprove.code18;

import java.util.List;

public class GraphNode {
    public int value;
    public int in;
    public int out;
    public List<GraphEdge> edges;
    public List<GraphNode> next;
    public GraphNode(int v) {
        value = v;
    }
}
