package leetcode.classic150;

public class Code112 {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        return process(root, targetSum);
    }

    public boolean process(TreeNode root, int rest) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            return rest == root.val;
        }
        return process(root.left, rest - root.val) || process(root.right, rest - root.val);
    }
}
