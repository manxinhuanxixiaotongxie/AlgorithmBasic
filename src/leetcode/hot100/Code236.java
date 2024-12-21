package leetcode.hot100;

public class Code236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return process(root, p, q).node;
    }

    /**
     * 二叉树的递归
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public Info process(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return new Info(false, false, null);
        }

        Info left = process(root.left, p, q);
        Info right = process(root.right, p, q);
        boolean findP = left.findP || right.findP || root == p;
        boolean findQ = left.findQ || right.findQ || root == q;
        if (left.node != null) {
            return new Info(findP, findQ, left.node);
        } else if (right.node != null) {
            return new Info(findP, findQ, right.node);
        } else {
            if (findP && findQ) {
                return new Info(findP, findQ, root);
            } else {
                return new Info(findP, findQ, null);
            }
        }

    }


    class Info {
        boolean findP;
        boolean findQ;
        TreeNode node;

        public Info(boolean findP, boolean findQ, TreeNode node) {
            this.findP = findP;
            this.findQ = findQ;
            this.node = node;
        }
    }
}
