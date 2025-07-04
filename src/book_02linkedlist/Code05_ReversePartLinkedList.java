package book_02linkedlist;

/**
 * @Description 翻转部分链表
 *
 * @Author Scurry
 * @Date 2023-02-09 17:13
 */
public class Code05_ReversePartLinkedList {

    /**
     * 给定链表头结点单链表
     * 给定from to
     * 翻转from到to这部分节点
     * 要求：链表长度为N时间复杂度要求到O（N），空间复杂度要求到O（1）
     * 如果不满足1 <= from <= to <= N 则不用调整
     */
    public Code02_DeleteKNode.ListNode reversePartLinkedList(Code02_DeleteKNode.ListNode head, int from, int to) {

        if (from > to || from < 1 || to < 0) {
            return head;
        }
        int len = 0;
        Code02_DeleteKNode.ListNode cur = head;
        // 找到from的上一个节点  以及to的下一个节点
        Code02_DeleteKNode.ListNode fromNode = null;
        Code02_DeleteKNode.ListNode toNode = null;
        // 找到要翻转链表的前一个节点和后一个节点
        while (cur != null) {
            len++;
            // 如果from值是1的话，那么就从head到to进行翻转
            // 如果from的值是链表的长度，那么直接已经来到链表的最后一步
            // 找到当前节点的前一个节点
//            fromNode = from==1?null:from==len+1?cur:cur;
            fromNode = len == from - 1 ? cur : fromNode;
            // 找到当前节点的下一个节点
            toNode = len == to + 1 ? cur : toNode;
            cur = cur.next;
        }

        if (from > len || to > len) {
            return head;
        }

        // 从from的上一个节点开始遍历
        // 头节点的上一个节点为空意味着从head到to进行翻转，头部要进行处理
        /**
         * 要翻转链表需要进行一个处理
         * 涉及要一个换头的问题
         * 如果fpre是null的话，直接返回新头的
         * fpre不为null的话，返回旧头
         * 开始节点刚好是head节点，那么新的头结点就是tonode的上一个节点
         * O O O O O O O O O
         *
         * 1 2 3 4   1 3   ----> 3 2 1 4
         * 1 2 3 4   2 3   ----> 1 3 2 4
         */
        cur = fromNode == null ? head : fromNode.next;

        Code02_DeleteKNode.ListNode nextNode = toNode;

        Code02_DeleteKNode.ListNode node2 = cur.next;
        node2.next = toNode;

        while (cur != toNode) {
            Code02_DeleteKNode.ListNode next = cur.next;
            cur.next = nextNode;
            nextNode = cur;
            cur = next;

        }

        // fromnode不为空 不需要换头
        if (fromNode != null) {
            fromNode.next = cur;
            return head;
        }
        return cur;
    }
}
