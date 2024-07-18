package systemreview.code08;

import systemimprove.code08.ListNode;

public class Code02_PartitionList {
    /**
     * 单向链表按某值划分成左边小、中间相等、右边大的形式
     */
    public static ListNode partitionList(ListNode head, int val) {
        if (head == null || head.next == null) {
            return head;
        }
        // 拆分成三个链表
        ListNode smallHead = null;
        ListNode smallTail = null;
        ListNode equalHead = null;
        ListNode equalTail = null;
        ListNode bigHead = null;
        ListNode bigTail = null;

        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            // 需要断开连接
            cur.next = null;
            if (cur.val < val) {
                if (smallHead == null) {
                    smallHead = cur;
                    smallTail = cur;
                } else {
                    smallTail.next = cur;
                    smallTail = cur;
                }
            } else if (cur.val > val) {
                if (bigHead == null) {
                    bigHead = cur;
                    bigTail = cur;
                } else {
                    bigTail.next = cur;
                    bigTail = cur;
                }
            } else {
                if (equalHead == null) {
                    equalHead = cur;
                    equalTail = cur;
                } else {
                    equalTail.next = cur;
                    equalTail = cur;
                }
            }
            cur = next;
        }
        // 可能存在换头的情况出现
        head = smallHead == null ? equalHead == null ? bigHead : equalHead : smallHead;

        // 将链表重新连接起来
        if (head == smallHead) {
            smallTail.next = equalHead == null ? bigHead : equalHead;
        }

        if (equalHead != null) {
            equalTail.next = bigHead;
        }

        return head;

    }

    public static void printLinkedList(ListNode node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(7);
        head1.next = new ListNode(9);
        head1.next.next = new ListNode(1);
        head1.next.next.next = new ListNode(8);
        head1.next.next.next.next = new ListNode(5);
        head1.next.next.next.next.next = new ListNode(2);
        head1.next.next.next.next.next.next = new ListNode(5);
        printLinkedList(head1);
        // head1 = listPartition1(head1, 4);
        head1 = partitionList(head1, 5);
        printLinkedList(head1);

    }
}
