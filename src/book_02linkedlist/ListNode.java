package book_02linkedlist;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-04-11 17:46
 */
public class ListNode {
    int val;
    public ListNode next;

    ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}