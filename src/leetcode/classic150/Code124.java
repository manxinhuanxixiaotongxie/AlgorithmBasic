package leetcode.classic150;

public class Code124 {
    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        Info info = process(root);
        return info.maxSum;
    }

    public Info process(TreeNode root) {
        // 讨论可能性
        // 空节点不太好设置 维持为空
        if (root == null) {
            return null;
        }
        // 讨论可能性
        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);
        int sideMaxSum = root.val;
        // 从当前节点出发 单边最大值
        if (leftInfo != null && rightInfo != null) {
            sideMaxSum = Math.max(sideMaxSum, root.val + Math.max(leftInfo.sideMaxSum, rightInfo.sideMaxSum));
        } else if (rightInfo != null) {
            sideMaxSum = Math.max(sideMaxSum, root.val + rightInfo.sideMaxSum);
        }else if (leftInfo != null) {
            sideMaxSum = Math.max(sideMaxSum, root.val + leftInfo.sideMaxSum);
        }
        int maxSum = root.val;
        if (leftInfo != null && rightInfo != null) {
            // 第一种可能性 因为左树 右树都存在
            maxSum = Math.max(maxSum, Math.max(leftInfo.maxSum, rightInfo.maxSum));
            maxSum = Math.max(maxSum, root.val + leftInfo.sideMaxSum + rightInfo.sideMaxSum);
            maxSum = Math.max(maxSum,Math.max(root.val + leftInfo.sideMaxSum, root.val + rightInfo.sideMaxSum));
        } else if (leftInfo != null) {
            maxSum = Math.max(maxSum, leftInfo.maxSum);
            maxSum = Math.max(maxSum, leftInfo.sideMaxSum + root.val);
        } else if (rightInfo != null) {
            maxSum = Math.max(maxSum, rightInfo.maxSum);
            maxSum = Math.max(maxSum, rightInfo.sideMaxSum + root.val);
        }
        return new Info(sideMaxSum, maxSum);
    }

    class Info {
        // 单边最大值
        int sideMaxSum;
        // 最大路径和
        int maxSum;

        Info(int sideMaxSum, int maxSum) {
            this.sideMaxSum = sideMaxSum;
            this.maxSum = maxSum;
        }
    }
}
