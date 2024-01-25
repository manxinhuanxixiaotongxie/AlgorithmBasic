package systemimprove.code10;

public class Code03_IsAvl {
    /**
     * 判断一棵树是不是平衡二叉树
     * 平衡二叉树的特性：
     * 1.左树是平衡二叉树
     * 2.右树是平衡二叉树
     * 3.左树和右树的高度差不超过1
     */
    /**
     * 光是高度不够，还要判断左树和右树是不是平衡二叉树
     * 举个例子：
     * 1.左树不是平衡二叉树 右树是平衡二叉树 但是高度差超过1
     * 这棵树不是平衡二叉树
     */

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    class Info {
        boolean isBalanced;
        int height;

        Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public boolean isAvlTree(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isBalanced;

    }

    public Info process(TreeNode node) {
        if (node == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalanced = false;
        if (leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(leftInfo.height - rightInfo.height) < 2) {
            isBalanced = true;
        }
        return new Info(isBalanced, height);
    }
}
