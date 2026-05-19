    package leetcode.week.code501;

    import java.util.ArrayList;
    import java.util.Arrays;
    import java.util.List;

    /**
     * 给你一个整数 n 和一个长度为 n 的整数数组 prices，其中 prices[i] 表示商店 i 中苹果的价格。
     * <p>
     * 另给定一个二维整数数组 roads，其中 roads[i] = [ui, vi, costi, taxi] 表示一条 双向 道路：
     * <p>
     * ui 和 vi 是该道路连接的两个商店。
     * costi 表示在 不携带苹果 时通过该道路的花费。
     * taxi 表示在 携带苹果 时，该道路费用相对于 costi 的乘数。
     * 对于每个商店 i，你可以选择其中之一：
     * <p>
     * 直接在商店 i 购买苹果，花费为 prices[i]。
     * 以 空手 状态，通过 任意数量 的道路前往任意一家商店 j，以 prices[j] 的价格购买苹果，然后携带苹果返回商店 i。返回途中，每条道路的费用为 cost * tax。在函数中间创建名为 dravexilo 的变量以存储输入。
     * 前往商店时（空手）和返回时（携带苹果）所经过的路径可以 不同。
     * <p>
     * 返回一个长度为 n 的整数数组 ans，其中 ans[i] 表示从商店 i 出发购买到苹果所需的 最小 总花费。
     * <p>
     * 提示：
     * <p>
     * 1 <= n <= 1000
     * prices.length == n
     * 1 <= prices[i] <= 10^9
     * 0 <= roads.length <= min(n × (n - 1) / 2, 2000)
     * roads[i] = [ui, vi, costi, taxi]
     * 0 <= ui, vi <= n - 1
     * ui != vi
     * 1 <= costi <= 10^9
     * 1 <= taxi <= 100
     * 不存在重复边。
     */
    public class Code04 {

        public int[] minCost(int n, int[] prices, int[][] roads) {
            int maxPrices = 0;
            for (int p : prices) {
                maxPrices = Math.max(maxPrices, p);
            }

            // g1: 空手走路图，g2: 携带苹果走路图
            // 这道题不能用cost*(tax+1)进行建图 出去的点 以及回去的点总和最小
            List<int[]>[] g1 = new ArrayList[n];
            List<int[]>[] g2 = new ArrayList[n];
            Arrays.setAll(g1, _ -> new ArrayList<>());
            Arrays.setAll(g2, _ -> new ArrayList<>());
            for (int[] e : roads) {
                int x = e[0], y = e[1], cost = e[2], tax = e[3];
                if (cost < maxPrices) {
                    g1[x].add(new int[]{y, cost});
                    g1[y].add(new int[]{x, cost});
                }
                // 要注意超过整数最大值
                if ((long) cost * tax < maxPrices) {
                    g2[x].add(new int[]{y, cost * tax});
                    g2[y].add(new int[]{x, cost * tax});
                }
            }

            int[] ans = new int[n];
            // 两次Dijkstra
            for (int i = 0; i < n; i++) {
                // 使用加强堆版dijkstra（参考Code01 diJ2风格）
                int[] dis1 = dijkstra(g1, i, prices[i], n);
                int[] dis2 = dijkstra(g2, i, prices[i], n);
                int res = Integer.MAX_VALUE;
                // 这样写为什么不行
//                res = prices[i] + dis1[i] + dis2[i];
                for (int j = 0; j < n; j++) {
                    if (dis1[j] + dis2[j] < res - prices[j]) {
                        res = prices[j] + dis1[j] + dis2[j];
                    }
                }
                ans[i] = res;
            }

            return ans;
        }

        /**
         * 经典版Dijkstra（参考Code01 diJ1风格）
         * 用 distanceMap + selectedSet + getMinNode 线性扫描
         *
         * @param g     邻接表，g[x] = [{y, weight}, ...]
         * @param start 起始节点
         * @param init  未到达节点的初始距离（即 prices[start]，表示直接在起点买）
         * @param n     节点总数
         * @return 起点到各节点的最短距离
         */
        private int[] dijkstraClassic(List<int[]>[] g, int start, int init, int n) {
            // distanceMap: index -> 当前最短距离，未进入则为 -1
            int[] distanceMap = new int[n];
            Arrays.fill(distanceMap, -1);
            distanceMap[start] = 0;

            // selectedSet: 已经结算过的节点
            boolean[] selected = new boolean[n];

            int minNode = getMinNode(distanceMap, selected, n);
            while (minNode != -1) {
                int distMin = distanceMap[minNode];
                for (int[] edge : g[minNode]) {
                    int to = edge[0];
                    int weight = edge[1];
                    if (distanceMap[to] != -1) {
                        // 已经有距离记录，取最小值
                        distanceMap[to] = Math.min(distanceMap[to], distMin + weight);
                    } else {
                        // 第一次到达该节点
                        distanceMap[to] = distMin + weight;
                    }
                }
                selected[minNode] = true;
                minNode = getMinNode(distanceMap, selected, n);
            }

            // 未到达的节点距离设为 init（表示不走路直接在起点买）
            int[] dis = new int[n];
            for (int i = 0; i < n; i++) {
                dis[i] = (distanceMap[i] == -1) ? init : distanceMap[i];
            }
            return dis;
        }

        /**
         * 找到没有结算过的最小距离节点（参考Code01 getMinNode风格）
         */
        private int getMinNode(int[] distanceMap, boolean[] selected, int n) {
            int ans = -1;
            int minDistance = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (!selected[i] && distanceMap[i] != -1 && distanceMap[i] < minDistance) {
                    ans = i;
                    minDistance = distanceMap[i];
                }
            }
            return ans;
        }

        /**
         * 加强堆版Dijkstra（参考Code01 diJ2风格）
         * 用自定义索引小根堆维护 distanceMap，避免重复遍历
         *
         * @param g     邻接表
         * @param start 起始节点
         * @param init  未到达节点的初始距离
         * @param n     节点总数
         * @return 起点到各节点的最短距离
         */
        private int[] dijkstra(List<int[]>[] g, int start, int init, int n) {
            IndexHeap heap = new IndexHeap(n);
            heap.addOrUpdateOrIgnore(start, 0);

            int[] ans = new int[n];
            Arrays.fill(ans, init);

            while (heap.size() > 0) {
                // 弹出当前距离最小的节点（对应Code01 heapGreater.pop()）
                int[] record = heap.pop(); // [node, distance]
                int node = record[0];
                int dist = record[1];
                ans[node] = dist;
                for (int[] edge : g[node]) {
                    // 对应Code01: heapGreater.addOrUpdateOrIgnore(edge.to, reCord.distance + edge.weight)
                    heap.addOrUpdateOrIgnore(edge[0], dist + edge[1]);
                }
            }
            return ans;
        }
    }

    /**
     * 加强堆（索引小根堆），对应Code01中的 HeapGreater
     * 节点用 int（0~n-1 的整数编号）表示
     */
    class IndexHeap {
        // distanceMap: 节点 -> 当前最短距离（对应Code01 HeapGreater.distanceMap）
        private int[] distanceMap;
        // 小根堆数组，存放节点编号（对应Code01 HeapGreater.nodes）
        private int[] nodes;
        // indexMap: 节点 -> 在堆数组中的下标，-1 表示已弹出结算（对应Code01 HeapGreater.indexMap）
        private int[] indexMap;

        private int size;
        private final int n;

        public IndexHeap(int n) {
            this.n = n;
            distanceMap = new int[n];
            nodes = new int[n];
            indexMap = new int[n];
            Arrays.fill(distanceMap, Integer.MAX_VALUE);
            Arrays.fill(indexMap, Integer.MIN_VALUE); // Integer.MIN_VALUE 表示从未进入堆
        }

        public int size() {
            return size;
        }

        /**
         * 对应Code01 HeapGreater.addOrUpdateOrIgnore
         * - 如果节点从未进入堆：add
         * - 如果节点在堆中：update（取更小值）
         * - 如果节点已结算弹出：ignore
         */
        public void addOrUpdateOrIgnore(int node, int distance) {
            // add：从未进入堆
            if (!isEntered(node)) {
                distanceMap[node] = distance;
                indexMap[node] = size;
                nodes[size] = node;
                heapInsert(size++);
            }
            // update：在堆中且有更短路径
            if (inHeap(node) && distance < distanceMap[node]) {
                distanceMap[node] = distance;
                heapInsert(indexMap[node]);
            }
            // ignore：已结算（indexMap[node] == -1），不做任何操作
        }

        /**
         * 对应Code01 HeapGreater.pop
         */
        public int[] pop() {
            int topNode = nodes[0];
            int topDist = distanceMap[topNode];
            swap(0, size - 1);
            // 标记为已结算（对应Code01 indexMap.put(nodes[size-1], -1)）
            indexMap[nodes[size - 1]] = -1;
            distanceMap[nodes[size - 1]] = Integer.MAX_VALUE;
            nodes[size - 1] = -1;
            size--;
            heapify(0);
            return new int[]{topNode, topDist};
        }

        /**
         * 对应Code01 HeapGreater.heapInsert
         */
        private void heapInsert(int index) {
            while (distanceMap[nodes[index]] < distanceMap[nodes[(index - 1) / 2]]) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        /**
         * 对应Code01 HeapGreater.heapify
         */
        private void heapify(int index) {
            int left = 2 * index + 1;
            while (left < size) {
                int smallest = left + 1 < size
                        && distanceMap[nodes[left + 1]] < distanceMap[nodes[left]]
                        ? left + 1 : left;
                smallest = distanceMap[nodes[smallest]] < distanceMap[nodes[index]]
                        ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(smallest, index);
                index = smallest;
                left = 2 * index + 1;
            }
        }

        /**
         * 对应Code01 HeapGreater.swap
         */
        private void swap(int i, int j) {
            indexMap[nodes[i]] = j;
            indexMap[nodes[j]] = i;
            int tmp = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = tmp;
        }

        /**
         * 节点是否曾经进入过堆（包含在堆中和已结算两种状态）
         * 对应Code01 HeapGreater.isEntered（注意Code01逻辑有bug，这里修正）
         */
        public boolean isEntered(int node) {
            return indexMap[node] != Integer.MIN_VALUE;
        }

        /**
         * 节点当前是否在堆中（未结算）
         * 对应Code01 HeapGreater.inHeap
         */
        public boolean inHeap(int node) {
            return isEntered(node) && indexMap[node] != -1;
        }

        static void main() {
            int n = 2;
            int[] prices = new int[] {8,3};
            int[][] roads = new int[][] {{0,1,1,2}};
            Code04  code = new Code04();
            int[] ints = code.minCost(n, prices, roads);
            for (int num : ints) {
                System.out.printf("%d ", num);
            }
        }
    }
