package systemimprove.code08;

import java.util.HashSet;

/**
 * 链表相交问题 如果两个链表相交  返回想第一个相交节点，如果不相交返回null
 * <p>
 * 链表相交的几种情况：
 * 1.第一种情况  两个链表都无环
 * 2.第二种情况  两个链表都有环
 * 两个链表都有环分为两种情况 第一种 两个链表的入环节点相同
 * 第二种情况 两个链表入环节点不相同
 */
public class Code05_InterSectionNode {
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        HashSet<ListNode> set = new HashSet<>();
        ListNode curA = headA;
        ListNode curB = headB;
        while (curA != null) {
            if (set.contains(curA)) {
                break;
            } else {
                set.add(curA);
            }
            curA = curA.next;
        }

        while (curB != null) {
            if (set.contains(curB)) {
                return curB;
            }
            curB = curB.next;
        }
        return null;
    }

    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode loopA = isLoop(headA);
        ListNode loopB = isLoop(headB);
        // 两个都无环
        if (loopA == null && loopB == null) {
            return getNoLoopFirstNode(headA, headB);
        }
        // 两个都有环
        if (loopA != null && loopB != null) {
            if (loopA == loopB) {
                return bothLoop(headA, headB, loopA, loopB);
            } else {
                ListNode cur = loopA.next;
                while (cur != loopA) {
                    if (cur == loopB) {
                        return loopB;
                    }
                    cur = cur.next;
                }
                return null;
            }
        }

        return null;
    }

    public static ListNode bothLoop(ListNode nodeA, ListNode nodeB, ListNode loop1, ListNode loop2) {
        if (nodeA == null || nodeB == null) {
            return null;
        }

        ListNode curA = nodeA;
        int n = 0;
        while (curA != loop1) {
            n++;
            curA = curA.next;
        }
        ListNode curB = nodeB;
        while (curB != loop2) {
            n--;
            curB = curB.next;
        }
        ListNode cur = null;
        cur = n > 0 ? nodeA : nodeB;
        int abs = Math.abs(n);
        while (abs > 0) {
            abs--;
            cur = cur.next;
        }
        ListNode cur2 = n > 0 ? nodeA : nodeB;
        while (cur != cur2) {
            cur = cur.next;
            cur2 = cur2.next;
        }
        return cur;
    }

    /**
     * 两个无环链表相交，返回第一个相交节点，不相交返回null
     *
     * @param nodeA
     * @param nodeB
     * @return
     */
    public static ListNode getNoLoopFirstNode(ListNode nodeA, ListNode nodeB) {
        if (nodeA == null || nodeB == null) {
            return null;
        }
        ListNode curA = nodeA;
        int n = 0;
        while (curA != null) {
            n++;
            curA = curA.next;
        }
        ListNode curB = nodeB;
        while (curB != null) {
            n--;
            curB = curB.next;
        }
        ListNode cur = null;
        cur = n > 0 ? nodeA : nodeB;
        int abs = Math.abs(n);
        while (abs > 0) {
            abs--;
            cur = cur.next;
        }
        ListNode cur2 = n > 0 ? nodeB : nodeA;
        while (cur != cur2) {
            cur = cur.next;
            cur2 = cur2.next;
        }
        return cur;

    }

    /**
     * 判断链表是否有环，有环返回入环节点，无环返回null
     *
     * @param node
     * @return
     */
    public static ListNode isLoop(ListNode node) {
        if (node == null || node.next == null || node.next.next == null) {
            return null;
        }
        // 使用快慢指针
        ListNode slow = node.next;
        ListNode fast = node.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        // 快慢指针第一次相交的时候 将快指针移动到头节点 并且快指针步长与慢指针相同
        // 此时 快指针与慢指针再次相交的时候 就是第一个入环的节点
        fast = node;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }


    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(6);
        head1.next.next.next.next.next.next = new ListNode(7);

        // 0->9->8->6->7->null
        ListNode head2 = new ListNode(0);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectionNode2(head1, head2).val);

        // 1->2->3->4->5->6->7->4...
        head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(6);
        head1.next.next.next.next.next.next = new ListNode(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new ListNode(0);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectionNode2(head1, head2).val);

        // 0->9->8->6->4->5->6..
        head2 = new ListNode(0);
        head2.next = new ListNode(9);
        head2.next.next = new ListNode(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectionNode(head1, head2).val);

    }
}
