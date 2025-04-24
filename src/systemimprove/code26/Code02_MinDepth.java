package systemimprove.code26;

import systemlearning.code16.Test;

/**
 * 最小深度：
 * <p>
 * 给定一个二叉树，找出其最小深度。
 * <p>
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * <p>
 * 说明：叶子节点是指没有子节点的节点。
 * <p>
 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 * <p>
 * 以下两种方式测试通过
 */
public class Code02_MinDepth {

    /**
     * 提供两种思路
     * 1.二叉树的递归套路
     * <p>
     * 2.改写Morris遍历
     * <p>
     * Morris遍历的过程：
     * 1.有左树
     * 1.找到左树的最右节点
     * 左树的最右侧节点的右指针指向空 那么让最右侧节点的右节点指向当前节点 当前节点来到左节点
     * 左树的最右侧节点的右指针指向当前节点 那么让最右侧节点的右指针指向空 并且当前来到右节点
     * 2.没有左树 当前节点来到右节点
     * <p>
     * 怎么将Morris遍历改写成求最小深度的过程：
     *
     * @param root
     * @return
     */
    public int minDepth1(Test.TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process(root).height;
    }

    private Info process(Test.TreeNode node) {
        if (node.left == null && node.right == null) {
            return new Info(1);
        }
        int leftHeight = Integer.MAX_VALUE;
        if (node.left != null) {
            leftHeight = process(node.left).height;
        }
        int rightHeight = Integer.MAX_VALUE;
        if (node.right != null) {
            rightHeight = process(node.right).height;
        }
        return new Info(Math.min(leftHeight, rightHeight) + 1);
    }

    /**
     * 求树的最小深度 与Morris遍历结合
     *
     * 根据Morris遍历的过程可以推断出来
     * 先明确一个结论 如果一个节点在Morris遍历的过程中 第二次来到某个节点
     *
     * 如果当前节点左树的最右侧节点是当前节点 并且左树的最右侧节点的左孩子节点为空 那么左树的最右侧节点就是叶子节点
     * <p>
     * 并且在第二次回到当前节点时候 进行结算 怎么进行结算 当前节点的高度就是 当前层的高度减去左树最右侧节点的个数
     * <p>
     * 如果在遍历的过程中左树的最右侧节点的左孩子节点不为空 那么左树的最右侧节点不是叶子节点 树在当前分支就还没有结束
     *
     * @param root
     * @return
     */
    public int minDepth2(Test.TreeNode root) {
        if (root == null) {
            return 0;
        }
        Test.TreeNode cur = root;
        int level = 0;
        int depth = Integer.MAX_VALUE;
        while (cur != null) {
            // 当前节点左树为空 当前节点就需要来到当前节点的右节点
            if (cur.left == null) {
                level++;
                cur = cur.right;
            } else {
                Test.TreeNode mostRight = cur.left;
                int rightBoardSize = 1;
                while (mostRight.right != null && mostRight.right != cur) {
                    rightBoardSize++;
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    level++;
                    mostRight.right = cur;
                    cur = cur.left;
                } else {
                    // mostRight的右孩子节点不为空的时候
                    // 意味着mostRight的右孩子节点的第二次来到该节点
                    // 意味着右孩子节点的所有节点都已经遍历完了
                    // 这里为什么还要判断右侧节点左节点为空
                    // 只有左侧节点为空的时候才是叶子节点 只有叶子节点才进行结算
                    if (mostRight.left == null) {
                        depth = Math.min(depth, level);
                    }
                    level -= rightBoardSize;

                    mostRight.right = null;
                    cur = cur.right;
                }
            }
        }

        // 针对左右侧的分支进行单独计算
        // 二叉树的右视图的那个路线没有进行结算
        int finalLevel = 1;
        cur = root;
        while (cur.right != null) {
            finalLevel++;
            cur = cur.right;
        }
        if (cur.left == null) {
            depth = Math.min(depth, finalLevel);
        }

        return depth;
    }

    public int minDepth3(Test.TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process3(root);
    }

    private int process3(Test.TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        int ans = Integer.MAX_VALUE;
        if (node.left != null) {
            ans = Math.min(ans, process3(node.left));
        }
        if (node.right != null) {
            ans = Math.min(ans, process3(node.right));
        }
        return ans + 1;
    }

    public static void main(String[] args) {
        // root = [2,null,3,null,4,null,5,null,6]
//        Test.TreeNode root = new Test.TreeNode(2);
//        root.right = new Test.TreeNode(3);
//        root.right.right = new Test.TreeNode(4);
//        root.right.right.right = new Test.TreeNode(5);
//        root.right.right.right.right = new Test.TreeNode(6);
        Test.TreeNode root = new Test.TreeNode(3);
        root.left = new Test.TreeNode(9);
        root.right = new Test.TreeNode(20);
        root.right.left = new Test.TreeNode(15);
        root.right.right = new Test.TreeNode(7);


        Code02_MinDepth test = new Code02_MinDepth();
        System.out.println(test.minDepth1(root));

        System.out.println(test.minDepth2(root));
    }
}

class Info {
    int height;

    Info(int height) {
        this.height = height;
    }
}
