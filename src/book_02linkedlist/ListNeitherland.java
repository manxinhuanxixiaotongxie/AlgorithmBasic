package book_02linkedlist;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-04-10 17:24
 */
public class ListNeitherland {

    public static boolean test01(DeleteKNode.Node head) {

        if (head == null || head.next == null) {
            return true;
        }

        // 找到左半边节点  右半区的开始节点
        DeleteKNode.Node n1 = head;
        DeleteKNode.Node n2 = head;

        while (n1.next != null && n2.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        // n1为左半区最后一个节点
        n2 = n1.next;
        // 翻转右半区链表
        n1.next = null;

        DeleteKNode.Node n3 = null;

        // 翻转链接
        while (n2.next != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;
        }

        boolean res = true;
        n3 = n2;
        n2 = head;
        // 翻转之后 n2变成最右边的节点
        while (n2 != null && n1 != null) {
            if (n1.value != n2.value) {
                res = false;
                break;
            }
            n1 = n1.next;
            n2 = n2.next;
        }

        // 恢复链表
        n1 = n3.next;
        n3.next = null;
        // 0 0 0 0 0
        while (n1 != null) {
            n2 = n1.next;
            n1.next = n3;
            n3 = n1;
            n1 = n2;
        }

        return res;


    }
}
