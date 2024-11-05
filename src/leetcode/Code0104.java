package leetcode;

public class Code0104 {

    public int maxDepth1(TreeNode root) {
        return procee(root);
    }

    private int procee(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = procee(root.left);
        int rightHeight = procee(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // 空间复杂度O（1）的方法
    // morris遍历

    /**
     * morris遍历的过程
     * 1。当前节点没有左树 当前节点直接来到右树
     * 2.当前节点有作数的  找到左树的最右侧节点
     * 最右侧节点是空值并且不是当前节点 将最右侧节点的有孩子指向当前节点 当前节点去当前节点左节点
     * 最右侧节点指向自己 那么的最右侧的节点右侧指向空 当前节点来到的右树
     */

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        TreeNode cur = root;
        int ans = 0;
        int curHeight = 0;
        while (cur != null) {
            if (cur.left == null) {
                cur = cur.right;
                curHeight++;
            } else {
                // 左树不为空 找到左树的最右侧节点
                TreeNode left = cur.left;
                // 当前层的高度
                int curLevel = 1;
                while (left.right != null && left.right != cur) {
                    left = left.right;
                    curLevel++;

                }

                // 此时left来到左树的最右侧节点
                if (left.right != null) {
                    left.right = null;
                    cur = cur.right;
                    if (left.left == null) {
                        ans = Math.max(ans, curHeight);
                    }
                    curHeight -= curLevel;
                } else {
                    left.right = cur;
                    cur = cur.left;
                    curHeight++;
                }
            }
        }
        // 单独结算只有右树
        int finalHeight = 0;
        while (root != null) {
            finalHeight++;
            root = root.right;
        }
        ans = Math.max(ans, finalHeight);
        return ans;
    }

    public static void main(String[] args) {
        Code0104 code0104 = new Code0104();
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        TreeNode rightLeft = new TreeNode(15);
        TreeNode rightRight = new TreeNode(7);
        root.left = left;
        root.right = right;
        right.left = rightLeft;
        right.right = rightRight;
        System.out.println(code0104.maxDepth(root));
//        TreeNode root = new TreeNode(1);
//        TreeNode left = new TreeNode(2);
//        root.left = left;
//        System.out.println(code0104.maxDepth(root));
    }
}
