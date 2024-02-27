package systemlearning.code16;

public class Test {
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
        TreeNode root = test.new TreeNode(10);
        TreeNode node1 = test.new TreeNode(5);
        TreeNode node2 = test.new TreeNode(15);
        TreeNode node3 = test.new TreeNode(3);
        TreeNode node4 = test.new TreeNode(7);
        TreeNode node5 = test.new TreeNode(18);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;
        System.out.println(test.rangeSumBST(root, 7, 15));
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

    class ListNode {
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

        return bothUnLoop(nodeA,nodeB);
    }

    public ListNode bothUnLoop(ListNode nodeA,ListNode nodeB) {
        int n = 0;
        ListNode curA = nodeA;
        while (curA !=null) {
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

}
