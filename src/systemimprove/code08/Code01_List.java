package systemimprove.code08;

public class Code01_List {
    /**
     * 链表问题：
     * 笔试一切为了时间复杂度- 不用太在乎空间复杂度
     * 面试：时间复杂度放在第一位，但是一定要找到空间最省的方式
     */
    /**
     * 技巧：
     * 使用容器：map stack 等
     * 快慢指针
     */

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回上中点
     */
    public Node getLastMId(Node head) {
        if (head == null) {
            return null;
        }
        Node slow;
        Node fast;
        slow = fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
     */
    public Node getNextMid(Node head) {
        if (head == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
     */
    public Node getLastMidPre(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node slow = head;
        Node fast = head.next.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /**
     * 输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
     */
    public Node getNextMidPre(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node slow = head.next;
        Node fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;

        }
        return slow;
    }

}
