package book_02linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-04-18 14:07
 */
public class DeleteRepeatNode {

    /**
     * 给定一个无序单链表的头结点head，删除其中的重复出现的节点
     * 实现两个方法
     * 1.链表长度为N，时间复杂度达到O（N）
     * 2。额外空间复杂度O（1）
     */
    public ListNode deleteRepeatNode1(ListNode head) {

        /**
         * 时间复杂度O（N）
         */
        if (head == null) {
            return head;
        }
        // 0 0 0 0
        ListNode pre = head;
        ListNode cur = head.next;
        Map<Integer, ListNode> map = new HashMap<>();
        map.put(cur.val, cur);
        while (cur != null) {
            if (map.containsKey(cur.val)) {
                pre.next = cur.next;
            } else {
                map.put(cur.val, cur);
                pre = cur;
            }
            cur = cur.next;
        }


        return head;
    }

    /**
     * 给定一个无序单链表的头结点head，删除其中的重复出现的节点
     * 实现两个方法
     * 1.链表长度为N，时间复杂度达到O（N）
     * 2。额外空间复杂度O（1）
     */
    public ListNode deleteRepeatNode2(ListNode head) {

        /**
         * 时间复杂度O（1）
         */
        if (head == null) {
            return head;
        }
        // 0 0 0 0
        ListNode cur = head;
        ListNode pre = null;
        ListNode next = null;
        while (cur != null) {
            pre = cur;
            next = cur.next;
            while (next != null) {
                if (cur.val == next.val) {
                    pre.next = cur.next;
                } else {
                    pre = next;
                }
                next = next.next;
            }
            cur = cur.next;
        }


        return head;
    }
}
