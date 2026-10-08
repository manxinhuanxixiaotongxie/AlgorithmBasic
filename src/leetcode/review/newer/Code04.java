package leetcode.review.newer;

public class Code04 {
    public boolean checkTree(TreeNode root) {
        return root != null && (root.val == root.left.val + root.right.val);
    }
}
