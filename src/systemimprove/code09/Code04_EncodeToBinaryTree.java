package systemimprove.code09;

import java.util.ArrayList;
import java.util.List;

public class Code04_EncodeToBinaryTree {

    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    // 提交时不要提交这个类
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public TreeNode encodeToBinaryTree(Node root) {
        if (root == null) {
            return null;
        }
        TreeNode head = new TreeNode(root.val);
        head.left = generateTreeNode(root.children);
        return head;


    }


    /**
     * 将多叉树的节点转换为二叉树的节点
     * 将多叉树的子节点转换为二叉树的左节点
     * 1
     * 2   3  4
     * 转换成：
     * 1
     * 2
     * 3
     * 4
     * 转换成这种形式
     *
     * @param children
     * @return
     */
    public TreeNode generateTreeNode(List<Node> children) {
        TreeNode head = null;
        TreeNode cur = null;
        for (Node child : children) {
            TreeNode tNode = new TreeNode(child.val);
            if (head == null) {
                head = tNode;
            } else {
                cur.right = tNode;
            }
            cur = tNode;
            cur.left = generateTreeNode(child.children);
        }
        return head;
    }

    public TreeNode en(Node root) {
        if (root == null) {
            return null;
        }
        TreeNode head = new TreeNode(root.val);
        head.left = generateTreeNode(root.children);
        return head;
    }

    public Node decode(TreeNode root) {
        if (root == null) {
            return null;
        }
        Node head = new Node(root.val);
        // 将二叉树的左节点转换为多叉树的子节点
        head.children = de(root.left);

        return head;
    }

    public List<Node> de(TreeNode root) {
        List<Node> children = new ArrayList<>();
        while (root != null) {
            Node cur = new Node(root.val, de(root.left));
            children.add(cur);
            root = root.right;
        }
        return children;
    }
}
