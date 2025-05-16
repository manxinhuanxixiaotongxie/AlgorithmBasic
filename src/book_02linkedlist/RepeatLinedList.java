package book_02linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 打印两个有序链表的重复部分
 * @Author Scurry
 * @Date 2023-02-03 10:24
 */
public class RepeatLinedList {

    class Node {
        Node next;
        int value;

        Node(Node next, int value) {
            this.next = next;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        /**
         *
         * 给定两个链表head1 head2
         *
         */
        Node head1;
        Node head2;


    }

    /**
     * 有序
     *
     * @param head1
     * @param head2
     */
    public static void printOrderedRepeatLinedList(Node head1, Node head2) {
        while (head1 != null && head2 != null) {
            if (head1.value > head2.value) {
                head2 = head2.next;
            }
            if (head1.value < head2.value) {
                head1 = head1.next;
            }
            if (head1.value == head2.value) {
                System.out.println(head1.value);
                head1 = head1.next;
                head2 = head2.next;
            }
        }
    }


}
