package systemlearning.code16;

import java.util.Arrays;

public class Test {

    static final int MOD = 1000000007;

    public int rangeSumBST(TreeNode root, int low, int high) {

        return process(root, low, high);
    }

    public int process(TreeNode node, int low, int high) {
        if (node == null) {
            return 0;
        }

        int left = process(node.left, low, high);
        int right = process(node.right, low, high);
        int child = left + right;
        if (node.val >= low && node.val <= high) {
            return node.val + child;
        }
        return child;

    }

    public static void main(String[] args) {
        Test test = new Test();
//        TreeNode root = test.new TreeNode(10);
//        TreeNode node1 = test.new TreeNode(5);
//        TreeNode node2 = test.new TreeNode(15);
//        TreeNode node3 = test.new TreeNode(3);
//        TreeNode node4 = test.new TreeNode(7);
//        TreeNode node5 = test.new TreeNode(18);
//        root.left = node1;
//        root.right = node2;
//        node1.left = node3;
//        node1.right = node4;
//        node2.right = node5;
//        System.out.println(test.rangeSumBST(root, 7, 15));
//        int N = 45;
//        System.out.println(test.fib(N));
//        System.out.println(test.fib2(N));
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(4);
//        ListNode node4 = new ListNode(1);
//        ListNode node5 = new ListNode(3);
//        ListNode node6 = new ListNode(4);

//        node1.next = node2;
//        node2.next = node3;
//
//        node4.next = node5;
//        node5.next = node6;

        test.mergeTwoLists(node2, node1);

        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(100, 100);
            int[] arr1 = copyArray(arr);
            int[] arr2 = copyArray(arr);
            test.heapSort(arr1);
            Arrays.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                System.out.println("出错了");
                printArray(arr);
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static boolean isEqual(int[] arr1, int[] arr2) {
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

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        return isLoop(head);
    }

    public ListNode isLoop(ListNode head) {
        ListNode slow = head.next;
        ListNode fast = head.next.next;
        // 快指针一次走两步 慢指针一次走一步
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode nodeA = isCycle(headA);
        ListNode nodeB = isCycle(headB);
        // 只有两个可能
        // 如果两个链表都有环  这种又分为两种情况   第一个入环节点相同  第一个入环节点不同
        // 两个链表都无环
        // 其他的场景都不可能相交
        if (nodeA != null && nodeB != null) {
            if (nodeA == nodeB) {
                return nodeA;
            }
        }

        if (nodeA == null && nodeB == null) {
            return null;
        }

        return bothUnLoop(nodeA, nodeB);
    }

    public ListNode bothUnLoop(ListNode nodeA, ListNode nodeB) {
        int n = 0;
        ListNode curA = nodeA;
        while (curA != null) {
            n++;
            curA = curA.next;
        }
        curA = nodeB;
        while (curA != null) {
            n--;
            curA = curA.next;
        }
        curA = n > 0 ? nodeA : nodeB;
        int abs = Math.abs(n);
        while (abs > 0) {
            abs--;
            curA = curA.next;
        }
        while (curA != null) {
            if (curA == nodeB) {
                return curA;
            }
            curA = curA.next;
            nodeB = nodeB.next;
        }
        return null;
    }

    // 返回第一个入环节点
    public ListNode isCycle(ListNode headA) {
        if (headA == null || headA.next == null || headA.next.next == null) {
            return null;
        }

        ListNode slow = headA.next;
        ListNode fast = headA.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next;
        }
        fast = headA;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    public int fib2(int n) {
        if (n < 2) {
            return n;
        }
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n - 1);
        return res[0][0];
    }

    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        // 打印ret
        for (int i = 0; i < ret.length; i++) {
            for (int j = 0; j < ret[0].length; j++) {
                System.out.print(ret[i][j] + " ");
            }
            System.out.println();
        }
        return ret;
    }

    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = (int) (((long) a[i][0] * b[0][j] + (long) a[i][1] * b[1][j]) % MOD);
            }
        }
        return c;
    }

    public int fib(int N) {
        if (N < 1) {
            return 0;
        }
        if (N == 1 || N == 2) {
            return 1;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] ans = getMatrix(base, N - 2);
        return 1 * ans[0][0] + 1 * ans[1][0];
    }

    public static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }
        // res = 矩阵中的1
        int[][] t = m;// 矩阵1次方
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = product(res, t);
            }
            t = product(t, t);
        }
        return res;
    }

    public int[][] getMatrix(int[][] base, int x) {
        int[][] ans = new int[base.length][base[0].length];
        // 单位矩阵 此矩阵与任何矩阵相乘都是原矩阵
        for (int i = 0; i < ans.length; i++) {
            ans[i][i] = 1;
        }
        int[][] temp = base;
        for (; x != 0; x >>= 1) {
            if ((x & 1) != 0) {
                ans = multiply(ans, temp);
            }
            // temp原地相乘
            temp = multiply(temp, temp);
        }
        // 打印ans
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
        return ans;
    }

    public int[][] muliMatrix(int[][] ans, int[][] temp) {
        // 这两个相乘生成的新的数组 行与ans数组的行相同，列与temp数组的列相同
        int[][] multi = new int[ans.length][temp[0].length];
        // 他们的计算方式是 ans数组的行的每一个数 乘以temp数组相同列的每一个数
        for (int i = 0; i < ans.length; i++) {
            // 列
            for (int j = 0; j < temp[0].length; j++) {
                // 行与列依次相乘之后的和就是新数组 i j位置的值
//                for (int k = 0; k < temp.length; k++) {
//                    multi[i][j] += (int) (((long) ans[i][k] * temp[k][j]))%MOD;
////                    multi[i][j] += (int) ((long) ans[i][k] % 1000000007 * (long) temp[k][j] % 1000000007);
//                }
                multi[i][j] = (int) (((long) ans[i][0] * temp[0][j] + (long) ans[i][1] * temp[1][j]) % MOD);
            }
        }
        return multi;
    }

    public static int[][] product(int[][] a, int[][] b) {
        int n = a.length;
        int m = b[0].length;
        int k = a[0].length; // a的列数同时也是b的行数
        int[][] ans = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int c = 0; c < k; c++) {
                    ans[i][j] += (int) (((long) a[i][c] * b[c][j]) % MOD);
                }
            }
        }
        return ans;
    }

    /**
     * 合并两个链 lettcode测试通过
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }
        ListNode head = list1.val <= list2.val ? list1 : list2;
        // 小头
        ListNode cur = head == list1 ? list1 : list2;
        // 大头
        ListNode cur1 = head == list1 ? list2 : list1;

        ListNode next = null;
        ListNode pre = null;
        while (cur != null && cur1 != null) {
            if (cur.val > cur1.val) {
                pre.next = cur1;
                pre = cur1;
                next = cur1.next;
                cur1.next = cur;
                cur1 = next;
            } else {
                next = cur.next;
                pre = cur;
                cur = next;
            }
        }
        // 有一个先到
        if (cur1 != null) {
            pre.next = cur1;
        }

        return head;
    }


    // 回顾堆排序
    public int[] heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }

        for (int N = arr.length - 1; N >= 0; N--) {
//            heapInsert(arr, N);
            heapify(arr, N, arr.length);
        }

        heapSize = arr.length;
        while (heapSize > 0) {
            swap(arr, 0, --heapSize);
            heapify(arr, 0, heapSize);
        }

        return arr;
    }

    private int heapSize;
    private int[] heap;

    // 小跟堆
    public void heapInsert(int[] arr, int index) {
        int fatherIndex = (index - 1) / 2;
        while (arr[index] < arr[fatherIndex]) {
            swap(arr, index, fatherIndex);
            fatherIndex = (fatherIndex - 1) / 2;
        }
    }

    public void heapify(int[] arr, int index, int heapSize) {
        int leftIndex = index * 2 + 1;
//        int lowerIndex = leftIndex + 1 < heapSize && arr[leftIndex] > arr[leftIndex + 1] ? leftIndex + 1 : leftIndex;
//        while (lowerIndex < heapSize && arr[index] > arr[lowerIndex]) {
//            swap(arr, index, lowerIndex);
//            index = lowerIndex;
//            leftIndex = index * 2 + 1;
//            lowerIndex = leftIndex + 1 < heapSize && arr[leftIndex] > arr[leftIndex + 1] ? leftIndex + 1 : leftIndex;
//        }

        while (leftIndex < heapSize) {
            // 取左右孩子的最大值 进行比较 因为是小根堆 所以取最大值
            int maxValueIndex = leftIndex + 1 < heapSize && arr[leftIndex] < arr[leftIndex + 1] ? leftIndex+1 : leftIndex;
            maxValueIndex = arr[maxValueIndex] > arr[index] ? maxValueIndex : index;
            if (maxValueIndex == index) {
                break;
            }
            swap(arr, index, maxValueIndex);
            index = maxValueIndex;
            leftIndex = index * 2 + 1;
        }
    }

    public  void heapify2(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int maxValueIndex = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;

            maxValueIndex = arr[maxValueIndex] > arr[index] ? maxValueIndex : index;

            if (maxValueIndex == index) {
                break;
            }
            swap(arr, index, maxValueIndex);
            index = maxValueIndex;
            left = index * 2 + 1;
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
