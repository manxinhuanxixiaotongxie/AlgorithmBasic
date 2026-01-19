package leetcode.classic150;

/**
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
 *
 *
 */
public class Code101 {

    /**
     * 递归实现
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root.left, root.right);
    }

    public boolean process(TreeNode root1,TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return process(root1.left, root2.right) && process(root1.right, root2.left);
    }
}
