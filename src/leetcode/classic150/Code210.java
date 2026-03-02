package leetcode.classic150;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 课程表2 与code207思路相同 只是返回图的拓扑结构
 *
 */
public class Code210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            int[] res = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                res[i] = i;
            }
            return res;
        }
        int n = prerequisites.length;
        // 节点node
        Map<Integer, Code210Node> map = new HashMap<>();
        // 图的连通性表达
        Map<Integer, List<Code210Node>> graph = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int from = prerequisites[i][1];
            int to = prerequisites[i][0];
            map.putIfAbsent(from, new Code210Node(from));
            map.putIfAbsent(to, new Code210Node(to));
            graph.computeIfAbsent(from, k -> new java.util.ArrayList<>()).add(map.get(to));
            map.get(to).inDegree++;
        }
        Queue<Integer> queue = new java.util.LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (!map.containsKey(i) || map.get(i).inDegree == 0) {
                queue.offer(i);
            }
        }
        int[] res = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            res[index++] = cur;
            List<Code210Node> toNodes = graph.get(cur);
            if (toNodes != null) {
                for (Code210Node toNode : toNodes) {
                    toNode.inDegree--;
                    if (toNode.inDegree == 0) {
                        queue.offer(toNode.course);
                    }
                }
            }
        }
        if (index != numCourses) {
            return new int[0];
        }
        return res;

    }

    class Code210Node {
        int course;
        int inDegree;

        Code210Node(int course) {
            this.course = course;
            this.inDegree = 0;
        }
    }
}
