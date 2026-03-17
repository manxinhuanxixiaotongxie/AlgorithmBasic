package leetcode.classic150;

/**
 * k个一组翻转链表
 * <p>
 * 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
 *
 */
public class Code025 {
    public ListNode reverseKGroup(ListNode head, int k) {
        // 新的影子节点
        ListNode dummy = new ListNode(-1);
        // 上一个分组的结束节点
        dummy.next = head;
        // 本段翻转之后的结束节点
        // 下一段的开始节点
        ListNode lastEnd = dummy, curEnd = head;

        // k个一组翻转连链表
        int index = 0;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            ListNode temp = cur;
            while (index < k-1 && next != null) {
                index++;
                cur = next;
                next = next.next;

            }
            if (index < k-1) {
                // 不足k个，不翻转
                break;
            }
            // 拿到本次翻转段的开始节点 以及结尾节点 并进行重新连接
            // 第一步 翻转temp到cur这一段链表
            ListNode curStart = reverses(temp, cur);
            // 拿到翻转之后的新链表进行重新连接
            curEnd.next = next;
            lastEnd.next = curStart;
            lastEnd = curEnd;
            curEnd = next;
            cur = next;
            index = 0;
        }

        return dummy.next;
    }

    // 翻转链表
    private ListNode reverses(ListNode head, ListNode tail) {
        ListNode cur = head;
        ListNode prev = null;
        while (cur != tail) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        tail.next = prev;
        return tail;
    }

    static void main() {
            ListNode head = new ListNode(1);
            head.next = new ListNode(2);
            head.next.next = new ListNode(3);
            head.next.next.next = new ListNode(4);
            head.next.next.next.next = new ListNode(5);

            Code025 code025 = new Code025();
            ListNode res = code025.reverseKGroup(head, 3);
            while (res != null) {
                System.out.println(res.val);
                res = res.next;
            }
    }
}
