package class03;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-06-16 22:09
 */

import com.sun.xml.internal.bind.marshaller.NoEscapeHandler;

/**
 * 删除链表指定的值
 *
 * 单向链表
 *
 */
public class Code02_DeleteGivenNum {

    class Node {
        private int value;
        private Node next;

        Node(int value) {
            this.value = value;
        }
    }



    private static Node deleteNumberInList(Node head, int num){


        /**
         * 一
         */
//        Node pre = null;
//        Node cur = head;
//        while (cur != null) {
//            /**
//             * 如果节点的值与给定的值相等
//             * 当前节点的前一个节点指向当前节点的后一个节点
//             */
//           if (head.value == num) {
//               head = head.next;
//               pre = head;
//               pre.next = cur.next;
//           }
//
//           if (head.value != num) {
//               pre = cur;
//           }
//            // 不管条件如何  角标要向后移动一位
//            cur = cur.next;
//
//        }
//        return head;


        /**
         *
         */

        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }



        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                /**
                 *   0(head)  ->  0  ->   0  ->   0
                 */
//                cur = cur.next;
                /**
                 * 不相等  意味着当前节点不需要被删除
                 * 跳过
                 */
//                cur = cur.next;
                pre = cur;
            }
            cur = cur.next;

        }

        return head;


    }

}
