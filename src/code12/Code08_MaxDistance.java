package code12;

import book_03binarytree.TreeNode;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-06-28 15:13
 */
public class Code08_MaxDistance {

    public int diameterOfBinaryTree(TreeNode root) {
        return process(root).maxDisTance;
    }

    public class Info {
        int height;
        int maxDisTance;

        Info(int height, int maxDisTance) {
            this.height = height;
            this.maxDisTance = maxDisTance;
        }
    }

    public Info process(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }

        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        // 左树的最大距离+右树最大距离

        /**
         *
         * 与X无关：左树的最大距离 右树的最大距离取最大值
         *
         * 与X有关 左树的高度 右树的高度 当前节点高度
         *
         */
        int maxDisTance = Math.max(Math.max(leftInfo.maxDisTance, rightInfo.maxDisTance), leftInfo.height + rightInfo.height + 1);
        return new Info(height, maxDisTance);

    }

}
