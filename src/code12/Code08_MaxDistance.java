package code12;

import book_03binarytree.TreeNode;

/**
 * @Description 给你一颗二叉树的额根节点 返回该树的直径
 * <p>
 * 二叉树的直径是指树中任意两个及诶单之间最长路径的长度 这条路径可能穿过也可能不穿过根节点
 * 两节点之间的路径长度是以它们之间边的数目表示
 * @Author Scurry
 * @Date 2023-06-28 15:13
 */
public class Code08_MaxDistance {

    public int diameterOfBinaryTree(TreeNode root) {
        return process(root).maxDisTance - 1;
    }

    public class Info {
        int height;
        int maxDisTance;

        Info(int height, int maxDisTance) {
            this.height = height;
            this.maxDisTance = maxDisTance;
        }
    }

    /**
     * 这个函数的含义是 从当前节点出发的最大距离
     * 最大距离有两种情况
     * 1.左树的高度+右树的高度+1
     * 2.左树的最大距离 右树的最大距离
     *
     * @param root
     * @return
     */
    public Info process(TreeNode root) {
        if (root == null) {
            return new Info(0, 0);
        }

        /**
         *
         * 二叉树的递归套路 用的就是二叉树的后序优先遍历 采集左右树的信息
         *
         */
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
        int maxDisTance = Math.max(Math.max(leftInfo.maxDisTance, rightInfo.maxDisTance),
                leftInfo.height + rightInfo.height + 1);
        return new Info(height, maxDisTance);

    }

}
