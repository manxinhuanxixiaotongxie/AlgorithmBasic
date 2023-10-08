package systemlearning.code16;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * 点
 *
 * @Author Scurry
 * @Date 2023-10-08 10:11
 */
public class Node {

    private int in;
    private int out;
    private int value;

    List<Edge> edges;

    List<Node> nexts;


    public Node(int value) {
        this.in = 0;
        this.out = 0;
        this.value = value;
        edges = new ArrayList<>();
        nexts = new ArrayList<>();
    }

    public int getIn() {
        return in;
    }

    public void setIn(int in) {
        this.in = in;
    }

    public int getOut() {
        return out;
    }

    public void setOut(int out) {
        this.out = out;
    }
}
