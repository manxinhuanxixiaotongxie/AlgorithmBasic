package systemimprove.code13;

import java.util.List;

/**
 * 图的表示方式
 *
 */
public class Node {
    // 边集
    public List<Edge> edges;
    // 点集
    public List<Node> nexts;
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
