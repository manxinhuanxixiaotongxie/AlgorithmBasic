package leetcode.hot100;

public class Code0226 {
    public TreeNode invertTree(TreeNode root) {
        return process(root);
    }

    // 返回调整之后的头节点
    private TreeNode process(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = process(root.right);
        TreeNode right = process(root.left);
        root.left = left;
        root.right = right;
        return root;
    }
}
