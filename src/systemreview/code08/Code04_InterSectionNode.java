package systemreview.code08;

public class Code04_InterSectionNode {

    /**
     * 给定两个链表
     * 判断链表是否相交
     * 如果不相交返回null
     * 如果相交 返回相交的节点
     *
     * @param a
     * @param b
     * @return
     */
    public static Node interSectionNode(Node a, Node b) {
        if (a == null || b == null) {
            return null;
        }
        // 首先判断两个链表是否有环
        Node loopA = getLoopNode(a);
        Node loopB = getLoopNode(b);
        // 根据不同的情况进行处理
        if (loopA == null && loopB == null) {
            // 两个链表都无环
            return bothUnLoop(a, b);
        } else if (loopA != null && loopB != null) {
            // 两个链表都有环
            return isBossLoop(a, loopA, b, loopB);
        }
        return null;
    }


    /**
     * 判断链表是否有环
     *
     * @param node
     * @return
     */
    private static Node getLoopNode(Node node) {
        if (node == null || node.next == null || node.next.next == null) {
            return null;
        }

        Node slow = node.next;
        Node fast = node.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        // 快指针与慢指针第一次相交
        // 让快指针来到头结点 快慢指针的步长调整成一致
        // 走到这里其实已经说明链表是有环的了
        fast = node;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    // 两个链表都无环 返回相交的节点
    private static Node bothUnLoop(Node a, Node b) {
        if (a == null || b == null) {
            return null;
        }
        int n = 0;
        Node curA = a;
        while (curA != null) {
            n++;
            curA = curA.next;
        }
        Node curB = b;
        while (curB != null) {
            n--;
            curB = curB.next;
        }

        Node biggerNode = n > 0 ? a : b;
        Node smallNode = n > 0 ? b : a;
        n = Math.abs(n);
        // 如果链表a的长度是5 链表b的长度是7 那么先让较长链表走两步
        while (n > 0) {
            biggerNode = biggerNode.next;
            n--;
        }
        while (biggerNode != smallNode) {
            biggerNode = biggerNode.next;
            smallNode = smallNode.next;
        }
        return biggerNode;
    }

    private static Node isBossLoop(Node a, Node loopA, Node b, Node loopB) {
        Node curA = a;
        Node curB = b;
        if (loopA == loopB) {
            int n = 0;
            // 两个链表相交 第一个入环节点相同
            while (curA != loopA) {
                curA = curA.next;
                n++;
            }
            while (curB != loopB) {
                curB = curB.next;
                n--;
            }
            Node biggerNode = n > 0 ? a : b;
            Node smallNode = n > 0 ? b : a;
            n = Math.abs(n);
            while (n > 0) {
                biggerNode = biggerNode.next;
                n--;
            }
            while (biggerNode != smallNode) {
                biggerNode = biggerNode.next;
                smallNode = smallNode.next;
            }
            return smallNode;
        } else {
            curA = loopA.next;
            while (curA != loopA) {
                if (curA == loopB) {
                    return loopA;
                }
                curA = curA.next;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(interSectionNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(interSectionNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(interSectionNode(head1, head2).value);
    }


}

