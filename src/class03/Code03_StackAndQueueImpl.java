package class03;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-06-23 19:17
 */

/**
 * 栈和队列实际实现
 * 栈：先进后出
 * 队列：先进先出
 * 两种实现：双向链表 数组
 */
public class Code03_StackAndQueueImpl {

    public static class Node<T> {
        private T value;
        private Node<T> pre;
        private Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }

    public static class DoubleQueue<T> {
        public Node<T> head;
        public Node<T> tail;

        /**
         * 双向链表
         * 从头加
         *
         * @param node
         */
        public void addFromHead(Node<T> node) {


            if (head == null) {
                head = node;
                tail = node;
            } else {

//                head.pre = node;
//                node.next = head;
                //此处尽管说明了pre为null 但是并不清楚头结点
//                node.pre = null;
                node.next = head;
                head.pre = node;
                head = node;
            }


        }


        public void addFromBottom(Node<T> node) {
            if (head == null) {

            } else {
                tail.next = node;
                node.pre = tail;
                node.next = null;
                tail = node;
            }
        }

        public Node<T> popFromHead() {

            /**
             * 不能直接返回head节点，弹出之前头结点要向后移动
             * 重新设置头节点
             */
            Node<T> cur = head;
            if (head == null) {
                head = null;
                tail = null;
            } else {

                head.next = null;
                head = cur.next;
                head.pre = null;


            }
            return cur;
        }


        public Node<T> popFromButtom() {

            Node<T> cur = tail;
            if (head == null) {
                head = null;
                tail = null;
            } else {

                tail = tail.pre;
                tail.next = null;
                /**
                 * 断开连接
                 */
                cur.pre = null;

            }

            return cur;
        }


    }


}
