package leetcode.day;

import leetcode.ListNode;
import leetcode.TreeNode;

import java.util.LinkedList;

/**
 * 给你一棵以 root 为根的二叉树和一个 head 为第一个节点的链表。
 * <p>
 * 如果在二叉树中，存在一条一直向下的路径，且每个点的数值恰好一一对应以 head 为首的链表中每个节点的值，那么请你返回 True ，否则返回 False 。
 * <p>
 * 一直向下的路径的意思是：从树中某个节点开始，一直连续向下的路径。
 */
public class Code1367 {
    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head.next == null) {
            return head.val == root.val || containsProcess(head, root.left) || containsProcess(head, root.right);
        }
        return containsProcess(head, root);
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
    private boolean containsProcess(ListNode head, TreeNode root) {
        if (root == null) {
            return false;
        }

        // 如果链表已经来到了最后一个节点
        if (head.next == null) {
            return head.val == root.val;
        }

        // 如果二叉树来到了最后一个节点
        if (root.left == null && root.right == null) {
            return false;
        }

        // 第一种情况
        // 二叉树的当前节点与链表的节点的值不相等
        if (head.val != root.val) {
            return containsProcess(head, root.left) || containsProcess(head, root.right);
        } else {
            // 如果是相等的话
            return containsProcess(head.next, root.left) || containsProcess(head.next, root.right) ||
                    containsProcess(head, root.left) || containsProcess(head, root.right);
        }
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
