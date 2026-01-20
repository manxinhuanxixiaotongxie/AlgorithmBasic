package leetcode.classic150;

/**
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 */
public class Code098 {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        Info info = process(root);
        return info.isBST;
    }

    public Info process(TreeNode root) {
        if (root == null) return null;
        if (root.left == null && root.right == null) {
            // 叶子节点
            return new Info(root.val, root.val, true);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        // 收集信息
        int maxVal = root.val;
        int minVal = root.val;
        boolean isBST = false;
        if (leftInfo != null && rightInfo != null) {
            // 更新最大值
            maxVal = Math.max(maxVal, Math.max(leftInfo.maxVal, rightInfo.maxVal));
            minVal = Math.min(minVal, Math.min(leftInfo.minVal, rightInfo.minVal));
            if (leftInfo.isBST && rightInfo.isBST && leftInfo.maxVal < root.val && rightInfo.minVal > root.val) {
                isBST = true;
            }
        } else if (leftInfo != null) {
            maxVal = Math.max(maxVal, leftInfo.maxVal);
            minVal = Math.min(minVal, leftInfo.minVal);
            if (leftInfo.isBST && leftInfo.maxVal < root.val) {
                isBST = true;
            }
        } else if (rightInfo != null) {
            maxVal = Math.max(maxVal, rightInfo.maxVal);
            minVal = Math.min(minVal, rightInfo.minVal);
            if (rightInfo.isBST && rightInfo.minVal > root.val) {
                isBST = true;
            }
        } else {
            isBST = true;
        }
        return new Info(maxVal, minVal, isBST);
    }

    // 二叉搜索树的定义 任一节点 左孩子比我小 右孩子比我大
    class Info {
        int maxVal;
        int minVal;
        boolean isBST;

        public Info(int maxVal, int minVal, boolean isBST) {
            this.maxVal = maxVal;
            this.minVal = minVal;
            this.isBST = isBST;
        }
    }
}
