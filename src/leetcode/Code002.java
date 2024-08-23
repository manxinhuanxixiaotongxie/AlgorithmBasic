package leetcode;

public class Code002 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode cur1 = l1;
        ListNode cur2 = l2;
        boolean needAdd = false;
        ListNode newHead = null;
        ListNode pre = null;
        while (cur1 != null && cur2 != null) {
            ListNode cur = null;
            if (cur1.val + cur2.val + (needAdd?1:0) >= 10) {
                cur = new ListNode(cur1.val + cur2.val - 10 + (needAdd?1:0));
                needAdd = true;
            }else {
                cur = new ListNode(cur1.val + cur2.val + (needAdd?1:0));
                needAdd = false;
            }
            if (newHead == null) {
                newHead = cur;
                pre = cur;
            }else {
                pre.next = cur;
                pre = cur;
            }
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        while (cur1 != null) {
            ListNode cur = null;
            if (cur1.val + (needAdd?1:0) >= 10) {
                cur = new ListNode(cur1.val - 10 + (needAdd?1:0));
                needAdd = true;
            }else {
                cur = new ListNode(cur1.val + (needAdd?1:0));
                needAdd = false;
            }
            pre.next = cur;
            pre = cur;
            cur1 = cur1.next;
        }
        while (cur2 != null) {
            ListNode cur = null;
            if (cur2.val + (needAdd?1:0) >= 10) {
                cur = new ListNode(cur2.val - 10 + (needAdd?1:0));
                needAdd = true;
            }else {
                cur = new ListNode(cur2.val + (needAdd?1:0));
                needAdd = false;
            }
            pre.next = cur;
            pre = cur;
            cur2 = cur2.next;
        }
        if (needAdd) {
            pre.next = new ListNode(1);
        }
        return newHead;
    }

    public static void main(String[] args) {
        Code002 code002 = new Code002();
        ListNode l1 = new ListNode(0);
        ListNode l2 = new ListNode(8);
        ListNode l3 = new ListNode(6);
        ListNode l4 = new ListNode(5);
        ListNode l5 = new ListNode(6);
        ListNode l6 = new ListNode(8);
        ListNode l7 = new ListNode(3);
        ListNode l8 = new ListNode(5);
        ListNode l9 = new ListNode(7);

        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;
        l7.next = l8;
        l8.next = l9;

        ListNode l11 = new ListNode(6);
        ListNode l12 = new ListNode(7);
        ListNode l13 = new ListNode(8);
        ListNode l14 = new ListNode(0);
        ListNode l15 = new ListNode(8);
        ListNode l16 = new ListNode(5);
        ListNode l17 = new ListNode(8);
        ListNode l18 = new ListNode(9);
        ListNode l19 = new ListNode(7);

        l11.next = l12;
        l12.next = l13;
        l13.next = l14;
        l14.next = l15;
        l15.next = l16;
        l16.next = l17;
        l17.next = l18;
        l18.next = l19;

        ListNode result = code002.addTwoNumbers(l1, l11);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

    }
}
