package book_02linkedlist;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-04-11 17:42
 */
public class Test {


    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        isPalindrome(node1);


    }


    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode n1 = head;
        ListNode n2 = head;

        while (n1.next != null && n2.next.next != null) {

            n1 = n1.next;
            n2 = n2.next.next;

        }

        // 此时n1走到了中点  n2走到了最后一步
        ListNode n3 = n1;
        n1 = n1.next;
        n3.next = null;
        // 翻转链表 1  2   2    1
        //           n3  n1  n2
        while (n2 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }
        boolean res = true;
        n2 = head;
        while (n2.next != null) {
            if (n2.val != n1.val) {
                res = false;
                break;
            }
            n2 = n2.next;
            n1 = n1.next;
        }

        // 1  2  2 1
        // 恢复链表
        n2 = n1.next;
        n1.next = null;

        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;

        }
        return res;

    }

}
