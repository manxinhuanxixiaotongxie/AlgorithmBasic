package leetcode.day;

import java.util.*;

/**
 * 给你一棵 n 个节点的无向树，节点编号为 0 到 n - 1 。给你整数 n 和一个长度为 n - 1 的二维整数数组 edges ，其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 有一条边。
 *
 * 同时给你一个下标从 0 开始长度为 n 的整数数组 values ，其中 values[i] 是第 i 个节点的 值 。再给你一个整数 k 。
 *
 * 你可以从树中删除一些边，也可以一条边也不删，得到若干连通块。一个 连通块的值 定义为连通块中所有节点值之和。如果所有连通块的值都可以被 k 整除，那么我们说这是一个 合法分割 。
 *
 * 请你返回所有合法分割中，连通块数目的最大值
 *
 */
public class Code2872 {
    public int maxKDivisibleComponents(int n, int[][] edges, int[] values, int k) {
        // n个节点
        // 构建图
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n-1; i++) {
            if (!graph.containsKey(edges[i][0])) {
                graph.putIfAbsent(edges[i][0], new ArrayList<>());
            }
            if (!graph.containsKey(edges[i][1])) {
                graph.putIfAbsent(edges[i][1], new ArrayList<>());
            }
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }
        int max = 1;

        for (int i = 0; i < n; i++) {
            Set<Integer> visited = new HashSet<>();
            max = Math.max(max, process(i, graph, visited, values, k, 0));
        }

        return max;
    }

    /**
     * 这段方法有问题 为什么？
     *
     * 逻辑完全偏离了 树分割 的本质
     *
     * 其实没有做到分割
     *
     * 这段代码的逻辑是从一个节点开始进行深度优先搜索（DFS），遍历图中的所有节点，并计算从该节点出发的路径上节点值的和。如
     * 果路径上的节点值之和能够被 k 整除，则计数器 ans 增加 1，并将 sum 重置为 0。最终返回从该节点出发能够形成的合法分割的数量。
     *
     * @param node
     * @param graph
     * @param visited
     * @param values
     * @param k
     * @param sum
     * @return
     */
    public int process(int node,Map<Integer, List<Integer>> graph,Set<Integer> visited,int[] values,int k,int sum) {
        int ans = 0;
        visited.add(node);

        for (int next : graph.get(node)) {
            if (!visited.contains(next)) {
                ans += process(next, graph, visited, values, k, sum);
            }
        }
        sum += values[node];
        if (sum % k == 0) {
            ans++;
            sum = 0;
        }
        return ans;
    }

    /**
     * 采用后序遍历的方式 进行计算
     *
     * @param n
     * @param edges
     * @param values
     * @param k
     * @return
     */

    int ans = 0;

    public int maxKDivisibleComponents2(int n, int[][] edges, int[] values, int k) {
        // n个节点
        // 构建图
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n-1; i++) {
            if (!graph.containsKey(edges[i][0])) {
                graph.putIfAbsent(edges[i][0], new ArrayList<>());
            }
            if (!graph.containsKey(edges[i][1])) {
                graph.putIfAbsent(edges[i][1], new ArrayList<>());
            }
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }
        Set<Integer> visited = new HashSet<>();
        process2(0, graph, k, values, visited);
        return ans;
    }

    public int process2(int nodeId, Map<Integer, List<Integer>> graph, int k, int[] values, Set<Integer> visited) {
        long sum = values[nodeId];
        visited.add(nodeId);
        if (graph.get(nodeId) != null) {
            for (Integer next : graph.get(nodeId)) {
                if (!visited.contains(next)) {
                    sum += process2(next, graph, k, values, visited);
                }
            }
        }
        visited.remove(nodeId);
        if (sum % k == 0) {
            ans++;
            return 0;
        }
        return (int)(sum % k);
    }




    public static void main(String[] args) {
        int n = 1;
        int[][] edges = new int[][] {{}};
        int[] values = new int[] {0};
        int k = 1;
        Code2872 code2872 = new Code2872();
        System.out.println(code2872.maxKDivisibleComponents2(n, edges, values, k));

    }

}

