package leetcode.day;

public class Code1290 {
    public int getDecimalValue(ListNode head) {
        if (head == null) return 0;
        ListNode cur = head;
        ListNode pre = null;
        ListNode next;
        // 翻转链表
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        int ans = 0;

        cur = pre;
        int index = 0;
        while (cur != null) {
            next = cur.next;
            // 计算二进制值
            ans += cur.val * (1<< index++);
            cur = next;
        }
        // 恢复链表
        cur = pre;
        pre = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return ans;
    }

    public static void main(String[] args) {
        Code1290 code1290 = new Code1290();
        ListNode head = new ListNode(1);
        head.next = new ListNode(0);
        head.next.next = new ListNode(1);
        int result = code1290.getDecimalValue(head);
        System.out.println("二进制转换为十进制的值: " + result); // 输出: 5
    }
}
