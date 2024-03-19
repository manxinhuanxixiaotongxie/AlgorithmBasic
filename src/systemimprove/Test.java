package systemimprove;

import systemimprove.code13.Edge;
import systemimprove.code13.Node;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Test {

    public void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = (L + R) >> 1;
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    /**
     * 小和问题  在归并排序的过程中，将左边比他小的数转化成右边比他大的数
     * 逆序对问题
     *
     * @param arr
     * @param L
     * @param mid
     * @param R
     */
    public void merge(int[] arr, int L, int mid, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= R) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    public int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        Test test = new Test();
//        int testTime = 500000;
//        int maxSize = 100;
//        int maxValue = 100;
//        boolean succeed = true;
//        for (int i = 0; i < testTime; i++) {
//            int[] arr1 = test.generateRandomArray(maxSize, maxValue);
//            int[] arr2 = test.copyArray(arr1);
//            Arrays.sort(arr1);
//            test.mergeSort(arr2);
//            if (!test.isEqual(arr1, arr2)) {
//                System.out.println("Oops!");
//                test.printArray(arr1);
//                test.printArray(arr2);
//                break;
//            }
//        }
//        System.out.println(succeed ? "Nice!" : "Oops!");

        // 用3 5这个链表测试reverseBetween方法
//        Test test = new Test();
//        ListNode head = test.new ListNode(3);
//        ListNode node1 = test.new ListNode(5);
//        head.next = node1;
//        test.reverseBetween(head, 1, 2);

//        ListNode head = new Test().new ListNode(0);
//        ListNode node1 = new Test().new ListNode(1);
//        ListNode node2 = new Test().new ListNode(2);
//
//        head.next = node1;
//        node1.next = node2;
//
//        ListNode head2 = new Test().new ListNode(1000000);
//        ListNode node3 = new Test().new ListNode(1000001);
//        ListNode node4 = new Test().new ListNode(1000002);
//        ListNode node5 = new Test().new ListNode(1000003);
//        head2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        new Test().mergeInBetween(head, 1, 1, head2);

        TreeNode node1 = new Test().new TreeNode(1);
        TreeNode node2 = new Test().new TreeNode(3);
        TreeNode node3 = new Test().new TreeNode(2);
        TreeNode node4 = new Test().new TreeNode(5);
        TreeNode node5 = new Test().new TreeNode(3);
        TreeNode node6 = new Test().new TreeNode(9);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.right = node5;
        node4.left = node6;
        new Test().widthOfBinaryTree(node1);
    }

    public int countOfRange(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process2(arr, 0, arr.length - 1);
    }

    public int process2(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = (L + R) >> 1;
        return process2(arr, L, mid) + process2(arr, mid + 1, R) + merge2(arr, L, mid, R);
    }

    public int merge2(int[] arr, int L, int mid, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        int ans = 0;
        while (p1 <= mid && p2 <= R) {
            ans += arr[p1] < arr[p2] ? (R - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }

    public int largestRectangleArea2(int[] height) {
        int[] stack = new int[height.length];
        int stackSize = 0;
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            // 等于要不要算
            while (stackSize > 0 && height[stack[stackSize - 1]] >= height[i]) {
                int pop = stack[--stackSize];
                int left = stackSize == 0 ? -1 : stack[stackSize - 1];
                ans = Math.max(ans, height[pop] * (i - left - 1));
            }
            stack[++stackSize] = i;
        }
        while (stackSize > 0) {
            int pop = stack[--stackSize];
            int left = stackSize == 0 ? -1 : stack[stackSize - 1];
            ans = Math.max(ans, height[pop] * (pop - left - 1));
        }
        return ans;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;

        TreeNode(int x) {
            val = x;
        }
    }


    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left < 1 || right < 1 || left >= right) {
            return head;
        }
        // 链表的的位置是从1开始的
        int index = 0;
        ListNode cur = head;
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode leftBefore = null;
        ListNode rightAfter = null;
        while (cur != null) {

            if (left != 1 && index == left - 2) {
                leftBefore = cur;
            }

            index++;
            if (index >= left && index <= right) {
                stack.push(cur);
            }
            if (index == right + 1) {
                rightAfter = cur;
            }
            cur = cur.next;

        }
        if (!stack.isEmpty() && leftBefore != null) {
            leftBefore.next = stack.peek();

        }
        while (!stack.isEmpty()) {
            cur = stack.pop();
            if (leftBefore == null) {
                head = cur;
                leftBefore = cur;
            }
            cur.next = stack.isEmpty() ? rightAfter : stack.peek();
        }


        return head;

    }

    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {
        private Node[] nodes; // 实际的堆结构
        // key 某一个node， value 上面堆中的位置
        private HashMap<Node, Integer> heapIndexMap;
        // key 某一个节点， value 从源节点出发到该节点的目前最小距离
        private HashMap<Node, Integer> distanceMap;
        private int size; // 堆上有多少个点

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // 有一个点叫node，现在发现了一个从源节点出发到达node的距离为distance
        // 判断要不要更新，如果需要的话，就更新
        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (inHeap(node)) {
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeapify(heapIndexMap.get(node));
            }
            if (!isEntered(node)) {
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(size++);
            }
        }

        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            heapIndexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            // free C++同学还要把原本堆顶节点析构，对java同学不必
            nodes[size - 1] = null;
            heapify(0, --size);
            return nodeRecord;
        }

        private void insertHeapify(int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index, int size) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left])
                        ? left + 1
                        : left;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        private boolean inHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }
    }

    // 改进后的dijkstra算法
    // 从head出发，所有head能到达的节点，生成到达每个节点的最小路径记录并返回
    public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        HashMap<Node, Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            result.put(cur, distance);
        }
        return result;
    }

    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        if (a <= 0 || b <= 0 || list1 == null || list2 == null || a > b) {
            return list1;
        }
        int index = 0;
        ListNode cur = list1;
        ListNode preA = null;
        ListNode preB = null;
        while (index != b + 1) {
            if (index == a - 1) {
                preA = cur;
            }
            if (index == b - 1) {
                preB = cur;
            }
            if (a == b) {
                preB = preA;
            }
            index++;
            cur = cur.next;
        }

        preA.next = list2;
        if (a != b) {
            preB.next = null;
        }
        ListNode cur2 = list2;
        while (cur2.next != null) {
            cur2 = cur2.next;
        }
        cur2.next = cur;

        return list1;

    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 使用宽度优先遍历
        // 优化点 不使用额外的容器 比如使用map设置层数
        // 当前层结束
        TreeNode curLevelEnd = root;
        TreeNode nextLevelEnd = null;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int ans = 0;
        int curSize = 0;
        while (!queue.isEmpty()) {

            TreeNode node = queue.poll();
            if (node.left != null) {
                nextLevelEnd = node.left;
                queue.add(node.left);
            }
            if (node.right != null) {
                nextLevelEnd = node.right;
                queue.add(node.right);
            }
            if (curLevelEnd == node) {
                // 当前层结束结算
                curSize++;
                ans = Math.max(ans, curSize);
                curLevelEnd = nextLevelEnd;
                curSize = 0;
            } else {
                // 当前层还没有结束的时候
                curSize++;
            }
        }
        return ans;

    }

    // 点
    class Node {
        public int value;
        // 入度
        public int in;
        // 出度
        public int out;

        Node(int value) {
            this.value = value;
        }

        List<Node> nexts;
        List<Edge> edges;

    }

    // 图
    class Graph {
        // 图的表示
        // 点集
        Map<Integer, Node> nodes;

        Set<Edge> edges;

        Graph() {
            nodes = new HashMap<>();
            edges = new HashSet<>();
        }

    }

    // 边
    class Edge {
        // 边的权重
        public int weight;
        // 边总是从一边指向另外一边
        // 边的起点
        public Node from;
        // 边的终点
        public Node to;

        Edge(Node from, Node to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;

        }
    }


    public Graph creatGraph(int[][] arr) {
        Graph graph = new Graph();
        for (int i = 0; i < arr.length; i++) {
            // 数组的第一列 代表的from
            Node from = graph.nodes.get(arr[i][0]);
            if (from == null) {
                from = new Node(arr[i][0]);
                graph.nodes.put(arr[i][0], from);
            }
            // 数组的第二列 代表的to
            Node to = graph.nodes.get(arr[i][1]);
            if (to == null) {
                to = new Node(arr[i][1]);
                graph.nodes.put(arr[i][1], to);
            }

            from.nexts.add(to);
            from.out++;
            to.in++;
            Edge edge = new Edge(from, to, arr[i][2]);
            from.edges.add(edge);
            graph.edges.add(edge);


        }
        return graph;

    }

    public Map<Node, Integer> DIj1(Node from) {
        if (from == null) {
            return null;
        }
        Map<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(from, 0);
        Set<Node> selectedNode = new HashSet<>();
        Node minNode = getMinNode(distanceMap, selectedNode);
        // 维护一张最小距离的表
        while (minNode != null) {
            // 找到临近的边
            for (Edge edge : minNode.edges) {
                Node to = edge.to;
                if (distanceMap.get(to) == null) {
                    distanceMap.put(to, edge.weight);
                } else {
                    // 如果在距离表已经有了  那么意味着可能会触发到距离的更新
                    // 怎么更呢？
                    // 是原有的distanceMap的距离 与从minNode出发到to的距离的比较取最小值
                    distanceMap.put(to, Math.min(distanceMap.get(to), edge.weight + distanceMap.get(minNode)));
                }
            }
            // 对每一个节点计算完成之后 该节点就需要进行结算 加入到selectedNode集合中
            selectedNode.add(minNode);
            // 重新获取没有结算过的点 并且在distanceMap中距离最小的点
            minNode = getMinNode(distanceMap, selectedNode);
        }
        return distanceMap;

    }

    public Map<Node, Integer> diJ2(Node from, int size) {
        if (from == null || size <= 0) {
            return null;
        }
        Map<Node, Integer> distanceMap = new HashMap<>();
        HeapGreater heapGreater = new HeapGreater(size);
        heapGreater.addOrUpdateOrIgnore(from, 0);
        while (!heapGreater.isEmpty()) {
            NodeRecord nodeRecord = heapGreater.pop();
            Node cur = nodeRecord.node;
            int distance = nodeRecord.distance;
            for (Edge edge : cur.edges) {
                heapGreater.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            distanceMap.put(cur, distance);
        }
        return distanceMap;
    }


    class HeapGreater {
        // 使用node类型的堆结构 进行小跟堆的维护
        private Node[] heap;
        // 距离表 用于对比是否需要更新
        private Map<Node, Integer> distanceMap;
        // 堆结构大小限制
        private int limit;

        // 维护一个下标索引 进行堆的改造 允许堆内的数据进行更新
        private Map<Node, Integer> indexMap;

        // 堆的大小
        private int size;

        HeapGreater(int limit) {
            this.limit = limit;
            heap = new Node[limit];
            distanceMap = new HashMap<>();
            indexMap = new HashMap<>();
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (inHeap(node)) {
                // 如果在堆内 并且没有结算的话 那么就需要进行比较之后更新
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                heapInsert(indexMap.get(node));
            }
            if (!isEntered(node)) {
                // 没有进过的节点就是新增
                distanceMap.put(node, distance);
                indexMap.put(node, size);
                heap[size] = node;
                heapInsert(size++);
            }
        }

        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(heap[0], distanceMap.get(heap[0]));
            // 交换第一位与最后一位
            swap(0, size - 1);
            distanceMap.remove(heap[size - 1]);
            indexMap.put(heap[size - 1], -1);
            heap[size - 1] = null;
            size--;
            heapify(0);
            return nodeRecord;
        }


        public void heapify(int index) {
            int left = index * 2 + 1;
            while (left < size) {
                int smallest = left + 1 < size && distanceMap.get(heap[left + 1]) < distanceMap.get(heap[left])
                        ? left + 1
                        : left;
                smallest = distanceMap.get(heap[smallest]) < distanceMap.get(heap[index]) ? smallest : index;
                if (smallest == index) {
                    break;
                }
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        public void heapInsert(int index) {
            while (distanceMap.get(heap[index]) < distanceMap.get(heap[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        public void swap(int i, int j) {
            indexMap.put(heap[i], j);
            indexMap.put(heap[j], i);
            Node tmp = heap[i];
            heap[i] = heap[j];
            heap[j] = tmp;

        }

        // 经典的实现需要维护一个单独的数据结构才能知道是不是已经结算过了
        // 在加强堆中 可以通过堆的indexMap知道是不是已经参与过结算
        public boolean inHeap(Node node) {
            return indexMap.get(node) != null && indexMap.get(node) != -1;
        }

        public boolean isEntered(Node node) {
            return indexMap.get(node) != null && indexMap.get(node) == -1;
        }

    }

    public Node getMinNode(Map<Node, Integer> distanceMap, Set<Node> selectedNode) {
        Node ans = null;
        int minValue = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int value = entry.getValue();
            if (!selectedNode.contains(node) && value < minValue) {
                ans = node;
                minValue = value;
            }
        }
        return ans;
    }


}
