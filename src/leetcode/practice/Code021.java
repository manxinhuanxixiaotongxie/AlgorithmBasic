package leetcode.practice;

import leetcode.ListNode;

public class Code021 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 基本条件过滤
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        // 设置新的头结点
        ListNode head = list1.val < list2.val ? list1 : list2;
        // 设置两个新的指针 在两个原始链表上进行遍历
        ListNode cur1 = list1 == head ? list1 : list2;
        ListNode cur2 = list1 == head ? list2 : list1;
        ListNode cur = new ListNode(0);
        ListNode next = cur;
        while (cur1 != null && cur2 != null) {
            // 开始对链表进行合并
            if (cur1.val < cur2.val) {
                // 当前链表的下一个节点
                // 两个链表进行比对
                // 谁小谁向下移动
                next.next = cur1;
                // 谁小谁向下移动
                next = next.next;
                cur1 = cur1.next;
            }else {
                next.next = cur2;
                next = next.next;
                // 当前链表的下一个节点
               cur2 = cur2.next;
            }

        }
        if (cur1 != null) {
            next.next = cur1;
        }
        if (cur2 != null) {
            next.next = cur2;
        }
        // 返回新链表头的头结点
        return cur.next;

    }

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        ListNode list2 = new ListNode(2);
        ListNode list3 = new ListNode(4);
        list1.next = list2;
        list2.next = list3;

        ListNode list4 = new ListNode(1);
        ListNode list5 = new ListNode(3);
        ListNode list6 = new ListNode(4);
        list4.next = list5;
        list5.next = list6;

        Code021 code021 = new Code021();
        ListNode listNode = code021.mergeTwoLists(list1, list4);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
