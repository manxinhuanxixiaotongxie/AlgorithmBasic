package leetcode.classic150;

import java.util.Map;

public class Code129 {
    private int ans = 0;
    public int sumNumbers(TreeNode root) {
        ans = 0;
        process(root, 0);
        return ans;
    }

    public void process(TreeNode root,int sum) {
        if (root == null) {
            return;
        }
        sum = 10 * sum + root.val;
        if (root.left == null && root.right == null) {
            // 结算
            ans += sum;
            return;
        }
        process(root.left, sum);
        process(root.right, sum);
    }
}
