package leetcode.classic150;

public class Code236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return process(root, p, q).node;
    }

    public Info process(TreeNode root,TreeNode p, TreeNode q) {
        if (root == null) return null;
        // 讨论可能性
        Info leftInfo = process(root.left, p, q);
        Info rightInfo = process(root.right, p, q);
        boolean findP = false;
        boolean findQ = false;
        TreeNode node = null;
        if (leftInfo != null && rightInfo != null) {
            findP = leftInfo.findP || rightInfo.findP || root == p;
            findQ = leftInfo.findQ || rightInfo.findQ || root == q;
            if (leftInfo.node != null) {
                node = leftInfo.node;
            }else if (rightInfo.node != null) {
                node = rightInfo.node;
            }else  if (leftInfo.node == null && rightInfo.node == null) {
                if (findP && findQ) {
                    node = root;
                }
            }
        }else if (leftInfo != null) {
            findP = leftInfo.findP || root == p;
            findQ = leftInfo.findQ || root == q;
            if (leftInfo.node != null) {
                node = leftInfo.node;
            } else if (leftInfo.node == null) {
                if (findP && findQ) {
                    node = root;
                }
            }
        }else if (rightInfo != null) {
            findP = rightInfo.findP || root == p;
            findQ = rightInfo.findQ || root == q;
            if (rightInfo.node != null) {
                node = rightInfo.node;
            } else if (rightInfo.node == null) {
                if (findP && findQ) {
                    node = root;
                }
            }
        }else {
            findP = root == p;
            findQ = root == q;
        }
        return new Info(findP, findQ, node);
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
