package leetcode.classic150;

import java.util.List;

public class Code086 {
    public ListNode partition(ListNode head, int x) {
        ListNode dummySmall = new ListNode(0);
        ListNode bummyBig = new ListNode(0);
        ListNode smallNode  = dummySmall;
        ListNode bigNode = bummyBig;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;

            if (cur.val < x) {
                smallNode.next = cur;
                smallNode = cur;
                smallNode.next = null;
            }else {
                bigNode.next = cur;
                bigNode = cur;
                bigNode.next = null;
            }
            cur = next;
        }
        smallNode.next = bummyBig.next;
        return dummySmall.next;
    }

    static void main() {
        Code086 code = new Code086();
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        ListNode res = code.partition(head, 3);
        while (res != null) {
            System.out.println(res.val);
            res = res.next;
        }
    }
}
