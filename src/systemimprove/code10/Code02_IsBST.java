package systemimprove.code10;

public class Code02_IsBST {
    /**
     * 搜索二叉树特性：
     * 1.左树都比头结点小
     * 2.右树都比头结点大
     * 经典的二叉搜索树没有重复值
     */
    /**
     * 讨论可能性：
     * X节点为头结点的树，可能性
     * 左树是搜索二叉树 右树是搜索二叉树 左树最大值小于X 右树最小值大于X
     */

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    class Info {
        boolean isBST;
        int max;
        int min;

        Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public boolean isBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isBST;
    }

    public Info process(TreeNode node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int max = node.val;
        int min = node.val;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }
        boolean isBST = false;
        if ((leftInfo == null ? true : (leftInfo.isBST && leftInfo.max < node.val))
                && (rightInfo == null ? true : (rightInfo.isBST && rightInfo.min > node.val))) {
            isBST = true;
        }
        return new Info(isBST, max, min);
    }
}
