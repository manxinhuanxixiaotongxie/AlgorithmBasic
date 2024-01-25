package systemimprove.code10;

import systemimprove.code09.TreeNode;

/**
 * 最低公共祖先
 */
public class Code05_LowestCommonAncestor {

    class Info {
        boolean findP;
        boolean findQ;
        TreeNode ans;

        Info(boolean findP, boolean findQ, TreeNode ans) {
            this.findP = findP;
            this.findQ = findQ;
            this.ans = ans;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        return process(root, p, q).ans;
    }

    public Info process(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return new Info(false, false, null);
        }

        Info leftInfo = process(root.left, p, q);
        Info rightInfo = process(root.right, p, q);
        boolean findP = leftInfo.findP || rightInfo.findP || root == p;
        boolean findQ = leftInfo.findQ || rightInfo.findQ || root == q;
        TreeNode ans;
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        } else if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        } else {
            if (findP && findQ) {
                ans = root;
            } else {
                ans = null;
            }
        }
        return new Info(findP, findQ, ans);
    }
}
