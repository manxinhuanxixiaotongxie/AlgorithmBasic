package systemimprove;

import systemimprove.code13.Edge;
import systemimprove.code13.Node;

import java.util.Arrays;
import java.util.HashMap;
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
        Test test = new Test();
        ListNode head = test.new ListNode(3);
        ListNode node1 = test.new ListNode(5);
        head.next = node1;
        test.reverseBetween(head, 1, 2);
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




}
