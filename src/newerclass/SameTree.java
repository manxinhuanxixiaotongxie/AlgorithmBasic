package newerclass;

/**
 * @Description 测试链接：https://leetcode.cn/problems/same-tree/
 * 深度有限遍历
 * @Author Scurry
 * @Date 2023-05-25 9:09
 */
public class SameTree {

    public static void main(String[] args) {
        System.out.println(true ^ true);

    }

    public boolean isSameTree(TreeNode root1, TreeNode root2) {

//        if (root1 == null || root2 == null) {
//            return (root1 != null || root2 != null) ? false : true;
//        }
        // 有一个不为空 异或  相同为0 不用为1
        if (root1 == null ^ root2 == null) {
            return false;
        }

        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1.val != root2.val) {
            return false;
        }

        return isSameTree(root1.left, root2.left) && isSameTree(root1.right, root2.right);
    }

    /**
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * <p>
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 807
     * <p>
     * <p>
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean isNeedAdd = false;
        ListNode newHead = new ListNode(0);
        ListNode head = null;
        ListNode pre = null;
        int n1 = 0;
        int n2 = 0;
        int n = 0;
        while (l1 != null || l2 != null) {

            n1 = l1 == null ? 0 : l1.val;
            n2 = l2 == null ? 0 : l2.val;

            if (!isNeedAdd && (n1 + n2) >= 10 || isNeedAdd && ((n1 + n2 + 1) > 10)) {
                head = new ListNode((l1.val + l2.val) % 10);
                isNeedAdd = true;
            } else {
                head = new ListNode((l1.val + l2.val));
                isNeedAdd = false;
            }
            newHead.next = head;
            pre = head;

        }

        if (isNeedAdd) {
            head.next = new ListNode(1);
        }

        return newHead.next;

    }
}
