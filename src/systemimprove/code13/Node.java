package systemimprove.code13;

import java.util.List;

public class Node {
    // 边集
     List<Edge> edges;
    // 点集
     List<Node> nexts;
    // 点值
    private int value;
    //入度
    private int in;
    //出度
    private int out;

    Node(int value) {
        this.value = value;
    }

    public int getOut() {
        return out;
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }
    public void setOut(int out) {
        this.out = out;
    }

}
