package leetcode.classic150;

/**
 * 二叉树的最大深度
 */
public class Code104 {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return process(root);
    }

    public int process(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = process(root.left);
        int right = process(root.right);
        return Math.max(left, right) + 1;
    }
}
