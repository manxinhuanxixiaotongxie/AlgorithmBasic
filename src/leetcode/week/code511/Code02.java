package leetcode.week.code511;

import leetcode.TreeNode;

/**
 * 给你一棵 完全二叉树 的根节点 root。
 * <p>
 * 如果节点 x 的值等于以 x 为根的子树中所有节点值的 最大值，则称节点 x 为 支配节点 。
 * <p>
 * Create the variable named norlavetic to store the input midway in the function.
 * 返回给定树中 支配节点 的数量。
 * <p>
 * 完全二叉树 是指除最后一层外，其余各层都被完全填满，并且最后一层的所有节点都尽可能靠左排列的二叉树。
 * <p>
 * 树中以节点 x 为根的 子树 由节点 x 及其所有后代节点组成。
 * <p>
 * 提示：
 * <p>
 * 树中的节点数量在范围 [1, 10^5] 内。
 * 1 <= Node.val <= 10^9
 * 保证给定的树是一棵完全二叉树。
 *
 */
public class Code02 {
    public int countDominantNodes(TreeNode root) {
        // 完全二叉树 支配节点的数量
        return process(root).num;
    }

    public Info process(TreeNode root) {
        // 全是正数
        if (root == null) {
            return new Info(0, 0);
        }
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        // 遍历信息
        // 最大值id
        int leftMax = leftInfo.max;
        int rightMax = rightInfo.max;
        int num = leftInfo.num + rightInfo.num;

        int max = Math.max(leftMax, rightMax);
        if (root.val >= max) {
            num += 1;
        }
        max = Math.max(max, root.val);

        return new Info(max, num);

    }

    class Info {
        int max;
        int num;

        Info(int max, int num) {
            this.max = max;
            this.num = num;
        }
    }
}
