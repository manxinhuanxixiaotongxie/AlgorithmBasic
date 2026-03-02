package leetcode.classic150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 课程表
 *
 */
public class Code207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        // 图的拓扑结构
        // 入度 出度
        // 图 邻接表法表示
        Map<Integer, List<Code207Node>> graph = new HashMap<>();
        Map<Integer,Code207Node> nodeMap = new HashMap<>();
        // 遍历 构建有向无环图 如果是有环图 一定是无法完成的
        for (int i = 0; i < prerequisites.length; i++) {
            int from = prerequisites[i][1];
            int to = prerequisites[i][0];
            nodeMap.putIfAbsent(from, new Code207Node(from));
            nodeMap.putIfAbsent(to, new Code207Node(to));
            graph.computeIfAbsent(from, k -> new ArrayList<>()).add(nodeMap.get(to));
            nodeMap.get(to).inDegree++;
        }
        // 找到入度为0的节点 以此类推 直到没有入度为0的节点 如果所有节点都被访问过了 则说明是有向无环图 可以完成课程
        // 遍历所有节点
        Set<Code207Node> inDegreeZeroNode = new HashSet<>();
        for (Code207Node node : nodeMap.values()) {
            if (node.inDegree == 0) {
                inDegreeZeroNode.add(node);
            }
        }
        if (inDegreeZeroNode.isEmpty()) {
            return false;
        }
        // 遍历入度为0的节点
        int visitedNodeCount = 0;
        while (!inDegreeZeroNode.isEmpty()) {
            // 遍历入度为0的节点
            // 弹出一个入度为0的节点
            Code207Node node = inDegreeZeroNode.iterator().next();
            inDegreeZeroNode.remove(node);
            visitedNodeCount++;
            // 该节点的连接节点的入度减1 如果入度为0 则加入入度为0的节点集合
            List<Code207Node> toNodes = graph.get(node.course);
            if (toNodes != null) {
                for (Code207Node toNode : toNodes) {
                    toNode.inDegree--;
                    if (toNode.inDegree == 0) {
                        inDegreeZeroNode.add(toNode);
                    }
                }
            }

        }
        return visitedNodeCount == nodeMap.size();
    }

    static class Code207Node {
        // 课程
        Integer course;
        // 入度
        Integer inDegree;

        Code207Node(int course) {
            this.course = course;
            this.inDegree = 0;
        }

        Code207Node(int course, int inDegree) {
            this.course = course;
            this.inDegree = inDegree;

        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Code207Node code207Node = (Code207Node) o;
            return course.equals(code207Node.course);
        }
    }

    static void main() {
        Code207 code207 = new Code207();
        int numCourses = 3;
        int[][] prerequisites = {{2, 1},{1,0}};
        System.out.println(code207.canFinish(numCourses, prerequisites));
    }

}