package book_02linkedlist;

/**
 * @Description 打印两个有序链表的重复部分
 * @Author Scurry
 * @Date 2023-02-03 10:24
 */
public class Code01_RepeatLinedList {

    class Node {
        Node next;
        int value;

        Node(Node next, int value) {
            this.next = next;
            this.value = value;
        }
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
            } else if (head1.value < head2.value) {
                head1 = head1.next;
            } else {
                System.out.println(head1.value);
                head1 = head1.next;
                head2 = head2.next;
            }
        }
    }


}
