package book_02linkedlist;

/**
 * @Description
 *
 * 环形约瑟夫环问题
 *
 * @Author Scurry
 * @Date 2023-03-15 17:19
 */
public class RingLinekList {

    class Node {
        private int value;
        private Node next;

        Node(int value,Node next) {
            this.next = next;
            this.value = value;
        }
    }

    public static void main(String[] args) {

    }

    public Node killPeople1(Node head,int num) {

        if (head == null || head.next == null ) {
            return head;
        }
//        while (head.next != head) {
//            tail = head.next;
//        }

        Node tail = head;
        while (tail.next != head) {
            tail = tail.next;
        }


        int index = 0;


        while (head != tail) {
            // 转圈继续报数
            if (++index == num) {
                tail.next = head.next;
                index =0;
            }else {
                tail = tail.next;
            }
            head = tail.next;
        }

//        while (head != tail) {
//            if (++index == num) {
//                tail.next = tail.next.next;
//                index = 0;
//
//            }else {
//
//                tail = tail.next;
//
//            }
//
//            head = tail.next;
//        }
        return head;
    }

    /**
     * todo 时间复杂度o(n)
     * @param head
     * @param count
     * @return
     */
    public static Node killPeopleImprove(Node head,int count) {

        return null;

    }
}
