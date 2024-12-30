package leetcode.practice;

import leetcode.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Code77 {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        List<ListNode> ans = new ArrayList<>();
        ListNode next = null;
        while (cur != null) {
            ans.add(cur);
            next = cur.next;
            cur.next = null;
            cur = next;
        }
        Collections.sort(ans, new MyComparator());
        head = ans.get(0);
        cur = head;
        for (int i = 1; i < ans.size(); i++) {
            cur.next = ans.get(i);
            cur = cur.next;
        }
        return head;

    }

    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        return process(head, null);

    }

    public ListNode process(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        // 找到链表的中点
        ListNode slow = head;
        ListNode fast = head;
        // o o o o
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode l = process(head, slow);
        ListNode r = process(slow, tail);
        ListNode merge = merge(l,r);
        return merge;
    }

    public ListNode merge(ListNode L,ListNode R) {
        ListNode left = L;
        ListNode right = R;
        // 初始化一个头结点
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (left != null && right != null) {
            if (left.val < right.val){
                cur.next = left;
                cur = left;
                left = left.next;
            }else {
                cur.next = right;
                cur = right;
                right = right.next;
            }

        }

//        while (left != null) {
//            cur.next = left;
//            cur = left;
//            left = left.next;
//        }
//
//        while (right != null) {
//            cur.next = right;
//            cur = right;
//            right = right.next;
//        }

        if (left != null) {
            cur.next = left;
        }

        if (right != null) {
            cur.next = right;

        }
        return head.next;

    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        Code77 code77 = new Code77();
        ListNode listNode = code77.sortList(head);
        while (listNode != null) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    class MyComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            return o1.val - o2.val;
        }
    }
}
