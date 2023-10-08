package systemlearning.code16;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-10-08 10:14
 */
public class Graph {

    // 点集
    public HashMap<Integer, Node> nodes;

    // 边集
    public HashSet<Edge> edges;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }





}
