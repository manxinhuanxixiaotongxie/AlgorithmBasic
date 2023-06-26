package code12;

import book_03binarytree.TreeNode;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-06-25 15:14
 */
public class Code05_IsBST {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        System.out.println(isValidBST(node1));
    }
    /**
     * 判断一棵树是否是二叉搜索树
     * 二叉搜索树特点：
     * 1.左树的值比父节点小(左树的所有值比当前节点小)
     * 2.右树的值比父节点大（右树的所有值比当前节点的值大）
     */

    public static boolean isValidBST(TreeNode root) {

        return process(root).isBST;
    }

    /**
     * 递归实现
     *          5
     *       4    6
     *     nu nu 3  7
     * @param root
     * @return
     */
    public static Info process(TreeNode root) {
        if (root == null) {
            return null;
        }
        int value = root.value;
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        // 右侧最小值
        int min = value;
        // 左侧最大值
        int max = value;

        if (leftInfo != null) {
            max = Math.max(leftInfo.max,max);
        }

        /**
         * 取左树最大值与取右树最小值
         * 注意：最大值与最小值要从左右数一起取
         * 有可能左右树是二叉搜索树 但是整体不是
         * 比如：
         *      *          5
         *      *       4    6
         *      *     nu nu 3  7
         *
         *           *     5
         *      *       4    6
         *      *     3 7  3
         *
         *
         * 这两科树就不是二叉搜索树
         *
         */
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
        }

        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
        }

        if (rightInfo != null) {
            min = Math.min(rightInfo.min,min);
        }

        boolean isBST = true;
        if (leftInfo != null && !leftInfo.isBST) {
            isBST = false;
        }
        if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        }
        if (leftInfo != null && leftInfo.max >= root.value) {
            isBST = false;
        }
        if (rightInfo != null && rightInfo.min <= root.value) {
            isBST = false;
        }
        return new Info(min,max,isBST);
    }

    public static class Info {
        int min;
        int max;
        boolean isBST;

        Info(int min,int max,boolean isBST) {
            this.min = min;
            this.max = max;
            this.isBST = isBST;
        }

    }

}
