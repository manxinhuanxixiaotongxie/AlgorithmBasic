package leetcode;

/**
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * <p>
 * 分析题意，要求我所有小于X的节点出现在大于或者等于X的节点之前
 * <p>
 * 1.第一步 找到第一个大于或者等于X的节点
 * 2.第二步 继续遍历链表 找到小于X的节点 将其插入到第一个大于或者等于X的节点之前
 * <p>
 * 注意几个点：
 * 1.该链表可能涉及到换头 因此要标记可能换头的场景
 * 2.当不换头的时候 相当于是大于等于X的节点后面小于X的节点从后面抽出 该节点的前面一个界面的位置没有发生变化
 */
public class Code86 {
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }

        // 找到第一个大于X的节点
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            if (cur.val >= x) {
                break;
            }
            pre = cur;
            cur = cur.next;
        }
//        cur = head;
        ListNode pre2 = null;
        ListNode next = null;
        // 可能涉及到换头
        while (cur != null) {
            next = cur.next;
            if (cur.val < x) {
                // pre为空 需要将第一个小于X的节点置为头结点
                if (pre == null) {
                    // 当前节点作为新的头结点
                    pre2.next = next;
                    cur.next = head;
                    // 换头 后面的pre不会为空了
                    head = cur;
                    pre = cur;
                } else {
                    // 如果pre不为空 意味着不需要换头
                    cur.next = pre.next;
                    pre.next = cur;
                    pre2.next = next;
                    pre = cur;

                }
            } else {
                pre2 = cur;
            }
            // 只有当cur.val < x的时候才会移动pre2
//            pre2 = cur;
            cur = next;
        }
        return head;
    }
}
