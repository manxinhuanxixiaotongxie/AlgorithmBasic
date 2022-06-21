package class03;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-06-16 19:21
 */
public class Code01_ReverseList {


    /**
     * 单向链表
     */
    public static class Node{
        private int value;
        private Node next;
        Node(int value) {
            this.value= value;
        }
    }

    public static class DoubleNode{

        private int value;
        private DoubleNode pre;
        private DoubleNode next;

        DoubleNode(int value) {
            this.value = value;
        }

    }


    /**
     * 单链表反转
     */

    public static void reverseSingleList(Node head){

        // 假设现在有这么一个链表  0 -> 1 -> 2 -> 3

        // 翻转之后变成          0 <- 1 <- 2 <- 3


        Node pre = null;

        // 每一次循环处理一个节点 ，然后头结点后移
        while (head != null) {
            // 找到头结点的下一个节点进行处理
            Node next = head.next;

            // 我们要做的就是讲next.next变成当前节点
            // 同时 下一个节点要变成下一次循环的当前节点
            // 头节点的next变成null
//             pre = head.next;
            head.next = pre;
            pre = head;
            // 下一二个节点变成头结点
            head = next;
        }

    }


    /**
     * 翻转双向链表
     *
     *
     * @param head
     */
    private static void reverseDoubleList(DoubleNode head){


        DoubleNode pre = null;

        DoubleNode next = null;


        /**
         * 假设有这样一个双向链表
         *
         * null  --> Node1
         *
         */
        while (head != null) {

            // head是当前节点

            // 取出当前节点的下一个节点
            next = head.next;
            /**
             * 我们要做的就是把当前节点next变成pre
             * 把当前节点的pre变成next
             *
             * 把这些做完之后  头节点需要变成next 同时 pre必须指向当前节点
             */
            head.next =pre;
            head.pre = next;

            pre = head;

            head = next;

        }




    }


}
