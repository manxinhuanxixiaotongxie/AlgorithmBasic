package leetcode.day;

import leetcode.ListNode;
import leetcode.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。
 * <p>
 * 如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。
 * <p>
 * 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
 */
public class Code1367 {
    public boolean isSubPath(ListNode head, TreeNode root) {
//        if (head.next == null) {
//            return head.val == root.val || containsProcess(head, root.left) || containsProcess(head, root.right);
//        }
        return containsProcess(head, head, root);
    }

    /**
     * 以root为头的二叉树 是否包含head为头的链表
     * <p>
     * 这样的方式有问题 容易出错
     * <p>
     * 出错点：
     * 1.当链表只剩一个节点时，如果二叉树的节点值不等于链表的节点值，就会返回false
     * 1.当前节点的值
     *
     * @param head
     * @param root
     * @return
     */
    private boolean containsProcess(ListNode head, ListNode cur, TreeNode root) {
        if (cur == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        // 分情况讨论
        // 第一种情况二叉树的节点与当前节点不相等
        // 不相等只有一种情况满足条件 在当前二叉树的左树找到了  或者在当前二叉树的右树找到了
//        if (root.val != head.val) {
//            return cur == head && (containsProcess(head,cur,root.left) || containsProcess(head,cur,root.right));
//        }
//        // 第二种情况  二叉树的节点值与当前节点是相同的
//        else {
//            // 这样写 其实还是遗漏情况
//            // 由于二叉树不保证没有重复节点
//            // 因此会遗漏
//            /**
//             *       1
//             *      1
//             *     10
//             *     这种情况就会遗漏
//             */
//            return (containsProcess(head,cur.next,root.left) || containsProcess(head,cur.next,root.right));
//        }


        /**
         * 讨论情况
         * 只有两种情况能够找到
         * 第一种情况：
         * 1.二叉树的当前节点与链表节点相等清切在二叉树遍历的过程中能够找到完全相等的
         * 2.二叉树从当前节点出发找不到与链表相同的路径 但是后续左树与右树能够找到
         */
        return (cur.val == root.val && (containsProcess(head, cur.next, root.left) || containsProcess(head, cur.next, root.right))) ||
                (cur == head && (containsProcess(head, cur, root.left) || containsProcess(head, cur, root.right)));
    }

    public boolean isSubPath3(ListNode head, TreeNode root) {
        return containsProcess2(head, head, root);
    }

    /**
     * 以root为头的二叉树 是否包含head为头的链表
     *
     * @param cur
     * @param root
     * @return
     */
    private boolean containsProcess2(ListNode head, ListNode cur, TreeNode root) {

        if (cur == null) {
            return true;
        }

        if (root == null) {
            return false;
        }


        // 第一种情况
        // 二叉树的当前节点与链表的节点的值不相等
        // 如果是相等的话
        // 这个方法会明显比上面的那个方法时间负责度高
        // 这是为什么
        boolean ans = root.val == cur.val && (containsProcess2(head, cur.next, root.left) || containsProcess2(head, cur.next, root.right));
        ans = ans || (containsProcess2(head, head, root.left) || containsProcess2(head, head, root.right));
        return ans;
    }

    public boolean isSubPath2(ListNode head, TreeNode root) {
        if (head == null || root == null) {
            return false;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val == head.val) {
                if (dfs(node, head)) {
                    return true;
                }
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return false;

    }

    public boolean dfs(TreeNode node, ListNode cur) {
        if (node == null) {
            return cur == null;
        }
        if (cur == null) {
            return true;
        }
        if (node.val != cur.val) {
            return false;
        }
        return dfs(node.left, cur.next) || dfs(node.right, cur.next);
    }


    public static void main(String[] args) {
        // head =
        //[4,2,8]
        // root =
        //[1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
//        ListNode head = new ListNode(4);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(8);
//
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(4);
//        root.left.right = new TreeNode(2);
//        root.left.right.left = new TreeNode(1);
//
//        root.right = new TreeNode(4);
//        root.right.left = new TreeNode(2);
//        root.right.left.left = new TreeNode(6);
//        root.right.left.right = new TreeNode(8);
//        root.right.left.right.left = new TreeNode(1);
//        root.right.left.right.right = new TreeNode(3);

        ListNode head = new ListNode(3);
        // root =
        //[1,5,3,null,4,null,3]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(5);
        root.left.right = new TreeNode(4);

        root.right = new TreeNode(3);
        root.right.right = new TreeNode(3);
        // head =
        //[4,2]
        // root =
        //[4,4,4,1,null,null,null,2]

//        ListNode head = new ListNode(4);
//        head.next = new ListNode(2);
//
//        TreeNode root = new TreeNode(4);
//        root.left = new TreeNode(4);
//        root.left.left = new TreeNode(1);
//        root.right = new TreeNode(4);
//        root.right.right = new TreeNode(2);


        Code1367 code1367 = new Code1367();
        System.out.println(code1367.isSubPath(head, root));
    }
}
