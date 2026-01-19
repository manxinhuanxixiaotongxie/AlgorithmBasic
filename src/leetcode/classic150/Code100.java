package leetcode.classic150;

/**
 * 判断两棵树是否相同
 *
 */
public class Code100 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return process(p, q);
    }

    public boolean process(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return process(p.left, q.left) && process(p.right, q.right);
    }
}
