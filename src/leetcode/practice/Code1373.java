package leetcode.practice;

import leetcode.TreeNode;

public class Code1373 {

    public static void main(String[] args) {
        Code1373 code1373 = new Code1373();
//        TreeNode root = new TreeNode(4);
//        TreeNode left = new TreeNode(8);
//        TreeNode leftLeft = new TreeNode(6);
//        TreeNode leftRight = new TreeNode(1);
//        TreeNode leftLeftLeft = new TreeNode(9);
//        TreeNode leftRightLeft = new TreeNode(-5);
//        TreeNode leftRightRight = new TreeNode(4);
//        TreeNode leftRightRightRight = new TreeNode(10);
//        root.left = left;
//        left.left = leftLeft;
//        left.right = leftRight;
//        leftLeft.left = leftLeftLeft;
//        leftRight.left = leftRightLeft;
//        leftRight.right = leftRightRight;
//        leftRightRight.right = leftRightRightRight;
        // [4,8,null,6,1,9,null,-5,4,null,null,null,-3,null,10]
//        TreeNode root = new TreeNode(4);
//        TreeNode left = new TreeNode(8);
//        TreeNode leftLeft = new TreeNode(6);
//        TreeNode leftRight = new TreeNode(1);
//        TreeNode leftLeftLeft = new TreeNode(9);
//        TreeNode leftRightLeft = new TreeNode(-5);
//        TreeNode leftRightRight = new TreeNode(4);
//        TreeNode leftRightRightRight = new TreeNode(10);
//        root.left = left;
//        left.left = leftLeft;
//        left.right = leftRight;
//        leftLeft.left = leftLeftLeft;
//        leftRight.left = leftRightLeft;
//        leftRight.right = leftRightRight;
//        leftRightRight.right = leftRightRightRight;
        // [4,-3,6,-5,-2,5,9]
//        TreeNode root = new TreeNode(4);
//        TreeNode left = new TreeNode(-3);
//        TreeNode right = new TreeNode(6);
//        TreeNode leftLeft = new TreeNode(-5);
//        TreeNode leftRight = new TreeNode(-2);
//        TreeNode rightLeft = new TreeNode(5);
//        TreeNode rightRight = new TreeNode(9);
//        root.left = left;
//        root.right = right;
//        left.left = leftLeft;
//        left.right = leftRight;
//        right.left = rightLeft;
//        right.right = rightRight;
        // [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
//        TreeNode root = new TreeNode(1);
//        TreeNode left = new TreeNode(4);
//        TreeNode right = new TreeNode(3);
//        TreeNode leftLeft = new TreeNode(2);
//        TreeNode leftRight = new TreeNode(4);
//        TreeNode rightLeft = new TreeNode(2);
//        TreeNode rightRight = new TreeNode(5);
//        TreeNode leftLeftLeft = new TreeNode(4);
//        TreeNode leftLeftRight = new TreeNode(6);
//        root.left = left;
//        root.right = right;
//        left.left = leftLeft;
//        left.right = leftRight;
//        right.left = rightLeft;
//        right.right = rightRight;
//        leftLeft.left = leftLeftLeft;
//        leftLeft.right = leftLeftRight;
//        rightRight.left = leftLeftLeft;
//        rightRight.right = leftLeftRight;
        // [-2,8,8,0,null,10,-1,-4,2,null,null,3,null,null,-2,1,5,2,3,null,null,null,null,null,null,null,null,null,4]
//        TreeNode root = new TreeNode(-2);
//        TreeNode left = new TreeNode(8);
//        TreeNode right = new TreeNode(8);
//        TreeNode leftLeft = new TreeNode(0);
//        TreeNode rightLeft = new TreeNode(10);
//        TreeNode rightRight = new TreeNode(-1);
//        TreeNode leftLeftLeft = new TreeNode(-4);
//        TreeNode leftLeftRight = new TreeNode(2);
//        TreeNode rightRightLeft = new TreeNode(3);
//        TreeNode rightRightRight = new TreeNode(-2);
//        TreeNode rightRightRightLeft = new TreeNode(1);
//        TreeNode rightRightRightRight = new TreeNode(5);
//        TreeNode rightRightRightRightLeft = new TreeNode(2);
//        TreeNode rightRightRightRightRight = new TreeNode(3);
//        TreeNode rightRightRightRightRightRight = new TreeNode(4);
//        root.left = left;
//        root.right = right;
//        left.left = leftLeft;
//        right.left = rightLeft;
//        right.right = rightRight;
//        leftLeft.left = leftLeftLeft;
//        leftLeft.right = leftLeftRight;
//        rightRight.left = rightRightLeft;
//        rightRight.right = rightRightRight;
//        rightRightRight.left = rightRightRightLeft;
//        rightRightRight.right = rightRightRightRight;
//        rightRightRightRight.left = rightRightRightRightLeft;
//        rightRightRightRight.right = rightRightRightRightRight;
//        rightRightRightRightRight.right = rightRightRightRightRightRight;
        // [-2,8,8,0,null,10,-1,-4,2,null,null,3,null,null,-2,1,5,2,3,null,null,null,null,null,null,null,null,null,4]
//        TreeNode root = new TreeNode(-2);
//        TreeNode left = new TreeNode(8);
//        TreeNode right = new TreeNode(8);
//        TreeNode leftLeft = new TreeNode(0);
//        TreeNode rightLeft = new TreeNode(10);
//        TreeNode rightRight = new TreeNode(-1);
//        TreeNode leftLeftLeft = new TreeNode(-4);
//        TreeNode leftLeftRight = new TreeNode(2);
//        TreeNode rightRightLeft = new TreeNode(3);
//        TreeNode leftLeftLeftRight = new TreeNode(-2);
//        TreeNode leftLeftRightLeft = new TreeNode(1);
//        TreeNode leftLeftRightRight = new TreeNode(5);
//        TreeNode rightRightRightRightLeft = new TreeNode(2);
//        TreeNode rightRightRightRightRight = new TreeNode(3);
//        TreeNode rightRightRightRightRightRight = new TreeNode(4);
//        root.left = left;
//        root.right = right;
//        left.left = leftLeft;
//        right.left = rightLeft;
//        right.right = rightRight;
//        leftLeft.left = leftLeftLeft;
//        leftLeft.right = leftLeftRight;
//        rightRight.left = rightRightLeft;
//        leftLeftLeft.right = leftLeftLeftRight;
//        leftLeftRight.left = leftLeftRightLeft;
//        leftLeftRight.right = leftLeftRightRight;
//        rightRightLeft.left = rightRightRightRightLeft;
//        rightRightLeft.right = rightRightRightRightRight;
//        rightRightRightRightRight.right = rightRightRightRightRightRight;

        // [0,2,9,null,null,8,1,-1,null,null,4,-5,2,null,1,null,-3,1,3,3,0]
        TreeNode root = new TreeNode(0);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(9);
        TreeNode rightLeft = new TreeNode(8);
        TreeNode rightRight = new TreeNode(1);
        TreeNode rightLeftLeft = new TreeNode(-1);
        TreeNode rightRightRight = new TreeNode(4);
        TreeNode rightLeftleftLeft = new TreeNode(-5);
        TreeNode rightRightRightRight = new TreeNode(2);
        TreeNode rightRightRightRightLeft = new TreeNode(1);
        TreeNode rightLeftLeftLeftRight = new TreeNode(-3);
        TreeNode rightRightRightRightRightLeft = new TreeNode(1);
        TreeNode rightRightRightRightRightRight = new TreeNode(3);
        TreeNode rightRightRightRightRightRightLeft = new TreeNode(3);
        TreeNode rightRightRightRightRightRightRight = new TreeNode(0);
        root.left = left;
        root.right = right;
        right.left = rightLeft;
        right.right = rightRight;

        rightLeft.left = rightLeftLeft;
        rightRight.right = rightRightRight;

        rightLeftLeft.left = rightLeftleftLeft;
        rightLeftLeft.right = rightRightRightRight;

        rightRightRight.right = rightRightRightRightLeft;


        rightLeftleftLeft.right = rightLeftLeftLeftRight;

        rightRightRightRight.left = rightRightRightRightRightLeft;
        rightRightRightRight.right = rightRightRightRightRightRight;

        rightRightRightRightLeft.left = rightRightRightRightRightRightLeft;
        rightRightRightRightLeft.right = rightRightRightRightRightRightRight;


        System.out.println(code1373.maxSumBST(root));

    }

    static int res = 0;

    public int maxSumBST(TreeNode root) {
        if (root == null) {
            return 0;
        }
        res = 0;
        process(root);
        return res;
    }

    private BInfo process(TreeNode node) {
        if (node == null) {
            return new BInfo(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, true);
        }
        BInfo left = process(node.left);
        BInfo right = process(node.right);
        if (left.isBST && right.isBST && left.maxValue < node.val && right.minValue > node.val) {
            res = Math.max(res, left.sumValue + right.sumValue + node.val);
            return new BInfo(Math.max(right.maxValue, node.val), Math.min(left.minValue, node.val), left.sumValue + right.sumValue + node.val, true);
        }
        return new BInfo(0, 0, 0, false);
    }

}

class BInfo {
    // 最大节点
    int maxValue;
    // 最小节点
    int minValue;
    // 累加和
    int sumValue;

    // 是否是二叉搜索树
    boolean isBST;

    BInfo(int maxValue, int minValue, int sumValue, boolean isBST) {
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.sumValue = sumValue;
        this.isBST = isBST;
    }
}
