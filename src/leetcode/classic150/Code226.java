package leetcode.classic150;

/**
 * 翻转二叉树
 */
public class Code226 {
    /**
     * 深度优先遍历
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        return process(root);
    }

    public TreeNode process(TreeNode root) {
        if (root == null) return root;
        if (root.left == null && root.right == null) return root;
        TreeNode left = process(root.left);
        TreeNode right = process(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
