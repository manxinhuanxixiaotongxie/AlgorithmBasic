package code12;

import book_03binarytree.TreeNode;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-06-26 11:04
 */
public class Code06_MaxChildBST {

    /**
     * 给定一棵二叉树的头节点head，
     * 返回这颗二叉树中最大的二叉搜索子树的大小
     */
    public int maxChildBST(TreeNode root) {

        return Math.max(process(root.left).max, process(root.right).max);
    }

    public Info process(TreeNode root) {

        if (root == null) {
            return null;
        }

        int value = root.value;
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        int min = value;
        int max = value;
        if (leftInfo != null) {
            min = Math.min(leftInfo.min, min);
        }
        if (rightInfo != null) {
            min = Math.min(rightInfo.min, min);
        }

        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
        }

        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
        }
//        boolean isBST = true;
        boolean isBST = false;
        int maxBSTSize = 0;
        if (leftInfo != null) {
            maxBSTSize = leftInfo.maxBSTSize;
        }
        if (rightInfo != null) {
            maxBSTSize = Math.max(rightInfo.maxBSTSize, maxBSTSize);
        }


        /**
         * 此处条件可以合并
         */
//        if (leftInfo != null && !leftInfo.isBST) {
//            isBST = false;
//        }
//        if (rightInfo != null && !rightInfo.isBST) {
//            isBST = false;
//        }
//        if (leftInfo != null && leftInfo.max >= root.value) {
//            isBST = false;
//        }
//        if (rightInfo != null && rightInfo.min <= root.value) {
//            isBST = false;
//        }

        // 与当前节点无关  与当前节点有关
        /**
         * 与当前节点无关：
         * 最大二搜树的大小就是左树的最大二搜树的大小与右树的最大二搜树的大小取最大值
         *
         * 与当前节点有关
         * 左树、右树与当前值构成一个二搜树
         * 左树是二搜树 右树是二搜树  左树的最大值小于X的值 右树的最小值大于当前值
         */
        if (leftInfo == null ? true : leftInfo.isBST && rightInfo == null ? true : rightInfo.isBST
                && leftInfo == null ? true : leftInfo.max < value && rightInfo == null ? true : rightInfo.min > value) {
            maxBSTSize = Math.max(maxBSTSize, ((leftInfo == null ? 0 : leftInfo.maxBSTSize) + (rightInfo == null ? 0 : rightInfo.maxBSTSize + 1)));
            isBST = true;
        }


        return new Info(max, min, maxBSTSize, isBST);
    }

    public class Info {
        int max;
        int min;
        boolean isBST;
        int maxBSTSize;

        Info(int max, int min, int maxBSTSize, boolean isBST) {
            this.max = max;
            this.min = min;
            this.isBST = isBST;
            this.maxBSTSize = maxBSTSize;
        }

    }


}
