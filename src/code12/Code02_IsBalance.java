package code12;

import newerclass.TreeNode;

/**
 * @Description
 * 给定一个二叉树的头结点
 * 判断这棵树是不是平衡二叉树
 *
 *
 * 平衡二叉树的特点：
 * 1.左右两颗数的高度差不超过1
 * 2.左树是平衡二叉树 右树是平衡二叉树
 *
 * lettcode110题
 *
 * @Author Scurry
 * @Date 2023-06-20 18:56
 */
public class Code02_IsBalance {


    class Info{
        boolean isBalance;
        int height;
        Info(boolean isBalance,int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    public boolean isBalanced(TreeNode root) {

        Info process = process(root);
        return process.isBalance;

    }

    public Info process(TreeNode root) {
        if (root == null) {
            return new Info(true,0);
        }

        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        int height = Math.max(leftInfo.height,rightInfo.height)+1;

        boolean isBalance = leftInfo.isBalance&&rightInfo.isBalance&&Math.abs(leftInfo.height- rightInfo.height)<2;

        return new Info(isBalance,height);

    }

}
