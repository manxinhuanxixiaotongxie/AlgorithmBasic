package leetcode.classic150;

public class Code002 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 进位
        int add = 0;
        // 影子节点
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l1 != null && l2 != null) {
            // 计算当前节点的值
            int curVal = l1.val + l2.val + add;
            add = curVal / 10;
            curVal = curVal % 10;
            // 创建新节点并连接到结果链表
            cur.next = new ListNode(curVal);
            cur = cur.next;
            // 移动到下一个节点
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            int curVal = l1.val + add;
            add = curVal / 10;
            curVal = curVal % 10;
            cur.next = new ListNode(curVal);
            cur = cur.next;
            l1 = l1.next;
        }

        while (l2 != null) {
            int curVal = l2.val + add;
            add = curVal / 10;
            curVal = curVal % 10;
            cur.next = new ListNode(curVal);
            cur = cur.next;
            l2 = l2.next;
        }
        if (add != 0) {
            cur.next = new ListNode(add);
        }

        return dummyHead.next;
    }
}
