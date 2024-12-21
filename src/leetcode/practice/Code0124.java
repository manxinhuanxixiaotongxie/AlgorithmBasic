package leetcode.practice;

public class Code0124 {
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;

        }
        return process(root).maxSum;
    }

    private TreeInfo process(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeInfo left = process(root.left);
        TreeInfo right = process(root.right);
        // 分情况讨论
        int maxSum = Integer.MIN_VALUE;
        int maxSideSum = Integer.MIN_VALUE;
        // 左树 右树都为空
        if (left == null && right == null) {
            maxSum = root.val;
            maxSideSum = root.val;
        }
        // 左树为空
        if (left == null && right != null) {
            // 左树为空的最大值的答案
            // 此时的maxSum为x的值  x的值加上右树maxSideSum 右树的maxSum的值取最大值
            maxSum = Math.max(root.val, Math.max(root.val + right.maxSideSum, right.maxSum));
            maxSideSum = Math.max(root.val, root.val + right.maxSideSum);

        }
        // 右树为空
        if (right == null && left != null) {
            maxSum = Math.max(root.val, Math.max(root.val + left.maxSideSum, left.maxSum));
            maxSideSum = Math.max(root.val, root.val + left.maxSideSum);
        }

        // 左右树都不为空
        if (left != null && right != null) {
            // 左树的最大值
            int leftMaxSum = left.maxSum;
            // 右树的最大值
            int rightMaxSum = right.maxSum;
            // 左树的单边最大值
            int leftMaxSideSum = left.maxSideSum;
            // 右树的单边最大值
            int rightMaxSideSum = right.maxSideSum;
            // 左树的最大值加上右树的最大值
            maxSum = Math.max(root.val, Math.max(root.val + leftMaxSideSum, Math.max(root.val + rightMaxSideSum, Math.max(leftMaxSum, Math.max(rightMaxSum, root.val + leftMaxSideSum + rightMaxSideSum)))));
            // 左树的最大值加上root的值
            maxSideSum = Math.max(root.val, Math.max(root.val + leftMaxSideSum, root.val + rightMaxSideSum));
        }


        return new TreeInfo(maxSum, maxSideSum);
    }
}

class TreeInfo {
    // 总和
    public int maxSum;
    // 单边最大的和
    public int maxSideSum;

    public TreeInfo(int maxSum, int maxSideSum) {
        this.maxSum = maxSum;
        this.maxSideSum = maxSideSum;
    }
}
