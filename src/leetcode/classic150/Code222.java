package leetcode.classic150;

import java.util.LinkedList;

public class Code222 {
    /**
     * 有更优解
     *
     * @param root
     * @return
     */
    public int countNodes(TreeNode root) {
        int ans = 0;
        if (root == null) return 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            ans++;
            if (node.left != null) queue.add(node.left);
            if (node.right != null) queue.add(node.right);
        }

        return ans;
    }
}
