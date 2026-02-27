package leetcode.classic150;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Code399 {
    public double[] calcEquation1(List<List<String>> equations,
                                  double[] values, List<List<String>> queries) {
        // dj斯特拉算法 求一个点到另外一个点最短距离
        // 算法流程
        int n = values.length;
        // 构建具有双向关系的图
        // 使用邻接表法表示的图
        Map<String, Map<String, Double>> graphMap = new HashMap<>();
        // 已经结算过的点 含义是：从该点出发到到其他的点已经结算过 在后续的过程中不应该再次参与结算

        for (int i = 0; i < n; i++) {
            // 开始构建图
            String from = equations.get(i).get(0);
            String to = equations.get(i).get(1);
            if (!graphMap.containsKey(from)) {
                HashMap<String, Double> stringDoubleHashMap = new HashMap<>();
                stringDoubleHashMap.put(to, values[i]);
                graphMap.put(from, stringDoubleHashMap);
                // to 节点
            } else {
                // 已经有了
                graphMap.get(from).put(to, values[i]);
            }
            if (!graphMap.containsKey(to)) {
                HashMap<String, Double> stringDoubleHashMap = new HashMap<>();
                stringDoubleHashMap.put(from, 1.0 / values[i]);
                graphMap.put(to, stringDoubleHashMap);
            } else {
                graphMap.get(to).put(from, 1.0 / values[i]);
            }
        }
        // 图已经构建好了 对图进行js斯特拉计算
        // 主要目的是为了计算出一个点到另外一个点的距离
        // 遍历图中任意节点
        Map<String, Map<String, Double>> allDistanceMap = new HashMap<>();
        for (Map.Entry<String, Map<String, Double>> entry : graphMap.entrySet()) {
            String from = entry.getKey();
            // 计算从from开始到另外的所有节点的最小距离
            Map<String, Double> distanceMap = new HashMap<>();
            String minNode = from;
            Set<String> nodeSet = new HashSet<>();
            while (minNode != null) {
                nodeSet.add(minNode);
                Map<String, Double> neighborMap = graphMap.get(minNode);
                for (Map.Entry<String, Double> neighborEntry : neighborMap.entrySet()) {
                    String to = neighborEntry.getKey();
                    double distance = neighborEntry.getValue();
                    if (!nodeSet.contains(to)) {
                        // 计算从from到to的距离
                        double fromToMinNodeDistance = distanceMap.getOrDefault(minNode, 1.0) * distance;
                        double fromToToDistance = distanceMap.getOrDefault(to, Double.MAX_VALUE);
                        if (fromToMinNodeDistance < fromToToDistance) {
                            distanceMap.put(to, fromToMinNodeDistance);
                        }
                    }
                }
                minNode = getMinNode(nodeSet, distanceMap);
            }
            allDistanceMap.put(from, distanceMap);
        }
        double[] ans = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String from = queries.get(i).get(0);
            String to = queries.get(i).get(1);
            if (from.equals(to) && graphMap.containsKey(from)) {
                ans[i] = 1.0;
                continue;
            }
            if (!allDistanceMap.containsKey(from) || !allDistanceMap.get(from).containsKey(to)) {
                ans[i] = -1.0;
            } else {
                ans[i] = allDistanceMap.get(from).get(to);
            }
        }
        return ans;
    }

    private String getMinNode(Set<String> nodeSet, Map<String, Double> distanceMap) {
        String minNode = null;
        double minDistance = Double.MAX_VALUE;

        for (Map.Entry<String, Double> entry : distanceMap.entrySet()) {
            // 获取当前节点的距离
            if (!nodeSet.contains(entry.getKey()) && entry.getValue() < minDistance) {
                minNode = entry.getKey();
                minDistance = entry.getValue();
            }
        }
        return minNode;

    }


    /**
     * 使用加强堆优化的算法
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation2(List<List<String>> equations,
                                  double[] values, List<List<String>> queries) {
        // dj斯特拉算法 求一个点到另外一个点最短距离
        // 算法流程
        int n = values.length;
        // 构建具有双向关系的图
        // 使用邻接表法表示的图
        Map<String, Map<String, Double>> graphMap = new HashMap<>();
        // 已经结算过的点 含义是：从该点出发到到其他的点已经结算过 在后续的过程中不应该再次参与结算
        for (int i = 0; i < n; i++) {
            // 开始构建图
            String from = equations.get(i).get(0);
            String to = equations.get(i).get(1);
            if (!graphMap.containsKey(from)) {
                HashMap<String, Double> stringDoubleHashMap = new HashMap<>();
                stringDoubleHashMap.put(to, values[i]);
                graphMap.put(from, stringDoubleHashMap);
                // to 节点
            } else {
                // 已经有了
                graphMap.get(from).put(to, values[i]);
            }
            if (!graphMap.containsKey(to)) {
                HashMap<String, Double> stringDoubleHashMap = new HashMap<>();
                stringDoubleHashMap.put(from, 1.0 / values[i]);
                graphMap.put(to, stringDoubleHashMap);
            } else {
                graphMap.get(to).put(from, 1.0 / values[i]);
            }
        }
        // 图已经构建好了 对图进行dj斯特拉计算
        // 主要目的是为了计算出一个点到另外一个点的距离
        // 遍历图中任意节点
        Map<String, Map<String, Double>> allDistanceMap = new HashMap<>();
        for (Map.Entry<String, Map<String, Double>> entry : graphMap.entrySet()) {
            String from = entry.getKey();
            // 计算从from开始到另外的所有节点的最小距离
            MyGreateHeap myGreateHeap = new MyGreateHeap();
            myGreateHeap.insertOrUpdateOrIgnore(from, 1.0);
            int times = 0;
            while (myGreateHeap.size > 0) {
                times++;
                Info pop = myGreateHeap.pop();
                // 此时获取到的就是minNode
                // 求民Node的邻边
                Map<String, Double> stringDoubleMap = graphMap.get(pop.node);
                // 邻边
                for (Map.Entry<String, Double> entry1 : stringDoubleMap.entrySet()) {
                    String to = entry1.getKey();
                    double distance = entry1.getValue();
                    myGreateHeap.insertOrUpdateOrIgnore(to, pop.instance * distance);

                }
                if (times > 100) {
                    System.out.println("times = " + times);
                    break;
                }
            }
            allDistanceMap.put(from, myGreateHeap.distanceMap);
        }
        double[] ans = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String from = queries.get(i).get(0);
            String to = queries.get(i).get(1);
            if (from.equals(to) && graphMap.containsKey(from)) {
                ans[i] = 1.0;
                continue;
            }
            if (!allDistanceMap.containsKey(from) || !allDistanceMap.get(from).containsKey(to)) {
                ans[i] = -1.0;
            } else {
                ans[i] = allDistanceMap.get(from).get(to);
            }
        }
        return ans;
    }

    class MyGreateHeap {
        // 记录从出发点map的key的距离
        Map<String, Double> distanceMap;
        Map<Integer, String> heapIndexMap;
        // 反向索引表 记录每个String在堆中所在位置 用于堆结构调整的时候 定位到元素的下标位置 进行堆结构的重新平衡
        // 已经结算过 可以将反向索引置为-1 表示已经结算过了 已经结算过的节点 不再参与计算
        Map<String, Integer> indexMap;
        // 用于标记堆内的元素个数
        int size;

        MyGreateHeap() {
            distanceMap = new HashMap<>();
            heapIndexMap = new HashMap<>();
            indexMap = new HashMap<>();
            size = 0;
        }

        // 判断一个节点是否已经结算过
        private boolean isEntered(String node) {
            return indexMap.containsKey(node);
        }

        // 判断一个节点是否在堆中并且没有结算
        private boolean isInHeap(String node) {
            return isEntered(node) && indexMap.get(node) != -1;
        }

        public Info pop() {
            if (size == 0) {
                return null;
            }
            // 构建元素
            Info info = new Info(heapIndexMap.get(0), distanceMap.get(heapIndexMap.get(0)));
            // 交换堆顶元素与堆最后一个元素
            swap(0, --size);
            // 对堆做成重新调整
            heapIndexMap.remove(size);
            indexMap.put(info.node, -1);
            heapify(0);
            return info;
        }

        /**
         * 如果一个节点已经被结算过 那么调用这个方法会被忽略 用于替换set的判断
         * 如果一个节点在堆中并且没有结算过 那么有可能需要对这个节点数值进行更新（在当前的场景设定下 只可能变小 没有可能会变大）
         * 如果一个节点完全没有进过堆 说明这个节点是第一次被访问 那么 就应该将这个节点放入堆中 然后按照小跟堆的方式进行建堆
         */
        public void insertOrUpdateOrIgnore(String node, double value) {
            if (isInHeap(node)) {
                // 在堆中 那么有可能需要更新
                // 根据反向索引表找到下标
                int index = indexMap.get(node);
                // 更新距离
                distanceMap.put(node, Math.min(distanceMap.get(node), value));
                // 调整堆
                heapInsert(heapIndexMap.get(index), index);
            }
            if (!isEntered(node)) {
                // 没有进入过堆 说明是第一次访问
                heapIndexMap.put(size, node);
                indexMap.put(node, size);
                distanceMap.put(node, value);
                heapInsert(node, size++);
            }
        }

        public void heapify(int index) {
            int left = index * 2 + 1;
            int hepifyTimes = 0;
            while (left < size) {
                hepifyTimes++;
                // 在下面两个节点找到更小的那一个
                int minIndex = left + 1 < size &&
                        distanceMap.get(heapIndexMap.get(left + 1)) < distanceMap.get(heapIndexMap.get(left)) ? left + 1 : left;
                // 比较当前index节点与孩子节点两个最小的找到最大的节点
                int smallIndex = distanceMap.get(heapIndexMap.get(index)) <
                        distanceMap.get(heapIndexMap.get(minIndex)) ? index : minIndex;
                if (index == smallIndex) {
                    // 说明当前节点已经是最小的了 不需要调整了
                    break;
                }
                // 交换节点
                swap(index, smallIndex);
                index = smallIndex;
                left = index * 2 + 1;
                if (hepifyTimes > 100) {
                    System.out.println("hepifyTimes = " + hepifyTimes);
                    break;
                }
            }
        }

        public void heapInsert(String node, int size) {
            int loop = 0;
            while (distanceMap.get(heapIndexMap.get(size)).compareTo(
                    distanceMap.get(heapIndexMap.get((size - 1) / 2))) < 0) {
                // 交换堆顶元素和当前元素
                swap(size, (size - 1) / 2);
                size = (size - 1) / 2;
                loop++;
                System.out.println("loop = " + loop);
                if (loop > 100) {
                    System.out.println("size = " + size);
                    break;
                }
            }
        }

        private void swap(int index1, int index2) {
            String node1 = heapIndexMap.get(index1);
            String node2 = heapIndexMap.get(index2);
            heapIndexMap.put(index1, node2);
            heapIndexMap.put(index2, node1);
            indexMap.put(node1, index2);
            indexMap.put(node2, index1);
        }

    }

    class Info {
        String node;
        Double instance;

        Info(String node, Double instance) {
            this.node = node;
            this.instance = instance;
        }
    }

    static void main() {
        // [["a","b"],["a","c"],["d","e"],["d","f"],["a","d"],["aa","bb"],["aa","cc"],["dd","ee"],["dd","ff"],["aa","dd"],["a","aa"]]
        // [2.0,3.0,4.0,5.0,7.0,5.0,8.0,9.0,3.0,2.0,2.0]
        // [["ff","a"]]
        List<List<String>> equations = new ArrayList<>();
        equations.add(List.of("a", "b"));
        equations.add(List.of("a", "c"));
        equations.add(List.of("d", "e"));
        equations.add(List.of("d", "f"));
        equations.add(List.of("a", "d"));
        equations.add(List.of("aa", "bb"));
        equations.add(List.of("aa", "cc"));
        equations.add(List.of("dd", "ee"));
        equations.add(List.of("dd", "ff"));
        equations.add(List.of("aa", "dd"));
        equations.add(List.of("a", "aa"));
        double[] values = new double[]{2.0, 3.0, 4.0, 5.0, 7.0, 5.0, 8.0, 9.0, 3.0, 2.0, 2.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(List.of("ff", "a"));
        Code399 code399 = new Code399();
        double[] ans = code399.calcEquation2(equations, values, queries);
         for (double an : ans) {
                System.out.println(an);
         }

    }
}
