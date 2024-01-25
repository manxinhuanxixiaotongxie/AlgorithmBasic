package systemimprove.code10;

public class Code04_IsFull {
    /**
     * 满二叉树特性：
     * 1.叶子节点都在最后一层
     * 2.非叶子节点都有左右孩子
     * 3.节点数为2^h-1
     * <p>
     * 有两种方法判断一棵树是不是满二叉树
     * 1.高度 节点数
     * 2.左树是满二叉树 右树是满二叉树 左树高度等于右树高度
     */

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
    public class Info1 {
        int height;
        int nodes;

        Info1(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public class Info2 {
        boolean isFull;
        int height;

        Info2(boolean isFull, int height) {
            this.isFull = isFull;
            this.height = height;
        }
    }

    public boolean isFull1(TreeNode root) {
        if (root == null) {
            return true;
        }
        Info1 info = process1(root);
        return info.nodes == (1 << info.height) - 1;

    }

    public Info1 process1(TreeNode node) {
        if (node == null) {
            return new Info1(0, 0);
        }
        Info1 leftInfo = process1(node.left);
        Info1 rightInfo = process1(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new Info1(height, nodes);
    }

    public boolean isFull2(TreeNode root) {
        if (root == null) {
            return true;
        }
        return process2(root).isFull;
    }

    public Info2 process2(TreeNode node) {
        if (node == null) {
            return new Info2(true, 0);
        }
        Info2 leftInfo = process2(node.left);
        Info2 rightInfo = process2(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = false;
        if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height) {
            isFull = true;
        }
        return new Info2(isFull, height);
    }
}
