package systemimprove.code09;

import systemimprove.code08.ListNode;

import java.util.Stack;

public class Code_PreInPos {

    public static void pre(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val+"    ");
        pre(root.left);
        pre(root.right);

    }

    public static void in(TreeNode root) {
        if (root == null) {
            return;
        }
        in(root.left);
        System.out.print(root.val+"    ");
        in(root.right);

    }

    public static void pos(TreeNode root) {
        if (root == null) {
            return;
        }
        pos(root.left);
        pos(root.right);
        System.out.print(root.val+"    ");
    }

    /**
     * 先序 头左右
     * @param root
     */
    public static void pre2(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            System.out.print(pop.val+"    ");
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
        System.out.println();
    }

    /**
     * 先序是头左右
     * 后序是左右头
     *
     * 怎么根据先序想出后序遍历的代码
     *
     * @param node
     */
    public static void pos2(TreeNode node) {
        if (node == null) {
            return;
        }
        Stack<TreeNode> help = new Stack<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            help.push(pop);
            if (pop.left != null) {
                stack.push(pop.left);
            }

            if (pop.right != null) {
                stack.push(pop.right);
            }

        }


        while (!help.isEmpty()) {
            System.out.print(help.pop().val+"    ");
        }
        System.out.println();
    }

    /**
     * 中序遍历的非递归实现
     * 左头右
     * in
     * 打印
     * in
     *
     *
     *
     */
    public static void in2(TreeNode node) {
        if (node == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = node;
        // 此时cur指向最左节点
        // 中序遍历的顺序是左头右
        // 那么对每一棵树来说 入栈顺序都是右头左
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode pop = stack.pop();
                System.out.print(pop.val+"    ");
                cur = pop.right;
            }
        }
        System.out.println();

    }

    public static void main(String[] args) {
        /**
         *               1
         *      2               3
         *  4     5           6   7
         */
        TreeNode  head = new TreeNode(1);
        head.left = new TreeNode(2);
        head.right = new TreeNode(3);
        head.left.left = new TreeNode(4);
        head.left.right = new TreeNode(5);
        head.right.left = new TreeNode(6);
        head.right.right = new TreeNode(7);

        pre(head);
        System.out.println("========");
        pre2(head);
        System.out.println("========");
        pos(head);
        System.out.println("========");
        pos2(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        in2(head);
    }


}
