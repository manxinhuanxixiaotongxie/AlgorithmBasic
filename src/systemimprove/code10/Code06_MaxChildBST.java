package systemimprove.code10;

public class Code06_MaxChildBST {
    /**
     * 返回最大搜索二叉子树的大小
     */

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    class Info {
        int max;
        int min;
        int size;
        boolean isBST;

        Info(int max, int min, int size, boolean isBST) {
            this.max = max;
            this.min = min;
            this.size = size;
            this.isBST = isBST;
        }
    }

    public int getMaxSize(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return process1(root).size;

    }

    public Info process1(TreeNode node) {
        if (node == null) {
            return null;
        }
        Info leftInfo = process1(node.left);
        Info rightInfo = process1(node.right);
        // 几种情况
        // 第一个 与X有关
        // 左树是二叉搜索树右树是二叉搜索树 左树最大值小于X 右树最小值大于X 那么size大小是就是左树的size加上右树的size加上1
        // 左树不是完全二叉树 右树是完全二叉树 最大值大小就是右树的size
        // 左树是完全二叉树 右树不是完全二叉树 最大值大小就是左树的size
        // 左树不是完全二叉树 右树不是完全二叉树 size就是左树的size和右树的size的最大值
        // 第二个 与X无关
        // 左树的size右树的size的最大值
        int max = node.val;
        int min = node.val;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }

        if (leftInfo != null && rightInfo != null) {
            if (leftInfo.isBST && rightInfo.isBST && leftInfo.max < node.val && rightInfo.min > node.val) {
                return new Info(max, min, leftInfo.size + rightInfo.size + 1, true);
            }
            if (leftInfo.isBST && !rightInfo.isBST && leftInfo.max < node.val) {
                return new Info(max, min, leftInfo.size, false);
            }
            if (!leftInfo.isBST && rightInfo.isBST && rightInfo.min > node.val) {
                return new Info(max, min, rightInfo.size, false);
            }
            return new Info(max, min, Math.max(leftInfo.size, rightInfo.size), false);
        }
        if (leftInfo != null) {
            if (leftInfo.isBST && leftInfo.max < node.val) {
                return new Info(max, min, leftInfo.size + 1, true);
            }
            return new Info(max, min, leftInfo.size, false);
        }
        if (rightInfo != null) {
            if (rightInfo.isBST && rightInfo.min > node.val) {
                return new Info(max, min, rightInfo.size + 1, true);
            }
            return new Info(max, min, rightInfo.size, false);
        }
        return new Info(max, min, 1, true);
    }
    /**
     * 返回最大搜索二叉子树的头结点
     */
}
