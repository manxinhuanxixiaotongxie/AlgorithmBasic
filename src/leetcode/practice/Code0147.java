package leetcode.practice;

import leetcode.ListNode;

public class Code0147 {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int size = 0;
        ListNode cur = head;
        while (cur != null) {
            size++;
            cur = cur.next;
        }
        ListNode[] nodes = new ListNode[size];
        cur = head;
        size = 0;
        while (cur != null) {
            nodes[size++] = cur;
            cur = cur.next;
        }

        // 构建数组之后进行插入排序
        int index = 1;
        while (index < size) {
            int temp = index;
            while (temp >= 1 && nodes[temp].val < nodes[temp - 1].val) {
                ListNode t = nodes[temp];
                nodes[temp] = nodes[temp - 1];
                nodes[temp - 1] = t;
                temp--;
            }
            index++;
        }
        // 重新构建链表
        cur = nodes[0];
        for (int i = 1; i < nodes.length; i++) {
            cur.next = nodes[i];
            cur = cur.next;
        }
        return nodes[0];
    }

    /**
     * 更优雅的实现方式
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        return null;

    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        Code0147 code0147 = new Code0147();
        ListNode listNode = code0147.insertionSortList2(head);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
