package systemlearning.code16;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Description 图的拓扑序
 * <p>
 * 1）在图中找到所有入度为0的点输出
 * 2）把所有入度为0的点在图中删掉，继续找入度为0的点输出，周而复始
 * 3）图的所有点都被删除后，依次输出的顺序就是拓扑排序
 * @Author Scurry
 * @Date 2023-10-10 10:39
 */
public class Code04_GraphTopology {
    class DirectedGraphNode {
        int label;
        List<DirectedGraphNode> neighbors;

        DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        if (graph == null || graph.size() == 0) {
            return null;
        }

        HashMap<DirectedGraphNode,Integer> indegreeMap = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            indegreeMap.put(node,0);
        }

        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                indegreeMap.put(neighbor,indegreeMap.get(neighbor) + 1);
            }
        }

        Queue<DirectedGraphNode> zeroIndegreeQueue = new LinkedList<>();
        for (DirectedGraphNode node : indegreeMap.keySet()) {
            if (indegreeMap.get(node) == 0) {
                zeroIndegreeQueue.add(node);
            }
        }

        ArrayList<DirectedGraphNode> result = new ArrayList<>();

        while (!zeroIndegreeQueue.isEmpty()) {
            DirectedGraphNode node = zeroIndegreeQueue.poll();
            result.add(node);
            for (DirectedGraphNode neighbor : node.neighbors) {
                indegreeMap.put(neighbor,indegreeMap.get(neighbor) - 1);
                if (indegreeMap.get(neighbor) == 0) {
                    zeroIndegreeQueue.offer(neighbor);
                }
            }
        }

        return result;


    }
}
