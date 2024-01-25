package systemimprove.code10;

import systemimprove.code09.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * 通过判断一棵树是否是完全二叉树的案例
 * 进行总结：
 * 1.是否是完全二叉树
 *   树是否是满的 即使不满 从左到右依次遍满
 *   有右树一定要有左树
 *   有左树不一定有右树
 *   第一个左右不双全的节点之后的节点都是叶子节点
 * 2.二叉树的递归套路 利用递归遍历二叉树的便利性
 *   1.假设X是头结点 可以从X左树和右树上获取信息
 *   2.讨论以X为头结点的树，得到答案的可能性（最重要）
 *   3.列出所有可能性后，确定到底需要那些信息
 *   4.左树右树信息求全集S
 *   5.递归函数都返回S 每一棵子树都这么要求
 *   6.写代码 在代码中考虑如何把左树和右树的信息整合出整棵树的信息
 *
 */
public class Code01_ISCBT {

    public boolean isCBT(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 完全二叉树
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean isLeaf = false;
        boolean ans = true;
        while (!queue.isEmpty()) {
            TreeNode node = queue.pollFirst();
            if (isLeaf && (node.left != null || node.right != null)) {
                return false;
            }
            // 完全二叉树 有右树一定要有左树
            if (node.left == null && node.right != null) {
                return false;
            }
            if (node.left == null || node.right == null) {
                isLeaf = true;
            }
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);

            }
        }
        return ans;
    }

    // 完全二叉树的特性
    // 有右树必有左树
    // 从左到右依次遍满
    // 列举所有可能性
    // 1 左树满 右树满
    // 2 左树满 右树不满 左树的高度右树的高度一样  右树是完全二叉树
    // 3 左树满 右树满 左树的高度比右树的高度大1
    // 4 左树不满 右树满 左树的高度比右树的高度大1 左树是完全二叉树

    class Info {
        boolean isFull;
        boolean isCBT;
        int height;

        public Info(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }

    public boolean isCBT2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process(root).isCBT;
    }

    public Info process(TreeNode node) {
        if (node == null) {
            return new Info(true, true, 0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = false;
        boolean isCBT = false;
        if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height) {
            isFull = true;
            isCBT = true;
        }

        if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height + 1) {
            isCBT = true;
        }

        if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
            isCBT = true;
        }

        if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
            isCBT = true;
        }

        return new Info(isFull, isCBT, height);
    }
}
